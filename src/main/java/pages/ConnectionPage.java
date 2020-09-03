package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ConnectionPage extends BasePage {

    public ConnectionPage(AndroidDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//*[@content-desc='Connect!']")
    private AndroidElement connectBtn;

    @AndroidFindBy(xpath = "//*[@index='3']")
    private AndroidElement ipAddressInputField;

    @AndroidFindBy(xpath = "//*[@index='1']")
    private AndroidElement portInputField;

    public CommandsPage openCommandsPage() throws InterruptedException {
        this.tapOnElement(commandsTab, 1);
        return new CommandsPage(driver);
    }

    public ConnectionPage reOpen() throws InterruptedException {
        this.tapOnElement(connectionTab, 1);
        return this;
    }

    public void clearIpAddressField() throws InterruptedException {
        clearText(ipAddressInputField);
    }

    public void clearPortField() throws InterruptedException {
        clearText(portInputField);
    }

    public void setIpAddressValue(String value) throws InterruptedException {
        clearIpAddressField();
        setText(ipAddressInputField, value);
    }

    public void setPortValue(String value) throws InterruptedException {
        clearPortField();
        setText(portInputField, value);
    }

    public String getIpAddressFieldContent() {
        return getContentValue(ipAddressInputField);
    }

    public String getPortFieldContent() {
        return getContentValue(portInputField);
    }

    private void clearText(AndroidElement element) throws InterruptedException {
        int stringLength = element.getAttribute("content-desc").length();

        tapOnElement(element, 1);

        for (int i = 0; i <= stringLength; i++) {
            driver.pressKey(new KeyEvent(AndroidKey.DPAD_RIGHT));
            driver.pressKey(new KeyEvent(AndroidKey.DEL));
        }

        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    private void setText(AndroidElement element, String text) throws InterruptedException {
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

    private String getContentValue(AndroidElement element) {
        return element.getAttribute("content-desc");
    }

}
