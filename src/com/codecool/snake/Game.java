package com.codecool.snake;

import com.codecool.snake.entities.enemies.BombEnemy;
import com.codecool.snake.entities.enemies.SimpleEnemy;
import com.codecool.snake.entities.enemies.SkullEnemy;
import com.codecool.snake.entities.powerups.SimplePowerUp;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.codecool.snake.entities.snakes.SnakeLaser;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Game extends Pane {
    private int numberOfPlayers;
    private List<Snake> snakes = new ArrayList<>();
    private GameTimer gameTimer = new GameTimer();
    private Map<String, Control> controls = new HashMap<>();
    private Map<String, String> snakeBodyImages = new HashMap<>();

    public Game(int numberOfPlayers) {
        Globals.getInstance().game = this;
        Globals.getInstance().display = new Display(this);
        Globals.getInstance().setupResources();
        this.numberOfPlayers = numberOfPlayers;
        controls.put("Player1", new Control(KeyCode.LEFT, KeyCode.RIGHT, KeyCode.K.SPACE));
        controls.put("Player2", new Control(KeyCode.A, KeyCode.D, KeyCode.Q));
        snakeBodyImages.put("Player1", "SnakeBody");
        snakeBodyImages.put("Player2", "SnakeBody2");
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
            String playerId = "Player" + (i + 1);
            Snake snake = new Snake(new Vec2d(500, 500 + i*-200), controls.get(playerId), snakeBodyImages.get(playerId));
            snakes.add(snake);
        }
    }

    public void spawnLaser(int numberOfLaser,SnakeHead snakeHead) {
        for(int i = 0; i < numberOfLaser; ++i) new SnakeLaser(snakeHead);
    }

    private void spawnEnemies(int numberOfEnemies) {
        for(int i = 0; i < numberOfEnemies; ++i) {
            if (i % 2 == 0) {
                new SimpleEnemy();
            } else {
                new SkullEnemy(snakes);
            }
        }
        for (int i = 0; i < 3; i++) {
            new BombEnemy();
        }
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
