package com.qaregression.appium.framework.infra;

import io.appium.java_client.service.local.AppiumDriverLocalService;

public class AppiumServer {

    public static AppiumDriverLocalService service;

    public static AppiumDriverLocalService launch() {
        boolean flag = checkIfRunnning();
        if (flag) {
            System.out.println("Appium Server already running");
        }
        else {
            System.out.println("Starting Appium Server");
            service = AppiumDriverLocalService.buildDefaultService();
            System.out.println("Service created");
            service.start();
            System.out.println("Appium Server Started");
        }
        return service;
    }

    public static void stop() {
        System.out.println("Stopping Appium Server : " + service);
        if(service != null) {
            System.out.println("Trying Stopping Appium Server");
            service.stop();
            System.out.println("Appium Server Stopped");
        }
    }

    public static boolean checkIfRunnning() {
        if(service == null) {
            return false;
        }
        else {
            return service.isRunning();
        }
    }
}
