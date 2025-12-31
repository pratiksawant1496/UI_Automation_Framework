package com.automationexercise.listeners;

import com.automationexercise.tests.TestBasic;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements IInvokedMethodListener {

    private final Path screenshotsDir = Paths.get("target", "screenshots");

    private void ensureDir() throws IOException {
        if (!Files.exists(screenshotsDir)) {
            Files.createDirectories(screenshotsDir);
        }
    }

    /** Capture WebDriver screenshot, attach to Allure, and save to disk */
    private void captureAndAttachScreenshot(ITestResult result) {
        WebDriver driver = TestBasic.getDriver();
        if (driver == null || !(driver instanceof TakesScreenshot)) return;

        try {
            byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            // Attach to Allure
            Allure.addAttachment("Listeners Screenshot - " + result.getName(),
                    new ByteArrayInputStream(bytes));

            // Save to disk
            ensureDir();
            String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));
            Path file = screenshotsDir.resolve(result.getName() + "_" + ts + ".png");
            Files.write(file, bytes);
        } catch (IOException ignored) {}
    }

    /** Attach any screenshots already saved by utility classes into target/screenshots */
    private void attachUtilityScreenshots() {
        if (!Files.exists(screenshotsDir)) return;

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(screenshotsDir, "*.png")) {
            for (Path file : stream) {
                try (InputStream is = Files.newInputStream(file)) {
                    Allure.addAttachment("Utility Screenshot - " + file.getFileName(), is);
                }
            }
        } catch (IOException ignored) {}
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
        if (!method.isTestMethod()) return;

        if (result.getStatus() == ITestResult.FAILURE) {
            // Capture WebDriver screenshot
            captureAndAttachScreenshot(result);

            // Attach any other screenshots saved by ScreenshotUtils
            attachUtilityScreenshots();
        }
    }
}
