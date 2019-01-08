package com.codecool.snake;

import javafx.scene.input.KeyCode;

public class Control {
    private static int controlCount = 0;
    private String name;
    private KeyCode leftControl;
    private KeyCode rightControl;

    public Control(KeyCode leftControl, KeyCode rightControl) {
        controlCount++;
        this.name = "Player" + controlCount;
        this.leftControl = leftControl;
        this.rightControl = rightControl;
    }

    public KeyCode getLeftControl() {
        return leftControl;
    }

    public KeyCode getRightControl() {
        return rightControl;
    }
}