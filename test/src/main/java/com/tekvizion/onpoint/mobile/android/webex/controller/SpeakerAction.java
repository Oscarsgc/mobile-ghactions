package com.tekvizion.onpoint.mobile.android.webex.controller;

import com.tekvizion.onpoint.mobile.model.android.AutomatedAndroidDevice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpeakerAction {
    private final Logger LOGGER = LogManager.getLogger(SpeakerAction.class);

    public String ExecuteAction(AutomatedAndroidDevice WebexSession, boolean dialingType, String conversionId) {

        try {
            LOGGER.info("Webex Session: " + WebexSession.getDriver().getSessionId());

            WebexSession.clickElementById("com.cisco.wx2.android:id/switch_audio_output_button");
            Thread.sleep(2000);

            if (WebexSession.elementByAccessibilityIdExists("Using speaker for audio")) {
                LOGGER.info("Webex Session: " + WebexSession.getDriver().getSessionId()
                        + " | Executed Turn On Speaker Action successfully.");
                LOGGER.info("Webex Session: " + WebexSession.getDriver().getSessionId() + " | Conversation id "
                        + conversionId);

                WebexSession.clickElementById("com.cisco.wx2.android:id/switch_audio_output_button");
                Thread.sleep(2000);

                if (WebexSession.elementByAccessibilityIdExists("Using earpiece for audio")) {
                    LOGGER.info("Webex Session: " + WebexSession.getDriver().getSessionId()
                            + " | Executed Turn Off Speaker Action successfully.");
                    LOGGER.info("Webex Session: " + WebexSession.getDriver().getSessionId() + " | Conversation id "
                            + conversionId);
                    return "Success";
                }

                LOGGER.error("Webex Session: " + WebexSession.getDriver().getSessionId()
                        + " | Error on Turn Off Speaker Action: Speaker wasn't deactivated");
                return "Turn Off Speaker Action Failed.";
            } else {
                LOGGER.error("Webex Session: " + WebexSession.getDriver().getSessionId()
                        + " | Error on Turn On Speaker Action: Speaker wasn't activated");
                return "Turn On Speaker Action Failed.";
            }
        } catch (Exception exception) {
            return "Speaker Action Failed.";
        }
    }
}
