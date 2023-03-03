package ru.globalsqa.globalsqa_test.common;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebDriverInstance {

    private static WebDriver driver = null;

    public static WebDriverWait wait;

    private static WebDriverInstance instance;

    private WebDriverInstance() {
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability("browserVersion", "110.0.5481.178");
        capability.setCapability("browserName", "chrome");
        capability.setCapability("remoteReadTimeout", "30000");
        capability.setCapability("timeouts.PageLoad", "30000");
        capability.setCapability("sessionTimeout", "10m");
        capability.setPlatform(Platform.WINDOWS);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--window-size=1920,1080");
        chromeOptions.merge(capability);
        try {
            driver = new RemoteWebDriver(new URL(ConfProperties.getProperty("url.selenium.grid")), chromeOptions);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Ошибка подключения Selenium GRID");
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    public static WebDriver getDriver() {
        if (instance == null) {
            instance = new WebDriverInstance();
        }
        return driver;
    }
}
