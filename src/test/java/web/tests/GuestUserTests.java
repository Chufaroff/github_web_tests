package web.tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import web.pages.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Owner("Chufarov Bogdan")
@Feature("GitHub")
@Story("Навигация")
@Tags({@Tag("Smoke"), @Tag("WEB"), @Tag("remote")})
@DisplayName("Тестирование навигации гостевого пользователя")
public class GuestUserTests extends TestBase {

    MainPage mainPage = new MainPage();
    SearchPage searchPage = new SearchPage();
    ArticlesPage articlesPage = new ArticlesPage();
    EnterprisePage enterprisePage = new EnterprisePage();
    RepositoryPage repositoryPage = new RepositoryPage();

    private final String WELCOME_MESSAGE = "The future of building happens together";
    private final String ENTERPRISE_MESSAGE = "The AI-powered developer platform for the agent-ready enterprise";
    private final String ARTICLES_HEADLINE = "GitHub Articles";
    private final String CHECKBOX_TEXT = "Software Development";
    private final String BUTTON_TEXT = "Apply";
    private final String REPO_NAME = "selenide";

    @Test
    @DisplayName("Проверка окна привествия на главной странице")
    @Severity(SeverityLevel.MINOR)
    void checkingTheWelcomeWindowTest() {

        step("Проверить текст приветствия", () ->
                mainPage.checkWelcomeText(WELCOME_MESSAGE)
        );
    }

    @Test
    @DisplayName("Проверка навигации по вкладкам 'Solutions' и 'Enterprises'")
    @Severity(SeverityLevel.NORMAL)
    void checkingTabsSolutionsAndEnterprises() {

        step("Навести курсор на 'Solutions' и перейти в 'Enterprise'", () ->
                mainPage.goToEnterprise()
        );
        step("Проверить заголовок открывшейся страницы", () ->
                enterprisePage.checkHeading(ENTERPRISE_MESSAGE)
        );
    }

    @Test
    @DisplayName("Проверка навигации по вкладкам 'Resources' и 'AI'")
    @Severity(SeverityLevel.NORMAL)
    void checkingTabsResourcesAndAI() {

        step("Навести курсор на 'Resources' и перейти в статьи AI", () ->
                mainPage.goToAIArticles()
        );
        step("Принять куки если есть баннер", () ->
                articlesPage.acceptCookies()
        );
        step("Проверить заголовок страницы статей", () ->
                articlesPage.checkHeading(ARTICLES_HEADLINE)
        );
    }

    @Test
    @DisplayName("Проверка клика выбранных чек-боксов")
    @Severity(SeverityLevel.NORMAL)
    void checkingCheckboxInTheTabAI() {

        step("Открыть страницу статей AI", () ->
                mainPage.goToAIArticles()
        );
        step("Принять куки", () ->
                articlesPage.acceptCookies()
        );
        step("Выбрать фильтр", () ->
                articlesPage.selectCheckbox(CHECKBOX_TEXT)
        );
        step("Применить фильтр", () ->
                articlesPage.clickButton(BUTTON_TEXT)
        );
    }

    @Test
    @DisplayName("Тестирование поиска конкретного репозитория")
    @Severity(SeverityLevel.CRITICAL)
    void selenideRepositorySearchTest() {

        step("Кликнуть на поиск", () ->
                mainPage.clickSearch()
        );
        step("Ввести текст для поиска", () ->
                searchPage.searchFor(REPO_NAME)
        );
        step("Перейти в репозиторий Selenide", () ->
                searchPage.clickSelenideSearchResult()
        );
        step("Проверить название репозитория", () ->
                repositoryPage.checkRepoContainerHeader(REPO_NAME)
        );
    }
}
