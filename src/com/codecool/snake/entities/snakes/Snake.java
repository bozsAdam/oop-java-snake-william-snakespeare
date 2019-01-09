package com.codecool.snake.entities.snakes;

import com.codecool.snake.Control;
import com.codecool.snake.DelayedModificationList;
import com.codecool.snake.Globals;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.eventhandler.InputHandler;

import com.sun.javafx.geom.Vec2d;
import javafx.scene.input.KeyCode;


public class Snake implements Animatable {
    private static int snakeCount = 0;
    private Control control;
    private int playerId;
    private static final float speed = 2;
    private int health = 100;

    public SnakeHead getHead() {
        return head;
    }

    private SnakeHead head;
    private DelayedModificationList<GameEntity> body;
    private boolean isDead;
    private boolean charged = true;
    private boolean superCharged = false;
    private Integer chargeDelay = 0;
    private Integer superChargeDelay = 0;

    public int getPlayerId() {
        return playerId;
    }


    public Snake(Vec2d position) {
        snakeCount ++;
        playerId = snakeCount;
        head = new SnakeHead(this, position);
        body = new DelayedModificationList<>();
        this.isDead = false;

        addPart(4);
    }

    public void step() {
        SnakeControl turnDir = getUserInput();
        head.updateRotation(turnDir, speed);

        updateSnakeBodyHistory();
        checkForSnakeCondition();

        body.doPendingModifications();
    }

    private SnakeControl getUserInput() {
//        SnakeControl turnDir = SnakeControl.INVALID;
//        if (playerId == 1) {
//            if (InputHandler.getInstance().isKeyPressed(KeyCode.LEFT)) turnDir = SnakeControl.TURN_LEFT;
//            if (InputHandler.getInstance().isKeyPressed(KeyCode.RIGHT)) turnDir = SnakeControl.TURN_RIGHT;
//        } else if (playerId == 2) {
//            if (InputHandler.getInstance().isKeyPressed(KeyCode.A)) turnDir = SnakeControl.TURN_LEFT;
//            if (InputHandler.getInstance().isKeyPressed(KeyCode.D)) turnDir = SnakeControl.TURN_RIGHT;
//        }
//        return turnDir;
        SnakeControl turnDir = SnakeControl.INVALID;
        if (InputHandler.getInstance().isKeyPressed(control.getLeftControl())) turnDir = SnakeControl.TURN_LEFT;
        if (InputHandler.getInstance().isKeyPressed(control.getRightControl())) turnDir = SnakeControl.TURN_RIGHT;
        if(InputHandler.getInstance().isKeyPressed(control.getShootControl())){
            shoot();
        }
        readyCheck();
        chargeDelay++;
        return turnDir;
    }

    public void addPart(int numParts) {
        GameEntity parent = getLastPart();
        Vec2d position = parent.getPosition();

        for (int i = 0; i < numParts; i++) {
            SnakeBody newBodyPart = new SnakeBody(position, this);
            body.add(newBodyPart);
        }
        Globals.getInstance().display.updateSnakeHeadDrawPosition(head);
    }

    public void changeHealth(int diff) {
        health += diff;
    }

    private void checkForSnakeCondition() {
        if (this.head.isOutOfBounds() || health <= 0) {
            System.out.println("Im ded");
            this.setDead(true);
//            Globals.getInstance().stopGame();
        }
    }

    private void updateSnakeBodyHistory() {
        GameEntity prev = head;
        for(GameEntity currentPart : body.getList()) {
            currentPart.setPosition(prev.getPosition());
            prev = currentPart;
        }
    }

    private GameEntity getLastPart() {
        GameEntity result = body.getLast();

        if(result != null) return result;
        return head;
    }

    private void shoot(){
        if(charged){
            new SnakeLaser(getHead());
            charged = false;
            chargeDelay = 0;
        }
    }

    private void readyCheck(){
        if(chargeDelay>100){
            charged = true;
        }
    }

    public void setControl(Control control) {
        this.control = control;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public boolean isDead() {
        return isDead;
    }

    public void destroy() {
        this.head.destroy();
        for (GameEntity bodypart: this.body.getList()
        ) {
            bodypart.destroy();
        }
    }
}
