package com.tekvizion.onpoint.mobile.iOS.tekvizion.controller;

import org.apache.logging.log4j.*;
import org.openqa.selenium.remote.SessionId;

import com.tekvizion.onpoint.mobile.model.iOS.AutomatedIosDevice;
import com.tekvizion.onpoint.tap.controller.TapControllerAppInitializer;

import io.appium.java_client.MobileBy;

public class ClickTabAction {
    private final Logger LOGGER = LogManager.getLogger(ClickTabAction.class);

    public String click(AutomatedIosDevice iosDevice, String originTab, String clickElement, String destinationTab) {
        SessionId sessionId = iosDevice.getDriver().getSessionId();
        try {
            LOGGER.info("Webex Session: " + sessionId);
            iosDevice.awaitUntilPresenceByAccessibilityId(originTab, TapControllerAppInitializer.WEBEX_LOGIN_TIMER);
            LOGGER.debug("Webex Session: " + sessionId + " | Tab Data Found.");
            // Touch 'Get Started Button'
            iosDevice.clickElementByAccessibilityId(clickElement);
            LOGGER.debug("Webex Session: " + sessionId + " | Clicked element");
            iosDevice.awaitUntilPresenceByAccessibilityId(destinationTab,
                    TapControllerAppInitializer.WEBEX_LOGIN_TIMER);
            LOGGER.debug("Webex Session: " + sessionId + " | Tab Data Found.");
            if (iosDevice.checkIfElementIsPresent(MobileBy.AccessibilityId(destinationTab))) {
                LOGGER.info("Webex Session: " + sessionId + " | Executed successfully.");
                return "Success";
            }
            return "Null";

        } catch (Exception e) {
            LOGGER.error("Error on click Action: " + sessionId + " " + e.getMessage());
            return "Action Failed.";
        }
    }
}
