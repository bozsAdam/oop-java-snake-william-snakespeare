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

    private static Stage primaryStage;
    private Game game;
    private int numberOfPlayers;

    public Menu() {}

    public Menu(Stage primaryStage) {
        this.primaryStage = primaryStage;
        System.out.println(primaryStage);
    }

    public void menuPopUp () {
        setVisible(true);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION); // always on top?
        alert.setTitle("Single- or Multiplayer Mode:");
        alert.setHeaderText("Do you want to play alone or with a friend/foe?");
        ButtonType buttonTypeOne = new ButtonType("Single Player");
        ButtonType buttonTypeTwo = new ButtonType("Two Players");
        ButtonType buttonTypeCancel = new ButtonType("Quit", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            numberOfPlayers = 1;
            game = new Game(1);
            setScene(game);
        } else if (result.get() == buttonTypeTwo) {
            numberOfPlayers = 2;
            game = new Game(2);
            setScene(game);
        } else {
            System.exit(0);
        }
        setVisible(false);
    }
    // object pool
    public void setScene(Game game) {
        Scene mainScene = new Scene(game, Globals.WINDOW_WIDTH, Globals.WINDOW_HEIGHT);
        game.createRestartButton(this);
        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(mainScene);
        primaryStage.show();
        game.start();
        }

    public void gameOver () {
        System.out.println(primaryStage);
        ButtonType yes = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "\"Good friend for Jesus sake forbeare, " +
                "\nTo dig the dust enclosed here. " +
                "\nBlessed be the man that spares these stones, " +
                "\nAnd cursed be he that moves my bones.\"", yes);
        alert.setHeaderText("Game Over");

        Platform.runLater(() -> {
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == yes) {
                menuPopUp();
                //Menu menu2 = new Menu();
                //Main.restart(menu2, primaryStage);
            }

        });
    }


}
