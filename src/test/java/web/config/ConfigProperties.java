package web.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/app.properties"
})
public interface ConfigProperties extends Config {

    @Key("username")
    String getUsername();

    @Key("password")
    String getPassword();

    @Key("invalidUsername")
    String getInvalidUsername();

    @Key("invalidPassword")
    String getInvalidPassword();

    @Key("baseUrl")
    String getBaseUrl();

    @Key("remoteUrl")
    String getRemoteUrl();

    @Key("browser")
    @DefaultValue("chrome")
    String getBrowser();

    @Key("browser.size")
    @DefaultValue("1920x1080")
    String getBrowserSize();

    @Key("timeout")
    @DefaultValue("10000")
    Long getTimeout();

    @Key("pageLoadStrategy")
    @DefaultValue("eager")
    String getPageLoadStrategy();

    @Key("holdBrowserOpen")
    @DefaultValue("false")
    Boolean getHoldBrowserOpen();
}
