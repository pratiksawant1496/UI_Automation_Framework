package com.automationexercise.utils;

//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//
//import java.io.IOException;
//
//public class DriverManager {
//
//    public static WebDriver setupBrowser() throws IOException {
//        WebDriver driver = null;
//        String name = PropertiesLoader.loadProperty("browser.name");
//        if (name.equalsIgnoreCase("Chrome")) {
//
//            String pathExtension = PropertiesLoader.loadProperty("chrome.extension.adblock.path");
//
//            System.setProperty("webdriver.chrome.silentOutput", "true");
//            ChromeOptions chromeOptions = new ChromeOptions();
//            chromeOptions.addArguments("load-extension=" + pathExtension); //uBlock Origin
//            chromeOptions.addArguments("--incognito");            // open browser in incognito mode
//            chromeOptions.addArguments("--start-maximized");            // open browser in maximized mode
//            chromeOptions.addArguments("--disable-infobars");           // disabling infobars
//            chromeOptions.addArguments("--disable-notifications");         // disabling notifications
////            chromeOptions.addArguments("--disable-extensions");          // disabling extensions
////            chromeOptions.addArguments("--headless");             // headless mode
//            driver = new ChromeDriver(chromeOptions);
//
//        } else if (name.equalsIgnoreCase("Firefox")) {
//            FirefoxOptions firefoxOptions = new FirefoxOptions();
//            firefoxOptions.addArguments("--headless");
//            firefoxOptions.addArguments("--private");
//            driver = new FirefoxDriver(firefoxOptions);
//        }
//        return driver;
//    }
//}


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.URL;

public class DriverManager {
    public static WebDriver setupBrowser() throws IOException {
        WebDriver driver = null;
        String name = PropertiesLoader.loadProperty("browser.name");
        String gridUrl = "http://localhost:4444/wd/hub";

        if (name.equalsIgnoreCase("Chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito", "--start-maximized", "--disable-infobars", "--disable-notifications");
            driver = new RemoteWebDriver(new URL(gridUrl), options);

        } else if (name.equalsIgnoreCase("Firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless", "--private");
            driver = new RemoteWebDriver(new URL(gridUrl), options);
        }
        return driver;
    }
}