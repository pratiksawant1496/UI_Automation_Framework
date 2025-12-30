package com.automationexercise.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;

public class DriverManager {
    public static WebDriver setupBrowser() throws IOException {
        WebDriver driver = null;
        String name = PropertiesLoader.loadProperty("browser.name");
        String gridUrl = PropertiesLoader.loadProperty("grid.url"); // configurable

        try {
            if (name.equalsIgnoreCase("Chrome")) {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito", "--start-maximized", "--disable-infobars", "--disable-notifications");
                driver = new RemoteWebDriver(new URL(gridUrl), options);

            } else if (name.equalsIgnoreCase("Firefox")) {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--headless", "--private");
                driver = new RemoteWebDriver(new URL(gridUrl), options);
            } else {
                throw new IllegalArgumentException("Unsupported browser: " + name);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to Selenium Grid at " + gridUrl, e);
        }

        return driver;
    }
}
