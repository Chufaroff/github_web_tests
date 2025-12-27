package web.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SettingsPage {

    private final SelenideElement avatarImage = $("img.avatar[src*='avatars.githubusercontent.com']"),
                                  fileUploadInput = $("input[type='file']"),
                                  successMessage = $("#js-flash-container");

    @Step("Кликнуть на аватар")
    public SettingsPage clickAvatar() {
        avatarImage.click();
        return this;
    }

    @Step("Загрузить фото профиля: {filePath}")
    public SettingsPage uploadProfilePhoto(String filePath) {
        File photo = new File(filePath);
        fileUploadInput.uploadFile(photo);
        return this;
    }

    @Step("Нажать 'Set new profile picture'")
    public SettingsPage clickSetProfilePicture() {
        $$("div.Box-footer button").findBy(text("Set new profile picture")).click();
        return this;
    }

    @Step("Проверить сообщение об успешной загрузке: {expectedMessage}")
    public SettingsPage checkSuccessMessage(String expectedMessage) {
        successMessage.shouldHave(text(expectedMessage));
        return this;
    }
}
