package com.codecool.snake.entities.snakes;

import com.codecool.snake.Control;
import com.codecool.snake.DelayedModificationList;
import com.codecool.snake.Game;
import com.codecool.snake.Globals;
import com.codecool.snake.PlayerImages;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.eventhandler.InputHandler;
import com.sun.javafx.geom.Vec2d;

public class Snake implements Animatable {
    private static int snakeCount = 0;
    private Control control;
    private int playerId;
    protected static final float speed = 2;
    protected int health;
    private SnakeHead head;
    protected DelayedModificationList<GameEntity> body;
    private boolean charged = true;
    private boolean superCharged = false;
    private Integer chargeDelay = 0;
    private Integer superChargeDelay = 0;
    private String bodyImage;
    private String headImage;
    private String laserImage;
    double imageHeight;
    double imageWidth;
    private int step;
    public static int snake1Score;
    public static int snake2Score;

    public int getPlayerId() {
        return playerId;
    }

    public static int getSnake1Score() {
        return snake1Score;
    }

    public static int getSnake2Score() {
        return snake2Score;
    }

    public void setSnakeScoreBy(int scoreChangeBy) {
        if (playerId == 1) {
            this.snake1Score += scoreChangeBy;
        }
        else {
            this.snake1Score += scoreChangeBy;
        }
    }


    public SnakeHead getHead() {
        return head;
    }

    public Snake(Vec2d position, Control control, PlayerImages playerImages) {
        snakeCount ++;
        playerId = snakeCount;
        this.control = control;
        this.bodyImage = playerImages.getBodyImage();
        this.headImage = playerImages.getHeadImage();
        this.laserImage = playerImages.getLaserImage();
        this.health = 100;
        head = new SnakeHead(this, position);
        body = new DelayedModificationList<>();
        addPart(4);
        this.imageHeight = Globals.getInstance().getImage(getHeadImage()).getHeight();
        this.imageWidth = Globals.getInstance().getImage(getHeadImage()).getWidth();
    }

    public void step() {
        SnakeControl turnDir = getUserInput();
        head.updateRotation(turnDir, speed);
        updateSnakeBodyHistory();
        checkForSnakeCondition();
        body.doPendingModifications();
    }

    private SnakeControl getUserInput() {
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
        health -= diff;
    }

    private void checkForSnakeCondition() {
        if (this.head.isOutOfBounds(imageHeight, imageWidth) || health <= 0) {
            System.out.println("Im ded");
            this.health = 0;
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
            new SnakeLaser(this);
            charged = false;
            chargeDelay = 0;
        }
    }

    private void readyCheck(){
        if(chargeDelay>100){
            charged = true;
        }
    }

    public boolean isDead() {
        if (this.health <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public void destroy() {
        this.head.destroy();
        for (GameEntity bodypart: this.body.getList()
        ) {
            bodypart.destroy();
        }
    }

    public String getBodyImage() {
        return bodyImage;
    }

    public String getHeadImage() {
        return headImage;
    }

    public String getLaserImage() {
        return laserImage;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }


}
