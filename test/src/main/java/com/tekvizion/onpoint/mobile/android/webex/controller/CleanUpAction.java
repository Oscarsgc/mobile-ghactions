package com.tekvizion.onpoint.mobile.android.webex.controller;

import com.tekvizion.onpoint.mobile.model.android.AutomatedAndroidDevice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CleanUpAction {

    private final Logger LOGGER = LogManager.getLogger(CleanUpAction.class);

    public String cleanUp(AutomatedAndroidDevice androidDevice) {
        LOGGER.info("Webex Session: " + androidDevice.getDriver().getSessionId());
        LOGGER.debug("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Quitting driver session.");
        androidDevice.getDriver().quit();
        return "Success";
    }
}
