package com.qaregression.appium.framework.pages;

import com.qaregression.appium.framework.AppiumBase;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.Test;


public class ApiDemoTest extends AppiumBase {

    @Test(dataProvider = "InputData", dataProviderClass = TestData.class)
    public void apiDemoTest(String input) throws Exception {

        AppiumDriver driver = getDriver();
        driver.resetApp();
        HomePage h = new HomePage(driver);

        h.Preferences.click();

        Preferences p = new Preferences(driver);
        p.dependencies.click();
        driver.findElementById("android:id/checkbox").click();
        driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();
        driver.findElementByClassName("android.widget.EditText").sendKeys(input);
        p.buttons.get(1).click();


    }


}