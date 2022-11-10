package com.tekvizion.onpoint.mobile.model.iOS;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class LogClient extends WebSocketClient {

    public List<String> logs = new ArrayList<String>();

    public LogClient(URI serverURI) {
        super(serverURI);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("WEBSOCKET OPENED");
    }

    @Override
    public void onMessage(String message) {
        // System.out.println(message);
        this.logs.add(message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        // File logFile = new File("/Users/oscar_sgc/Desktop/exported" + ".txt");
        // PrintWriter log_file_writer;
        // try {
        // log_file_writer = new PrintWriter(logFile);
        // log_file_writer.println(this.logs);
        // log_file_writer.flush();
        // } catch (FileNotFoundException e) {
        // // TODO Auto-generated catch block
        // System.out.println("Error: " + e.toString());
        // e.printStackTrace();
        // }
        // System.out.println("Connection closed, log streaming has stopped");
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
        // if the error is fatal then onClose will be called additionally
    }
}