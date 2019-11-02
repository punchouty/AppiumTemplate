package com.qaregression.appium.framework.infra;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.qaregression.appium.framework.AppiumBase.initProperties;

public class AppiumServerTest {

    @BeforeClass
    public void setup() {
        initProperties();
    }

    @Test
    public void testAppiumServer() {
        AppiumServer.launch();
        System.out.println("Check if running");
        boolean running = AppiumServer.checkIfRunnning();
        System.out.println("is running : " + running);
        AppiumServer.stop();
    }

    @Test
    public void testIfRunning() {
        boolean running = AppiumServer.checkIfRunnning();
        System.out.println(running);
    }
}
