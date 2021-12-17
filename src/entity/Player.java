package entity;

import inventory.Inventory;
import item.*;
import main.Game;
import main.GameControlHandler;
import main.MapManager;
import object.Object;
import tile.Map;
import tile.Tile;
import tile.TileManager;
import tile.TileMap;

import java.awt.*;
import java.io.Serializable;

public class Player extends Human implements Serializable, Runnable {
    private Map currentMap;
    public static double screenPosX;
    public static double screenPosY;
    public static NPC interactNPC;
    public static Object interactObj;
    public boolean isInteracting;
    private int energy;
    private Inventory inventory;
    private int selectedItem;
    public double borderLeft, borderRight, borderTop, borderBot;
    private Tile forwardTile;
    private int fowardTilePosX, fowardTilePosY;
    private GameControlHandler controlHandler;


    public static Rectangle playerArea = new Rectangle();

    public static Thread playerThread;

    public Player(String name, GameControlHandler controlHandler) {
        setName(name);
        setControlHandler(controlHandler);
        loadSprite(name);
        playerInit();
        playerThread = new Thread(this);
        playerThread.start();
    }

    public void playerInit() {
        this.solidArea = new Rectangle(Game.width/2 - Game.scaledTileSize/2 + 20,Game.height/2 + Game.scaledTileSize/4, Game.scaledTileSize-40, Game.scaledTileSize/4);
        this.setWalkSpeed(2 * Game.scale);
        this.setTilePosX(32);
        this.setTilePosY(54);
        this.setPixelPosX(getTilePosX() * Game.scaledTileSize);
        this.setPixelPosY(getTilePosY() * Game.scaledTileSize);
        this.setEnergy(100);
        this.setDirection("down");
        this.setMaxActionFrame(3);
        this.setSpriteLoadTime(Game.FPS/this.getMaxActionFrame());
        this.setWallet(new HumanWallet(100));
        this.setInventory(new Inventory(9));
        this.getInventory().addItem(new Hoe());
        this.getInventory().addItem(new PinkBush());
        this.getInventory().addItem(new BlueBush());
        this.getInventory().addItem(new Corn());
        this.getInventory().addItem(new Flowder());
        this.getInventory().addItem(new Lotus());
        this.getInventory().addItem(new PoiSian());
        this.setSelectedItem(0);
    }

    public void update() {
        if (this.getControlHandler().numbers.contains(true)) {
            this.setSelectedItem(this.getControlHandler().numbers.indexOf(true));
        }
        if(this.getControlHandler().activeItem && this.getSelectedItem() < this.getInventory().getSize()){
            if(!(this.getInventory().getItem(this.getSelectedItem()).getName().equals("Hoe") && this.getEnergy() < 10))
                this.getInventory().getItem(this.getSelectedItem()).active(this);
            this.getControlHandler().activeItem = false;
        }
        if(this.getControlHandler().pos){
            System.out.println(this.getTilePosX()+", "+this.getTilePosY());
            this.getControlHandler().pos = false;
        }
        this.borderTop = this.getPixelPosY() - this.getScreenPosY() + this.solidArea.y;
        this.borderBot = this.getPixelPosY() - this.getScreenPosY() + this.solidArea.y + this.solidArea.height;
        this.borderLeft = this.getPixelPosX() - this.getScreenPosX() + this.solidArea.x;
        this.borderRight = this.getPixelPosX() - this.getScreenPosX() + this.solidArea.x + this.solidArea.width;

        this.collisionCheck();

        if(!this.getCurrentMap().NPCs.isEmpty()) {
            for (NPC npc : this.getCurrentMap().NPCs) {
                this.checkNPC(npc);
            }
        }
        else{
            this.collisionNPC = true;
        }
        if(!this.getCurrentMap().objects.isEmpty()) {
            for (Object object : this.getCurrentMap().objects) {
                this.checkObject(object);
            }
        }
        else{
            this.collisionObj = true;
        }

        boolean isCollision = this.collisionTile && this.collisionNPC && this.collisionObj && !isInteracting;
        if (this.getControlHandler().scaleUp || this.getControlHandler().scaleDown) {
            this.setWalkSpeed(5 * Game.scale);
            this.setPixelPosX(getTilePosX() * Game.scaledTileSize);
            this.setPixelPosY(getTilePosY() * Game.scaledTileSize);
        }

        if (!this.isInteracting) {
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
        }
        if (collisionNPC)
            interactNPC = null;
        if (collisionObj)
            interactObj = null;
        this.setScreenPosX(Game.width/2D - Game.scaledTileSize/2D);
        this.setScreenPosY(Game.height/2D - Game.scaledTileSize/2D);
        Player.playerArea.setRect((int)screenPosX, (int)screenPosY, Game.scaledTileSize, Game.scaledTileSize);
        this.collisionNPC = true;
        this.collisionObj = true;
    }

