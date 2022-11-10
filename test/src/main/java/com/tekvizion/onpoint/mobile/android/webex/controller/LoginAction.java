package com.tekvizion.onpoint.mobile.android.webex.controller;

import com.tekvizion.onpoint.mobile.model.android.AndroidConstants;
import com.tekvizion.onpoint.mobile.model.android.AutomatedAndroidDevice;
import com.tekvizion.onpoint.tap.controller.TapControllerAppInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Dimension;

public class LoginAction {
    private final Logger LOGGER = LogManager.getLogger(LoginAction.class);

    public String loginWithValidCredentials(AutomatedAndroidDevice androidDevice, String emailAddress, String password) {
        try {
            LOGGER.debug("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Open LoginActivity");
            androidDevice.startWebexActivity(AndroidConstants.WEBEX_LOGIN_ACTIVITY);

            Thread.sleep(10000);

            loginWithSpecificEmail(androidDevice, emailAddress);

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

    public void loginWithSpecificEmail(AutomatedAndroidDevice androidDevice, String emailAddress) {
        try {
            LOGGER.debug("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Clicking get started button");
            androidDevice.clickElementById(AndroidConstants.GET_STARTED_BUTTON_ID);
            Thread.sleep(5000);
            introduceEmail(androidDevice, emailAddress);
            if (androidDevice.elementByIdExists(AndroidConstants.EMAIL_TEXT_ID))
                introduceEmail(androidDevice, emailAddress);
        } catch (Exception exception) {
            LOGGER.error("Error on Login Action: " + androidDevice.getDriver().getSessionId() + " " + exception.getMessage());
        }
    }
    
    private void introduceEmail(AutomatedAndroidDevice androidDevice, String emailAddress) {
        try {
            LOGGER.debug("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Entering Email address.");
            androidDevice.sendKeysToElementById(emailAddress, AndroidConstants.EMAIL_TEXT_ID);
            Thread.sleep(1000);
            
            LOGGER.debug("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Clicking enter email button");
            androidDevice.clickElementById(AndroidConstants.EMAIL_NEXT_BUTTON_ID);
            Thread.sleep(15000);
        } catch (Exception exception) {
            LOGGER.error("Error on Login Action: " + androidDevice.getDriver().getSessionId() + " " + exception.getMessage());
        }
    }

    public String introducePassword(AutomatedAndroidDevice androidDevice, String password) {
        try {
            if (!androidDevice.elementByIdExists(AndroidConstants.LOGIN_NAV_ID)){
                LOGGER.error("Error on Login Action: " + androidDevice.getDriver().getSessionId() + " | Password Web View wasn't found.");
                return "Failed";
            }
            LOGGER.debug("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Tapping password field");

            Dimension windowSize = androidDevice.getWindowSize();
            int height = windowSize.getHeight();
            int x = windowSize.getWidth() / 2;
            int y = this.findPasswordField(androidDevice, x, height / 3, height);
            if (y == 0) {
                LOGGER.error("Error on Login Action: " + androidDevice.getDriver().getSessionId() + " | Couldn't find the password field.");
                return "Failed";
            }

            LOGGER.debug("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Entering Password.");
            androidDevice.sendKeysToNativeKeyboard(password);
            Thread.sleep(1000);

            LOGGER.debug("Webex Session: " + androidDevice.getDriver().getSessionId()
                    + " | Hiding keyboard after password input.");
            androidDevice.getDriver().hideKeyboard();
            Thread.sleep(1000);

            if (!this.findSubmitButton(androidDevice, x, y + 100, height)){
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
                Thread.sleep(1000);
                // check if the password was clicked and keyboard is displayed
                if (androidDevice.isKeyboardShown())
                    return y;
                // moving down 100 pixels to try again
                y += 100;
            }
            return 0;
        } catch (Exception exception) {
            LOGGER.error("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Error exception: ",
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
                    Thread.sleep(1000);
                } else {
                    Thread.sleep(4000);
                    LOGGER.debug(
                            "Webex Session: " + androidDevice.getDriver().getSessionId() + "| coords: " + x + "," + y);
                    // check if the main view is being loaded
                    if (!androidDevice.elementByIdExists("com.cisco.wx2.android:id/sub_header"))
                        return true;
                }
                // moving down 100 pixels to try again
                y += 100;
            }
            return false;
        } catch (Exception exception) {
            LOGGER.error("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Error exception: ",
                    exception);
            return false;
        }
    }

    public String validateMainViewLoad(AutomatedAndroidDevice androidDevice) {
        new WebDriverWait(androidDevice.getDriver(), TapControllerAppInitializer.WEBEX_LOGIN_TIMER)
                    .until(ExpectedConditions.presenceOfElementLocated(By.id(AndroidConstants.TEAMS_ACTIVITY_FIRST_PANE_ID)));

        if(androidDevice.elementByIdExists(AndroidConstants.TEAMS_ACTIVITY_FIRST_PANE_ID)){
            LOGGER.info("Webex Session: " + androidDevice.getDriver().getSessionId()
                    + " | Executed Login action successfully.");
            return "Success";
        }

        LOGGER.error("Webex Session: " + androidDevice.getDriver().getSessionId()
                + " | Error on Login action: Main View wasn't found");
        return "Failed";
    }
}
