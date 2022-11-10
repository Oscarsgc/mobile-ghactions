package com.tekvizion.onpoint.mobile.android.teams.controller;

import com.tekvizion.onpoint.mobile.model.android.AndroidConstants;
import com.tekvizion.onpoint.mobile.model.android.AutomatedAndroidDevice;
import com.tekvizion.onpoint.tap.controller.TapControllerAppInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginAction {
    private final Logger LOGGER = LogManager.getLogger(LoginAction.class);

    public String loginWithValidCredentials(AutomatedAndroidDevice androidDevice, String emailAddress, String password) {
        try {
            LOGGER.debug("Teams Session: " + androidDevice.getDriver().getSessionId() + " | Open LoginActivity");
            androidDevice.startTeamsActivity(AndroidConstants.TEAMS_LOGIN_ACTIVITY);

            new WebDriverWait(androidDevice.getDriver(), TapControllerAppInitializer.TEAMS_INIT_TIMER)
                    .until(ExpectedConditions.presenceOfElementLocated(By.id(AndroidConstants.TEAMS_JOIN_MEETING_BUTTON)));

            if (!androidDevice.elementByIdExists(AndroidConstants.TEAMS_SIGN_IN_BUTTON_ID)) {
                LOGGER.debug("Teams Session: " + androidDevice.getDriver().getSessionId() + " | No sign in button detected");
                if (androidDevice.elementByAccessibilityIdExists(emailAddress)) {
                    androidDevice.clickElementByAccessibilityId(emailAddress);
                    Thread.sleep(TapControllerAppInitializer.TEAMS_INPUT_PAUSE_MS);
                    LOGGER.debug("Teams Session: " + androidDevice.getDriver().getSessionId() + " | Clicking suggested credentials");
                } else {
                    androidDevice.clickElementByXpath(AndroidConstants.TEAMS_SIGN_IN_ANOTHER_ACCOUNT_BUTTON_XPATH);
                    LOGGER.debug("Teams Session: " + androidDevice.getDriver().getSessionId() + " | Clicking sign in with another account button");
                    new WebDriverWait(androidDevice.getDriver(), TapControllerAppInitializer.TEAMS_VIEW_TIMER)
                            .until(ExpectedConditions.presenceOfElementLocated(By.id(AndroidConstants.TEAMS_EMAIL_TEXT_ID)));
                    introduceEmail(androidDevice, emailAddress);
                }

            } else {
                introduceEmail(androidDevice, emailAddress);
            }
            String introducePasswordMessage = introducePassword(androidDevice, password);
            if (introducePasswordMessage.equals("Failed"))
                return introducePasswordMessage;
            
            return validateMainViewLoad(androidDevice);
        }
        catch (Exception exception) {
            LOGGER.error("Error on Login Action: " + androidDevice.getDriver().getSessionId() + " " + exception.getMessage());
            return "Failed";
        }
    }
    
    private void introduceEmail(AutomatedAndroidDevice androidDevice, String emailAddress) {
        try {
            LOGGER.debug("Teams Session: " + androidDevice.getDriver().getSessionId() + " | Entering Email address.");
            androidDevice.sendKeysToElementById(emailAddress, AndroidConstants.TEAMS_EMAIL_TEXT_ID);
            LOGGER.debug("Teams Session: " + androidDevice.getDriver().getSessionId() + " | Clicking enter email button");
            androidDevice.clickElementById(AndroidConstants.TEAMS_SIGN_IN_BUTTON_ID);
            new WebDriverWait(androidDevice.getDriver(), TapControllerAppInitializer.TEAMS_WEBVIEW_TIMER)
                    .until(ExpectedConditions.presenceOfElementLocated(By.id(AndroidConstants.TEAMS_PASSWORD_WEB_VIEW_ID)));
        } catch (Exception exception) {
            LOGGER.error("Error on Login Action: " + androidDevice.getDriver().getSessionId() + " " + exception.getMessage());
        }
    }

    public String introducePassword(AutomatedAndroidDevice androidDevice, String password) {
        try {
            if (!androidDevice.elementByIdExists(AndroidConstants.TEAMS_PASSWORD_WEB_VIEW_ID)){
                LOGGER.error("Error on Login Action: " + androidDevice.getDriver().getSessionId() + " | Password Web View wasn't found.");
                return "Failed";
            }
            LOGGER.debug("Teams Session: " + androidDevice.getDriver().getSessionId() + " | Tapping password field");

            Dimension windowSize = androidDevice.getWindowSize();
            int height = windowSize.getHeight();
            int x = windowSize.getWidth() * 4 / 5;
            int y = this.findPasswordField(androidDevice, x, height / 5, height);
            if (y == 0) {
                LOGGER.error("Error on Login Action: " + androidDevice.getDriver().getSessionId() + " | Couldn't find the password field.");
                return "Failed";
            }

            LOGGER.debug("Teams Session: " + androidDevice.getDriver().getSessionId() + " | Entering Password.");
            androidDevice.sendKeysToNativeKeyboard(password);
            Thread.sleep(TapControllerAppInitializer.TEAMS_INPUT_PAUSE_MS);

            LOGGER.debug("Teams Session: " + androidDevice.getDriver().getSessionId()
                    + " | Hiding keyboard after password input.");
            androidDevice.getDriver().hideKeyboard();
            Thread.sleep(TapControllerAppInitializer.TEAMS_INPUT_PAUSE_MS);

            if (!this.findSubmitButton(androidDevice, x, y + 200, height)){
                LOGGER.error("Error on Login Action: " + androidDevice.getDriver().getSessionId() + " | Couldn't find the submit button.");
                return "Failed";
            }
            return "";
        } catch (Exception exception) {
            LOGGER.error("Error on Login Action: " + androidDevice.getDriver().getSessionId() + " " + exception.getMessage());
            return "Failed";
        }
    }

    private int findPasswordField(AutomatedAndroidDevice androidDevice, int x, int y, int height) {
        try {
            while (y <= height) {
                androidDevice.tapScreenByCoordinates(x, y);
                Thread.sleep(TapControllerAppInitializer.TEAMS_INPUT_PAUSE_MS);
                // check if the password was clicked and keyboard is displayed
                if (androidDevice.isKeyboardShown())
                    return y;
                // moving down 100 pixels to try again
                y += 50;
            }
            return 0;
        } catch (Exception exception) {
            LOGGER.error("Teams Session: " + androidDevice.getDriver().getSessionId() + " | Error exception: ",
                    exception);
            return 0;
        }
    }

    private boolean findSubmitButton(AutomatedAndroidDevice androidDevice, int x, int y, int height) {
        try {
            while (y <= height) {
                androidDevice.tapScreenByCoordinates(x, y);
                // make sure we didn't click in the password field again
                if (androidDevice.isKeyboardShown()) {
                    androidDevice.getDriver().hideKeyboard();
                    Thread.sleep(TapControllerAppInitializer.TEAMS_INPUT_PAUSE_MS);
                } else {
                    LOGGER.debug(
                            "Teams Session: " + androidDevice.getDriver().getSessionId() + "| coords: " + x + "," + y);
                    // check if the main view is being loaded
                    Thread.sleep(TapControllerAppInitializer.TEAMS_VIEW_TIMER);
                    if (!androidDevice.elementByIdExists(AndroidConstants.TEAMS_WEBVIEW_ACTION_BAR_ID)) {
                        return true;
                    }
                }
                // moving down 100 pixels to try again
                y += 100;
            }
            return false;
        } catch (Exception exception) {
            LOGGER.error("Teams Session: " + androidDevice.getDriver().getSessionId() + " | Error exception: ",
                    exception);
            return false;
        }
    }

    public String validateMainViewLoad(AutomatedAndroidDevice androidDevice) throws InterruptedException {
        new WebDriverWait(androidDevice.getDriver(), TapControllerAppInitializer.TEAMS_LOGIN_TIMER)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.id(AndroidConstants.TEAMS_SIGNING_IN_MESSAGE_ID)));
        if (androidDevice.elementByIdExists(AndroidConstants.SYSTEM_SAVE_PASS_FORM_ID)) {
            androidDevice.clickElementById(AndroidConstants.SYSTEM_DECLINE_SAVE_PASS_BUTTON_ID);
            Thread.sleep(TapControllerAppInitializer.TEAMS_INPUT_PAUSE_MS);
        }
        if (androidDevice.elementByIdExists(AndroidConstants.TEAMS_NEXT_BUTTON_ID)) {
            androidDevice.clickElementById(AndroidConstants.TEAMS_NEXT_BUTTON_ID);

            new WebDriverWait(androidDevice.getDriver(), TapControllerAppInitializer.TEAMS_VIEW_TIMER)
                    .until(ExpectedConditions.presenceOfElementLocated(By.id(AndroidConstants.TEAMS_NEXT_BUTTON_ID)));
            androidDevice.clickElementById(AndroidConstants.TEAMS_NEXT_BUTTON_ID);

            new WebDriverWait(androidDevice.getDriver(), TapControllerAppInitializer.TEAMS_VIEW_TIMER)
                    .until(ExpectedConditions.presenceOfElementLocated(By.id(AndroidConstants.TEAMS_LAST_BUTTON_ID)));
            androidDevice.clickElementById(AndroidConstants.TEAMS_LAST_BUTTON_ID);

            new WebDriverWait(androidDevice.getDriver(), TapControllerAppInitializer.TEAMS_VIEW_TIMER)
                    .until(ExpectedConditions.presenceOfElementLocated(By.id(AndroidConstants.TEAMS_PRIVACY_GOT_IT_BUTTON_ID)));
            androidDevice.clickElementById(AndroidConstants.TEAMS_PRIVACY_GOT_IT_BUTTON_ID);
        }
        new WebDriverWait(androidDevice.getDriver(), TapControllerAppInitializer.TEAMS_INIT_TIMER)
                    .until(ExpectedConditions.presenceOfElementLocated(By.id(AndroidConstants.TEAMS_CHAT_MESSAGES_LIST_ID)));

        if(androidDevice.elementByIdExists(AndroidConstants.TEAMS_CHAT_MESSAGES_LIST_ID)){
            LOGGER.info("Teams Session: " + androidDevice.getDriver().getSessionId()
                    + " | Executed Login action successfully.");
            return "Success";
        }

        LOGGER.error("Teams Session: " + androidDevice.getDriver().getSessionId()
                + " | Error on Login action: Main View wasn't found");
        return "Failed";
    }
}
