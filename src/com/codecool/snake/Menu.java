package com.codecool.snake;

import com.codecool.snake.entities.snakes.Snake;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Optional;

import static javafx.scene.control.Alert.AlertType.CONFIRMATION;

public class Menu extends Pane {

    private Stage primaryStage;
    private Game game;

    public Menu(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void menuPopUp () {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Single- or Multiplayer Mode:");
        alert.setHeaderText("Do you want to play alone or with a friend/foe!");
        ButtonType buttonTypeOne = new ButtonType("Single Player");
        ButtonType buttonTypeTwo = new ButtonType("Two Players");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            game = new Game(1);
            setScene(game);
        } else if (result.get() == buttonTypeTwo) {
            game = new Game(2);
            setScene(game);
        } else {
            System.exit(0);
        }
    }
    public void setScene(Game game) {
        Scene mainScene = new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);
        game.createRestartButton(this);
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(mainScene);
        primaryStage.show();
        game.start();
        }


}
