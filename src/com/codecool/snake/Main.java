package com.codecool.snake;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    Menu menu = null;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        menu = new Menu(primaryStage);
        menu.menuPopUp();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Exiting..");
    }


    public static void restart(Menu menu, Stage primaryStage) {
        menu.menuPopUp();
    }
}

