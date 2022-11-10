package com.tekvizion.onpoint.tap.controller;

import com.tekvizion.onpoint.mobile.android.webex.controller.WebexAndroidClientExecutor;
import com.tekvizion.onpoint.mobile.model.WebSocketSessionMock;

import com.tekvizion.onpoint.mobile.android.teams.controller.TeamsAndroidClientExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.web.socket.TextMessage;

import java.util.ArrayList;
import java.util.List;

public class TapControllerClient {

    private final static Logger LOGGER = LogManager.getLogger(TapControllerClient.class);

    public static void main(String[] args) {
        connect();
    }

    public static void connect() {
        JSONObject actionJsonObject;
        List<JSONObject> actionList = new ArrayList<>();
//        actionJsonObject = getJSONPropertiesByAction(ANDROID_WEBEX_LOGOUT);
//        actionJsonObject.put("udid", "RF8RB20BVAL"); // Galaxy S20FE
//        actionList.add(actionJsonObject);
//        actionJsonObject = getJSONPropertiesByAction(ANDROID_WEBEX_LOGOUT);
//        actionJsonObject.put("udid", "15151JEC207036"); // Pixel 4a
//        actionList.add(actionJsonObject);
//
//        actionJsonObject = getJSONPropertiesByAction(ANDROID_WEBEX_LOGIN);
//        actionJsonObject.put("udid", "RF8RB20BVAL"); // Galaxy S20FE
//        actionJsonObject.put("user", "Dev-user10@tekvizion.com");
//        actionJsonObject.put("password", "P@ssw0rd!@");
//        actionList.add(actionJsonObject);
//        actionJsonObject = getJSONPropertiesByAction(ANDROID_WEBEX_LOGIN);
//        actionJsonObject.put("udid", "RF8RB20BVAL"); // Galaxy S20FE
//        actionJsonObject.put("user", "qa-user35@tekvizion.com");
//        actionJsonObject.put("password", "P@ssw0rd!@");
//        actionList.add(actionJsonObject);
//        actionJsonObject = getJSONPropertiesByAction(ANDROID_WEBEX_LOGIN);
//        actionJsonObject.put("udid", "15151JEC207036"); // Pixel 4a
//        actionJsonObject.put("user", "qa-user35@tekvizion.com");
//        actionJsonObject.put("password", "P@ssw0rd!@");
//        actionList.add(actionJsonObject);

//        actionJsonObject = getJSONPropertiesByAction(ANDROID_WEBEX_CALL);
//        actionJsonObject.put("udid", "RF8RB20BVAL"); // Galaxy S20FE
////        actionJsonObject.put("user", "Dev-user10@tekvizion.com");
//        actionJsonObject.put("user", "+19725987017");
//        actionList.add(actionJsonObject);
//        actionJsonObject = getJSONPropertiesByAction(ANDROID_WEBEX_ANSWER);
//        actionJsonObject.put("udid", "15151JEC207036"); // Pixel 4a
//        actionList.add(actionJsonObject);
//        actionJsonObject = getJSONPropertiesByAction(ANDROID_WEBEX_DECLINE);
//        actionJsonObject.put("udid", "15151JEC207036"); // Pixel 4a
//        actionList.add(actionJsonObject);

//        actionJsonObject = getJSONPropertiesByAction(ANDROID_WEBEX_LOGOUT);
//        actionJsonObject.put("udid", "RF8RB20BVAL"); // Galaxy S20FE
//        actionList.add(actionJsonObject);
//        actionJsonObject = getJSONPropertiesByAction(ANDROID_WEBEX_LOGOUT);
//        actionJsonObject.put("udid", "15151JEC207036"); // Pixel 4a
//        actionList.add(actionJsonObject);

//        actionList.add(getJSONPropertiesByAction(ANDROID_WEBEX_CALL));
//        actionJsonObject.put("udid", "15151JEC207036"); // Pixel 4a
//        actionJsonObject.put("user", "QA-user36@tekvizion.com");
//        actionList.add(getJSONPropertiesByAction(ANDROID_WEBEX_ANSWER));
//        actionJsonObject.put("udid", "RF8RB20BVAL"); // Galaxy S20FE
//        actionList.add(getJSONPropertiesByAction(ANDROID_WEBEX_DECLINE));


//        actionJsonObject = getJSONPropertiesByAction(ANDROID_WEBEX_END_CALL);
//        actionJsonObject.put("udid", "RF8RB20BVAL"); // Galaxy S20FE
//        actionList.add(actionJsonObject);


//        actionJsonObject = getJSONPropertiesByAction(ANDROID_WEBEX_CLEAN_UP);
//        actionJsonObject.put("udid", "RF8RB20BVAL"); // Galaxy S20FE
//        actionList.add(actionJsonObject);
//        actionJsonObject = getJSONPropertiesByAction(ANDROID_WEBEX_CLEAN_UP);
//        actionJsonObject.put("udid", "15151JEC207036"); // Pixel 4a
//        actionList.add(actionJsonObject);

        actionJsonObject = getJSONPropertiesByAction(ANDROID_TEAMS_MUTE);
        actionJsonObject.put("udid", "emulator-5554"); // Emulator
//        actionJsonObject.put("user", "aitbol3@tekvizionlabs.com");
//        actionJsonObject.put("password", "q334qALc");
        actionList.add(actionJsonObject);
        actionJsonObject = getJSONPropertiesByAction(ANDROID_TEAMS_UNMUTE);
        actionJsonObject.put("udid", "emulator-5554"); // Emulator
        actionList.add(actionJsonObject);

//        actionJsonObject = getJSONPropertiesByAction(ANDROID_TEAMS_LOGIN);
//        actionJsonObject.put("udid", "RF8RB20BVAL"); // Galaxy S20FE
//        actionJsonObject.put("user", "aitbol3@tekvizionlabs.com");
//        actionJsonObject.put("password", "q334qALc");
//        actionList.add(actionJsonObject);
//        actionJsonObject = getJSONPropertiesByAction(ANDROID_TEAMS_LOGOUT);
//        actionJsonObject.put("udid", "RF8RB20BVAL"); // Galaxy S20FE
//        actionList.add(actionJsonObject);

//        List<JSONObject> actionList = new ArrayList<>();
//        actionList.add(getJSONPropertiesByAction("ANDROID_WEBEX_LOGIN"));
//        actionList.add(getJSONPropertiesByAction("ANDROID_WEBEX_CALL"));
//        actionList.add(getJSONPropertiesByAction("ANDROID_WEBEX_CALL_MUTE"));
//        actionList.add(getJSONPropertiesByAction("ANDROID_WEBEX_CALL_UNMUTE"));
//        actionList.add(getJSONPropertiesByAction("ANDROID_WEBEX_CALL_SPEAKER_ON_OFF"));
//        actionList.add(getJSONPropertiesByAction("ANDROID_WEBEX_END_CALL"));
//        actionList.add(getJSONPropertiesByAction("ANDROID_WEBEX_QUIT"));
        WebSocketSessionMock session = new WebSocketSessionMock();
        for (JSONObject jsonObject : actionList) {
            System.out.println(jsonObject);
            if (jsonObject.getString("Request-URI").equalsIgnoreCase("/api/TapController/v1/TapControllerMessage")) {
                String actionName = jsonObject.getString("action").trim().toUpperCase();
                LOGGER.info("Session: " + session + ". Action Name: " + actionName);
                String mid = jsonObject.getString("mid");
                ActionExecutor actionExecutor = getClientExecutorFactory(actionName);
                String errorMessage = actionExecutor.execute(actionName, jsonObject, session);
                if (errorMessage.equalsIgnoreCase("void"))
                    return;
                try {
                    JSONObject responseJsonObject = new JSONObject();
                    responseJsonObject.put("responseEntity", errorMessage);
                    responseJsonObject.put("Request-URI", "/api/TapController/v1/TapControllerResponse");
                    responseJsonObject.put("mid", mid);
                    TextMessage textMessage = new TextMessage(responseJsonObject.toString());
                    LOGGER.info("Session: " + session + ". Send: " + responseJsonObject);
                    session.sendMessage(textMessage);
                } catch (Exception e1) {
                    LOGGER.error("Session: " + session + ". Send error: " + e1.getMessage());
                }
            }
        }
    }

