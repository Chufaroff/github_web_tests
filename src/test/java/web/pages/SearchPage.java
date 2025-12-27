package web.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class SearchPage {

    private final SelenideElement searchQueryField = $("#query-builder-test");

    @Step("Ввести текст для поиска: {query}")
    public SearchPage searchFor(String query) {
        searchQueryField.setValue(query).pressEnter();
        return this;
    }

    @Step("Кликнуть на результат поиска Selenide")
    public SearchPage clickSelenideSearchResult() {
        $("a[href='/selenide/selenide']").hover().click();
        return this;
    }
}
