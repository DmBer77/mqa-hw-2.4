package ru.netology.qa;

import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.netology.qa.screens.MainScreen;

import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_PACKAGE;
import static io.appium.java_client.remote.MobileCapabilityType.*;
import static org.junit.jupiter.api.TestInstance.Lifecycle;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

@TestInstance(Lifecycle.PER_CLASS)
class UIAutomatorTest {

    private AppiumDriver driver;
    public String inputText = "hello";


    @BeforeAll
    public void createDriver() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(PLATFORM_NAME, "android");
        desiredCapabilities.setCapability(DEVICE_NAME, "any name");
        desiredCapabilities.setCapability(APP_PACKAGE, "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability(APP_ACTIVITY, "ru.netology.testing.uiautomator.MainActivity");

        driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
    }

    @Test
    public void changeTextInTextField() {
        MainScreen mainScreen = new MainScreen(driver);
        String expected = mainScreen.textField.getText();
        mainScreen.textTest.setValue(" ");
        mainScreen.buttonChange.click();
        Assertions.assertEquals(expected, mainScreen.textField.getText());
    }

    @Test
    public void showTextOnNewActivity() {
        MainScreen mainScreen = new MainScreen(driver);
        mainScreen.textTest.setValue(inputText);
        mainScreen.newActivityButton.click();
        Assertions.assertEquals(inputText, mainScreen.textOnNewActivity.getText());
    }

    @AfterAll
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
