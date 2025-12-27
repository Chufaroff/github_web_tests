package web.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final SelenideElement 
            welcomeHeading = $("#hero-section-brand-heading"),
            solutionsTab = $(byText("Solutions")),
            resourcesTab = $(byText("Resources")),
            searchInput = $(".search-input"),
            enterpriseLink = $("a[href='https://github.com/enterprise']"),
            aiArticlesLink = $("a[href='https://github.com/resources/articles?topic=ai']");

    @Step("Открыть главную страницу")
    public MainPage openPage() {
        open("");
        return this;
    }

    @Step("Нажать кнопку 'Sign in' через JavaScript")
    public MainPage clickSignInWithJS() {
        executeJavaScript("document.querySelector('a[href=\"/login\"]').click();");
        return this;
    }

    @Step("Проверить текст приветствия: {expectedText}")
    public MainPage checkWelcomeText(String expectedText) {
        welcomeHeading.shouldHave(text(expectedText));
        return this;
    }

    @Step("Навести курсор на 'Solutions' и перейти в Enterprise")
    public MainPage goToEnterprise() {
        solutionsTab.hover();
        enterpriseLink.click();
        return this;
    }

    @Step("Навести курсор на 'Resources' и перейти в статьи AI")
    public MainPage goToAIArticles() {
        resourcesTab.hover();
        aiArticlesLink.click();
        return this;
    }

    @Step("Кликнуть на поиск")
    public MainPage clickSearch() {
        searchInput.click();
        return this;
    }
}

