package com.mws.guessgame;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 * modified by Markus Schäffter, 2024
 */

/*
 * userInput per Konsole oder auf "Website"?
 * */
public class ClientApp extends Application {

    private Parent createContent() {
        Controller controller = new RemoteController();
        return new View(controller);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    public static class Launcher {
        public static void main(String[] args) {
            Application.launch(ClientApp.class);
        }
    }
}
