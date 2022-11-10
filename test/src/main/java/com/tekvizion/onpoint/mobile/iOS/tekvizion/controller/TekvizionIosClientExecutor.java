package com.tekvizion.onpoint.mobile.iOS.tekvizion.controller;

import com.tekvizion.onpoint.mobile.model.iOS.AutomatedIosDevice;
import com.tekvizion.onpoint.mobile.model.WebSocketSessionMock;
import com.tekvizion.onpoint.tap.controller.ActionExecutor;

import org.apache.logging.log4j.*;
import org.json.JSONObject;

public class TekvizionIosClientExecutor extends ActionExecutor {
    private final Logger LOGGER = LogManager.getLogger(TekvizionIosClientExecutor.class);
    AutomatedIosDevice iosDevice;
    String resultMessage;

    public TekvizionIosClientExecutor(AutomatedIosDevice iosDevice) {
        this.iosDevice = iosDevice;
    }

    @Override
    public String execute(String actionName, JSONObject jsonObject, WebSocketSessionMock session) {
        try {
            switch (actionName) {
                case "CLICK_TAB":
                    String originTab = jsonObject.getString("originTab").trim();
                    String clickElement = jsonObject.getString("clickElement").trim();
                    String destinationTab = jsonObject.getString("destinationTab").trim();
                    resultMessage = new ClickTabAction().click(this.iosDevice, originTab, clickElement, destinationTab);
                    break;
            }
        } catch (Exception e) {
            resultMessage = "Failed to execute action";
            LOGGER.error("Failed to execute action. Exception: " + e.getMessage());
        }
        return resultMessage;
    }
}
