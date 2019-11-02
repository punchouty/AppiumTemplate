package com.qaregression.appium.framework;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AppiumBaseTest extends AppiumBase {

    @Test
    public void testDriver() throws Exception {
        WebDriver webDriver = getDriver();
        Assert.assertNotNull(webDriver);
    }
}
