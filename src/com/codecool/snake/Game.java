package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;


public class Game extends Pane {
    private int numberOfPlayers;
    private List<Snake> snakes = new ArrayList<>();
    private GameTimer gameTimer = new GameTimer();

    public Game(int numberOfPlayers) {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();
        this.numberOfPlayers = numberOfPlayers;
        startEventListener();
    }

    private void startEventListener () {
        // ha start lett akkor:
        init();
    }

    public void init() {
        spawnSnake();
        spawnEnemies(20);
        spawnPowerUps(20);
        GameLoop gameLoop = new GameLoop(snakes);
        Globals.getInstance().setGameLoop(gameLoop);
        gameTimer.setup(gameLoop::step);
        gameTimer.play();
    }

    public void start() {
        setupInputHandling();
        Globals.getInstance().startGame();
    }

    private void spawnSnake() {
        for (int i = 0; i < numberOfPlayers; i++) {
            snakes.add(new Snake(new Vec2d(500, 500 + i*-200)));
        }
    }

    public void restartButton() {
        Image restartImage = new Image(getClass().getResourceAsStream("/restart_button.png"));
        ImageView imageView = new ImageView(restartImage);
        imageView.setFitWidth(66);
        imageView.setFitHeight(66);
        Button restartButton = new Button("RESTART");
        restartButton.setGraphic(imageView);
        restartButton.setContentDisplay(ContentDisplay.TOP);
        restartButton.setLayoutX(1100);
        restartButton.setLayoutY(20);
        restartButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                    //List<GameEntity> gameObjs = Globals.getInstance().display.getObjectList();
                    //for (GameEntity entity: gameObjs) {
                     //   entity.destroy();
                for (Snake snake : snakes) {
                      snake.getHead().destroy();
                    snake.getBody().clear();
                }
                Globals.getInstance().stopGame();

                init();
                start();
                Globals.getInstance().startGame();
                }});
                //for (Snake snake : snakes) {
                  //  snake.getBody().clear();
                    //snake.getBody().clear();


        getChildren().add(restartButton);
    }

    private void spawnEnemies(int numberOfEnemies) {
        for(int i = 0; i < numberOfEnemies; ++i) new SimpleEnemy();
    }

    private void spawnPowerUps(int numberOfPowerUps) {
        for(int i = 0; i < numberOfPowerUps; ++i) new SimplePowerUp();
    }

    private void setupInputHandling() {
        Scene scene = getScene();
        scene.setOnKeyPressed(event -> InputHandler.getInstance().setKeyPressed(event.getCode()));
        scene.setOnKeyReleased(event -> InputHandler.getInstance().setKeyReleased(event.getCode()));
    }
}
