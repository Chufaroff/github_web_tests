package web.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import web.config.ConfigProperties;
import web.helpers.Attachments;
import web.pages.MainPage;

import java.util.Map;

public class TestBase {

    protected static final ConfigProperties config =
            ConfigFactory.create(ConfigProperties.class, System.getProperties());

    protected final String username = config.getUsername(),
                           password = config.getPassword(),
                           invalidUsername = config.getInvalidUsername(),
                           invalidPassword = config.getInvalidPassword();

    @BeforeAll
    static void setUp() {

        Configuration.baseUrl = config.getBaseUrl();
        Configuration.browser = config.getBrowser();
        Configuration.browserSize = config.getBrowserSize();
        Configuration.timeout = config.getTimeout();
        Configuration.pageLoadStrategy = config.getPageLoadStrategy();
        Configuration.holdBrowserOpen = config.getHoldBrowserOpen();


        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)      // Делать скриншоты
                .savePageSource(true)   // Сохранять HTML
                .includeSelenideSteps(true) // true - показывать все шаги Selenide
        );

        // Удаленный браузер (если передан в конфиге)
        String remoteUrl = config.getRemoteUrl();
        if (remoteUrl != null && !remoteUrl.isEmpty()) {
            Configuration.remote = remoteUrl;

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true,
                    "enableLog", true,
                    "sessionTimeout", "10m"
            ));

            Configuration.browserCapabilities = capabilities;
        }
    }

    @BeforeEach
    void openMainPage() {
        new MainPage().openPage();
    }

    @AfterEach
    void addAttachments() {
        Attachments.screenshotAs("Last screenshot");
        Attachments.pageSource();
        Attachments.browserConsoleLogs();
        Attachments.addVideo();
        Selenide.closeWebDriver();
    }
}





