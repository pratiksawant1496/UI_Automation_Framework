package com.automationexercise.listeners;

import com.automationexercise.tests.TestBasic;
import io.qameta.allure.Allure;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class TestListener implements ITestListener {

    private final Path screenshotsDir = Paths.get("target", "screenshots");  // directory to save screenshots.

    private void ensureDir() throws IOException {
        if (!Files.exists(screenshotsDir)) {
            Files.createDirectories(screenshotsDir);
        }
    }

    private void captureAndAttachScreenshot(ITestResult result) {
        try {
            WebDriver driver = TestBasic.getDriver();
            if (driver == null) return;
            if (!(driver instanceof TakesScreenshot)) return;

            byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            // attach to Allure
            Allure.addAttachment("Screenshot - " + result.getName(),
                    new ByteArrayInputStream(bytes));

            // save to disk
            ensureDir();
            String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));
            Path file = screenshotsDir.resolve(result.getName() + "_" + ts + ".png");
            Files.write(file, bytes);
        } catch (IOException ignored) {
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        captureAndAttachScreenshot(result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // optional: capture screenshot for skipped if desired
    }

    @Override public void onTestStart(ITestResult result) {}
    @Override public void onTestSuccess(ITestResult result) {}
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}
}