package com.codecool.snake.entities.enemies;

import com.codecool.snake.entities.GameEntity;

public abstract class Enemy extends GameEntity{

    private int damage;

    public Enemy(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }


}
