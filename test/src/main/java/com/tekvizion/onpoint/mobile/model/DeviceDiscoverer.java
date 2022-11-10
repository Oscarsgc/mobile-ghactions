package com.tekvizion.onpoint.mobile.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.tekvizion.onpoint.mobile.model.android.AutomatedAndroidDevice;
import com.tekvizion.onpoint.mobile.model.iOS.AutomatedIosDevice;

public class DeviceDiscoverer {
    public static List<AutomatedMobileDevice> availableDevices() throws MalformedURLException {
        List<AutomatedMobileDevice> devices = new ArrayList<AutomatedMobileDevice>();
        devices.addAll(discoverIOSDevices());
        devices.addAll(discoverAndroidDevices());
        return devices;
    }

    public static List<AutomatedAndroidDevice> discoverAndroidDevices() {
        List<AutomatedAndroidDevice> androidDevices = new ArrayList<>();
        ProcessBuilder processBuilder = new ProcessBuilder().command("cmd.exe", "/c", "adb", "devices", "-l");
        Pattern adbDeviceListLine = Pattern.compile("^(\\w+)\\s+device product:\\w+ model:(\\w+) device:\\w+ transport_id:\\d+$"); // group 1 = serial number, group 2 = model
        // Pattern adbGetPropValuePattern = Pattern.compile("^\\[([\\w,.]+)]:\\[([^\\s]+)]"); // group 1 = property name, group 2 = property value
        List<String> udidFoundList = new ArrayList<>();

        try {
            Process runningCommandProcess = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(runningCommandProcess.getInputStream()));
            reader.lines().forEach(line -> {
                Matcher adbDeviceLineMatches = adbDeviceListLine.matcher(line);
                if (adbDeviceLineMatches.matches()) {
                    udidFoundList.add(adbDeviceLineMatches.group(1));
                }
            });

            runningCommandProcess.destroy();

            for (String udidFound : udidFoundList) {
                runningCommandProcess = new ProcessBuilder().command("cmd.exe", "/c", "adb", "-s", udidFound, "shell",
                        "getprop", "ro.vendor.build.version.release]").start();
                // process = new ProcessBuilder().command("cmd.exe", "/c", "adb", "-s",
                // udidFound.getValue(), "shell", "getprop", "|", "findstr", "\"ro.boot.em.model
                // ro.vendor.build.version.release]\"").start();
                BufferedReader propertyReader = new BufferedReader(
                        new InputStreamReader(runningCommandProcess.getInputStream()));
                propertyReader.lines().forEach(line -> {
                    // Matcher adbPropertyMatches = adbGetPropValuePattern.matcher(line);
                    try {
                        androidDevices.add(new AutomatedAndroidDevice(udidFound, line));
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                });
                runningCommandProcess.destroy();
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return androidDevices;
    }

    public static List<AutomatedIosDevice> discoverIOSDevices() {
        List<AutomatedIosDevice> iosDevices = new ArrayList<>();
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("xcrun", "xctrace", "list", "devices");
        Process process;
        try {
            process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                if (!line.contains("=") && !line.contains("Mac") && !line.contains("Simulator") && !line.isEmpty()) {
                    ArrayList<String> deviceData = new ArrayList<String>();
                    deviceData.add(line.split(" \\(", 2)[0]);
                    Matcher matcher = Pattern.compile("\\((.*?)\\)").matcher(line);
                    while (matcher.find()) {
                        deviceData.add(matcher.group(1));
                    }
                    AutomatedIosDevice device = new AutomatedIosDevice(deviceData.get(0),
                            deviceData.get(0),
                            "XCUITest",
                            "iOS",
                            deviceData.get(1),
                            deviceData.get(2),
                            "http://localhost:4723/wd/hub");
                    device.addDesiredCapability(
                            new DesiredCapability(DesiredCapabilityOption.BUNDLE_ID, "com.cisco.squared"));
                    device.addDesiredCapability(new DesiredCapability(DesiredCapabilityOption.XCODE_ORG, "97C4K3R6W5"));
                    device.addDesiredCapability(
                            new DesiredCapability(DesiredCapabilityOption.XCODE_SIGN, "6TF655T33J"));
                    device.addDesiredCapability(new DesiredCapability(DesiredCapabilityOption.UPDATED_BUNDLE,
                            "io.appium.WebDriverAgentRunner"));
                    iosDevices.add(device);
                }
            }
            return iosDevices;
        } catch (IOException e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return null;
        }
    }
}
