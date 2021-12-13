package entity;

import main.GameControlHandler;
import main.Map;
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
    public  boolean collisionEntity = true;

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

    protected void collisionCheck() {
        Tile tile1 = null;
        Tile tile2 = null;
        Npc npc = this.getCurrentMap().npcs.get(0);
        boolean foundEntity = true;
//        this.getCurrentMap().tileMaps.get(4).map[pixelToTile(npc.getPixelPosY())][pixelToTile(npc.getPixelPosX())] = 293;
        switch (this.getDirection()){
            case "up" -> {
                tile1 = TileManager.getTileByNumber(this.getCurrentMap().tileMaps.get(4).map[pixelToTile(borderTop)][pixelToTile(borderLeft)]);
                tile2 = TileManager.getTileByNumber(this.getCurrentMap().tileMaps.get(4).map[pixelToTile(borderTop)][pixelToTile(borderRight)]);
                if(Math.floor(npc.getPixelPosX()) == this.borderTop && ((Math.floor(npc.getTilePosX()) == this.pixelToTile(borderLeft))
                || Math.floor(npc.getTilePosX()) == this.pixelToTile(borderRight))){
                    foundEntity = false;
                }
            }
            case "down" -> {
                tile1 = TileManager.getTileByNumber(this.getCurrentMap().tileMaps.get(4).map[pixelToTile(borderBot)][pixelToTile(borderLeft)]);
                tile2 = TileManager.getTileByNumber(this.getCurrentMap().tileMaps.get(4).map[pixelToTile(borderBot)][pixelToTile(borderRight)]);
                if(Math.floor(npc.getTilePosY()) == this.pixelToTile(borderBot) && ((Math.floor(npc.getTilePosX()) == this.pixelToTile(borderLeft))
                || (Math.floor(npc.getTilePosX()) == this.pixelToTile(borderRight)))){
                    foundEntity = false;
                }
            }
            case "left" -> {
                tile1 = TileManager.getTileByNumber(this.getCurrentMap().tileMaps.get(4).map[pixelToTile(borderTop)][pixelToTile(borderLeft)]);
                tile2 = TileManager.getTileByNumber(this.getCurrentMap().tileMaps.get(4).map[pixelToTile(borderBot)][pixelToTile(borderLeft)]);
                if( Math.floor(npc.getTilePosX()) == this.pixelToTile(borderLeft) && (Math.floor(npc.getTilePosY()) == this.pixelToTile(borderTop))
                || (Math.floor(npc.getTilePosY()) == this.pixelToTile(borderBot))){
                    foundEntity = false;
                }
            }
            case "right" -> {
                tile1 = TileManager.getTileByNumber(this.getCurrentMap().tileMaps.get(4).map[pixelToTile(borderTop)][pixelToTile(borderRight)]);
                tile2 = TileManager.getTileByNumber(this.getCurrentMap().tileMaps.get(4).map[pixelToTile(borderBot)][pixelToTile(borderRight)]);
                if(Math.floor(npc.getTilePosX()) == this.pixelToTile(borderRight) && ((Math.floor(npc.getTilePosY()) == this.pixelToTile(borderTop))
                || ((Math.floor(npc.getTilePosY()) == this.pixelToTile(borderBot))))) {
                    foundEntity = false;

                }
                }
            }
        System.out.println((int) (npc.getTilePosX()));
        this.collisionObj = (((tile1 == null) ||(tile2 == null)) && foundEntity);
    }

    private int pixelToTile(double pixel){
        return (int) pixel / Map.scaledTileSize;
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
