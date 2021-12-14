package entity;

import main.Game;
import main.GameControlHandler;

import java.awt.*;
import java.util.ArrayList;

public class Player extends Human {

    public static double screenPosX;
    public static double screenPosY;
    public static Interactable interactEntity;

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
        this.collisionCheck();
        ArrayList<NPC> npcs = this.getCurrentMap().npcs;
        for (NPC npc : npcs) {
            this.checkEntity(npc);
        }
        if (this.getControlHandler().scaleUp || this.getControlHandler().scaleDown) {
            this.setWalkSpeed(2 * Game.scale);
            this.setPixelPosX(getTilePosX() * Game.scaledTileSize);
            this.setPixelPosY(getTilePosY() * Game.scaledTileSize);
        }
        if (this.getControlHandler().upKeyPressed) {
            this.setDirection("up");
            if (this.collisionObj && this.collisionEntity) {
                this.setPixelPosY((this.getTilePosY() * Game.scaledTileSize) - this.getWalkSpeed());

            }
        } else if (this.getControlHandler().downKeyPressed) {
            this.setDirection("down");
            if (this.collisionObj && this.collisionEntity)
                this.setPixelPosY((this.getTilePosY() * Game.scaledTileSize) + this.getWalkSpeed());
        } else if (this.getControlHandler().leftKeyPressed) {
            this.setDirection("left");
            if (this.collisionEntity && this.collisionObj)
                this.setPixelPosX((this.getTilePosX() * Game.scaledTileSize) - this.getWalkSpeed());
        } else if (this.getControlHandler().rightKeyPressed) {
            this.setDirection("right");
            if (this.collisionEntity && this.collisionObj)
                this.setPixelPosX((this.getTilePosX() * Game.scaledTileSize) + this.getWalkSpeed());
        }
        this.borderTop = this.getPixelPosY() - this.getScreenPosY() + this.solidArea.y;
        this.borderBot = this.getPixelPosY() - this.getScreenPosY() + this.solidArea.y + this.solidArea.height;
        this.borderLeft = this.getPixelPosX() - this.getScreenPosX() + this.solidArea.x;
        this.borderRight = this.getPixelPosX() - this.getScreenPosX() + this.solidArea.x + this.solidArea.width;
        this.setScreenPosX((double) Game.width/2 - (double) Game.scaledTileSize/2);
        this.setScreenPosY((double) Game.height/2 - (double) Game.scaledTileSize/2);
        playerArea.setRect((int)screenPosX, (int)screenPosY, Game.scaledTileSize, Game.scaledTileSize);
    }

    public void draw(Graphics2D renderer) {
        this.setSpriteOnAction();
        if(this.getControlHandler().interact && !collisionEntity){
            if(this.interactEntity != null)
                interactEntity.interact(renderer);
        }
        // draw player at center of screen
        renderer.drawImage(this.getSpriteOnAction(), playerArea.x, playerArea.y, playerArea.width, playerArea.height, null);
//        renderer.setColor(Color.CYAN);
//        renderer.fillRect(playerArea.x, playerArea.y, playerArea.width, playerArea.height);
//        renderer.setColor(Color.red);
//        renderer.fillRect(this.solidArea.x, this.solidArea.y, this.solidArea.width, this.solidArea.height);
    }

    public void checkEntity(Entity target){
        Rectangle recIntersection = playerArea.intersection(target.solidArea);
        if(playerArea.intersects(target.solidArea)){
            if(target.getClass().getInterfaces()[0].getSimpleName().equals("Interactable")) {
                this.interactEntity = (Interactable) target;
            }

            target.collisionEntity = false;
            switch (this.getDirection()){
                case "up" -> {
                    if(recIntersection.y == playerArea.y && recIntersection.width > recIntersection.height)
                        this.collisionEntity = false;
                    else
                        this.collisionEntity = true;
                }
                case "down" -> {
                    if(recIntersection.y == target.solidArea.y && recIntersection.width > recIntersection.height)
                        this.collisionEntity = false;
                    else
                        this.collisionEntity = true;
                }
                case "left" -> {
                    if(recIntersection.x == playerArea.x && recIntersection.height > recIntersection.width)
                        this.collisionEntity = false;
                    else
                        this.collisionEntity = true;
                }
                case "right" -> {
                    if(recIntersection.x == target.solidArea.x &&  recIntersection.height > recIntersection.width)
                        this.collisionEntity = false;
                    else
                        this.collisionEntity = true;
                }
            }
        }
        else{
            this.collisionEntity = true;
            this.interactEntity = null;
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

}
