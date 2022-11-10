package com.tekvizion.onpoint.tap.controller;

import com.tekvizion.onpoint.mobile.model.WebSocketSessionMock;

import org.json.JSONObject;

public abstract class ActionExecutor {
    protected Process process = null;
    protected static String userDir = "user.home";

    public abstract String execute(String actionName, JSONObject jsonObject, WebSocketSessionMock session);
}
