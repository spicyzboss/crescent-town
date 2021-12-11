package entity;

import main.GameControlHandler;
import main.Map;
import tile.CollisionChecker;
import tile.MainMap;
import tile.TileManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Human {
    private int walkSpeed;
    private int screenX;
    private int screenY;
    private int posX;
    private int posY;
    private int energy;
    public Rectangle playerArea = new Rectangle(((Map.tileColumn)/2)*Map.scaledTileSize, ((Map.tileRow)/2)*Map.scaledTileSize, Map.scaledTileSize, Map.scaledTileSize);
    public Rectangle solidArea = new Rectangle(((Map.tileColumn)/2)*Map.scaledTileSize+14, ((Map.tileRow)/2)*Map.scaledTileSize+48, Map.tileSize+1, Map.tileSize/2);

    private MainMap currentMap;

    private GameControlHandler controlHandler;

    public Player(String name, GameControlHandler controlHandler) {
        setName(name);
        setControlHandler(controlHandler);
        loadSprite(name);
        playerInit();
    }

    public void playerInit() {
        this.setWalkSpeed(Map.scale*2);
        this.setEnergy(100);
        this.setDirection("down");
        this.setMaxActionFrame(3);
    }
    public void update() {
        if (controlHandler.upKeyPressed) {
            this.setDirection("up");
            CollisionChecker.checkTile(this);
            if(!CollisionChecker.checkTile(this))
                this.setScreenY(this.getScreenY() - this.getWalkSpeed());
        } else if (controlHandler.downKeyPressed) {
            this.setDirection("down");
            if(!CollisionChecker.checkTile(this))
                this.setScreenY(this.getScreenY() + this.getWalkSpeed());
        } else if (controlHandler.leftKeyPressed) {
            this.setDirection("left");
            if(!CollisionChecker.checkTile(this))
                this.setScreenX(this.getScreenX() - this.getWalkSpeed());
        } else if (controlHandler.rightKeyPressed) {
            this.setDirection("right");
            if(!CollisionChecker.checkTile(this))
                this.setScreenX(this.getScreenX() + this.getWalkSpeed());
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
        g2d.drawImage(image, playerArea.x, playerArea.y, playerArea.width, playerArea.height, null);

        g2d.fillRect(solidArea.x, solidArea.y, solidArea.width, solidArea.height);
    }

    public void setWalkSpeed(int walkSpeed) {
        this.walkSpeed = walkSpeed;
    }

    public int getWalkSpeed() {
        return walkSpeed;
    }

    public void setScreenX(int screenX) {
        this.screenX = screenX;
    }

    public int getScreenX() {
        return screenX;
    }

    public void setScreenY(int screenY) {
        this.screenY = screenY;
    }

    public int getScreenY() {
        return screenY;
    }

    public int getPosX(){
        return posX;
    }

    public int getPosY(){
        return posY;
    }

    public void setPosX(int posX){
        this.posX = posX;
    }

    public void setPosY(int posY){
        this.posY = posY;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getEnergy() {
        return energy;
    }

    public void setCurrentMap(MainMap map){
        this.currentMap = map;
    }

    public MainMap getCurrentMap(){
        return currentMap;
    }


    public void setControlHandler(GameControlHandler controlHandler) {
        this.controlHandler = controlHandler;
    }

    public GameControlHandler getControlHandler() {
        return controlHandler;
    }
}
