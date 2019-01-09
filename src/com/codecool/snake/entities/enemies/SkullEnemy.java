package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.codecool.snake.entities.snakes.SnakeLaser;
import com.sun.javafx.geom.Vec2d;
import javafx.geometry.Point2D;

import java.util.List;
import java.util.Random;


public class SkullEnemy extends Enemy implements Animatable {

    private Point2D heading;
    private static Random rnd = new Random();
    private SnakeHead snakeHead;
    private int stepsToDie = 400;
    private boolean isJustCreated = true;

    public SkullEnemy(List<Snake> snakes) {
        super(20);
        snakeHead = snakes.get((int)(Math.random()*snakes.size())).getHead();
        setImage(Globals.getInstance().getImage("SkullEnemy"));
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
        heading = getHeading ();
    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }

        if (snakeHead.getY()-getY() > 0){
            setY(getY() - getHeading().getY());
            setX(getX() + getHeading().getX());
        } else {
            setY(getY() + getHeading().getY());
            setX(getX() - getHeading().getX());
        }
        stepsToDie--;
        if (stepsToDie == 0) destroy();
        if (super.isJustCreated) {
            super.isJustCreated = false;
        }
    }

    private Point2D getHeading () {
        double distanceToHeadX = snakeHead.getX()-getX();
        double distanceToHeadY = snakeHead.getY()-getY();
        double directionToHead = (18.25*(Math.PI))*Math.atan(distanceToHeadX/distanceToHeadY);
        if (snakeHead.getY()-getY() > 0) {
            setRotate(directionToHead);
        } else {
            setRotate(directionToHead-180);
        }
        int speed = 1;
        return Utils.directionToVector(directionToHead, speed);
    }
}
