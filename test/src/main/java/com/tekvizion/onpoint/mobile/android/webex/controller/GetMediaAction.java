package com.tekvizion.onpoint.mobile.android.webex.controller;

import com.tekvizion.onpoint.mobile.model.android.AndroidConstants;
import com.tekvizion.onpoint.mobile.model.android.AutomatedAndroidDevice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;

public class GetMediaAction {
    private final Logger LOGGER = LogManager.getLogger(GetMediaAction.class);

    public String ValidateMediaStats(AutomatedAndroidDevice WebexSession, boolean dialingType, String conversionId) {
        try {
            LOGGER.debug("Webex Session: " + WebexSession.getDriver().getSessionId() + " | Look for media stats");
            Thread.sleep(500);
            if (!WebexSession.elementByIdExists(AndroidConstants.IN_CALL_INDICATOR_ID)) {
                LOGGER.info("Webex Session: " + WebexSession.getDriver().getSessionId() + " | Not in a call");
                return "Failed to find an active call";
            }
            if (!WebexSession.elementByIdExists(AndroidConstants.IN_CALL_MORE_OPTIONS_BUTTON_ID))
            necessaryClickScreen(WebexSession);
            WebexSession.clickElementById(AndroidConstants.IN_CALL_MORE_OPTIONS_BUTTON_ID);
            Thread.sleep(500);
            WebexSession.clickElementByXpath(AndroidConstants.IN_CALL_ADVANCED_OPTIONS_XPATH);
            Thread.sleep(500);
            WebexSession.clickElementByXpath(AndroidConstants.IN_CALL_COPY_STATS_ID);
            Thread.sleep(500);
            // WebexSession.getClipboard(ClipboardContentType.PLAINTEXT); // get plaintext
            String mediaStats = WebexSession.getClipboardText();
            LOGGER.info("Webex Session: " + WebexSession.getDriver().getSessionId() + " | Call Media Stats: " + mediaStats);
            return mediaStats;
        } catch (Exception exception) {
            LOGGER.error("Webex Session: " + WebexSession.getDriver().getSessionId() + " | Unable to get call media stats. Exception: " + exception.getMessage());
            return "Failed";
        }
    }

    private void necessaryClickScreen(AutomatedAndroidDevice WebexSession) {
        try {
            Dimension windowSize = WebexSession.getWindowSize();
            int y = windowSize.getHeight() / 4;
            int x = windowSize.getWidth() / 2;
            WebexSession.tapScreenByCoordinates(x, y);
            Thread.sleep(500);
        } catch (Exception exception) {
            LOGGER.error("Webex Session: " + WebexSession.getDriver().getSessionId() + " | Unable to get call media stats. Exception: " + exception.getMessage());
        }
    }
}
