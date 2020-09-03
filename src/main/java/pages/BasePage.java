package pages;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    public AndroidDriver driver;
    public TouchAction touch;

    public BasePage(AndroidDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//*[@content-desc='Commands']")
    public AndroidElement commandsTab;

    @AndroidFindBy(xpath = "//*[@content-desc='Connection']")
    public AndroidElement connectionTab;

    public void tapOnElement(AndroidElement element, int numberOfTimes) throws InterruptedException {
        Thread.sleep(1_000);

        touch = new TouchAction(driver);
        touch.tap(TapOptions.tapOptions().withElement(ElementOption.element(element)).withTapsCount(numberOfTimes));
        touch.perform();
    }

}
