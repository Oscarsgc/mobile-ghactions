package com.tekvizion.onpoint.mobile.model;

public enum DesiredCapabilityOption {

    // related documentation https://appium.io/docs/en/writing-running-appium/caps/
    DEVICE("device"), // The kind of mobile device or emulator to use
    DEVICE_NAME("deviceName"), // The kind of mobile device or emulator to use
    AUTOMATION_NAME("automationName"), // Which automation engine to use
    PLATFORM_NAME("platformName"),
    PLATFORM_VERSION("platformVersion"),
    UDID("udid"),
    APP_ACTIVITY("appActivity"),
    NO_RESET("noReset"),
    BUNDLE_ID("bundleId"),
    XCODE_ORG("xcodeOrgId"),
    XCODE_SIGN("xcodeSigningId"),
    IOS_LOG("showIOSLog"),
    UPDATED_BUNDLE("updatedWDABundleId"),
    WDA_PORT("wdaLocalPort"),
    ACCEPT_ALERTS("autoAcceptAlerts"),
    NEW_COMMAND_TIMEOUT("newCommandTimeout"),
    HEADLESS("isHeadless"),
    APP_PACKAGE("appPackage");

    private final String name;

    DesiredCapabilityOption(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}