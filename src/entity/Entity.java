package entity;

import main.GameControlHandler;
import main.Map;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Entity {
    private String name;
    private String direction;
    private BufferedImage sprite;
    private BufferedImage spriteOnAction;
    private int actionFrame;
    private int maxActionFrame;
    private int spriteLoadTime;
    private int walkSpeed;
    private double tilePosX;
    private double tilePosY;
    private double pixelPosX;
    private double pixelPosY;

    public void loadSprite(String file) {
        try {
            sprite = ImageIO.read(new File("src/resource/sprite/" + file + ".png"));
            System.out.println("\u001B[32m" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + " - LOADED: sprite " + file + "\u001B[0m");
        } catch (IOException e) {
            System.out.println("\u001B[31m" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + " - ERROR: sprite " + file + "\u001B[0m");
        }
    }

    public BufferedImage getSprite(int xGrid, int yGrid) {
        return sprite.getSubimage(xGrid * Map.tileSize, yGrid * Map.tileSize, Map.tileSize, Map.tileSize);
    }

    public void setSpriteOnAction(){
        BufferedImage image = switch (this.direction != null?this.getDirection():"none") {
            case "left" -> this.spriteUpdate(1);
            case "right" -> this.spriteUpdate(2);
            case "up" -> this.spriteUpdate(3);
            default -> this.spriteUpdate(0);
        };
        this.spriteOnAction = image;
    }
    private BufferedImage spriteUpdate(int spriteAction) {
        if (Map.frame != 0 && this.getSpriteLoadTime()!= 0) {
            this.setActionFrame(Math.min(Map.frame / this.getSpriteLoadTime(), this.getMaxActionFrame() - 1));
        } else {
            this.setActionFrame(0);
        }
        return this.getSprite(this.getActionFrame(), spriteAction);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public void setActionFrame(int actionFrame) {
        this.actionFrame = actionFrame;
    }

    public int getActionFrame() {
        return actionFrame;
    }

    public void setMaxActionFrame(int maxActionFrame) {
        this.maxActionFrame = maxActionFrame;
    }

    public int getMaxActionFrame() {
        return maxActionFrame;
    }

    public void setSpriteLoadTime(int millis) {
        this.spriteLoadTime = millis;
    }

    public int getSpriteLoadTime() {
        return spriteLoadTime;
    }

    public BufferedImage getSpriteOnAction() {
        return spriteOnAction;
    }

    public void setPixelPosX(double pixelPosX) {
        this.pixelPosX = pixelPosX;
        this.tilePosX = pixelPosX/Map.scaledTileSize;
    }

    public double getPixelPosX() {
        return pixelPosX;
    }

    public void setPixelPosY(double pixelPosY) {
        this.pixelPosY = pixelPosY;
        this.tilePosY = pixelPosY/Map.scaledTileSize;
    }

    public double getPixelPosY() {
        return pixelPosY;
    }

    public void setTilePosX(double tilePosX) {
        this.tilePosX = tilePosX;
        this.pixelPosX = tilePosX * Map.scaledTileSize;
    }

    public double getTilePosX() {
        return tilePosX;
    }

    public void setTilePosY(double tilePosY) {
        this.tilePosY = tilePosY;
        this.pixelPosY = tilePosY * Map.scaledTileSize;
    }

    public double getTilePosY() {
        return tilePosY;
    }

    public void setWalkSpeed(int walkSpeed) {
        this.walkSpeed = walkSpeed;
    }

    public int getWalkSpeed() {
        return walkSpeed;
    }
}
