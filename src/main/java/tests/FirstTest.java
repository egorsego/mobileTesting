package tests;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class FirstTest extends BaseTest {

    private AndroidDriver<AndroidElement> driver;
    private TouchAction touch;

    @BeforeClass
    public void setUp() throws Exception {
        File app = new File("./src/main/resources/testApp.apk");

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("app", app.getAbsolutePath());
        //caps.setCapability(MobileCapabilityType.UDID, "9c94aabb");
        caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

        driver = new AndroidDriver<>(getServiceUrl(), caps);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void returnToInitialState() throws InterruptedException {
        tapOnElement(driver.findElementByXPath("//*[@content-desc='Commands']"), 1);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test()
    public void switchToConnectionScreen() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        AndroidElement connectionTab = driver.findElementByXPath("//*[@content-desc='Connection']");

        tapOnElement(connectionTab, 1);

        softAssert.assertTrue(driver.findElementsByXPath("//*[@content-desc='Connect!']").size() == 1, "Connect! button not found");
        softAssert.assertTrue(driver.findElementsByXPath("//*[@content-desc='Pirit IP adress:']").size() == 1, "IP Address tag not found");
        softAssert.assertTrue(driver.findElementsByXPath("//*[@content-desc='Pirit Port:']").size() == 1, "Port tag not found");
        softAssert.assertAll();
    }

    @Test()
    public void switchToCommandsScreen() throws InterruptedException {
        SoftAssert softAssert = new SoftAssert();

        AndroidElement connectionTab = driver.findElementByXPath("//*[@content-desc='Connection']");
        AndroidElement commandsTab = driver.findElementByXPath("//*[@content-desc='Commands']");

        tapOnElement(connectionTab, 1);
        assertTrue(driver.findElementsByXPath("//*[@content-desc='Connect!']").size() == 1, "Connect! button not found");

        tapOnElement(commandsTab, 1);
        softAssert.assertTrue(driver.findElementsByXPath("//*[@content-desc='Scroll Paper']").size() == 1, "Scroll Paper button not found");
        softAssert.assertTrue(driver.findElementsByXPath("//*[@content-desc='Print Service Doc']").size() == 1, "Print Service Doc button not found");
        softAssert.assertTrue(driver.findElementsByXPath("//*[@content-desc='Print X Report']").size() == 1, "Print X Report button not found");
        softAssert.assertTrue(driver.findElementsByXPath("//*[@content-desc='Open Shift']").size() == 1, "Open Shift button not found");
        softAssert.assertTrue(driver.findElementsByXPath("//*[@content-desc='print Reciept']").size() == 1, "print Reciept button not found");
        softAssert.assertTrue(driver.findElementsByXPath("//*[@content-desc='Close Shift']").size() == 1, "Close Shift button not found");
        softAssert.assertAll();
    }

    @Test()
    public void changePortValue() throws InterruptedException {
        AndroidElement connectionTab = driver.findElementByXPath("//*[@content-desc='Connection']");

        tapOnElement(connectionTab, 1);

        assertTrue(driver.findElementsByXPath("//*[@content-desc='50003']").size() == 1, "Port input field not found");

        AndroidElement portInputField = driver.findElementByXPath("//*[@content-desc='50003']");

        String newPortValue = "8088";

        cleatText(portInputField);
        setText(portInputField, newPortValue);

        assertEquals(portInputField.getAttribute("content-desc"), newPortValue);
    }

    @Test()
    public void changeIpAddressValue() throws InterruptedException {
        AndroidElement connectionTab = driver.findElementByXPath("//*[@content-desc='Connection']");

        tapOnElement(connectionTab, 1);

        assertTrue(driver.findElementsByXPath("//*[@content-desc='192.168.0.53']").size() == 1, "IP Address input field not found");

        AndroidElement ipInputField = driver.findElementByXPath("//*[@content-desc='192.168.0.53']");

        String newIpAddressValue = "192.168.243.3";

        cleatText(ipInputField);
        setText(ipInputField, newIpAddressValue);

        assertEquals(ipInputField.getAttribute("content-desc"), newIpAddressValue);
    }

    private void cleatText(WebElement element) throws InterruptedException {
        int stringLength = element.getAttribute("content-desc").length();

        tapOnElement(element, 1);

        for (int i = 0; i <= stringLength; i++) {
            driver.pressKey(new KeyEvent(AndroidKey.DPAD_RIGHT));
            driver.pressKey(new KeyEvent(AndroidKey.DEL));
        }

        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    private void setText(WebElement element, String text) throws InterruptedException {
        tapOnElement(element, 1);

        for (char c: text.toCharArray()) {
            if (c == '.') {
                driver.pressKey(new KeyEvent(AndroidKey.PERIOD));
            } else {
                driver.pressKey(new KeyEvent(AndroidKey.valueOf("DIGIT_" + c)));
            }
        }
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    private void tapOnElement(WebElement element, int numberOfTimes) throws InterruptedException {
        Thread.sleep(1_000);

        touch = new TouchAction(driver);
        touch.tap(TapOptions.tapOptions().withElement(ElementOption.element(element)).withTapsCount(numberOfTimes));
        touch.perform();
    }

    private void longPress(WebElement element) throws InterruptedException {
        Thread.sleep(1_000);

        touch = new TouchAction(driver);
        touch.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(element)));
        touch.perform();
    }

}