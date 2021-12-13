package entity;

import main.Game;

import java.awt.*;

public class NPC extends Human {
    private String job;
    private int moveAble;
    private int movedX;
    private int movedY;

    public NPC(String name, String gender, String job){
        this.setName(name);
        this.setGender(gender);
        npcInit();
    }

    public void npcInit(){
        this.solidArea = new Rectangle();
        this.loadSprite(this.getName());
        this.setWalkSpeed(Game.scale / 2);
        this.setMaxActionFrame(3);
        this.setSpriteLoadTime(Game.FPS/this.getMaxActionFrame());
    }

    public void draw(Graphics2D g2d, int sceneX, int sceneY, Player player) {
        // get direction of npc
        this.setSpriteOnAction();

        // get pos npc should be on current scenario
        double screenX = this.getPixelPosX() - sceneX;
        double screenY = this.getPixelPosY() - sceneY;

        // check if npc is on scenario
        if(this.getPixelPosX() + Game.scaledTileSize > player.getPixelPosX() - player.getScreenPosX()
        && this.getPixelPosX() - Game.scaledTileSize < player.getPixelPosX() + player.getScreenPosX()
        && this.getPixelPosY() + Game.scaledTileSize > player.getPixelPosY() - player.getScreenPosY()
        && this.getPixelPosY() - Game.scaledTileSize < player.getPixelPosY() + player.getScreenPosY()){
            g2d.drawImage(this.getSpriteOnAction(), (int) screenX, (int) screenY, Game.scaledTileSize, Game.scaledTileSize, null);
            this.solidArea.setRect((int) screenX, (int) screenY, Game.scaledTileSize, Game.scaledTileSize);

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
        this.moveAble = (moveAble * Game.scaledTileSize)/this.getWalkSpeed();
    }

    public void update(){
//        if(this.collisionEntity){
//            this.move();
//        }
        this.setWalkSpeed(2* Game.scale);
    }

    public void move(){
        if(this.movedX < this.moveAble && this.movedY == 0) {
            this.setPixelPosX((this.getTilePosX() * Game.scaledTileSize) + this.getWalkSpeed());
            this.movedX++;
            this.setDirection("right");
        }
        else if(this.movedY < this.moveAble && this.movedX == moveAble){
            this.setPixelPosY((this.getTilePosY() * Game.scaledTileSize) + this.getWalkSpeed());
            this.movedY++;
            this.setDirection("down");
        }
        else if(this.movedX > 0){
            this.setPixelPosX((this.getTilePosX() * Game.scaledTileSize) - this.getWalkSpeed());
            this.movedX--;
            this.setDirection("left");
        }
        else{
            this.setPixelPosY((this.getTilePosY() * Game.scaledTileSize) - this.getWalkSpeed());
            this.movedY--;
            this.setDirection("up");
        }
    }
}
