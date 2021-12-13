package entity;

import main.Game;
import main.GameControlHandler;
import tile.*;

import java.awt.*;

public class Player extends Human {
    public static MainMap currentMap;

    private double screenPosX;
    private double screenPosY;
    private double collisionBoxTop;
    private double collisionBoxLeft;
    private double collisionBoxBottom;
    private double collisionBoxRight;
    private int energy;

    private GameControlHandler controlHandler;


    public Player(String name, GameControlHandler controlHandler) {
        setName(name);
        setControlHandler(controlHandler);
        loadSprite(name);
        playerInit();
    }

    public void playerInit() {
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
        if (this.getControlHandler().scaleUp || this.getControlHandler().scaleDown) {
            this.setWalkSpeed(2 * Game.scale);
            this.setPixelPosX(getTilePosX() * Game.scaledTileSize);
            this.setPixelPosY(getTilePosY() * Game.scaledTileSize);
        }
        if (this.getControlHandler().upKeyPressed) {
            this.setDirection("up");
            if (this.collisionCheck())
                this.setPixelPosY((this.getTilePosY() * Game.scaledTileSize) - this.getWalkSpeed());
        } else if (this.getControlHandler().downKeyPressed) {
            this.setDirection("down");
            if (this.collisionCheck())
                this.setPixelPosY((this.getTilePosY() * Game.scaledTileSize) + this.getWalkSpeed());
        } else if (this.getControlHandler().leftKeyPressed) {
            this.setDirection("left");
            if (this.collisionCheck())
                this.setPixelPosX((this.getTilePosX() * Game.scaledTileSize) - this.getWalkSpeed());
        } else if (this.getControlHandler().rightKeyPressed) {
            this.setDirection("right");
            if (this.collisionCheck())
                this.setPixelPosX((this.getTilePosX() * Game.scaledTileSize) + this.getWalkSpeed());
        }
        // update screen pos for scale size
        this.setScreenPosX((double) Game.width/2 - (double) Game.scaledTileSize/2);
        this.setScreenPosY((double) Game.height/2 - (double) Game.scaledTileSize/2);
    }

    public void draw(Graphics2D renderer) {
        this.setSpriteOnAction();
        // draw player at center of screen
        renderer.drawImage(this.getSpriteOnAction(), (int)this.getScreenPosX(), (int)this.getScreenPosY(), Game.scaledTileSize, Game.scaledTileSize, null);
        renderer.fillRect(Game.width/2 - Game.scaledTileSize/2, Game.height/2 + Game.scaledTileSize/4, Game.scaledTileSize, Game.scaledTileSize/4);
    }

    private boolean collisionCheck() {
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
        return tile != 293;
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

    public void setCollisionBoxTop(double collisionBoxTop) {
        this.collisionBoxTop = collisionBoxTop;
    }

    public double getCollisionBoxTop() {
        return collisionBoxTop;
    }

    public void setCollisionBoxLeft(double collisionBoxLeft) {
        this.collisionBoxLeft = collisionBoxLeft;
    }

    public double getCollisionBoxLeft() {
        return collisionBoxLeft;
    }

    public void setCollisionBoxBottom(double collisionBoxBottom) {
        this.collisionBoxBottom = collisionBoxBottom;
    }

    public double getCollisionBoxBottom() {
        return collisionBoxBottom;
    }

    public void setCollisionBoxRight(double collisionBoxRight) {
        this.collisionBoxRight = collisionBoxRight;
    }

    public double getCollisionBoxRight() {
        return collisionBoxRight;
    }
}
