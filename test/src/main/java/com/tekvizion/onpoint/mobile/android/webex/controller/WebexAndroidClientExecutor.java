package com.tekvizion.onpoint.mobile.android.webex.controller;

import com.tekvizion.onpoint.mobile.model.android.AutomatedAndroidDevice;
import com.tekvizion.onpoint.mobile.model.PhoneResourceManager;
import com.tekvizion.onpoint.mobile.model.WebSocketSessionMock;

import org.json.JSONObject;

import com.tekvizion.onpoint.tap.controller.ActionExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WebexAndroidClientExecutor extends ActionExecutor {
    private final Logger LOGGER = LogManager.getLogger(WebexAndroidClientExecutor.class);

    @Override
    public String execute(String actionName, JSONObject jsonObject, WebSocketSessionMock session) {

        String errorMessage = "";
        String conversionId;

        try {
            PhoneResourceManager.discoverAndRegisterDevices();
            AutomatedAndroidDevice device = (AutomatedAndroidDevice) PhoneResourceManager.getDeviceByUDID(jsonObject.getString("udid"));

            switch (actionName) {
                case "WEBEX_LOGIN":
                    String emailAddress = jsonObject.getString("user").trim();
                    String password = jsonObject.getString("password").trim();
                    errorMessage = new LoginAction().loginWithValidCredentials(device, emailAddress, password);
                    break;
                case "WEBEX_CALL":
                    String userToBeCalled = jsonObject.getString("user").trim();
                    conversionId = jsonObject.getString("conversionId").trim();
                    errorMessage = new CallAction().call(device, userToBeCalled, true, conversionId);
                    break;
                case "WEBEX_LOGOUT":
                    errorMessage = new LogoutAction().logout(device);
                    break;
                case "WEBEX_CALL_MUTE":
                    conversionId = jsonObject.getString("conversionId").trim();
                    errorMessage = new MuteAction().ExecuteAction(device, true, conversionId);
                    break;
                case "WEBEX_CALL_UNMUTE":
                    conversionId = jsonObject.getString("conversionId").trim();
                    errorMessage = new UnmuteAction().ExecuteAction(device, true, conversionId);
                    // AndroidLogController androidLogController = new
                    // AndroidLogController(device.getDriver());
                    // androidLogController.captureLogcat();
                    break;
                case "WEBEX_CALL_SPEAKER_ON_OFF":
                    conversionId = jsonObject.getString("conversionId").trim();
                    errorMessage = new SpeakerAction().ExecuteAction(device, true, conversionId);
                    break;
                case "WEBEX_END_CALL":
                    conversionId = jsonObject.getString("conversionId").trim();
                    errorMessage = new EndCallAction().ExecuteAction(device, true, conversionId);
                    break;
                case "WEBEX_ANSWER":
                    conversionId = jsonObject.getString("conversionId").trim();
                    errorMessage = new AnswerCallAction().answer(device, true, conversionId);
                    break;
                case "WEBEX_DECLINE":
                    conversionId = jsonObject.getString("conversionId").trim();
                    errorMessage = new DeclineCallAction().decline(device, true, conversionId);
                    break;
                case "WEBEX_END":
                    // conversionId = jsonObject.getString("conversionId") == null ? "" :
                    // jsonObject.getString("conversionId").trim();
                    // errorMessage = new EndAction().executeEndCall(replaceWithAndroidDriver,
                    // mainWindowHandle, conversionId);
                    break;
                case "WEBEX_VALIDATE_LOGIN":
                    String validateEmailAddress = jsonObject.getString("user").trim();
                    String validatePassword = jsonObject.getString("password").trim();
                    errorMessage = new LoginValidationAction().validateLogin(device, validateEmailAddress, validatePassword);
                    break;
                case "WEBEX_CALL_STATE":
                    conversionId = jsonObject.getString("conversionId").trim();
                    // String callState = jsonObject.getString("callSettings") == null ? "": jsonObject.getString("callSettings").trim();
                    // if(callState.equalsIgnoreCase("mute") || callState.equalsIgnoreCase("unMute")) {
                    //     errorMessage = new ValidateCallStateAction().validateMuteUnmute(webDriver, conversionId);
                    // else
                    errorMessage = new ValidateCallStateAction().getWebexCallState(device, conversionId);
                    break;
                case "GET_MEDIA_FILE":
                    conversionId = jsonObject.getString("conversionId").trim();
                    errorMessage = new GetMediaAction().ValidateMediaStats(device, true, conversionId);
                    break;
                case "WEBEX_VERSION_PROJECT":
                case "WEBEX_VERSION":
                    errorMessage = "42.1.0.21190 | 8.8.0 | Win 64";
                    break;
                case "WEBEX_LOG_SIZE":
                case "WEBEX_SYSTEM_SIZE":
                    errorMessage = "12";
                    break;
                case "GET_SYSTEM_FILE":
                case "GET_LOG_FILE":
                    errorMessage = "Success";
                    break;
                case "WEBEX_CLEAN_UP":
                case "WEBEX_CLEANUP":
                    errorMessage = new CleanUpAction().cleanUp(device);
                    break;
                default:
                    errorMessage = "Invalid action";
            }

        } catch (Exception e) {
            errorMessage = "Failed to execute action";
            LOGGER.error("Failed to execute action. Exception: " + e.getMessage());
        }
        return errorMessage;
    }
}
