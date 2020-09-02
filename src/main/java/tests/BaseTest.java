package tests;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.URL;

public abstract class BaseTest {

    private static AppiumDriverLocalService service;

    @BeforeSuite
    public void globalSetup () {
        service = AppiumDriverLocalService.buildDefaultService();
        service.start();
    }

    @AfterSuite(alwaysRun = true)
    public void globalTearDown () {
        service.stop();
    }

    public URL getServiceUrl () {
        return service.getUrl();
    }

}