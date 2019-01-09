package com.codecool.snake.entities.snakes;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import javafx.geometry.Point2D;

import java.util.Random;

public class SnakeLaser extends GameEntity implements Animatable, Interactable {

    private Point2D heading;
    private SnakeHead snakeHead;

    public SnakeLaser(SnakeHead snakeHead) {
        this.snakeHead = snakeHead;
        setImage(Globals.getInstance().getImage("SnakeLaser"));
        setX(snakeHead.getX());
        setY(snakeHead.getY());
        setRotate(snakeHead.getRotate());
        int speed = 3;
        heading = Utils.directionToVector(snakeHead.getRotate(), speed);
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        setX(getX() + heading.getX());
        setY(getY() + heading.getY());
    }

    @Override
    public void apply(GameEntity entity) {
        //if you wold wanna make something happen to this after contacting with an entity than here you can do it}
    }

    @Override
    public String getMessage() {
        //message you want to use later on to show dealt damage or something
        return ("");
    }
}