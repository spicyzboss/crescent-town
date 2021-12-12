package entity;

import main.GameControlHandler;
import main.Map;
import tile.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Human {
    private int walkSpeed;
    private double screenPosX;
    private double screenPosY;
    private double tilePosX;
    private double tilePosY;
    private double pixelPosX;
    private double pixelPosY;
    private int screenX;
    private int screenY;
    private int energy;

    private MainMap currentMap;

    private GameControlHandler controlHandler;

    public Player(String name, GameControlHandler controlHandler) {
        setName(name);
        setControlHandler(controlHandler);
        loadSprite(name);
        playerInit();
    }

    public void playerInit() {
        this.setWalkSpeed(2 * Map.scale);
        this.setTilePosX(33);
        this.setTilePosY(55);
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
            this.setPixelPosX(getTilePosX() * Map.scaledTileSize);
            this.setPixelPosY(getTilePosY() * Map.scaledTileSize);
        }
        if (this.getControlHandler().upKeyPressed) {
            this.setDirection("up");
//            if (this.collisionCheck())
                this.setPixelPosY((this.getTilePosY() * Map.scaledTileSize) - this.getWalkSpeed());
        } else if (this.getControlHandler().downKeyPressed) {
            this.setDirection("down");
//            if (this.collisionCheck())
                this.setPixelPosY((this.getTilePosY() * Map.scaledTileSize) + this.getWalkSpeed());
        } else if (this.getControlHandler().leftKeyPressed) {
            this.setDirection("left");
//            if (this.collisionCheck())
                this.setPixelPosX((this.getTilePosX() * Map.scaledTileSize) - this.getWalkSpeed());
        } else if (this.getControlHandler().rightKeyPressed) {
            this.setDirection("right");
//            if (this.collisionCheck())
                this.setPixelPosX((this.getTilePosX() * Map.scaledTileSize) + this.getWalkSpeed());
        }
        this.setTilePosX(getPixelPosX() / (double)Map.scaledTileSize);
        this.setTilePosY(getPixelPosY() / (double)Map.scaledTileSize);
        this.setScreenPosX((double) Map.width/2 - (double) Map.scaledTileSize/2);
        this.setScreenPosY((double) Map.height/2 - (double) Map.scaledTileSize/2);
    }

    public void draw(Graphics2D renderer) {
        BufferedImage image = switch (this.getDirection()) {
            case "down" -> this.spriteUpdate(0);
            case "left" -> this.spriteUpdate(1);
            case "right" -> this.spriteUpdate(2);
            case "up" -> this.spriteUpdate(3);
            default -> this.spriteUpdate(0);
        };
        // draw player at center of screen
        renderer.drawImage(image, (int)this.getScreenPosX(), (int)this.getScreenPosY(), Map.scaledTileSize, Map.scaledTileSize, null);
        renderer.fillRect(Map.width/2 - Map.scaledTileSize/2, Map.height/2 + Map.scaledTileSize/4, Map.scaledTileSize, Map.scaledTileSize/4);
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

    private boolean collisionCheck() {
        ArrayList<TileMap> tileMaps = this.getCurrentMap().getTileMaps();
        int tile = -1;
        if (this.getTilePosX() - 1 >= 0 && this.getTilePosY() - 1 >= 0 && this.getTilePosX() <= this.getCurrentMap().getTileMaps().get(0).mapWidth && this.getTilePosY() <= this.getCurrentMap().getTileMaps().get(0).mapHeight) {
            tile = switch (this.getDirection()) {
                case "up" -> this.getCurrentMap().getTileMaps().get(4).map[(int)this.getTilePosX()][(int)this.getTilePosY() - 1];
                case "down" -> this.getCurrentMap().getTileMaps().get(4).map[(int)this.getTilePosX()][(int)this.getTilePosY() + 1];
                case "left" -> this.getCurrentMap().getTileMaps().get(4).map[(int)this.getTilePosX() - 1][(int)this.getTilePosY()];
                case "right" -> this.getCurrentMap().getTileMaps().get(4).map[(int)this.getTilePosX() + 1][(int)this.getTilePosY()];
                default -> -1;
            };
        }
        return tile == 293;
    }

    public void setWalkSpeed(int walkSpeed) {
        this.walkSpeed = walkSpeed;
    }

    public int getWalkSpeed() {
        return walkSpeed;
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
