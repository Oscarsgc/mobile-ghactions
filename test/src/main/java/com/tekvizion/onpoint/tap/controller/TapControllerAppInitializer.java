package com.tekvizion.onpoint.tap.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("file:conf/tap-controller.properties")
public class TapControllerAppInitializer {

    public static final long WEBDRIVER_WAITING_TIME_20SEC = 20;
    @Value("${app.settings.webex.login.timer:60}")
    private int webexLoginTimer = 60;

    @Value("${app.settings.webex.logout.timer:30}")
    private int webexLogoutTimer = 30;

    @Value("${app.settings.webex.driver.waiting:30}")
    private int webexDriverWaitTime;

    @Value("${app.settings.webex.view.waiting:3}")
    private int webexViewWaitTime;

    @Value("${app.settings.webex.alert.waiting:3}")
    private int webexAlertWaitTime;

    @Value("${app.settings.webex.call.pause:5}")
    private int webexCallPause;

    @Value("${app.settings.webex.login.pause:5}")
    private int webexLoginPause;

    @Value("${app.settings.teams.login.timer:5}")
    private int teamsLoginTimer;

    @Value("${app.settings.teams.view.timer:5}")
    private int teamsViewTimer;

    public static int WEBEX_LOGIN_TIMER = 15;
    public static int WEBEX_LOGOUT_TIMER = 30;
    public static int WEBEX_DRIVER_WAITING = 30;
    public static int WEBEX_VIEW_WAITING = 3;
    public static int WEBEX_CALL_PAUSE = 5;
    public static int WEBEX_LOGIN_PAUSE = 5;
    public static int WEBEX_LOGIN_NEW = 10;
    public static int WEBEX_ALERT_WAITING = 1;

    public static int TEAMS_INIT_TIMER = 10;
    public static int TEAMS_LOGIN_TIMER = 15;
    public static int TEAMS_VIEW_TIMER = 3;
    public static int TEAMS_WEBVIEW_TIMER = 5;
    public static int TEAMS_INPUT_PAUSE_MS = 1000;

    @PostConstruct
    public void setup() {
        WEBEX_LOGIN_TIMER = webexLoginTimer;
        WEBEX_LOGOUT_TIMER = webexLogoutTimer;
        WEBEX_DRIVER_WAITING = webexDriverWaitTime;
        WEBEX_VIEW_WAITING = webexViewWaitTime;
        WEBEX_CALL_PAUSE = webexCallPause;
        WEBEX_ALERT_WAITING = webexAlertWaitTime;
        WEBEX_LOGIN_PAUSE = webexLoginPause;
        TEAMS_LOGIN_TIMER = teamsLoginTimer;
        TEAMS_VIEW_TIMER = teamsViewTimer;

    }
}