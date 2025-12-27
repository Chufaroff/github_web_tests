package web.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {

    private final SelenideElement userMenuButton = $("[data-login='Chufaroff']"),
                                  dashboardHeader = $("#dashboard"),
                                  profileLink = $("a[href='/Chufaroff']"),
                                  repositoriesLink = $("a[href='/Chufaroff?tab=repositories']"),
                                  settingsLink = $("a[href='/settings/profile']");

    @Step("Проверить, что Dashboard загружен")
    public DashboardPage shouldBeLoaded() {
        dashboardHeader.shouldBe(visible);
        return this;
    }

    @Step("Кликнуть по меню пользователя")
    public DashboardPage clickUserMenu() {
        userMenuButton.click();
        return this;
    }

    @Step("Перейти в профиль")
    public DashboardPage goToProfile() {
        profileLink.click();
        return this;
    }

    @Step("Перейти в репозитории")
    public DashboardPage goToRepositories() {
        repositoriesLink.click();
        return this;
    }

    @Step("Перейти в настройки")
    public DashboardPage goToSettings() {
        settingsLink.click();
        return this;
    }
}
