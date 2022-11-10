package com.tekvizion.onpoint.mobile.android.webex.controller;

import com.tekvizion.onpoint.mobile.model.android.AutomatedAndroidDevice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UnmuteAction {
    private final Logger LOGGER = LogManager.getLogger(UnmuteAction.class);

    public String ExecuteAction(AutomatedAndroidDevice WebexSession, boolean dialingType, String conversionId) {

        try {
            LOGGER.info("Webex Session: " + WebexSession.getDriver().getSessionId());

            WebexSession.clickElementById("com.cisco.wx2.android:id/call_mute_button");
            Thread.sleep(2000);

            if (WebexSession.elementByAccessibilityIdExists("Mute, button, double tap to activate")) {
                LOGGER.info("Webex Session: " + WebexSession.getDriver().getSessionId()
                        + " | Executed Unmute Action successfully.");
                LOGGER.info("Webex Session: " + WebexSession.getDriver().getSessionId() + " | Conversation id "
                        + conversionId);
                return "Success";
            }

            LOGGER.error("Webex Session: " + WebexSession.getDriver().getSessionId()
                    + " | Error on Unmute Action: Mute button wasn't found");
            return "Unmute Action Failed.";
        } catch (Exception exception) {
            return "Unmute Action Failed.";
        }
    }
}
