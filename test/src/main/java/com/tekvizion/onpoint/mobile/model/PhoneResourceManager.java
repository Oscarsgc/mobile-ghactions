package com.tekvizion.onpoint.mobile.model;

import com.tekvizion.onpoint.mobile.model.android.AutomatedAndroidDevice;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneResourceManager {

    public final static Map<String, AutomatedMobileDevice> DEVICE_REGISTRY = new HashMap<>();
    public final static Integer initialPort = 8100;
    public final static Integer finalPort = 8299;

    public static AutomatedMobileDevice getDeviceByUDID(String udid) throws Exception {
        if (DEVICE_REGISTRY.containsKey(udid))
            return DEVICE_REGISTRY.get(udid);
        throw new Exception("Phone UDID not found");
    }

    public static void initializeAllRegisteredDevices() {
        if (!DEVICE_REGISTRY.containsKey("RF8RB20BVAL")) {
            try {
                DEVICE_REGISTRY.put("RF8RB20BVAL",
                        new AutomatedAndroidDevice(
                                "Android",
                                "RF8RB20BVAL",
                                "UIAutomator2",
                                "Android",
                                "11",
                                "RF8RB20BVAL",
                                "http://127.0.0.1:4723/wd/hub"));
//                DEVICE_REGISTRY.put("15151JEC207036",
//                        new AutomatedAndroidDevice(
//                                "Android",
//                                "15151JEC207036",
//                                "UIAutomator2",
//                                "Android",
//                                "11",
//                                "15151JEC207036",
//                                "http://127.0.0.1:4723/wd/hub"));
//                DEVICE_REGISTRY.put("emulator-5554",
//                        new AutomatedAndroidDevice(
//                                "Android",
//                                "emulator-5554",
//                                "UIAutomator2",
//                                "Android",
//                                "12",
//                                "emulator-5554",
//                                "http://127.0.0.1:4723/wd/hub"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        for (AutomatedMobileDevice device : DEVICE_REGISTRY.values()) {
            for (Integer port = initialPort; port < finalPort; port++) {
                if (portIsFreeToUse(port.toString())) {
                    device.automationPort = port.toString();
                    break;
                }
            }
            device.initialize();
        }
    }

    public static boolean portIsFreeToUse(String port) {
        try (Socket ignored = new Socket("localhost", Integer.parseInt(port))) {
            System.out.println("Port " + port + " is not available");
            return false;
        } catch (IOException ignored) {
            System.out.println("Port " + port + " is available");
            return true;
        }
    }

    public static void discoverAndRegisterDevices() {
        initializeAllRegisteredDevices();
    }

    public static void registerDiscoveredDevices(List<AutomatedMobileDevice> discoveredDevices) {
        for (AutomatedMobileDevice device : discoveredDevices) {
            if (!DEVICE_REGISTRY.containsKey(device.getUdid()))
                DEVICE_REGISTRY.put(device.getUdid(), device);
        }
    }

    public static void initializeDevice(AutomatedMobileDevice device) {
        if (DEVICE_REGISTRY.containsKey(device.getUdid()))
            DEVICE_REGISTRY.get(device.getUdid()).initialize();
    }

    public static AutomatedMobileDevice getRegisteredDevice(AutomatedMobileDevice device) throws Exception {
        if (DEVICE_REGISTRY.containsKey(device.getUdid()))
            return DEVICE_REGISTRY.get(device.getUdid());
        throw new Exception("Phone UDID not found");
    }

}
