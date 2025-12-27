package web.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class RepositoriesPage {

    private final SelenideElement repositoriesList = $("#user-repositories-list"),
                                  specificRepoLink = $("a[href='/Chufaroff/rest_api_example_tests']");

    @Step("Проверить, что список репозиториев виден")
    public RepositoriesPage shouldBeLoaded() {
        repositoriesList.shouldBe(visible);
        return this;
    }

    @Step("Проверить наличие репозитория: {repoName}")
    public RepositoriesPage checkRepositoryExists(String repoName) {
        repositoriesList.shouldHave(text(repoName));
        return this;
    }

    @Step("Нажать 'Create repository' через JavaScript")
    public void clickCreateNewRepositoryWithJS() {
        executeJavaScript("document.querySelector('a[href=\"/new\"]').click();");
    }

    @Step("Перейти в конкретный репозиторий")
    public RepositoriesPage goToSpecificRepository() {
        specificRepoLink.click();
        return this;
    }
}
