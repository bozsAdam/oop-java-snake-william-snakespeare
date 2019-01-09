package com.codecool.snake;

import com.codecool.snake.entities.GameEntity;
import com.codecool.snake.entities.Animatable;
import com.codecool.snake.entities.Interactable;
import com.codecool.snake.entities.snakes.Snake;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameLoop extends Menu {
    private List<Snake> snakes;
    private boolean running = false;
    private int steps = 1;
    private int stepsToSpawnBomb = (int) Math.random()*150+150;
    private int stepsToSpawnSkull = (int) Math.random()*150+150;
    private int stepsToSpawnSimple = (int) Math.random()*100+50;
    private int stepsToSpawnSimplePowerUp = (int) Math.random()*200+500;

    public GameLoop(List<Snake> snakes) { this.snakes = snakes; }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }

    public void step() {
        if(running) {
            int deadSnakeCount = 0;
            for (Snake snake: snakes
                 ) {
                if(!snake.isDead()) {
                    snake.step();
                } else {
                    deadSnakeCount++;
                    snake.destroy();
                }
            };
            //snake2.step();
            for (GameEntity gameObject : Globals.getInstance().display.getObjectList()) {
                if (gameObject instanceof Animatable) {
                    ((Animatable) gameObject).step();
                }
            }
            checkCollisions();
            if(deadSnakeCount == snakes.size()) {
                Globals.getInstance().stopGame();
                gameOver();
            }
            if (steps % stepsToSpawnBomb == 0) {
                Game.randomlySpawnBombEnemy();
            }
            if (steps % stepsToSpawnSkull == 0) {
                Game.randomlySpawnSkullEnemy(snakes);
            }
            if (steps % stepsToSpawnSimple == 0) {
                Game.randomlySpawnSimpleEnemy();
            }
            if (steps % stepsToSpawnSimplePowerUp == 0) {
                Game.randomlySpawnSimplePowerUp();
            }
            steps++;

        }
        Globals.getInstance().display.frameFinished();
    }

    private void checkCollisions() {
        List<GameEntity> gameObjs = Globals.getInstance().display.getObjectList();
        for (int idxToCheck = 0; idxToCheck < gameObjs.size(); ++idxToCheck) {
            GameEntity objToCheck = gameObjs.get(idxToCheck);
            if (objToCheck instanceof Interactable) {
                for (int otherObjIdx = idxToCheck + 1; otherObjIdx < gameObjs.size(); ++otherObjIdx) {
                    GameEntity otherObj = gameObjs.get(otherObjIdx);
                    if (otherObj instanceof Interactable){
                        if(objToCheck.getBoundsInParent().intersects(otherObj.getBoundsInParent())){
                            ((Interactable) objToCheck).apply(otherObj);
                            ((Interactable) otherObj).apply(objToCheck);
                        }
                    }
                }
            }
        }
    }


    }

