package entity;

import main.GameControlHandler;
import main.Map;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Human {
    private int walkSpeed;
    private double tilePosX;
    private double tilePosY;
    private double pixelPosX;
    private double pixelPosY;
    private int energy;
    private GameControlHandler controlHandler;

    public Player(String name, GameControlHandler controlHandler) {
        setName(name);
        setControlHandler(controlHandler);
        loadSprite(name);
        playerInit();
    }

    public void playerInit() {
        walkSpeed = 2 * Map.scale;
        this.setTilePosX(25);
        this.setTilePosY(50);
        this.setPixelPosX(getTilePosX() * Map.scaledTileSize);
        this.setPixelPosY(getTilePosY() * Map.scaledTileSize);
        this.setEnergy(100);
        this.setDirection("down");
        this.setMaxActionFrame(3);
        this.setSpriteLoadTime(Map.FPS/this.getMaxActionFrame());
    }

    public void update() {
        if (this.getControlHandler().scaleUp || this.getControlHandler().scaleDown) {
            this.setWalkSpeed(2 * Map.scale);
            this.setPixelPosX(getTilePosX() * Map.scaledTileSize * 100);
            this.setPixelPosY(getTilePosY() * Map.scaledTileSize * 100);
        }
        if (this.getControlHandler().upKeyPressed) {
            this.setDirection("up");
            this.setPixelPosY((this.getTilePosY() * Map.scaledTileSize) - this.getWalkSpeed());
        } else if (this.getControlHandler().downKeyPressed) {
            this.setDirection("down");
            this.setPixelPosY((this.getTilePosY() * Map.scaledTileSize) + this.getWalkSpeed());
        } else if (this.getControlHandler().leftKeyPressed) {
            this.setDirection("left");
            this.setPixelPosX((this.getTilePosX() * Map.scaledTileSize) - this.getWalkSpeed());
        } else if (this.getControlHandler().rightKeyPressed) {
            this.setDirection("right");
            this.setPixelPosX((this.getTilePosX() * Map.scaledTileSize) + this.getWalkSpeed());
        }
        this.setTilePosX(getPixelPosX() / (double)Map.scaledTileSize);
        this.setTilePosY(getPixelPosY() / (double)Map.scaledTileSize);
    }

    public void draw(Graphics2D g2d) {
        BufferedImage image = switch (this.getDirection()) {
            case "down" -> this.spriteUpdate(0);
            case "left" -> this.spriteUpdate(1);
            case "right" -> this.spriteUpdate(2);
            case "up" -> this.spriteUpdate(3);
            default -> this.spriteUpdate(0);
        };
        // draw player at center of screen
        g2d.drawImage(image, Map.width/2 - Map.scaledTileSize/2, Map.height/2 - Map.scaledTileSize/2, Map.scaledTileSize, Map.scaledTileSize, null);
    }

    private BufferedImage spriteUpdate(int spriteAction) {
        if (this.getControlHandler().arrowKeyPressed) {
            if (Map.frame != 0) {
                this.setActionFrame(Math.min(Map.frame / this.getSpriteLoadTime(), this.getMaxActionFrame() - 1));
            } else {
                this.setActionFrame(0);
            }
        }
        return this.getSprite(this.getActionFrame(), spriteAction);
    }

    public void setWalkSpeed(int walkSpeed) {
        this.walkSpeed = walkSpeed;
    }

    public int getWalkSpeed() {
        return walkSpeed;
    }

    public void setPixelPosX(double x) {
        this.pixelPosX = x;
    }

    public double getPixelPosX() {
        return pixelPosX;
    }

    public void setPixelPosY(double pixelPosY) {
        this.pixelPosY = pixelPosY;
    }

    public double getPixelPosY() {
        return pixelPosY;
    }

    public void setTilePosX(double tilePosX) {
        this.tilePosX = tilePosX;
    }

    public double getTilePosX() {
        return tilePosX;
    }

    public void setTilePosY(double tilePosY) {
        this.tilePosY = tilePosY;
    }

    public double getTilePosY() {
        return tilePosY;
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
