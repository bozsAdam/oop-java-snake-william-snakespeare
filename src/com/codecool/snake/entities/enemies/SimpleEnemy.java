package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import java.util.Random;

import com.codecool.snake.entities.snakes.SnakeLaser;
import javafx.geometry.Point2D;



public class SimpleEnemy extends Enemy implements Animatable {

    private Point2D heading;
    private static Random rnd = new Random();
    private static String image = "SimpleEnemy";

    public SimpleEnemy() {
        super(10, image);

        double direction = rnd.nextDouble() * 360;
        setRotate(direction);

        int speed = 1;
        heading = Utils.directionToVector(direction, speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds(super.imageHeight, super.imageWidth)) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
        if (super.isJustCreated) {
            super.isJustCreated = false;
        }
    }
}
