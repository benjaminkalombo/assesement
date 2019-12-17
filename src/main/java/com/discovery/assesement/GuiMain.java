package com.discovery.assesement;

import com.discovery.assesement.controller.AppController;
import javafx.application.Application;
import javafx.stage.*;

public class GuiMain extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        AppController ac = AppController.getInstance();
        ac.setStage(primaryStage);
        ac.runWithStartingScene();
    }

}