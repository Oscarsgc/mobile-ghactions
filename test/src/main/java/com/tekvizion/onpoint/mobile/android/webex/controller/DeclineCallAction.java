package com.tekvizion.onpoint.mobile.android.webex.controller;

import com.tekvizion.onpoint.mobile.model.android.AndroidConstants;
import com.tekvizion.onpoint.mobile.model.android.AutomatedAndroidDevice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DeclineCallAction {

    private final Logger LOGGER = LogManager.getLogger(DeclineCallAction.class);

    public String decline(AutomatedAndroidDevice androidDevice, boolean dialType, String conversionId){

        LOGGER.info("Webex Session: " + androidDevice.getDriver().getSessionId());

        try {

            LOGGER.debug("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Open Webex Teams Activity");
            androidDevice.startWebexActivity(AndroidConstants.WEBEX_TEAMS_ACTIVITY);
            Thread.sleep(500);

            LOGGER.debug("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Open phone notifications");
            androidDevice.openNotifications();
            Thread.sleep(500);

            LOGGER.debug("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Look for Decline button");
            if(!androidDevice.elementByXpathExists(AndroidConstants.NOTIFICATION_DECLINE_XPATH)) {
                LOGGER.debug("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Call answer button not found, waiting" + 5000 + "ms before retry");
                Thread.sleep(5000);
            }
            LOGGER.debug("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Pressing Decline button");
            androidDevice.clickElementByXpath(AndroidConstants.NOTIFICATION_DECLINE_XPATH);
            Thread.sleep(500);

            LOGGER.debug("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Press back button to close notifications");
            androidDevice.pressBackButton();
            Thread.sleep(500);


            if(androidDevice.elementByIdExists(AndroidConstants.SEARCH_ICON_ID)){
                LOGGER.info("Webex Session: " + androidDevice.getDriver().getSessionId()
                        + " | Executed Decline action successfully.");
                return "Success";
            }

        } catch (Exception exception) {
            LOGGER.error("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Unable to Decline call. Exception  :"
                    + exception.getMessage());
            return "Decline Call Action Failed.";
        }


        return "Decline Call Action Failed.";
    }
}
