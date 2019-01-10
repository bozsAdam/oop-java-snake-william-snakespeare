package com.codecool.snake.entities.enemies;

import com.codecool.snake.Globals;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.codecool.snake.entities.snakes.SnakeLaser;

import java.util.Random;

public abstract class Enemy extends GameEntity implements Interactable {

    private int damage;
    double imageHeight;
    double imageWidth;
    private static Random rnd = new Random();

    public boolean isJustCreated() {
        return isJustCreated;
    }

    boolean isJustCreated = true;

    public Enemy(int damage, String image) {
        this.damage = damage;
        this.setImage(Globals.getInstance().getImage(image));
        this.imageHeight = Globals.getInstance().getImage(image).getHeight();
        this.imageWidth = Globals.getInstance().getImage(image).getWidth();
        this.setX(rnd.nextDouble() * Globals.GAME_WIDTH);
        this.setY(rnd.nextDouble() * Globals.WINDOW_HEIGHT);
    }

    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public void apply(GameEntity entity) {
            if (entity instanceof SnakeHead || entity instanceof SnakeLaser) {
                if (isJustCreated) {
                    destroy();
                    new BombEnemy();
                } else {
                    System.out.println(getMessage());
                    destroy();
            }
        }
    }

    @Override
    public String getMessage() {
        return (getDamage() + " damage");
    }


}
