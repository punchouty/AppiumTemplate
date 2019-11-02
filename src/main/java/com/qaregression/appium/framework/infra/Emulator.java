package com.qaregression.appium.framework.infra;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class Emulator {

    private static String sdkPath = System.getProperty("android.sdk.directory");
    private static String adbPath = sdkPath + "/platform-tools" + File.separator + "adb";
    private static String emulatorPath = sdkPath + "/tools" + File.separator + "emulator";

    public static void launch() {
        boolean isAnyRunningDevices = isAnyRunningDevices();
        if(isAnyRunningDevices) {
            System.exit(-1);
        }
        String nameOfAVD = System.getProperty("android.avd.name");
        System.out.println("Starting emulator for '" + nameOfAVD + "' ...");
        String[] aCommand = new String[] { emulatorPath, "-avd", nameOfAVD };
        try {
            Process process = new ProcessBuilder(aCommand).start();
            process.waitFor(3, TimeUnit.SECONDS);
            System.out.println("Emulator launched successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isAnyRunningDevices() {
        System.out.println("Checking attach devices for ...");
        String[] aCommand = new String[] { adbPath, "devices" };
        try {
            Process process = new ProcessBuilder(aCommand).start();
            process.waitFor(1, TimeUnit.SECONDS);
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = inputStream.readLine(); // line == List of devices attached
            while (line != null) {
                line = inputStream.readLine(); // line == a534ce7	device
                if(line != null && line.endsWith("device")) {
                    System.out.println(line);
                    System.out.println("Device is already attached. Please shut down before starting tests");
                    return true;
                }
            }

        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void waitForBeReady() {
        try {
            String[] commandBootComplete = new String[] { adbPath, "shell", "getprop", "dev.bootcomplete" };
            Process process = new ProcessBuilder(commandBootComplete).start();
            process.waitFor(1, TimeUnit.SECONDS);
            BufferedReader inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line = inputStream.readLine();
            System.out.println("Line outer 1 : " + line);
            while(line == null) {
                process.waitFor(2, TimeUnit.SECONDS);
                process = new ProcessBuilder(commandBootComplete).start();
                inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
                line = inputStream.readLine();
                if(line == null) {
//                    System.out.println("Line is null : " + line);
                }
                else if(!line.equals("1")) {
                    System.out.println("Line inner 1 : " + line);
                    line = null;
                }
                else {
                    System.out.println("Line inner 1 : " + line);
                }
            }

            String[] commandBootAnim = new String[] { adbPath, "shell", "getprop", "init.svc.bootanim" };
            process = new ProcessBuilder(commandBootAnim).start();
            inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));

            line = inputStream.readLine();
            System.out.println("Line outer 2 : " + line);
            // wait till the property returns 'stopped'
            while (line == null || !line.equals("stopped")) {
                process.waitFor(2, TimeUnit.SECONDS);
                process = new ProcessBuilder(commandBootAnim).start();
                inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));line = inputStream.readLine();
                line = inputStream.readLine();
                if(line == null) {
//                    System.out.println("Line is null : " + line);
                }
                else if(!line.equals("stopped")) {
                    System.out.println("Line inner 2 : " + line);
                    line = null;
                }
                else {
                    System.out.println("Line inner 2 : " + line);
                }
            }

            System.out.println("Emulator is ready to use!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isRunning() {

        try {
            String[] commandDevices = new String[] { adbPath, "devices" };
            Process process = new ProcessBuilder(commandDevices).start();

            BufferedReader inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String output = "";
            String line = null;
            while ((line = inputStream.readLine()) != null) {
                System.out.println(line);
                output = output + line;
            }
            if (!output.replace("List of devices attached", "").trim().equals("")) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void stop() {
        System.out.println("Killing emulator...");
        String[] aCommand = new String[] { adbPath, "emu", "kill" };
        try {
            Process process = new ProcessBuilder(aCommand).start();
            process.waitFor(1, TimeUnit.SECONDS);
            System.out.println("Emulator closed successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