    final static int ANDROID_WEBEX_LOGIN = 1;
    final static int ANDROID_WEBEX_CALL = 2;
    final static int ANDROID_WEBEX_LOGOUT = 3;
    final static int ANDROID_WEBEX_ANSWER = 4;
    final static int ANDROID_WEBEX_DECLINE = 5;
    final static int ANDROID_WEBEX_CLEAN_UP = 6;
    final static int ANDROID_WEBEX_END_CALL = 7;
    final static int ANDROID_TEAMS_LOGIN = 8;
    final static int ANDROID_TEAMS_LOGOUT = 9;
    final static int ANDROID_TEAMS_MUTE = 10;
    final static int ANDROID_TEAMS_UNMUTE = 11;

    public static JSONObject getJSONPropertiesByAction(int actionCode) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mid", "mid_value");
        jsonObject.put("Request-URI", "/api/TapController/v1/TapControllerMessage");
        switch (actionCode) {
            case ANDROID_WEBEX_LOGIN:
                jsonObject.put("action", "webex_login");
                break;
            case ANDROID_WEBEX_CALL:
                jsonObject.put("action", "webex_call");
                jsonObject.put("conversionId", "ABC");
                break;
            case ANDROID_WEBEX_LOGOUT:
                jsonObject.put("action", "webex_logout");
                break;
            case ANDROID_WEBEX_ANSWER:
                jsonObject.put("action", "webex_answer");
                jsonObject.put("conversionId", "ABC");
                break;
            case ANDROID_WEBEX_DECLINE:
                jsonObject.put("action", "webex_decline");
                jsonObject.put("conversionId", "ABC");
                break;
            case ANDROID_WEBEX_CLEAN_UP:
                jsonObject.put("action", "webex_clean_up");
                break;
            case ANDROID_WEBEX_END_CALL:
                jsonObject.put("action", "webex_end_call");
                jsonObject.put("conversionId", "ABC");
                break;
            case ANDROID_TEAMS_LOGIN:
                jsonObject.put("action", "teams_login");
                break;
            case ANDROID_TEAMS_LOGOUT:
                jsonObject.put("action", "teams_logout");
                break;
            case ANDROID_TEAMS_MUTE:
                jsonObject.put("action", "teams_call_mute");
                jsonObject.put("conversionId", "ABC");
                break;
            case ANDROID_TEAMS_UNMUTE:
                jsonObject.put("action", "teams_call_unmute");
                jsonObject.put("conversionId", "ABC");
                break;
        }
        return jsonObject;
    }


    public static JSONObject getJSONPropertiesByAction(String actionCode) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("mid", "mid_value");
        jsonObject.put("Request-URI", "/api/TapController/v1/TapControllerMessage");
        jsonObject.put("action", actionCode);
        switch (actionCode) {
            case "ANDROID_WEBEX_LOGIN":
                jsonObject.put("user", "QA-user35@tekvizion.com"); // user logging in
                jsonObject.put("password", "P@ssw0rd!@");
                break;
            case "ANDROID_WEBEX_CALL":
                jsonObject.put("user", "QA-user36@tekvizion.com"); // user to be called
                jsonObject.put("conversionId", "ABC");
                break;
            default:
                jsonObject.put("conversionId", "ABC");
        }
        return jsonObject;
    }

    public static ActionExecutor getClientExecutorFactory(String action) {
        System.out.println("TapControllerClient.getClientExecutor(): action = " + action);
        if (action.startsWith("TEAMS")) return new TeamsAndroidClientExecutor();
        return new WebexAndroidClientExecutor();
    }
}