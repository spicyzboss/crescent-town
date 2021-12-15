package entity;

import main.Game;
import main.GameControlHandler;
import main.Maps;
import object.Object;
import tile.Map;
import tile.Tile;
import tile.TileManager;

import java.awt.*;
import java.util.ArrayList;

public class Player extends Human {
    private Map currentMap;
    public static double screenPosX;
    public static double screenPosY;
    public static Interactable interactEntity, interactObj;
    private int energy;

    private GameControlHandler controlHandler;

    public static Rectangle playerArea = new Rectangle();

    public Player(String name, GameControlHandler controlHandler) {
        setName(name);
        setControlHandler(controlHandler);
        loadSprite(name);
        playerInit();
    }

    public void playerInit() {
        this.solidArea = new Rectangle(Game.width/2 - Game.scaledTileSize/2 + 20,
                Game.height/2 + Game.scaledTileSize/4, Game.scaledTileSize-40, Game.scaledTileSize/4);
        this.setWalkSpeed(2 * Game.scale);
        this.setTilePosX(32);
        this.setTilePosY(54);
        this.setPixelPosX(getTilePosX() * Game.scaledTileSize);
        this.setPixelPosY(getTilePosY() * Game.scaledTileSize);
        this.setEnergy(100);
        this.setDirection("down");
        this.setMaxActionFrame(3);
        this.setSpriteLoadTime(Game.FPS/this.getMaxActionFrame());
    }


    public void update() {


        this.borderTop = this.getPixelPosY() - this.getScreenPosY() + this.solidArea.y;
        this.borderBot = this.getPixelPosY() - this.getScreenPosY() + this.solidArea.y + this.solidArea.height;
        this.borderLeft = this.getPixelPosX() - this.getScreenPosX() + this.solidArea.x;
        this.borderRight = this.getPixelPosX() - this.getScreenPosX() + this.solidArea.x + this.solidArea.width;

        this.collisionCheck();

        ArrayList<NPC> npcs = this.getCurrentMap().NPCs;
        System.out.println("npc: "+npcs.isEmpty());
        if(!npcs.isEmpty()) {
            for (NPC npc : npcs) {
                this.checkEntity(npc);
            }
        }
        else{
            this.collisionEntity = true;
        }
        ArrayList<Object> objects = this.getCurrentMap().objects;
        System.out.println("object: "+objects.isEmpty());
        if(!objects.isEmpty()) {
            for (Object object : objects) {
                this.checkObject(object);
            }
        }
        else{
            this.collisionObj = true;
        }

        boolean isCollision = this.collisionTile && this.collisionEntity && this.collisionObj;
        if (this.getControlHandler().scaleUp || this.getControlHandler().scaleDown) {
            this.setWalkSpeed(4 * Game.scale);
            this.setPixelPosX(getTilePosX() * Game.scaledTileSize);
            this.setPixelPosY(getTilePosY() * Game.scaledTileSize);
        }
        if (this.getControlHandler().upKeyPressed) {
            this.setDirection("up");
            if (isCollision) {
                this.setPixelPosY((this.getTilePosY() * Game.scaledTileSize) - this.getWalkSpeed());
            }
        } else if (this.getControlHandler().downKeyPressed) {
            this.setDirection("down");
            if (isCollision) {
                this.setPixelPosY((this.getTilePosY() * Game.scaledTileSize) + this.getWalkSpeed());
            }
        } else if (this.getControlHandler().leftKeyPressed) {
            this.setDirection("left");
            if (isCollision) {
                this.setPixelPosX((this.getTilePosX() * Game.scaledTileSize) - this.getWalkSpeed());
            }
        } else if (this.getControlHandler().rightKeyPressed) {
            this.setDirection("right");
            if (isCollision) {
                this.setPixelPosX((this.getTilePosX() * Game.scaledTileSize) + this.getWalkSpeed());
            }
        }

        this.setScreenPosX((double) Game.width/2 - (double) Game.scaledTileSize/2);
        this.setScreenPosY((double) Game.height/2 - (double) Game.scaledTileSize/2);
        playerArea.setRect((int)screenPosX, (int)screenPosY, Game.scaledTileSize, Game.scaledTileSize);


    }

    public void draw(Graphics2D renderer) {
        this.setSpriteOnAction();
        if (interactEntity != null)
            interactEntity.interact(renderer, this);
        if (interactObj != null) {
            interactObj.interact(renderer, this);
        }

        // draw player at center of screen
        renderer.drawImage(this.getSpriteOnAction(), playerArea.x, playerArea.y, playerArea.width, playerArea.height, null);
//        renderer.setColor(Color.CYAN);
//        renderer.fillRect(playerArea.x, playerArea.y, playerArea.width, playerArea.height);
//        renderer.setColor(Color.red);
//        renderer.fillRect(this.solidArea.x, this.solidArea.y, this.solidArea.width, this.solidArea.height);
    }

