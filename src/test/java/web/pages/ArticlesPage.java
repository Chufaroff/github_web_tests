package web.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ArticlesPage {

    private final SelenideElement articlesHeading = $("#hero-section-brand-heading"),
                                  cookieBanner = $("#wcpConsentBannerCtrl");

    @Step("Проверить заголовок страницы статей: {expectedText}")
    public ArticlesPage checkHeading(String expectedText) {
        articlesHeading.shouldHave(text(expectedText));
        return this;
    }

    @Step("Принять куки если есть баннер")
    public ArticlesPage acceptCookies() {
        if (cookieBanner.exists()) {
            cookieBanner.$(byText("Accept")).click();
        }
        return this;
    }

    @Step("Выбрать чекбокс: {checkboxText}")
    public ArticlesPage selectCheckbox(String checkboxText) {
        $$("label").filterBy(visible).findBy(text(checkboxText)).click();
        return this;
    }

    @Step("Нажать кнопку: {buttonText}")
    public ArticlesPage clickButton(String buttonText) {
        $$("button[type='submit']").findBy(text(buttonText)).click();
        return this;
    }
}
