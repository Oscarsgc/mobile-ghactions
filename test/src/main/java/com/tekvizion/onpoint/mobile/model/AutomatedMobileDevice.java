package com.tekvizion.onpoint.mobile.model;

import io.appium.java_client.AppiumDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class AutomatedMobileDevice {

    protected final URL appiumServerUrl;
    protected final DesiredCapability device;
    protected final DesiredCapability deviceName;
    protected final DesiredCapability automationName;
    protected final DesiredCapability platformName;
    protected final DesiredCapability platformVersion;
    protected final DesiredCapability udid;
    protected String automationPort = "";
    protected final List<DesiredCapability> additionalCapabilities = new ArrayList<>();
    protected AppiumDriver<?> driver;

    public AutomatedMobileDevice(String device, String deviceName, String automationName, String platformName,
            String platformVersion, String udid, String appiumServerUrl) throws MalformedURLException {
        this.device = new DesiredCapability(DesiredCapabilityOption.DEVICE, device);
        this.deviceName = new DesiredCapability(DesiredCapabilityOption.DEVICE_NAME, deviceName);
        this.automationName = new DesiredCapability(DesiredCapabilityOption.AUTOMATION_NAME, automationName);
        this.platformName = new DesiredCapability(DesiredCapabilityOption.PLATFORM_NAME, platformName);
        this.platformVersion = new DesiredCapability(DesiredCapabilityOption.PLATFORM_VERSION, platformVersion);
        this.udid = new DesiredCapability(DesiredCapabilityOption.UDID, udid);
        this.appiumServerUrl = new URL(appiumServerUrl);
    }

    public abstract void clickElementByAccessibilityId(String accessibilityId);

    public abstract void clickElementByClassname(String classname);

    public abstract void sendKeysToElementByAccessibilityId(String keys, String accessibilityId);

    public abstract void initialize();

    public AppiumDriver<?> getDriver() {
        return driver;
    }

    public void addDesiredCapability(DesiredCapability desiredCapability) {
        this.additionalCapabilities.add(desiredCapability);
    }

    public abstract void resetApp();

    public String getUdid() {
        return this.udid.getValue().toString();
    }

    public abstract void sendKeysToNativeKeyboard(String keys);

    public abstract void sendKeysToElementByXpath(String keys, String xpath);

    public abstract void clickElementById(String webElementId);

    public abstract void clickElementByXpath(String xpath);

    public abstract void sendKeysToElementById(String keys, String webElementId);

    public abstract String getElementTextById(String elementId);

    public abstract boolean elementByIdExists(String id);

    public abstract boolean elementByXpathExists(String xpath);

    public boolean isIOS() {
        return this.platformName.getValue().equals("iOS");
    }

    public boolean isAndroid() {
        return this.platformName.getValue().equals("Android");
    }
}