    public void collisionCheck() {
        Tile tile1 = null;
        Tile tile2 = null;

        switch (this.getDirection()){
            case "up" -> {
                tile1 = TileManager.getTileByNumber(this.getCurrentMap().collisionTileMap.map[pixelToTile(borderTop)][pixelToTile(borderLeft)]);
                tile2 = TileManager.getTileByNumber(this.getCurrentMap().collisionTileMap.map[pixelToTile(borderTop)][pixelToTile(borderRight)]);
            }
            case "down" -> {
                tile1 = TileManager.getTileByNumber(this.getCurrentMap().collisionTileMap.map[pixelToTile(borderBot)][pixelToTile(borderLeft)]);
                tile2 = TileManager.getTileByNumber(this.getCurrentMap().collisionTileMap.map[pixelToTile(borderBot)][pixelToTile(borderRight)]);
            }
            case "left" -> {
                tile1 = TileManager.getTileByNumber(this.getCurrentMap().collisionTileMap.map[pixelToTile(borderTop)][pixelToTile(borderLeft)]);
                tile2 = TileManager.getTileByNumber(this.getCurrentMap().collisionTileMap.map[pixelToTile(borderBot)][pixelToTile(borderLeft)]);

            }
            case "right" -> {
                tile1 = TileManager.getTileByNumber(this.getCurrentMap().collisionTileMap.map[pixelToTile(borderTop)][pixelToTile(borderRight)]);
                tile2 = TileManager.getTileByNumber(this.getCurrentMap().collisionTileMap.map[pixelToTile(borderBot)][pixelToTile(borderRight)]);
            }
        }

        this.collisionTile = (((tile1 == null) ||(tile2 == null)));
    }

    public void checkEntity(Entity target){
        Rectangle recIntersection = playerArea.intersection(target.solidArea);
        if(playerArea.intersects(target.solidArea)){
            if(target.getClass().getInterfaces()[0].getSimpleName().equals("Interactable")) {
                interactEntity = (Interactable) target;
            }
            target.collisionEntity = false;
            switch (this.getDirection()){
                case "up" -> this.collisionEntity = recIntersection.y != playerArea.y || recIntersection.width <= recIntersection.height;
                case "down" -> this.collisionEntity = recIntersection.y != target.solidArea.y || recIntersection.width <= recIntersection.height;
                case "left" -> this.collisionEntity = recIntersection.x != playerArea.x || recIntersection.height <= recIntersection.width;
                case "right" -> this.collisionEntity = recIntersection.x != target.solidArea.x || recIntersection.height <= recIntersection.width;
            }
        }
        else{
            this.collisionEntity = true;
            target.collisionEntity = true;
            interactEntity = null;
        }
    }

    public void checkObject(Object obj){
        Rectangle recIntersection = playerArea.intersection(obj.solidArea);
        System.out.println("test");
        if(playerArea.intersects(obj.solidArea)){
            if(obj.getClass().getInterfaces()[0].getSimpleName().equals("Interactable")) {
                interactObj = (Interactable) obj;
            }
            switch (this.getDirection()){
                case "up" -> this.collisionObj = recIntersection.y != playerArea.y || recIntersection.width <= recIntersection.height;
                case "down" -> this.collisionObj = recIntersection.y != obj.solidArea.y || recIntersection.width <= recIntersection.height;
                case "left" -> this.collisionObj = recIntersection.x != playerArea.x || recIntersection.height <= recIntersection.width;
                case "right" -> this.collisionObj = recIntersection.x != obj.solidArea.x || recIntersection.height <= recIntersection.width;
            }
        }
        else{
            this.collisionObj = true;
            interactObj = null;
        }
    }

    public void setScreenPosX(double screenPosX) {
        Player.screenPosX = screenPosX;
    }

    public double getScreenPosX() {
        return screenPosX;
    }

    public void setScreenPosY(double screenPosY) {
        Player.screenPosY = screenPosY;
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

    public void setCurrentMap(Map map){
        this.currentMap = map;
    }

    public Map getCurrentMap(){
        return this.currentMap;
    }

    public void setCurrentMapByName(String destination){
        this.setCurrentMap(Maps.getMap(destination));
    }


    private int pixelToTile(double pixel){
        return (int) pixel / Game.scaledTileSize;
    }

}
