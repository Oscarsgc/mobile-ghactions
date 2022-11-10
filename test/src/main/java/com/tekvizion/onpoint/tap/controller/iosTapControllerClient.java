
package com.tekvizion.onpoint.tap.controller;

import com.tekvizion.onpoint.mobile.model.iOS.AutomatedIosDevice;
import com.tekvizion.onpoint.mobile.iOS.tekvizion.controller.TekvizionIosClientExecutor;
import com.tekvizion.onpoint.mobile.model.WebSocketSessionMock;

import org.json.JSONObject;
import org.springframework.web.socket.TextMessage;
import org.apache.logging.log4j.*;

public class iosTapControllerClient {
    private final static Logger LOGGER = LogManager.getLogger(TapControllerClient.class);
    private AutomatedIosDevice iosDevice;
    private ActionExecutor actionExecutor = null;

    public iosTapControllerClient(AutomatedIosDevice device) {
        this.iosDevice = device;
        this.actionExecutor = getClientExecutorFactory();
    }

    public void connect(JSONObject incomingMessage) {
        JSONObject jsonObject = incomingMessage;
        WebSocketSessionMock session = new WebSocketSessionMock();
        if (jsonObject.getString("Request-URI").equalsIgnoreCase("/api/TapController/v1/TapControllerMessage")) {
            String actionName = jsonObject.getString("action").trim().toUpperCase();
            LOGGER.info("Session: " + session + ". Action Name: " + actionName);
            String mid = jsonObject.getString("mid");
            String errorMessage = this.actionExecutor.execute(actionName, jsonObject, session);
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

    public ActionExecutor getClientExecutorFactory() {
        // return new WebexIosClientExecutor(this.iosDevice);
        return new TekvizionIosClientExecutor(this.iosDevice);
    }
}
