package com.codecool.snake;

import javafx.scene.input.KeyCode;

public class Control {
    private static int controlCount = 0;
    private String name;
    private KeyCode leftControl;
    private KeyCode rightControl;
    private KeyCode shootControl;

    public Control(KeyCode leftControl, KeyCode rightControl, KeyCode shootControl) {
        controlCount++;
        this.name = "Player" + controlCount;
        this.leftControl = leftControl;
        this.rightControl = rightControl;
        this.shootControl = shootControl;
    }

    public KeyCode getLeftControl() {
        return leftControl;
    }

    public KeyCode getRightControl() {
        return rightControl;
    }

    public KeyCode getShootControl() {
        return shootControl;
    }
}