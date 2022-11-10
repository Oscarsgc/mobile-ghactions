package com.tekvizion.onpoint;

import java.net.MalformedURLException;

import com.tekvizion.onpoint.mobile.model.iOS.AutomatedIosDevice;
import com.tekvizion.onpoint.tap.controller.iosTapControllerClient;

import org.json.JSONObject;

public class App {

    private static AutomatedIosDevice simulator;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        simulator = new AutomatedIosDevice("iPhone 14 Pro Max", "iPhone 8", "XCUITest", "iOS",
                "16.0", "E219F748-DB7A-4DE8-9882-CE05708698D7", "http://localhost:4723",
                "9725987015", "P@ssw0rd!@", "false", "8100");
        simulatorTest();
    }

    private static JSONObject setInitialParameters() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("Request-URI", "/api/TapController/v1/TapControllerMessage");
        jsonObject.put("mid", "mid_value");
        return jsonObject;
    }

    private static JSONObject clickTab(String originTab, String clickElement, String destinationTab) {
        JSONObject callObject = setInitialParameters();
        callObject.put("action", "CLICK_TAB");
        callObject.put("originTab", originTab);
        callObject.put("clickElement", clickElement);
        callObject.put("destinationTab", destinationTab);
        return callObject;
    }

    private static void simulatorTest() throws InterruptedException {
        simulator.initialize();
        System.out.println("----------------------------------");
        Thread.sleep(3000);
        iosTapControllerClient simTap = new iosTapControllerClient(simulator);
        simTap.connect(clickTab("Tab 1 page", "ellipse Tab 2", "Tab 2 page"));
        Thread.sleep(5000);
        simTap.connect(clickTab("Tab 2 page", "triangle Tab 1", "Tab 1 page"));
        Thread.sleep(5000);
        simTap.connect(clickTab("Tab 1 page", "ellipse Tab 2", "Tab 2 page"));
        Thread.sleep(5000);
        System.out.println("==================================");
        simulator.tearDown();
    }
}
