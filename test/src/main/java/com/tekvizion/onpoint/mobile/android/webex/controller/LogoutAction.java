package com.tekvizion.onpoint.mobile.android.webex.controller;

import com.tekvizion.onpoint.mobile.model.android.AndroidConstants;
import com.tekvizion.onpoint.mobile.model.android.AutomatedAndroidDevice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogoutAction {

    private final Logger LOGGER = LogManager.getLogger(LogoutAction.class);

    public String logout(AutomatedAndroidDevice androidDevice) {
        try {
            LOGGER.info("Webex Session: " + androidDevice.getDriver().getSessionId());

            if(androidDevice.elementByIdExists("com.android.systemui:id/notification_panel")) {
                androidDevice.pressBackButton();
            }

            androidDevice.startWebexActivity(AndroidConstants.WEBEX_TEAMS_ACTIVITY);
            Thread.sleep(1000);

            androidDevice.clickElementById("com.cisco.wx2.android:id/avatar_view");
            Thread.sleep(1000);

            androidDevice.clickElementByXpath("//android.view.ViewGroup[@content-desc=\"Sign out button\"]");
            Thread.sleep(1000);

            androidDevice.clickElementById("android:id/button1");
            Thread.sleep(5000);

            if(!androidDevice.elementByIdExists(AndroidConstants.SEARCH_ICON_ID)){
                LOGGER.info("Webex Session: " + androidDevice.getDriver().getSessionId()
                        + " | Executed Logout action by User name successfully.");
                return "Success";
            }

            LOGGER.error("Webex Session: " + androidDevice.getDriver().getSessionId()
                    + " | Error on Logout action: Main View wasn't found");
            return "Logout Action Failed.";
        }
        catch (Exception exception) {
            return "Logout Action Failed.";
        }
    }
}
