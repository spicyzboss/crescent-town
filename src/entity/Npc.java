package entity;

import main.GameControlHandler;
import main.Map;
import org.w3c.dom.ls.LSOutput;
import tile.MainMap;

import java.awt.*;

public class Npc extends Human{
    private String job;
    private int moveAble;
    private int movedX;
    private int movedY;

    public Npc(String name, String gender, String job){
        this.setName(name);
        this.setGender(gender);
        npcInit();
    }

    public void npcInit(){
        this.loadSprite(this.getName());
        this.setWalkSpeed(2*Map.scale);
    }

    public void draw(Graphics2D g2d, int sceneX, int sceneY, Player player) {
        // get direction of npc
        this.setSpriteOnAction();

        // get pos npc should be on current scenario
        double screenX = this.getPixelPosX() - sceneX;
        double screenY = this.getPixelPosY() - sceneY;

        // check if npc is on scenario
        if(this.getPixelPosX() + Map.scaledTileSize > player.getPixelPosX() - player.getScreenPosX()
        && this.getPixelPosX() - Map.scaledTileSize < player.getPixelPosX() + player.getScreenPosX()
        && this.getPixelPosY() + Map.scaledTileSize > player.getPixelPosY() - player.getScreenPosY()
        && this.getPixelPosY() - Map.scaledTileSize < player.getPixelPosY() + player.getScreenPosY()){
            g2d.drawImage(this.getSpriteOnAction(), (int) screenX, (int) screenY, Map.scaledTileSize, Map.scaledTileSize, null);
        }
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getMoveAble() {
        return moveAble;
    }

    public void setMoveAble(int moveAble) {
        this.moveAble = (moveAble*Map.scaledTileSize)/this.getWalkSpeed();
    }

    public void update(){
        this.move();
    }

    public void move(){
        if(this.movedX < this.moveAble && this.movedY == 0) {
            this.setPixelPosX((this.getTilePosX() * Map.scaledTileSize) + this.getWalkSpeed());
            this.movedX++;
            this.setDirection("right");
        }
        else if(this.movedY < this.moveAble && this.movedX == moveAble){
            this.setPixelPosY((this.getTilePosY() * Map.scaledTileSize) + this.getWalkSpeed());
            this.movedY++;
            this.setDirection("down");
        }
        else if(this.movedX > 0){
            this.setPixelPosX((this.getTilePosX() * Map.scaledTileSize) - this.getWalkSpeed());
            this.movedX--;
            this.setDirection("left");
        }
        else{
            this.setPixelPosY((this.getTilePosY() * Map.scaledTileSize) - this.getWalkSpeed());
            this.movedY--;
            this.setDirection("up");
        }
    }
}
