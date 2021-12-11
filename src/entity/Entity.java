package entity;

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
    private int actionFrame;
    private int maxActionFrame;
    private int spriteLoadTime;

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
}
