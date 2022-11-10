package com.tekvizion.onpoint.mobile.android.teams.controller;

import com.tekvizion.onpoint.mobile.model.android.AndroidConstants;
import com.tekvizion.onpoint.mobile.model.android.AutomatedAndroidDevice;
import com.tekvizion.onpoint.tap.controller.TapControllerAppInitializer;
import io.appium.java_client.MobileBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MuteAction {
    private final Logger LOGGER = LogManager.getLogger(MuteAction.class);

    public String ExecuteAction(AutomatedAndroidDevice device, boolean dialingType, String conversionId) {

        try {
            LOGGER.info("Teams Session: " + device.getDriver().getSessionId());

            if (device.elementByIdExists(AndroidConstants.TEAMS_CALLING_OPTIONS_BAR_ID)) {
                if (device.elementByIdExists(AndroidConstants.TEAMS_MUTE_BUTTON_ID)) {
                    device.clickElementById(AndroidConstants.TEAMS_MUTE_BUTTON_ID);
                    try {
                        new WebDriverWait(device.getDriver(), TapControllerAppInitializer.WEBEX_VIEW_WAITING)
                                .until(ExpectedConditions.presenceOfElementLocated(MobileBy.AccessibilityId(AndroidConstants.TEAMS_MUTED_ACCESSIBILITY_ID)));
                        LOGGER.info("Teams Session: " + device.getDriver().getSessionId()
                                + " | Executed Mute Action successfully.");
                        LOGGER.info("Teams Session: " + device.getDriver().getSessionId() + " | Conversation id "
                                + conversionId);
                        return "Success";
                    } catch (Exception e) {
                        LOGGER.error("Teams Session: " + device.getDriver().getSessionId()
                                + " | Error on Mute Action: Button pressed but call was not muted");
                        return "Mute Action Failed.";
                    }
                } else {
                    LOGGER.error("Teams Session: " + device.getDriver().getSessionId()
                            + " | Error on Mute Action: Unmute button wasn't found");
                    return "Mute Action Failed.";
                }
            } else {
                LOGGER.error("Teams Session: " + device.getDriver().getSessionId()
                        + " | Error on Mute Action: Calling options bar not found");
                return "Mute Action Failed.";
            }
        } catch (Exception exception) {
            return "Mute Action Failed.";
        }
    }
}
