package web.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class EnterprisePage {

    private final SelenideElement enterpriseHeading = $("#hero-section-brand-heading");

    @Step("Проверить заголовок страницы Enterprise: {expectedText}")
    public EnterprisePage checkHeading(String expectedText) {
        enterpriseHeading.shouldHave(text(expectedText));
        return this;
    }
}
