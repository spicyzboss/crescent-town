package entity;

import main.GameControlHandler;
import main.Map;
import tile.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Human {

    public static double screenPosX;
    public static double screenPosY;
    public static double worldX;
    public static double worldY;
    private int energy;

    private GameControlHandler controlHandler;


    public Player(String name, GameControlHandler controlHandler) {
        setName(name);
        setControlHandler(controlHandler);
        loadSprite(name);
        playerInit();
    }

    public void playerInit() {
        this.solidArea = new Rectangle(Map.width/2 - Map.scaledTileSize/2 + 20,
                Map.height/2 + Map.scaledTileSize/4, Map.scaledTileSize-40, Map.scaledTileSize/4);
        this.setWalkSpeed(2 * Map.scale);
        this.setTilePosX(32);
        this.setTilePosY(54);
        this.setPixelPosX(getTilePosX() * Map.scaledTileSize);
        this.setPixelPosY(getTilePosY() * Map.scaledTileSize);
        this.setEnergy(100);
        this.setDirection("down");
        this.setMaxActionFrame(3);
        this.setSpriteLoadTime(Map.FPS/this.getMaxActionFrame());
    }


    public void update() {
        this.collisionCheck();
        if (this.getControlHandler().scaleUp || this.getControlHandler().scaleDown) {
            this.setWalkSpeed(2 * Map.scale);
            this.setPixelPosX(getTilePosX() * Map.scaledTileSize);
            this.setPixelPosY(getTilePosY() * Map.scaledTileSize);
        }
        if (this.getControlHandler().upKeyPressed) {
            this.setDirection("up");
            if (this.collisionEntity && this.collisionObj)
                this.setPixelPosY((this.getTilePosY() * Map.scaledTileSize) - this.getWalkSpeed());
        } else if (this.getControlHandler().downKeyPressed) {
            this.setDirection("down");
            if (this.collisionObj && this.collisionEntity)
                this.setPixelPosY((this.getTilePosY() * Map.scaledTileSize) + this.getWalkSpeed());
        } else if (this.getControlHandler().leftKeyPressed) {
            this.setDirection("left");
            if (this.collisionEntity && this.collisionObj)
                this.setPixelPosX((this.getTilePosX() * Map.scaledTileSize) - this.getWalkSpeed());
        } else if (this.getControlHandler().rightKeyPressed) {
            this.setDirection("right");
            if (this.collisionEntity && this.collisionObj)
                this.setPixelPosX((this.getTilePosX() * Map.scaledTileSize) + this.getWalkSpeed());
        }
        this.borderTop = this.getPixelPosY() - this.getScreenPosY() + this.solidArea.y;
        this.borderBot = this.getPixelPosY() - this.getScreenPosY() + this.solidArea.y + this.solidArea.height;
        this.borderLeft = this.getPixelPosX() - this.getScreenPosX() + this.solidArea.x;
        this.borderRight = this.getPixelPosX() - this.getScreenPosX() + this.solidArea.x + this.solidArea.width;
        this.setScreenPosX((double) Map.width/2 - (double) Map.scaledTileSize/2);
        this.setScreenPosY((double) Map.height/2 - (double) Map.scaledTileSize/2);
        worldX = this.getPixelPosX() + this.getScreenPosX();
        worldY = this.getPixelPosY() + this.getScreenPosY();
    }

    public void draw(Graphics2D renderer) {
        this.setSpriteOnAction();
        // draw player at center of screen
        renderer.drawImage(this.getSpriteOnAction(), (int)this.getScreenPosX(), (int)this.getScreenPosY(), Map.scaledTileSize, Map.scaledTileSize, null);
        renderer.setColor(Color.red);
        renderer.fillRect(this.solidArea.x, this.solidArea.y, this.solidArea.width, this.solidArea.height);
    }






    public void setScreenPosX(double screenPosX) {
        this.screenPosX = screenPosX;
    }

    public double getScreenPosX() {
        return screenPosX;
    }

    public void setScreenPosY(double screenPosY) {
        this.screenPosY = screenPosY;
    }

    public double getScreenPosY() {
        return screenPosY;
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