    public void draw(Graphics2D renderer) {
        this.setSpriteOnAction();
        if (interactNPC != null) {
            interactNPC.interact(renderer, this);
        }
        if (interactObj != null) {
            if (this.getControlHandler().interact || interactObj.getType().equals("passive")){
                interactObj.interact(renderer, this);
                this.getControlHandler().interact = false;
            }
        }

        // draw player at center of screen
        renderer.drawImage(this.getSpriteOnAction(), playerArea.x, playerArea.y, playerArea.width, playerArea.height, null);
    }

    public void checkFowardTile() {
        if (this.getCurrentMap().name.equals("village")) {
            TileMap tileMap = this.getCurrentMap().tileMaps.get(1);
            Tile choosenTile = null;
            int centerX = Map.sceneX + Game.width / 2;
            int centerY = Map.sceneY + Game.height / 2;
            switch (this.getDirection()) {
                case "up" -> {
                    fowardTilePosX = this.pixelToTile(centerX);
                    fowardTilePosY = this.pixelToTile(this.borderTop);
                    forwardTile = TileManager.getTile((tileMap.map[fowardTilePosY][fowardTilePosX]) + "");
                }
                case "down" -> {
                    fowardTilePosX = this.pixelToTile(centerX);
                    fowardTilePosY = this.pixelToTile(this.borderBot);
                    forwardTile = TileManager.getTile((tileMap.map[fowardTilePosY][fowardTilePosX]) + "");
                }
                case "left" -> {
                    fowardTilePosX = this.pixelToTile(this.borderLeft);
                    fowardTilePosY = this.pixelToTile(centerY);
                    forwardTile = TileManager.getTile((tileMap.map[fowardTilePosY][fowardTilePosX]) + "");
                }
                case "right" -> {
                    fowardTilePosX = this.pixelToTile(this.borderRight);
                    fowardTilePosY = this.pixelToTile(centerY);
                    forwardTile = TileManager.getTile((tileMap.map[fowardTilePosY][fowardTilePosX]) + "");
                }
            }
        }
    }
    public void collisionCheck() {
        Tile tile1 = null;
        Tile tile2 = null;

        switch (this.getDirection()){
            case "up" -> {
                tile1 = TileManager.getTile((this.getCurrentMap().collisionTileMap.map[pixelToTile(borderTop)][pixelToTile(borderLeft)])+"");
                tile2 = TileManager.getTile((this.getCurrentMap().collisionTileMap.map[pixelToTile(borderTop)][pixelToTile(borderRight)])+"");
            }
            case "down" -> {
                tile1 = TileManager.getTile((this.getCurrentMap().collisionTileMap.map[pixelToTile(borderBot)][pixelToTile(borderLeft)])+"");
                tile2 = TileManager.getTile((this.getCurrentMap().collisionTileMap.map[pixelToTile(borderBot)][pixelToTile(borderRight)])+"");
            }
            case "left" -> {
                tile1 = TileManager.getTile((this.getCurrentMap().collisionTileMap.map[pixelToTile(borderTop)][pixelToTile(borderLeft)])+"");
                tile2 = TileManager.getTile((this.getCurrentMap().collisionTileMap.map[pixelToTile(borderBot)][pixelToTile(borderLeft)])+"");

            }
            case "right" -> {
                tile1 = TileManager.getTile((this.getCurrentMap().collisionTileMap.map[pixelToTile(borderTop)][pixelToTile(borderRight)])+"");
                tile2 = TileManager.getTile((this.getCurrentMap().collisionTileMap.map[pixelToTile(borderBot)][pixelToTile(borderRight)])+"");
            }
        }
        this.collisionTile = (((tile1 == null) || (tile2 == null)));
    }

