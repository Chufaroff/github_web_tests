package web.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import web.pages.DashboardPage;
import web.pages.LoginPage;
import web.pages.MainPage;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Owner("Chufarov Bogdan")
@Feature("GitHub")
@Story("Авторизация")
@Tags({@Tag("Auth"), @Tag("WEB"), @Tag("remote")})
@DisplayName("Тестирование авторизации в личном кабинете")
public class LoginTests extends TestBase {


    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();
    DashboardPage dashboardPage = new DashboardPage();

    private final String WELCOME_MESSAGE = "The future of building happens together";
    private final String ERROR_MESSAGE = "Incorrect username or password.";

    @Test
    @DisplayName("Проверка окна привествия на главной странице")
    @Severity(SeverityLevel.NORMAL)
    void checkingTheWelcomeWindowTest() {

        step("Проверить текст приветствия", () ->
                mainPage.checkWelcomeText(WELCOME_MESSAGE)
        );
    }

    @Test
    @DisplayName("Позитивный сценарий авторизации на сайте")
    @Severity(SeverityLevel.NORMAL)
    void successfulLoginTest() {

        step("Нажать кнопку 'Sign in' через JavaScript", () ->
                mainPage.clickSignInWithJS()
        );
        step("Заполнить форму авторизации", () ->
                loginPage.setLogin(username)
                        .setPasswordAndPressEnter(password)
        );
        step("Проверить успешный вход", () ->
                dashboardPage.shouldBeLoaded()
        );
    }

    @Test
    @DisplayName("Негативный сценарий авторизации с невалидным значением полей")
    @Severity(SeverityLevel.NORMAL)
    void negativeLoginTest() {

        step("Нажать кнопку 'Sign in' через JavaScript", () ->
                mainPage.clickSignInWithJS()
        );
        step("Заполнить форму невалидными данными", () ->
                loginPage.setLogin(invalidUsername)
                        .setPasswordAndPressEnter(invalidPassword)
        );
        step("Проверить сообщение об ошибке некорректного входа", () ->
                loginPage.checkErrorMessage(ERROR_MESSAGE)
        );
    }
}
