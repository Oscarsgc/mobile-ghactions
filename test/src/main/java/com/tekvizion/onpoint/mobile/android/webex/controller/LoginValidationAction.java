package com.tekvizion.onpoint.mobile.android.webex.controller;

import com.tekvizion.onpoint.mobile.model.android.AndroidConstants;
import com.tekvizion.onpoint.mobile.model.android.AutomatedAndroidDevice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginValidationAction {

    private final Logger LOGGER = LogManager.getLogger(LoginValidationAction.class);

    public String validateLogin(AutomatedAndroidDevice androidDevice, String emailAddress, String password) {
        try {
            LOGGER.debug("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Open LoginActivity");
            androidDevice.startWebexActivity(AndroidConstants.WEBEX_LOGIN_ACTIVITY);
            Thread.sleep(5000);
            // check if the user is already logged in
            if (androidDevice.elementByIdExists(AndroidConstants.TEAMS_ACTIVITY_FIRST_PANE_ID)){
                LOGGER.info("Webex Session: " + androidDevice.getDriver().getSessionId()
                        + " | Webex user is already loggedin.");
                return "Webex user is already loggedin";
            }
            // declare login action object to perform login process actions
            LoginAction loginAction = new LoginAction();
            // check if the user email address is invalid
            loginAction.loginWithSpecificEmail(androidDevice, emailAddress);
            if (androidDevice.elementByIdExists(AndroidConstants.PIN_CONFIRMATION_TITLE_ID) || androidDevice.elementByIdExists(AndroidConstants.PIN_CONFIRMATION_INPUT_ID)) {
                LOGGER.info("Webex Session: " + androidDevice.getDriver().getSessionId()
                        + " | Invalid email address provided.");
                return "Invalid email address provided";
            }
            // check if the password is invalid
            loginAction.introducePassword(androidDevice, password);
            if (androidDevice.elementByIdExists(AndroidConstants.LOGIN_NAV_ID)) {
                LOGGER.info("Webex Session: " + androidDevice.getDriver().getSessionId()
                        + " | Invalid password provided.");
                return "ERROR: Wrong username or password";
            }
            if (loginAction.validateMainViewLoad(androidDevice) == "Failed")
                return "Failed";
            return validatePhoneService(androidDevice);
        }
        catch (Exception exception) {
            LOGGER.error("Error on Login Action: " + androidDevice.getDriver().getSessionId() + " " + exception.getMessage());
            return "Login Action Failed.";
        }
    }

    private String validatePhoneService(AutomatedAndroidDevice androidDevice) {
        try {
            LOGGER.debug("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Looking for phone service information.");
            androidDevice.clickElementById(AndroidConstants.AVATAR_VIEW_ID);
            Thread.sleep(500);
            androidDevice.clickElementById(AndroidConstants.SETTINGS_BUTTON_XPATH);
            Thread.sleep(500);
            if (androidDevice.elementByIdExists(AndroidConstants.PHONE_SERVICE_INDICATOR_ID))
                return "Phone service disconnected";
            return "Success";
        } catch (Exception exception) {
            LOGGER.error("Error on Login Action: " + androidDevice.getDriver().getSessionId() + " " + exception.getMessage());
            return "Failed";
        }
    }
}
