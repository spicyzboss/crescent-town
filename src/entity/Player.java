package entity;

import main.GameControlHandler;
import main.Map;
import tile.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Human {
    public static MainMap currentMap;

    private double screenPosX;
    private double screenPosY;
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

    @Override
    public void setTilePosX(double tilePosX) {
        super.setTilePosX(tilePosX);
        this.borderLeft = this.getPixelPosX() - this.getScreenPosX() + this.solidArea.x;
        this.borderRight = this.getPixelPosX() - this.getScreenPosX() + this.solidArea.x + this.solidArea.width;

    }

    @Override
    public void setTilePosY(double tilePosY) {
        super.setTilePosY(tilePosY);
        this.borderTop = this.getPixelPosY() - this.getScreenPosY() + this.solidArea.y;
        this.borderBot = this.getPixelPosY() - this.getScreenPosY() + this.solidArea.y + this.solidArea.height;
    }

    @Override
    public void setPixelPosX(double pixelPosX) {
        super.setPixelPosX(pixelPosX);
        this.borderLeft = this.getPixelPosX() - this.getScreenPosX() + this.solidArea.x;
        this.borderRight = this.getPixelPosX() - this.getScreenPosX() + this.solidArea.x + this.solidArea.width;
    }

    @Override
    public void setPixelPosY(double pixelPosY) {
        super.setPixelPosY(pixelPosY);
        this.borderTop = this.getPixelPosY() - this.getScreenPosY() + this.solidArea.y;
        this.borderBot = this.getPixelPosY() - this.getScreenPosY() + this.solidArea.y + this.solidArea.height;
    }

    public void update() {
        if (this.getControlHandler().scaleUp || this.getControlHandler().scaleDown) {
            this.setWalkSpeed(2 * Map.scale);
            this.setPixelPosX(getTilePosX() * Map.scaledTileSize);
            this.setPixelPosY(getTilePosY() * Map.scaledTileSize);
        }
        if (this.getControlHandler().upKeyPressed) {
            this.setDirection("up");
            if (this.collisionCheck())
                this.setPixelPosY((this.getTilePosY() * Map.scaledTileSize) - this.getWalkSpeed());
        } else if (this.getControlHandler().downKeyPressed) {
            this.setDirection("down");
            if (this.collisionCheck())
                this.setPixelPosY((this.getTilePosY() * Map.scaledTileSize) + this.getWalkSpeed());
        } else if (this.getControlHandler().leftKeyPressed) {
            this.setDirection("left");
            if (this.collisionCheck())
                this.setPixelPosX((this.getTilePosX() * Map.scaledTileSize) - this.getWalkSpeed());
        } else if (this.getControlHandler().rightKeyPressed) {
            this.setDirection("right");
            if (this.collisionCheck())
                this.setPixelPosX((this.getTilePosX() * Map.scaledTileSize) + this.getWalkSpeed());
        }
        this.setScreenPosX((double) Map.width/2 - (double) Map.scaledTileSize/2);
        this.setScreenPosY((double) Map.height/2 - (double) Map.scaledTileSize/2);
    }

    public void draw(Graphics2D renderer) {
        this.setSpriteOnAction();
        // draw player at center of screen
        renderer.drawImage(this.getSpriteOnAction(), (int)this.getScreenPosX(), (int)this.getScreenPosY(), Map.scaledTileSize, Map.scaledTileSize, null);
        renderer.fillRect(this.solidArea.x, this.solidArea.y, this.solidArea.width, this.solidArea.height);
    }

    private int pixelToTile(double pixel){
        return (int) pixel / Map.scaledTileSize;
    }

    private boolean collisionCheck() {
        System.out.println(getDirection());
        Tile tile1 = null;
        Tile tile2 = null;
        switch (this.getDirection()){
            case "up" -> {
                tile1 = TileManager.getTileByNumber(this.getCurrentMap().tileMaps.get(4).map[pixelToTile(borderTop)][pixelToTile(borderLeft)]);
                tile2 = TileManager.getTileByNumber(this.getCurrentMap().tileMaps.get(4).map[pixelToTile(borderTop)][pixelToTile(borderRight)]);
            }
            case "down" -> {
                tile1 = TileManager.getTileByNumber(this.getCurrentMap().tileMaps.get(4).map[pixelToTile(borderBot)][pixelToTile(borderLeft)]);
                tile2 = TileManager.getTileByNumber(this.getCurrentMap().tileMaps.get(4).map[pixelToTile(borderBot)][pixelToTile(borderRight)]);
            }
            case "left" -> {
                tile1 = TileManager.getTileByNumber(this.getCurrentMap().tileMaps.get(4).map[pixelToTile(borderTop)][pixelToTile(borderLeft)]);
                tile2 = TileManager.getTileByNumber(this.getCurrentMap().tileMaps.get(4).map[pixelToTile(borderBot)][pixelToTile(borderLeft)]);
            }
            case "right" -> {
                tile1 = TileManager.getTileByNumber(this.getCurrentMap().tileMaps.get(4).map[pixelToTile(borderTop)][pixelToTile(borderRight)]);
                tile2 = TileManager.getTileByNumber(this.getCurrentMap().tileMaps.get(4).map[pixelToTile(borderBot)][pixelToTile(borderRight)]);
            }
        }
        System.out.println(tile1);
        System.out.println(tile2);
        return (tile1 == null) && (tile2 == null);
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
