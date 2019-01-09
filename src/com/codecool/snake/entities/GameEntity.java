package com.codecool.snake.entities;

import com.codecool.snake.Globals;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.image.ImageView;

// The base class for every game entity.
public abstract class GameEntity extends ImageView {

    protected GameEntity() {
        Globals.getInstance().display.add(this);
    }

    public void destroy() {
        Globals.getInstance().display.remove(this);
    }

    public Vec2d getPosition() {
        return new Vec2d(getX(), getY());
    }

    public void setPosition(Vec2d pos) {
        setX(pos.x);
        setY(pos.y);
    }

    public boolean isOutOfBounds(double imageHeight, double imageWidth) {
        if (getX() > Globals.WINDOW_WIDTH-imageWidth || getX() < 0 ||
            getY() > Globals.WINDOW_HEIGHT-imageHeight || getY() < 0) {
            return true;
        }
        return false;
    }
}
