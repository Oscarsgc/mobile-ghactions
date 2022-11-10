package com.tekvizion.onpoint.mobile.android.webex.controller;

import com.tekvizion.onpoint.mobile.model.android.AndroidConstants;
import com.tekvizion.onpoint.mobile.model.android.AutomatedAndroidDevice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

public class CallAction {
    private final Logger LOGGER = LogManager.getLogger(CallAction.class);

    public String call(AutomatedAndroidDevice androidDevice, String userEmail, boolean dialingType, String conversionId) {
        try {
            LOGGER.info("Webex Session: " + androidDevice.getDriver().getSessionId());

            androidDevice.startWebexActivity(AndroidConstants.WEBEX_TEAMS_ACTIVITY);
            Thread.sleep(1000);

            if(androidDevice.elementByIdExists(AndroidConstants.NOTIFICATION_PANEL_ID))
                androidDevice.pressBackButton();

            Pattern emailAddressPattern = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");

            if (emailAddressPattern.matcher(userEmail).matches()){
                return this.callThroughEmail(androidDevice, userEmail, dialingType, conversionId);
            }

            return this.callThoughDID(androidDevice, userEmail, dialingType, conversionId);
        }
        catch(Exception exception){
            return "Call Action Failed.";
        }
    }

    private String callThoughDID(AutomatedAndroidDevice androidDevice, String userEmail, boolean dialingType, String conversionId) throws InterruptedException {
        androidDevice.clickElementByXpath(AndroidConstants.CALLING_LOWER_TAB_RELATIVE_XPATH);
        Thread.sleep(1000);

        androidDevice.clickElementById(AndroidConstants.FLOATING_ACTION_BUTTON_ID);
        Thread.sleep(250);

        for (String number : userEmail.split("")) {
            if (number.equals("+"))
                androidDevice.longPressElementById(AndroidConstants.NUMBER_PAD_N_NUMBER_ID + "0");
            else
                androidDevice.clickElementById(AndroidConstants.NUMBER_PAD_N_NUMBER_ID + number);
        }
        Thread.sleep(250);

        androidDevice.clickElementById(AndroidConstants.NUMBER_PAD_CALL_BUTTON_ID);
        Thread.sleep(500);

        androidDevice.clickElementById(AndroidConstants.BUTTON_1_ID);

        Thread.sleep(1000);
        androidDevice.acceptPermissionWindow(); // use microphone
        Thread.sleep(250);
        androidDevice.acceptPermissionWindow(); // record audio
        Thread.sleep(250);
        androidDevice.acceptPermissionWindow(); // make and manage phone calls

        LOGGER.info("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Executed call action successfully.");
        LOGGER.info("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Conversation id " + conversionId);
        return "Success";
    }

    private String callThroughEmail(AutomatedAndroidDevice androidDevice, String userEmail, boolean dialingType, String conversionId) throws InterruptedException {
        androidDevice.clickElementById(AndroidConstants.SEARCH_ICON_ID);
        Thread.sleep(1000);

        androidDevice.acceptPermissionWindow(); // access to contacts
        Thread.sleep(250);

        androidDevice.sendKeysToElementByXpath(userEmail, AndroidConstants.EDIT_TEXT_RELATIVE_XPATH);
        Thread.sleep(5000);

        androidDevice.clickElementById(AndroidConstants.AUDIO_CALL_ID);
        Thread.sleep(1000);
        androidDevice.acceptPermissionWindow(); // use microphone
        Thread.sleep(250);
        androidDevice.acceptPermissionWindow(); // record audio
        Thread.sleep(250);
        androidDevice.acceptPermissionWindow(); // make and manage phone calls

        LOGGER.info("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Executed call action successfully.");
        LOGGER.info("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Conversation id " + conversionId);
        return "Success";
    }
}
