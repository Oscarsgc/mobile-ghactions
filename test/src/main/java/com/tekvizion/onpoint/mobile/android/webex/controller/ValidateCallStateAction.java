package com.tekvizion.onpoint.mobile.android.webex.controller;

import com.tekvizion.onpoint.mobile.model.android.AndroidConstants;
import com.tekvizion.onpoint.mobile.model.android.AutomatedAndroidDevice;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ValidateCallStateAction {

    private final Logger LOGGER = LogManager.getLogger(LoginAction.class);
	String callState = "Idle";
	
	public String getWebexCallState(AutomatedAndroidDevice androidDevice, String conversionId) {

        LOGGER.info("Webex Session: " + androidDevice.getDriver().getSessionId());

        try {
            // LOGGER.debug("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Open Webex Teams Activity");
            // androidDevice.startWebexActivity(AndroidConstants.WEBEX_TEAMS_ACTIVITY);
            Thread.sleep(1000);

			if (androidDevice.elementByIdExists(AndroidConstants.IN_CALL_INDICATOR_ID)) {
				if (androidDevice.elementByIdExists(AndroidConstants.CALL_CONNECTING_INDICATOR_ID))
				callState = "Ringback";
				else {
					if (androidDevice.elementByIdExists(AndroidConstants.CALL_RESUME_ID))
						callState = "CallHold";
					else callState = "Connected";
					//here we need to add validation to differentiate between Connected and CallHeld
				}
			} else {
				if (androidDevice.elementByIdExists(AndroidConstants.INCOMING_CALL_INDICATOR_ID))
					callState = "Ringing";
				else {
					LOGGER.debug("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Open phone notifications");
					androidDevice.openNotifications();
					Thread.sleep(1000);
					LOGGER.debug("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Look for Answer button");
					// if (!androidDevice.elementByXpathExists(AndroidConstants.NOTIFICATION_ANSWER_XPATH)) {
					// 	LOGGER.debug("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Call answer button not found, waiting" + 5000 + "ms before retry");
					// 	Thread.sleep(5000);
					// }
					if (androidDevice.elementByXpathExists(AndroidConstants.NOTIFICATION_ANSWER_XPATH)) 
						callState = "Ringing";
				}
			}
        } catch (Exception exception) {
            LOGGER.error("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Unable to find Call State. Exception: "
                    + exception.getMessage());
            return "Failed";
        }

		LOGGER.info("Webex Session: " + androidDevice.getDriver().getSessionId() + " | Call state identified: " + callState);
        return callState;
    }

	public String validateMuteUnmute(AutomatedAndroidDevice androidDevice, String conversionId) {

	    // Validate Mute
		// try {
		// 	WebElement muteValidate = androidDevice.findElement(By.id("microphone-button"));
		// 	String muteStatus = muteValidate.getAttribute("aria-label");
			
		// 	if (muteStatus.startsWith("Unmute")) {
		// 		return "Mute";
		// 	}
		// 	else if(muteStatus.startsWith("Mute")){
		// 		return "Unmute";
		// 	}
		// } catch (Exception e) {
		// 	LOGGER.error("Failed to validate CallMute");
		// }
		return callState;
	}

}
