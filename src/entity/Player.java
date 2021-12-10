package entity;

import main.GameControlHandler;
import main.Map;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Human {
    private int walkSpeed;
    private int posX;
    private int posY;
    private int energy;
    private GameControlHandler controlHandler;

    public Player(String name, GameControlHandler controlHandler) {
        setName(name);
        setControlHandler(controlHandler);
        loadSprite(name);
        playerInit();
    }

    public void playerInit() {
        this.setWalkSpeed(4);
        this.setPosX(0);
        this.setPosY(0);
        this.setEnergy(100);
        this.setDirection("down");
        this.setMaxActionFrame(3);
    }

    public void update() {
        if (controlHandler.upKeyPressed) {
            this.setDirection("up");
            this.setPosY(this.getPosY() - this.getWalkSpeed());
        } else if (controlHandler.downKeyPressed) {
            this.setDirection("down");
            this.setPosY(this.getPosY() + this.getWalkSpeed());
        } else if (controlHandler.leftKeyPressed) {
            this.setDirection("left");
            this.setPosX(this.getPosX() - this.getWalkSpeed());
        } else if (controlHandler.rightKeyPressed) {
            this.setDirection("right");
            this.setPosX(this.getPosX() + this.getWalkSpeed());
        }
    }

    public void draw(Graphics2D g2d) {
        BufferedImage image = null;
        String state = this.getDirection();

        switch (this.getDirection()) {
            case "down" -> {
                if (controlHandler.arrowKeyPressed) {
                    if (state.equals("down") && this.getActionFrame() + 1 < this.getMaxActionFrame()) {
                        if (Map.frame % 20 == 0) {
                            this.setActionFrame(this.getActionFrame() + 1);
                        }
                    } else {
                        state = "down";
                        this.setActionFrame(0);
                    }
                }
                image = this.getSprite(this.getActionFrame(), 0);
            }
            case "left" -> {
                if (controlHandler.arrowKeyPressed) {
                    if (state.equals("left") && this.getActionFrame() + 1 < this.getMaxActionFrame()) {
                        if (Map.frame % 20 == 0) {
                            this.setActionFrame(this.getActionFrame() + 1);
                        }
                    } else {
                        state = "left";
                        this.setActionFrame(0);
                    }
                }
                image = this.getSprite(this.getActionFrame(), 1);
            }
            case "right" -> {
                if (controlHandler.arrowKeyPressed) {
                    if (state.equals("right") && this.getActionFrame() + 1 < this.getMaxActionFrame()) {
                        if (Map.frame % 20 == 0) {
                            this.setActionFrame(this.getActionFrame() + 1);
                        }
                    } else {
                        state = "right";
                        this.setActionFrame(0);
                    }
                }
                image = this.getSprite(this.getActionFrame(), 2);
            }
            case "up" -> {
                if (controlHandler.arrowKeyPressed) {
                    if (state.equals("up") && this.getActionFrame() + 1 < this.getMaxActionFrame()) {
                        if (Map.frame % 20 == 0) {
                            this.setActionFrame(this.getActionFrame() + 1);
                        }
                    } else {
                        state = "up";
                        this.setActionFrame(0);
                    }
                }
                image = this.getSprite(this.getActionFrame(), 3);
            }
        }
        g2d.drawImage(image, 480, 228, Map.scaledTileSize, Map.scaledTileSize, null);
    }

    public void setWalkSpeed(int walkSpeed) {
        this.walkSpeed = walkSpeed;
    }

    public int getWalkSpeed() {
        return walkSpeed;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosY() {
        return posY;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getEnergy() {
        return energy;
    }

    public void setControlHandler(GameControlHandler controlHandler) {
        this.controlHandler = controlHandler;
    }

    public GameControlHandler getControlHandler() {
        return controlHandler;
    }
}
