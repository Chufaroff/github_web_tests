package web.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class RepositoryPage {

    private final SelenideElement repositoryHeader = $("#repo-title-component"),
                                  repoContainerHeader = $("#repository-container-header");

    @Step("Проверить заголовок репозитория: {repoName}")
    public RepositoryPage checkRepositoryName(String repoName) {
        repositoryHeader.shouldHave(text(repoName));
        return this;
    }

    @Step("Проверить заголовок контейнера репозитория: {repoName}")
    public RepositoryPage checkRepoContainerHeader(String repoName) {
        repoContainerHeader.shouldHave(text(repoName));
        return this;
    }
}
