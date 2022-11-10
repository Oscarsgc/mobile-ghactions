package com.tekvizion.onpoint.mobile.android.teams.controller;

import com.tekvizion.onpoint.mobile.model.android.AndroidConstants;
import com.tekvizion.onpoint.mobile.model.android.AutomatedAndroidDevice;
import com.tekvizion.onpoint.tap.controller.TapControllerAppInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LogoutAction {

    private final Logger LOGGER = LogManager.getLogger(LogoutAction.class);

    public String logout(AutomatedAndroidDevice androidDevice) {
        try {
            LOGGER.info("Teams Session: " + androidDevice.getDriver().getSessionId());

            if(androidDevice.elementByIdExists("com.android.systemui:id/notification_panel")) {
                androidDevice.pressBackButton();
            }

            androidDevice.startTeamsActivity(AndroidConstants.TEAMS_MAIN_ACTIVITY);

            new WebDriverWait(androidDevice.getDriver(), TapControllerAppInitializer.TEAMS_VIEW_TIMER)
                    .until(ExpectedConditions.presenceOfElementLocated(By.id(AndroidConstants.TEAMS_AVATAR_ID)));
            androidDevice.clickElementById(AndroidConstants.TEAMS_AVATAR_ID);

            new WebDriverWait(androidDevice.getDriver(), TapControllerAppInitializer.TEAMS_VIEW_TIMER)
                    .until(ExpectedConditions.presenceOfElementLocated(By.id(AndroidConstants.TEAMS_SETTINGS_BUTTON_ID)));
            androidDevice.clickElementById(AndroidConstants.TEAMS_SETTINGS_BUTTON_ID);

            new WebDriverWait(androidDevice.getDriver(), TapControllerAppInitializer.TEAMS_VIEW_TIMER)
                    .until(ExpectedConditions.presenceOfElementLocated(By.id(AndroidConstants.TEAMS_SETTINGS_LIST_ID)));
            androidDevice.scrollToElementId(AndroidConstants.TEAMS_SIGN_OUT_CONTAINER_ID);

            new WebDriverWait(androidDevice.getDriver(), TapControllerAppInitializer.TEAMS_VIEW_TIMER)
                    .until(ExpectedConditions.presenceOfElementLocated(By.id(AndroidConstants.TEAMS_SIGN_OUT_BUTTON_ID)));
            androidDevice.clickElementById(AndroidConstants.TEAMS_SIGN_OUT_BUTTON_ID);

            new WebDriverWait(androidDevice.getDriver(), TapControllerAppInitializer.TEAMS_VIEW_TIMER)
                    .until(ExpectedConditions.presenceOfElementLocated(By.id(AndroidConstants.TEAMS_SIGN_OUT_CONFIRM_BUTTON_ID)));
            androidDevice.clickElementById(AndroidConstants.TEAMS_SIGN_OUT_CONFIRM_BUTTON_ID);

            new WebDriverWait(androidDevice.getDriver(), TapControllerAppInitializer.TEAMS_VIEW_TIMER)
                    .until(ExpectedConditions.presenceOfElementLocated(By.id(AndroidConstants.TEAMS_JOIN_MEETING_BUTTON)));
            LOGGER.info("Teams Session: " + androidDevice.getDriver().getSessionId() + " | Executed Logout action by User name successfully.");
            return "Success";
        }
        catch (Exception exception) {
            LOGGER.error("Teams Session: " + androidDevice.getDriver().getSessionId() + " | Error on Logout action:" + exception.getMessage());
            return "Logout Action Failed.";
        }
    }
}
