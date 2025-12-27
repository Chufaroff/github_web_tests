package web.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import web.pages.*;

import static io.qameta.allure.Allure.step;

@Owner("Chufarov Bogdan")
@Feature("GitHub")
@Story("Навигация")
@Tags({@Tag("Auth"), @Tag("WEB"), @Tag("remote")})
@DisplayName("Тестирование навигации авторизированного пользователя")
public class AuthorizedUserNavigationTests extends TestBase {


    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();
    ProfilePage profilePage = new ProfilePage();
    RepositoriesPage repositoriesPage = new RepositoriesPage();
    RepositoryPage repositoryPage = new RepositoryPage();
    SettingsPage settingsPage = new SettingsPage();

    private final String PROFILE_NAME = "Bogdan Chufarov";
    private final String BIO_TEXT = "QA Automation Engineer";
    private final String PHOTO_SUCCESS_MESSAGE = "Your profile picture has been updated.";
    private final String REPO_NAME = "rest_api_example_tests";
    private final String PHOTO_PATH = "src/test/resources/photoprofile.jfif";

    @Test
    @DisplayName("Тестирование вкладки 'Profile'")
    @Severity(SeverityLevel.NORMAL)
    void clickOnTheProfileTabTest() {

        step("Авторизоваться", () -> {
           mainPage.clickSignInWithJS();
           loginPage.setLogin(username)
                   .setPasswordAndPressEnter(password);
           dashboardPage.shouldBeLoaded();
        });
        step("Открыть меню пользователя и перейти в профиль", () ->
           dashboardPage.clickUserMenu()
                   .goToProfile()
        );
        step("Проверить возможность загрузки аватара", () ->
           profilePage.checkUploadAvatarVisible()
        );
    }

    @Test
    @DisplayName("Тестирование вкладки 'Repositories'")
    @Severity(SeverityLevel.NORMAL)
    void clickOnTheRepositoriesTabTest() {

        step("Авторизоваться", () -> {
            mainPage.clickSignInWithJS();
            loginPage.setLogin(username)
                    .setPasswordAndPressEnter(password);
            dashboardPage.shouldBeLoaded();
        });
        step("Открыть меню пользователя и перейти в репозитории", () ->
            dashboardPage.clickUserMenu()
                        .goToRepositories()
        );
        step("Проверить наличие репозитория в появившемся списке", () ->
            repositoriesPage.shouldBeLoaded()
                    .checkRepositoryExists(REPO_NAME)
        );
    }

    @Test
    @DisplayName("Создание нового репозитория")
    @Severity(SeverityLevel.NORMAL)
    void creatingNewRepositoryTest() {

        step("Авторизоваться", () -> {
            mainPage.clickSignInWithJS();
            loginPage.setLogin(username)
                    .setPasswordAndPressEnter(password);
            dashboardPage.shouldBeLoaded();
        });
        step("Перейти в репозитории", () -> {
            dashboardPage.clickUserMenu()
                    .goToRepositories();
            repositoriesPage.shouldBeLoaded();
        });
        step("Начать создание нового репозитория", () ->
            repositoriesPage.clickCreateNewRepositoryWithJS()
        );
    }

    @Test
    @DisplayName("Переход в конкретный репозиторий")
    @Severity(SeverityLevel.NORMAL)
    void clickOnTheRepositoriesTabAndGoingToSpecificRepositoryTest() {

        step("Авторизоваться", () -> {
            mainPage.clickSignInWithJS();
            loginPage.setLogin(username)
                    .setPasswordAndPressEnter(password);
            dashboardPage.shouldBeLoaded();
        });
        step("Перейти в репозитории", () -> {
            dashboardPage.clickUserMenu()
                    .goToRepositories();
            repositoriesPage.shouldBeLoaded();
        });
        step("Перейти в конкретный репозиторий", () ->
            repositoriesPage.goToSpecificRepository()
        );
        step("Проверить заголовок репозитория", () ->
            repositoryPage.checkRepositoryName(REPO_NAME)
        );
    }

    @Test
    @DisplayName("Попытка изменить профиль с помощью нажатия кнопки 'Edit profile'")
    @Severity(SeverityLevel.NORMAL)
    void clickOnTheButtonEditProfileTest() {

        step("Авторизоваться", () -> {
            mainPage.clickSignInWithJS();
            loginPage.setLogin(username)
                    .setPasswordAndPressEnter(password);
            dashboardPage.shouldBeLoaded();
        });
        step("Перейти в профиль", () ->
            dashboardPage.clickUserMenu()
                        .goToProfile()
        );
        step("Нажать 'Edit Profile'", () ->
            profilePage.clickEditProfile()
        );
        step("Проверить имя профиля", () ->
            profilePage.checkProfileName(PROFILE_NAME)
        );
    }

    @Test
    @DisplayName("Добавление информации в 'Bio'")
    @Severity(SeverityLevel.NORMAL)
    void addingInformationToBioTest() {

        step("Авторизоваться", () -> {
            mainPage.clickSignInWithJS();
            loginPage.setLogin(username)
                    .setPasswordAndPressEnter(password);
            dashboardPage.shouldBeLoaded();
        });
        step("Перейти в профиль и редактировать", () -> {
            dashboardPage.clickUserMenu()
                    .goToProfile();
            profilePage.clickEditProfile();
        });
        step("Добавить информацию в 'Bio'", () ->
            profilePage.setBio(BIO_TEXT)
        );
        step("Сохранить изменения и проверить обновления", () ->
            profilePage.saveChanges()
                    .checkBioText(BIO_TEXT)
        );
    }

    @Test
    @DisplayName("Загрузка фотографии профиля через настройки аккаунта")
    @Severity(SeverityLevel.NORMAL)
    void uploadingProfilePhotoTest() {

        step("Авторизоваться", () -> {
            mainPage.clickSignInWithJS();
            loginPage.setLogin(username)
                    .setPasswordAndPressEnter(password);
            dashboardPage.shouldBeLoaded();
        });
        step("Перейти в настройки", () ->
            dashboardPage.clickUserMenu()
                    .goToSettings()
        );
        step("Изменить фото профиля", () ->
            settingsPage.clickAvatar()
                    .uploadProfilePhoto(PHOTO_PATH)
                    .clickSetProfilePicture()
        );
        step("Проверить сообщение, что новое фото загрузилось", () ->
            settingsPage.checkSuccessMessage(PHOTO_SUCCESS_MESSAGE)
        );
    }
}

