package com.tekvizion.onpoint.mobile.android.webex.controller;

import com.tekvizion.onpoint.mobile.model.android.AndroidConstants;
import com.tekvizion.onpoint.mobile.model.android.AutomatedAndroidDevice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EndCallAction {
    private final Logger LOGGER = LogManager.getLogger(EndCallAction.class);

    public String ExecuteAction(AutomatedAndroidDevice WebexSession, boolean dialingType, String conversionId) {

        try {
            LOGGER.info("Webex Session: " + WebexSession.getDriver().getSessionId());

            WebexSession.clickElementById(AndroidConstants.CALL_END_BUTTON_ID);
            Thread.sleep(5000);

            if (!WebexSession.elementByAccessibilityIdExists("End call")) {
                LOGGER.info("Webex Session: " + WebexSession.getDriver().getSessionId()
                        + " | Executed End Call Action successfully.");
                LOGGER.info("Webex Session: " + WebexSession.getDriver().getSessionId() + " | Conversation id "
                        + conversionId);
                return "Success";
            }

            LOGGER.error("Webex Session: " + WebexSession.getDriver().getSessionId()
                    + " | Error on End Call Action: Call is still ongoing");
            return "End Call Action Failed.";
        } catch (Exception exception) {
            return "End Call Action Failed.";
        }
    }
}
