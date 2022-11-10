package com.tekvizion.onpoint.mobile.android.webex.controller;

import com.tekvizion.onpoint.mobile.model.android.AndroidConstants;
import com.tekvizion.onpoint.mobile.model.android.AutomatedAndroidDevice;
import com.tekvizion.onpoint.tap.controller.TapControllerAppInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AnswerCallAction {

    private final Logger LOGGER = LogManager.getLogger(AnswerCallAction.class);

    public String answer(AutomatedAndroidDevice androidDevice, boolean dialType, String conversionId) {

        LOGGER.info("Webex Session: " + androidDevice.getDriver().getSessionId());

        try {

            LOGGER.debug("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Open phone notifications");
            androidDevice.openNotifications();
            Thread.sleep(500);

            LOGGER.debug("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Look for Answer button");
            new WebDriverWait(androidDevice.getDriver(), TapControllerAppInitializer.WEBDRIVER_WAITING_TIME_20SEC)
                    .until(ExpectedConditions.presenceOfElementLocated(By.xpath(AndroidConstants.NOTIFICATION_ANSWER_XPATH)));

            LOGGER.debug("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Pressing Answer button");
            androidDevice.clickElementByXpath(AndroidConstants.NOTIFICATION_ANSWER_XPATH);
            Thread.sleep(500);

            androidDevice.acceptPermissionWindow(); // record video
            Thread.sleep(250);
            androidDevice.acceptPermissionWindow(); // record audio
            Thread.sleep(250);
            androidDevice.acceptPermissionWindow(); // manage phone calls
            Thread.sleep(250);

            LOGGER.debug("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Press back button to close notifications");
            androidDevice.pressBackButton();
            Thread.sleep(250);

            LOGGER.info("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Executed call action successfully.");
            LOGGER.info("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Conversation id " + conversionId);
            return "Success";

        } catch (Exception exception) {
            LOGGER.error("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Unable to Answer call. Exception  :"
                    + exception.getMessage());
            return "Answer Call Action Failed.";
        }
    }
}
