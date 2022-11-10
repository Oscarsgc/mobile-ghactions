package com.tekvizion.onpoint.mobile.android.teams.controller;

import com.tekvizion.onpoint.mobile.model.PhoneResourceManager;
import com.tekvizion.onpoint.mobile.model.WebSocketSessionMock;
import com.tekvizion.onpoint.mobile.model.android.AutomatedAndroidDevice;
import com.tekvizion.onpoint.tap.controller.ActionExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class TeamsAndroidClientExecutor extends ActionExecutor {
    private final Logger LOGGER = LogManager.getLogger(TeamsAndroidClientExecutor.class);

    @Override
    public String execute(String actionName, JSONObject jsonObject, WebSocketSessionMock session) {

        String errorMessage = "";
        String conversionId;

        try {
            PhoneResourceManager.discoverAndRegisterDevices();
            AutomatedAndroidDevice device = (AutomatedAndroidDevice) PhoneResourceManager.getDeviceByUDID(jsonObject.getString("udid"));

            switch (actionName) {
                case "TEAMS_LOGIN":
                    String emailAddress = jsonObject.getString("user").trim();
                    String password = jsonObject.getString("password").trim();
                    errorMessage = new LoginAction().loginWithValidCredentials(device, emailAddress, password);
                    break;
                case "TEAMS_LOGOUT":
                    errorMessage = new LogoutAction().logout(device);
                    break;
                case "TEAMS_CALL_MUTE":
                    conversionId = jsonObject.getString("conversionId").trim();
                    errorMessage = new MuteAction().ExecuteAction(device, true, conversionId);
                    break;
                case "TEAMS_CALL_UNMUTE":
                    conversionId = jsonObject.getString("conversionId").trim();
                    errorMessage = new UnmuteAction().ExecuteAction(device, true, conversionId);
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
