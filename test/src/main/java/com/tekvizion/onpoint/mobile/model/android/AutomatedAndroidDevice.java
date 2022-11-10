package com.tekvizion.onpoint.mobile.model.android;

import com.tekvizion.onpoint.mobile.model.DesiredCapabilityOption;
import io.appium.java_client.HasOnScreenKeyboard;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.tekvizion.onpoint.mobile.model.AutomatedMobileDevice;
import com.tekvizion.onpoint.mobile.model.DesiredCapability;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

public class AutomatedAndroidDevice extends AutomatedMobileDevice {

        public AutomatedAndroidDevice(String device, String deviceName, String automationName, String platformName,
                        String platformVersion, String udid, String appiumServerUrl) throws MalformedURLException {
                super(device, deviceName, automationName, platformName, platformVersion, udid, appiumServerUrl);
        }

        public AutomatedAndroidDevice(String udid, String platformVersion) throws MalformedURLException {
                super(
                        AndroidConstants.PLATFORM_NAME,
                        udid,
                        AndroidConstants.UI_AUTOMATOR_2,
                        AndroidConstants.PLATFORM_NAME,
                        platformVersion,
                        udid,
                        AndroidConstants.APPIUM_SERVER_URL
                );
        }

        @Override
        public void initialize() {
                if(this.driver != null)
                        return;
                DesiredCapabilities androidDeviceDesiredCapabilities = new DesiredCapabilities();
                List<DesiredCapability> desiredCapabilitiesList = getDesiredCapabilities();
                for (DesiredCapability desiredCapability : desiredCapabilitiesList) {
                        androidDeviceDesiredCapabilities.setCapability(desiredCapability.getOptionName(),
                                        desiredCapability.getValue());
                }
                this.driver = new AndroidDriver<>(this.appiumServerUrl, androidDeviceDesiredCapabilities);
        }

        @Override
        public AndroidDriver<?> getDriver(){
                return (AndroidDriver<?>)this.driver;
        }

        public void resetApp(){
                this.driver.resetApp();
        }    

        private List<DesiredCapability> getDesiredCapabilities() {
                List<DesiredCapability> desiredCapabilities = new ArrayList<>();
                desiredCapabilities.add(this.device);
                desiredCapabilities.add(this.deviceName);
                desiredCapabilities.add(this.automationName);
                desiredCapabilities.add(this.platformName);
                desiredCapabilities.add(this.platformVersion);
                desiredCapabilities.add(this.udid);
                desiredCapabilities.add(new DesiredCapability(DesiredCapabilityOption.NEW_COMMAND_TIMEOUT, "10000")); // 30min timeout per session
                desiredCapabilities.add(new DesiredCapability(DesiredCapabilityOption.HEADLESS, false));
                desiredCapabilities.addAll(additionalCapabilities);
                return desiredCapabilities;
        }

        public void startWebexActivity(String activity) {
                Activity newActivity = new Activity(AndroidConstants.WEBEX_APP_PACKAGE, AndroidConstants.WEBEX_LAUNCHER_ACTIVITY);
                newActivity.setAppWaitActivity(activity);
                this.getDriver().startActivity(newActivity);
        }

        public void startTeamsActivity(String activity) {
                Activity newActivity = new Activity(AndroidConstants.TEAMS_APP_PACKAGE, AndroidConstants.TEAMS_LAUNCHER_ACTIVITY);
                newActivity.setAppWaitActivity(activity);
                this.getDriver().startActivity(newActivity);
        }

        public void tapScreenByCoordinates(int x, int y) {
                new TouchAction<>(this.driver).tap(point(x, y)).waitAction(waitOptions(ofMillis(250))).perform();
        }

        public void tapScreenByPoint(PointOption<?> pointToTap){
                new TouchAction<>(this.driver).tap(pointToTap).waitAction(waitOptions(ofMillis(250))).perform();
        }

        public void clickElementByAccessibilityId(String accessibilityId) {
//                throw new Exception("Unsupported action");
        }

        public void acceptPermissionWindow() throws InterruptedException {
                if(elementByIdExists(AndroidConstants.PERMISSION_MESSAGE_ID)) {
                        if (elementByIdExists(AndroidConstants.ALLOW_FOREGROUND_ONLY_PERMISSION_BUTTON_ID)){
                                Thread.sleep(250);
                                clickElementById(AndroidConstants.ALLOW_FOREGROUND_ONLY_PERMISSION_BUTTON_ID);
                                return;
                        }
                        if (elementByIdExists(AndroidConstants.ALLOW_PERMISSION_BUTTON_ID)){
                                Thread.sleep(250);
                                clickElementById(AndroidConstants.ALLOW_PERMISSION_BUTTON_ID);
                        }
                }
        }

        public String getClipboardText() {
                return this.getDriver().getClipboardText();
        }

        public void openNotifications(){
                this.getDriver().openNotifications();
        }

        public void pressBackButton() {
                this.getDriver().pressKey(new KeyEvent(AndroidKey.BACK));
        }

        public Dimension getWindowSize() {
                return this.driver.manage().window().getSize();
        }

        public boolean isKeyboardShown() {
                return ((HasOnScreenKeyboard) this.driver).isKeyboardShown();
        }

        public void sendKeysToNativeKeyboard(String keys) {
                this.getDriver().getKeyboard().sendKeys(keys);
        }

        public void sendKeysToElementByXpath(String keys, String xpath) {
                this.getDriver().findElement(By.xpath(xpath)).sendKeys(keys);
        }

        public void clickElementById(String webElementId) {
                this.getDriver().findElement(By.id(webElementId)).click();
        }

        public void longPressElementById(String webElementId) {
                new TouchAction<>(this.getDriver()).longPress(point(this.getDriver().findElement(By.id(webElementId)).getLocation())).perform();
        }

        public void clickElementByXpath(String xpath) {
                this.getDriver().findElement(By.xpath(xpath)).click();
        }

        public void sendKeysToElementById(String keys, String webElementId) {
                this.getDriver().findElement(By.id(webElementId)).sendKeys(keys);
        }

        public String getElementTextById(String elementId) {
                return this.getDriver().findElement(By.id(elementId)).getText();
        }

        public boolean elementByAccessibilityIdExists(String accessibilityId) {
                try {
                        return this.getDriver().findElementByAccessibilityId(accessibilityId).isDisplayed();
                } catch (Exception exception) {
                        return false;
                }
        }

        public boolean elementByIdExists(String id) {
                try {
                        return this.getDriver().findElement(By.id(id)).isDisplayed();
                } catch (Exception exception) {
                        return false;
                }
        }

        public boolean elementByXpathExists(String xpath) {
                try {
                        return this.getDriver().findElement(By.xpath(xpath)).isDisplayed();
                } catch (Exception exception) {
                        return false;
                }
        }

        @Override
        public void sendKeysToElementByAccessibilityId(String keys, String accessibilityId) {
                // TODO Auto-generated method stub

        }

        @Override
        public void clickElementByClassname(String classname) {
                // TODO Auto-generated method stub

        }

        public void scrollToElementId(String elementId) {
                ((AndroidDriver<?>)driver).findElementsByAndroidUIAutomator(String.format("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().resourceId(\"%s\"))", elementId));
        }
}
