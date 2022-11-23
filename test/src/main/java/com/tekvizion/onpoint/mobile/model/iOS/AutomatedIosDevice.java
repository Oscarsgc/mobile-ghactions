package com.tekvizion.onpoint.mobile.model.iOS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableMap;
import com.tekvizion.onpoint.mobile.model.AutomatedMobileDevice;
import com.tekvizion.onpoint.mobile.model.DesiredCapability;
import com.tekvizion.onpoint.mobile.model.DesiredCapabilityOption;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;

import io.appium.java_client.TouchAction;
import io.appium.java_client.clipboard.ClipboardContentType;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class AutomatedIosDevice extends AutomatedMobileDevice {
    private IOSDriver<?> driver;
    private String iosLogs;
    public String username;
    public String password;
    public String wdaLocalPort;

    public AutomatedIosDevice(String device, String deviceName, String automationName, String platformName,
            String platformVersion, String udid, String appiumServerUrl, String username, String password,
            String iosLogs, String wdaPort) throws MalformedURLException {
        super(device, deviceName, automationName, platformName, platformVersion, udid, appiumServerUrl);
        this.username = username;
        this.password = password;
        this.iosLogs = iosLogs;
        this.wdaLocalPort = wdaPort != null ? wdaPort : "8100";
    }

    public AutomatedIosDevice(String device, String deviceName, String automationName, String platformName,
            String platformVersion, String udid, String appiumServerUrl) throws MalformedURLException {
        super(device, deviceName, automationName, platformName, platformVersion, udid, appiumServerUrl);
        this.wdaLocalPort = "8100";
    }

    public AutomatedIosDevice(String device, String deviceName,
            String platformVersion, String udid) throws MalformedURLException {
        super(device, deviceName, "XCUITest", "iOS", platformVersion, udid, "http://localhost:4723/wd/hub");

        this.wdaLocalPort = "8100";
    }

    @Override
    public String toString() {
        String template = "Device: %s, Version: %s, UDID: %s";
        return String.format(template, this.device, this.platformVersion, this.udid);
    }

    @Override
    public void initialize() {
        DesiredCapabilities iosDeviceDesiredCapabilities = new DesiredCapabilities();
        List<DesiredCapability> desiredCapabilitiesList = getDesiredCapabilities();
        for (DesiredCapability desiredCapability : desiredCapabilitiesList) {
            iosDeviceDesiredCapabilities.setCapability(desiredCapability.getOptionName(), desiredCapability.getValue());
        }
        this.driver = new IOSDriver<>(this.appiumServerUrl, iosDeviceDesiredCapabilities);
    }

    private List<DesiredCapability> getDesiredCapabilities() {
        List<DesiredCapability> desiredCapabilities = new ArrayList<>();
        // desiredCapabilities.add(this.device);
        desiredCapabilities.add(this.deviceName);
        desiredCapabilities.add(this.automationName);
        desiredCapabilities.add(this.platformName);
        desiredCapabilities.add(this.platformVersion);
        desiredCapabilities.add(this.udid);
        this.setAdditionalCapabilities();

        desiredCapabilities.addAll(this.additionalCapabilities);
        return desiredCapabilities;
    }

    public void tearDown() {
        this.driver.quit();
    }

    public IOSDriver<?> getDriver() {
        return driver;
    }

    public void resetApp(){
        this.driver.resetApp();
    }    

    public void setAdditionalCapabilities() {
        // this.additionalCapabilities
        // .add(new DesiredCapability(DesiredCapabilityOption.IOS_LOG, "true"));
        this.additionalCapabilities.add(new DesiredCapability(DesiredCapabilityOption.NEW_COMMAND_TIMEOUT, "300"));
        this.additionalCapabilities
                .add(new DesiredCapability(DesiredCapabilityOption.BUNDLE_ID, "com.aitbol.spotlight"));
        this.additionalCapabilities
                .add(new DesiredCapability(DesiredCapabilityOption.XCODE_ORG, "97C4K3R6W5"));
        this.additionalCapabilities
                .add(new DesiredCapability(DesiredCapabilityOption.XCODE_SIGN, "6TF655T33J"));
        // this.additionalCapabilities
        //         .add(new DesiredCapability(DesiredCapabilityOption.UPDATED_BUNDLE, "io.appium.WebDriverAgentRunner"));
        this.additionalCapabilities.add(new DesiredCapability(DesiredCapabilityOption.WDA_PORT, this.wdaLocalPort));
        this.additionalCapabilities.add(new DesiredCapability(DesiredCapabilityOption.HEADLESS, false));
        // this.additionalCapabilities
        // .add(new DesiredCapability(DesiredCapabilityOption.ACCEPT_ALERTS, "true"));
    }

    @Override
    public void clickElementByAccessibilityId(String accessibilityId) {
        this.driver.findElementByAccessibilityId(accessibilityId).click();
    }

    @Override
    public void sendKeysToElementByAccessibilityId(String keys, String accessibilityId) {
        WebElement element = this.driver.findElementByAccessibilityId(accessibilityId);
        element.clear();
        element.sendKeys(keys);
    }

    public void awaitUntilPresenceByAccessibilityId(String accessibilityId, long awaitTime) {
        WebDriverWait wait = new WebDriverWait(this.driver, awaitTime);
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId(accessibilityId)));
    }

    public void awaitUntilPresenceByClassName(String className, long awaitTime) {
        WebDriverWait wait = new WebDriverWait(this.driver, awaitTime);
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.iOSClassChain(className)));
    }

    @Override
    public void clickElementByClassname(String classname) {
        this.driver.findElement(MobileBy.iOSClassChain(classname)).click();
    }

    public void awaitUntilAlert(long awaitTime) {
        WebDriverWait wait = new WebDriverWait(this.driver, awaitTime);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void clickOnAcceptAlert() {
        this.driver.switchTo().alert().accept();
    }

    public void clickOnCancelAlert() {
        this.driver.switchTo().alert().dismiss();
    }

    public boolean checkIfElementIsPresent(By condition) {
        return !this.driver.findElements(condition).isEmpty();
    }

    public WebElement getElementByAccessibilityId(String accessibilityId) {
        return this.driver.findElementByAccessibilityId(accessibilityId);
    }

    public void pressLockButton() {
        this.driver.lockDevice(Duration.ofMillis(1000));
    }

    public boolean isDeviceLocked() {
        return this.driver.isDeviceLocked();
    }

    public void showNotifications() {
        this.manageNotifications(true);
    }

    public void hideNotifications() {
        this.manageNotifications(false);
    }

    private void manageNotifications(Boolean show) {
        Dimension screenSize = this.driver.manage().window().getSize();
        int yMargin = 50;
        int xMid = screenSize.width / 2;
        PointOption top = PointOption.point(xMid, yMargin);
        PointOption bottom = PointOption.point(xMid, screenSize.height - yMargin);

        TouchAction action = new TouchAction(this.driver);
        if (show) {
            action.press(top);
        } else {
            action.press(bottom);
        }
        action.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)));
        if (show) {
            action.moveTo(bottom);
        } else {
            action.moveTo(top);
        }
        action.release();
        action.perform();
    }

    public void swipeElementIOS(WebElement element, String dir) {
        final int ANIMATION_TIME = 200;
        final int PRESS_TIME = 600;

        Dimension screenDimension = this.driver.manage().window().getSize();
        Rectangle rect = element.getRect();

        if (rect.x >= screenDimension.width || rect.x + rect.width <= 0
                || rect.y >= screenDimension.height || rect.y + rect.height <= 0) {
            throw new IllegalArgumentException("swipeElementIOS(): Element outside screen");
        }

        int leftBorder = 0;
        int rightBorder = 0;
        int upBorder = 0;
        int downBorder = 0;

        if (rect.x < 0) {
            rect.width = rect.width + rect.x;
            rect.x = 0;
        }
        if (rect.y < 0) {
            rect.height = rect.height + rect.y;
            rect.y = 0;
        }
        if (rect.width > screenDimension.width)
            rect.width = screenDimension.width;
        if (rect.height > screenDimension.height)
            rect.height = screenDimension.height;

        PointOption<?> pointOptionStart, pointOptionEnd;
        switch (dir) {
            case "DOWN": // From up to down
                pointOptionStart = PointOption.point(rect.x + rect.width / 2,
                        rect.y + upBorder);
                pointOptionEnd = PointOption.point(rect.x + rect.width / 2,
                        rect.y + rect.height - downBorder);
                break;
            case "UP": // From down to up
                pointOptionStart = PointOption.point(rect.x + rect.width / 2,
                        rect.y + rect.height - downBorder);
                pointOptionEnd = PointOption.point(rect.x + rect.width / 2,
                        rect.y + upBorder);
                break;
            case "LEFT": // From right to left
                pointOptionStart = PointOption.point(rect.x + rect.width - rightBorder,
                        rect.y + rect.height / 2);
                pointOptionEnd = PointOption.point(rect.x + leftBorder,
                        rect.y + rect.height / 2);
                break;
            case "RIGHT": // From left to right
                pointOptionStart = PointOption.point(rect.x + leftBorder,
                        rect.y + rect.height / 2);
                pointOptionEnd = PointOption.point(rect.x + rect.width - rightBorder,
                        rect.y + rect.height / 2);
                break;
            default:
                throw new IllegalArgumentException("swipeElementIOS(): dir: '" + dir + "' NOT supported");
        }
        // Execute swipe using TouchAction
        try {
            new TouchAction<>(this.driver)
                    .press(pointOptionStart)
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeElementIOS(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    public void goBack() {
        this.driver.navigate().back();
    }

    public Point getElementCoordinates(String accessibilityId) {
        return this.driver.findElementByAccessibilityId(accessibilityId).getLocation();
    }

    public void clickOnCoordinates(int xPoint, int yPoint) {
        new TouchAction(this.driver).press(PointOption.point(xPoint, yPoint))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).perform();
    }

    public void scrollFromElement(WebElement element, String direction, int points) {
        Point location = element.getLocation();
        PointOption pointOptionStart, pointOptionEnd;
        pointOptionStart = PointOption.point(location.getX(), location.getY());
        switch (direction) {
            case "up": // From Bottom to up
                pointOptionEnd = PointOption.point(0, -points);
                break;
            case "down":
                pointOptionEnd = PointOption.point(0, points);
                break;
            case "left":
                pointOptionEnd = PointOption.point(points, 0);
                break;
            case "right":
                pointOptionEnd = PointOption.point(-points, 0);
                break;
            default:
                throw new IllegalArgumentException("swipeElementIOS(): dir: '" + direction + "' NOT supported");
        }

        new TouchAction(this.driver).press(pointOptionStart)
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(pointOptionEnd)
                .release().perform();
    }

    public void longPressComponentByAccessibilityId(String accessibilityId, int duration) {
        WebElement element = this.driver.findElementByAccessibilityId(accessibilityId);
        Point location = element.getLocation();

        new TouchAction(this.driver)
                .press(PointOption.point(location.getX(), location.getY() + 10))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(5)))
                .release().perform();
    }

    public void toggleSwitch(String accessibilityId, boolean enabled) {
        WebElement toggleElement = this.driver.findElementByAccessibilityId(accessibilityId);
        int value = Integer.parseInt(toggleElement.getAttribute("value"));
        if ((value == 0 && enabled) || (value == 1 && !enabled))
            toggleElement.click();
    }

    public byte[] pullFile(String filename) {
        byte[] fileBase64 = this.driver.pullFile(filename);
        return fileBase64;
    }

    public void sendKeysToNativeKeyboard(String keys) {
    }

    public void sendKeysToElementByXpath(String keys, String xpath) {
    }

    public void clickElementById(String webElementId) {
    }

    public void clickElementByXpath(String xpath) {
    }

    public void sendKeysToElementById(String keys, String webElementId) {
    }

    public String getElementTextById(String elementId) {
        return "";
    }

    public boolean elementByIdExists(String id) {
        return false;
    }

    public boolean elementByXpathExists(String xpath) {
        return false;
    }

    public String getAttributePerAccessibilityId(String accessibilityId, String attribute) {
        try {
            return this.getDriver()
                    .findElement(MobileBy.AccessibilityId(
                            accessibilityId))
                    .getAttribute(attribute);
        } catch (Exception exception) {
            return null;
        }
    }

    public String getClipboardText() throws InterruptedException{
        this.driver.activateApp("com.apple.springboard");
        this.driver.activateApp("com.facebook.WebDriverAgentRunner.xctrunner");
        Thread.sleep(1000);
        String clipboard = this.driver.getClipboardText();
        Thread.sleep(1000);
        this.driver.activateApp(getAppUnderTest());
        return clipboard;
    }

    public String getAppUnderTest(){
        return "com.cisco.squared";       
    }

}