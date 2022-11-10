package com.tekvizion.onpoint.mobile.android.webex.controller;

import com.tekvizion.onpoint.mobile.model.android.AutomatedAndroidDevice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MuteAction {
    private final Logger LOGGER = LogManager.getLogger(MuteAction.class);

    public String ExecuteAction(AutomatedAndroidDevice WebexSession, boolean dialingType, String conversionId) {

        try {
            LOGGER.info("Webex Session: " + WebexSession.getDriver().getSessionId());

            WebexSession.clickElementById("com.cisco.wx2.android:id/call_mute_button");
            Thread.sleep(2000);

            if (WebexSession.elementByAccessibilityIdExists("Unmute, button, double tap to activate")) {
                LOGGER.info("Webex Session: " + WebexSession.getDriver().getSessionId()
                        + " | Executed Mute Action successfully.");
                LOGGER.info("Webex Session: " + WebexSession.getDriver().getSessionId() + " | Conversation id "
                        + conversionId);
                return "Success";
            }

            LOGGER.error("Webex Session: " + WebexSession.getDriver().getSessionId()
                    + " | Error on Mute Action: Unmute button wasn't found");
            return "Mute Action Failed.";
        } catch (Exception exception) {
            return "Mute Action Failed.";
        }
    }
}
