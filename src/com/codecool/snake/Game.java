package com.codecool.snake;

import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.codecool.snake.entities.snakes.SnakeLaser;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.Scene;
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
        spawnEnemies(60);
        spawnPowerUps(30);
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

    public void spawnLaser(int numberOfLaser,SnakeHead snakeHead) {
        for(int i = 0; i < numberOfLaser; ++i) new SnakeLaser(snakeHead);
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
