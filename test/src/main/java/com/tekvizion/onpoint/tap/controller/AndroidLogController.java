package com.tekvizion.onpoint.tap.controller;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.logging.LogEntries;

import java.util.Set;
import java.util.stream.StreamSupport;

public class AndroidLogController {

    AppiumDriver<?> driver;

    public AndroidLogController(AppiumDriver<?> appiumDriver) {
        driver = appiumDriver;
    }

    public void captureLogcat() {
        // inspect available log types
        Set<String> logtypes = driver.manage().logs().getAvailableLogTypes();
        System.out.println("suported log types: " + logtypes.toString()); // [logcat, bugreport, server, client]

        // print first and last 10 lines of logs
        LogEntries logs = driver.manage().logs().get("logcat");
        System.out.println("First and last ten lines of log: ");
        StreamSupport.stream(logs.spliterator(), false).limit(10).forEach(System.out::println);
        System.out.println("...");
        StreamSupport.stream(logs.spliterator(), false).skip(logs.getAll().size() - 10).forEach(System.out::println);

        // wait for more logs
        try {
            Thread.sleep(5000);
        } catch (Exception ign) {
        } // pause to allow visual verification

        // demonstrate that each time get logs, we only get new logs
        // which were generated since the last time we got logs
        LogEntries secondCallToLogs = driver.manage().logs().get("logcat");
        System.out.println("\nFirst ten lines of next log call: ");
        StreamSupport.stream(secondCallToLogs.spliterator(), false).limit(10).forEach(System.out::println);
    }

    public void captureNewLogs() {
        LogEntries newLogs = driver.manage().logs().get("logcat");
        System.out.println("\nFirst ten lines of next log call: ");
        StreamSupport.stream(newLogs.spliterator(), false).limit(10).forEach(System.out::println);
    }

}