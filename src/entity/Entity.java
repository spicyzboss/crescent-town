package entity;

import main.Game;
import tile.MainMap;
import tile.Tile;
import tile.TileManager;

import javax.imageio.ImageIO;
import java.awt.*;
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
    protected double borderLeft;
    protected double borderRight;
    protected double borderTop;
    protected double borderBot;
    public static MainMap currentMap;
    public boolean collisionObj;
    public boolean collisionEntity = true;

    protected Rectangle solidArea;

    public void loadSprite(String file) {
        try {
            sprite = ImageIO.read(new File("src/resource/sprite/" + file + ".png"));
            System.out.println("\u001B[32m" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + " - LOADED: sprite " + file + "\u001B[0m");
        } catch (IOException e) {
            System.out.println("\u001B[31m" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + " - ERROR: sprite " + file + "\u001B[0m");
        }
    }

    public BufferedImage getSprite(int xGrid, int yGrid) {
        return sprite.getSubimage(xGrid * Game.tileSize, yGrid * Game.tileSize, Game.tileSize, Game.tileSize);
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
        if (Game.frame != 0 && this.getSpriteLoadTime()!= 0) {
            this.setActionFrame(Math.min(Game.frame / this.getSpriteLoadTime(), this.getMaxActionFrame() - 1));
        } else {
            this.setActionFrame(0);
        }
        return this.getSprite(this.getActionFrame(), spriteAction);
    }

    public void collisionCheck() {
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

        this.collisionObj = (((tile1 == null) ||(tile2 == null)));
    }

    public void checkEntity(Entity target){
        if (this.solidArea.intersects(target.solidArea)){
            switch (this.getDirection()){
                case "up" -> {
                    this.setPixelPosY((this.getTilePosY() * Game.scaledTileSize) + walkSpeed/2);
                    this.collisionEntity = false;
                }
                case "down" -> {
                    this.setPixelPosY((this.getTilePosY() * Game.scaledTileSize) - walkSpeed/2);
                    this.collisionEntity = false;
                }
                case "left" -> {
                    this.setPixelPosX((this.getTilePosX() * Game.scaledTileSize) + walkSpeed/2);
                    this.collisionEntity = false;
                }
                case "right" -> {
                    this.setPixelPosX((this.getTilePosX() * Game.scaledTileSize) - walkSpeed/2);
                    this.collisionEntity = false;
                }
            }
        }
        else{
            this.collisionEntity = true;
        }
//        if(this.solidArea.intersects(target.solidArea)){
//            target.collisionEntity = false;
//        }
//        else{
//            target.collisionEntity = true;
//        }
    }

    private int pixelToTile(double pixel){
        return (int) pixel / Game.scaledTileSize;
    }

    public void setCurrentMap(MainMap map){
        this.currentMap = map;
    }

    public MainMap getCurrentMap(){
        return currentMap;
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
        this.tilePosX = pixelPosX/ Game.scaledTileSize;
    }

    public double getPixelPosX() {
        return pixelPosX;
    }

    public void setPixelPosY(double pixelPosY) {
        this.pixelPosY = pixelPosY;
        this.tilePosY = pixelPosY/ Game.scaledTileSize;
    }

    public double getPixelPosY() {
        return pixelPosY;
    }

    public void setTilePosX(double tilePosX) {
        this.tilePosX = tilePosX;
        this.pixelPosX = tilePosX * Game.scaledTileSize;
    }

    public double getTilePosX() {
        return tilePosX;
    }

    public void setTilePosY(double tilePosY) {
        this.tilePosY = tilePosY;
        this.pixelPosY = tilePosY * Game.scaledTileSize;
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
