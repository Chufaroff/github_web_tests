package web.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private final SelenideElement loginField = $("#login_field"),
                                  passwordField = $("#password"),
                                  errorMessage = $("#js-flash-container");

    @Step("Заполнить поле логина: {login}")
    public LoginPage setLogin(String login) {
        loginField.setValue(login);
        return this;
    }

    @Step("Заполнить поле пароля и нажать Enter: {password}")
    public LoginPage setPasswordAndPressEnter(String password) {
        passwordField.setValue(password).pressEnter();
        return this;
    }

    @Step("Проверить сообщение об ошибке: {expectedMessage}")
    public LoginPage checkErrorMessage(String expectedMessage) {
        errorMessage.shouldHave(text(expectedMessage));
        return this;
    }
}
