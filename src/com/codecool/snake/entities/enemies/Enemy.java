package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.SnakeHead;
import com.codecool.snake.entities.snakes.SnakeLaser;

public abstract class Enemy extends GameEntity implements Interactable {

    private int damage;

    public boolean isJustCreated() {
        return isJustCreated;
    }

    boolean isJustCreated = true;

    public Enemy(int damage) {
        this.damage = damage;
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