    public void checkNPC(NPC target){
        Rectangle recIntersection;
        if (target.getJob().equals("merchant")) {
            NPCMerchant merchant = (NPCMerchant) target;
            if (playerArea.intersects(((NPCMerchant) target).saleArea)) {
                recIntersection = playerArea.intersection(merchant.saleArea);
                interactNPC = target;
                target.collisionNPC = false;
                merchant.onSale = true;
                collisionNPC(recIntersection, merchant.saleArea);
            } else if (playerArea.intersects(target.solidArea)) {
                recIntersection = playerArea.intersection(target.solidArea);
                interactNPC = target;
                target.collisionNPC = false;
                collisionNPC(recIntersection, merchant.solidArea);
            } else {
                target.collisionNPC = true;
            }
        }
        else {
            recIntersection = playerArea.intersection(target.solidArea);
            if (playerArea.intersects(target.solidArea)){
                interactNPC = target;
                target.collisionNPC = false;
                collisionNPC(recIntersection, target.solidArea);
            }
            else {
                target.collisionNPC = true;
            }
        }

    }

    public void collisionNPC(Rectangle recIntersection, Rectangle targetArea){
        switch (this.getDirection()){
            case "up" -> this.collisionNPC = recIntersection.y != playerArea.y || recIntersection.width <= recIntersection.height;
            case "down" -> this.collisionNPC = recIntersection.y != targetArea.y || recIntersection.width <= recIntersection.height;
            case "left" -> this.collisionNPC = recIntersection.x != playerArea.x || recIntersection.height <= recIntersection.width;
            case "right" -> this.collisionNPC = recIntersection.x != targetArea.x || recIntersection.height <= recIntersection.width;
        }
    }

    public void checkObject(Object obj){
        Rectangle recIntersection = playerArea.intersection(obj.solidArea);
        if(playerArea.intersects(obj.solidArea)){
            interactObj = obj;
            switch (this.getDirection()){
                case "up" -> this.collisionObj = recIntersection.y != playerArea.y || recIntersection.width <= recIntersection.height;
                case "down" -> this.collisionObj = recIntersection.y != obj.solidArea.y || recIntersection.width <= recIntersection.height;
                case "left" -> this.collisionObj = recIntersection.x != playerArea.x || recIntersection.height <= recIntersection.width;
                case "right" -> this.collisionObj = recIntersection.x != obj.solidArea.x || recIntersection.height <= recIntersection.width;
            }
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
        this.setCurrentMap(MapManager.getMap(destination));
    }


    public int pixelToTile(double pixel){
        return (int) pixel / Game.scaledTileSize;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
    }

    public int getSelectedItem() {
        return selectedItem;
    }

    public void run() {
        double refreshInterval =  Math.pow(10, 9) / Game.FPS; // capture refresh rate to max FPS in nanosecond

        // Game loop theory
        while (playerThread != null) {
            update();
            try {
                Thread.sleep((long) ((refreshInterval/Math.pow(10, 9)) * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Tile getForwardTile() {
        return forwardTile;
    }

    public int getFowardTilePosX() {
        return fowardTilePosX;
    }

    public int getFowardTilePosY() {
        return fowardTilePosY;
    }
}
