package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.Utils;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.codecool.snake.entities.snakes.SnakeLaser;
import javafx.geometry.Point2D;

import java.util.Random;


public class BombEnemy extends Enemy implements Animatable, Interactable {

    private static Random rnd = new Random();
    private int stepsToDie = 40;
    private int stepsToExplode = 200;
    private boolean isExploded = false;
    private boolean isDamaged = false;

    public BombEnemy() {
        super(50);

        setImage(Globals.getInstance().getImage("BombEnemy"));
        setX(rnd.nextDouble() * Globals.WINDOW_WIDTH);
        setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);

        double direction = 360;
        setRotate(direction);

    }

    @Override
    public void step() {
        if (isOutOfBounds()) {
            destroy();
        }
        if (stepsToExplode == 0 && !isExploded ) {
            explode();
        }
        if (isExploded) {
            stepsToDie--;
        }
        if (stepsToDie == 0) {
            destroy();
        }
        if (super.isJustCreated) {
            super.isJustCreated = false;
        }
        stepsToExplode--;
    }

    private void explode () {
        setImage(Globals.getInstance().getImage("BombExplosion"));
        setX(getX() - 100);
        setY(getY() - 90);
        isExploded = true;
        this.setOpacity(0.8);
    }

    @Override
    public void apply(GameEntity entity) {
            if (!isExploded && !isDamaged) {
                if (entity instanceof SnakeHead || entity instanceof SnakeLaser) {
                    if (super.isJustCreated) {
                        destroy();
                        new BombEnemy();
                    } else {
                    explode();
                    }
                }
            } else if (!isDamaged) {
                if (entity instanceof SnakeHead || entity instanceof SnakeLaser) {
                    System.out.println(getMessage());
                    isDamaged = true;

                }
            } else {
                this.setDamage(0);
            }
        }

    @Override
    public String getMessage() {
        return (getDamage() + " damage");
    }
}
