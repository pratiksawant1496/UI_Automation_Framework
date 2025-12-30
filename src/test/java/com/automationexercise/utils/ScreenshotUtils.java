package com.automationexercise.utils;

import com.automationexercise.tests.TestBasic;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenshotUtils extends TestBasic {
    private static final Logger logger = LogManager.getLogger(ScreenshotUtils.class);

    public static String captureScreenshot(String status) {
        try {
            TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
            File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

            String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String screenshotName = "Screenshot_" + status + "_" + timeStamp + ".png";
            String destinationPath = "target/screenshots/" + screenshotName;

            new File("target/screenshots").mkdirs();
            FileUtils.copyFile(sourceFile, new File(destinationPath));

            logger.info("Screenshot captured: {}", destinationPath);
            return destinationPath;

        } catch (IOException e) {
            logger.error("Failed to capture screenshot", e);
            return null;
        }
    }
}
