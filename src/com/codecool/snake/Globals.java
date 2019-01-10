package com.codecool.snake;

import com.codecool.snake.resources.Resources;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

// class for holding all static stuff
public class Globals {
    private static Globals instance = null;

    public static final double WINDOW_WIDTH = 1000;
    public static final double WINDOW_HEIGHT = 700;

    public Display display;
    public Game game;

    private GameLoop gameLoop;
    private Resources resources;


    public static Globals getInstance() {
        if(instance == null) instance = new Globals();
        return instance;
    }

    public void setGameLoop(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    public void setupResources() {
        resources = new Resources();
        resources.addImage("SnakeHead", new Image("snake_head.png"));
        resources.addImage("SnakeHead2", new Image("snake_head2.png"));
        resources.addImage("SnakeBody", new Image("snake_body.png"));
        resources.addImage("SnakeBody2", new Image("snake_body2.png"));
        resources.addImage("SimpleEnemy", new Image("simple_enemy.png"));
        resources.addImage("SkullEnemy", new Image("skull_enemy.png"));
        resources.addImage("BombEnemy", new Image("bomb_enemy.png"));
        resources.addImage("BombExplosion", new Image("bomb_explosion.png"));
        resources.addImage("PowerUpBerry", new Image("powerup_berry.png"));
        resources.addImage("SnakeLaser1", new Image("laser1.png"));
        resources.addImage("SnakeLaser2", new Image("laser2.png"));
        resources.addImage("background", new Image("background.png"));
    }

    public Image getImage(String name) { return resources.getImage(name); }

    public void startGame() { gameLoop.start(); }

    public void stopGame() { gameLoop.stop(); }

    private Globals() {
        // singleton needs the class to have private constructor
    }
}
