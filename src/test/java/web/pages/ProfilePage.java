package web.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ProfilePage {

    private final SelenideElement editProfileButton = $(byText("Edit profile")),
                                  uploadAvatarLink = $("#upload-avatar-link"),
                                  bioField = $("#user_profile_bio"),
                                  saveButton = $("button[data-target='waiting-form.submit']"),
                                  userBio = $("div.user-profile-bio"),
                                  profileNameField = $("input[name='user[profile_name]']");

    @Step("Проверить наличие ссылки загрузки аватара")
    public ProfilePage checkUploadAvatarVisible() {
        uploadAvatarLink.shouldBe(visible);
        return this;
    }

    @Step("Нажать 'Edit profile'")
    public ProfilePage clickEditProfile() {
        editProfileButton.click();
        return this;
    }

    @Step("Проверить имя профиля: {expectedName}")
    public ProfilePage checkProfileName(String expectedName) {
        profileNameField.shouldHave(value(expectedName));
        return this;
    }

    @Step("Заполнить поле Bio: {text}")
    public ProfilePage setBio(String text) {
        bioField.setValue(text);
        return this;
    }

    @Step("Сохранить изменения")
    public ProfilePage saveChanges() {
        saveButton.click();
        return this;
    }

    @Step("Проверить текст Bio: {expectedText}")
    public ProfilePage checkBioText(String expectedText) {
        userBio.shouldHave(text(expectedText));
        return this;
    }
}
