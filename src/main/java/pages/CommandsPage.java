package pages;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class CommandsPage extends BasePage {

    public CommandsPage(AndroidDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//*[@content-desc='Scroll Paper']")
    private AndroidElement scrollPaperBtn;

    @AndroidFindBy(xpath = "//*[@content-desc='Print Service Doc']")
    private AndroidElement printServiceDocBtn;

    @AndroidFindBy(xpath = "//*[@content-desc='Print X Report']")
    private AndroidElement printXReportBtn;

    @AndroidFindBy(xpath = "//*[@content-desc='Open Shift']")
    private AndroidElement openShiftBtn;

    @AndroidFindBy(xpath = "//*[@content-desc='print Reciept']")
    private AndroidElement printReceiptBtn;

    @AndroidFindBy(xpath = "//*[@content-desc='Close Shift']")
    private AndroidElement closeShiftBtn;


    public ConnectionPage openConnectionPage() throws InterruptedException {
        this.tapOnElement(connectionTab, 1);
        return new ConnectionPage(driver);
    }

    public CommandsPage reOpen() throws InterruptedException {
        this.tapOnElement(commandsTab, 1);
        return this;
    }

}
