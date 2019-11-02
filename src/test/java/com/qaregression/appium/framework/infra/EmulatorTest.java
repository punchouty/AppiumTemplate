package com.qaregression.appium.framework.infra;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.qaregression.appium.framework.AppiumBase.initProperties;

public class EmulatorTest {

    @BeforeClass
    public void setup() {
        initProperties();
    }

    @Test
    public void testEmulator() {
        Emulator.launch();
        Emulator.waitForBeReady();
        Emulator.stop();
    }

    @Test
    public void testDevices() {
        Emulator.isAnyRunningDevices();
    }
}
