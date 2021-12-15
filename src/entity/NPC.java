package entity;

import main.Game;
import main.GameControlHandler;
import main.GameUI;
import tile.Map;

import java.awt.*;

public class NPC extends Human implements Interactable {
    private String job;
    private int moveAble;
    private int movedX;
    private int movedY;
    private String[] map;

    public NPC(String name, String gender, String job){
        this.setName(name);
        this.setGender(gender);
        NPCInit();
    }

    public void NPCInit(){
        this.solidArea = new Rectangle();
        this.loadSprite(this.getName());
        this.setWalkSpeed(Game.scale / 2);
        this.setMaxActionFrame(3);
        this.setSpriteLoadTime(Game.FPS/this.getMaxActionFrame());
    }

    public void draw(Graphics2D g2d, Player player) {
        // get direction of npc
        this.setSpriteOnAction();

        // get pos npc should be on current scenario
        double screenX = this.getPixelPosX() - Map.sceneX;
        double screenY = this.getPixelPosY() - Map.sceneY;

        // check if npc is on scenario
        if (this.getPixelPosX() + Game.scaledTileSize > player.getPixelPosX() - player.getScreenPosX()
        && this.getPixelPosX() - Game.scaledTileSize < player.getPixelPosX() + player.getScreenPosX()
        && this.getPixelPosY() + Game.scaledTileSize > player.getPixelPosY() - player.getScreenPosY()
        && this.getPixelPosY() - Game.scaledTileSize < player.getPixelPosY() + player.getScreenPosY()){
            this.solidArea.setRect((int) screenX, (int) screenY, Game.scaledTileSize, Game.scaledTileSize);
//            g2d.fillRect(this.solidArea.x, this.solidArea.y, this.solidArea.width, this.solidArea.height);
            g2d.drawImage(this.getSpriteOnAction(), (int) screenX, (int) screenY, Game.scaledTileSize, Game.scaledTileSize, null);
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
        this.moveAble = ((moveAble * Game.scaledTileSize)/this.getWalkSpeed())/Game.scale/2;
    }

    public void update(){
        if(this.collisionEntity){
            this.move();
        }
        this.setWalkSpeed(2* Game.scale);
    }

    public void move() {
        if (this.movedX < this.moveAble && this.movedY == 0) {
            this.setPixelPosX((this.getTilePosX() * Game.scaledTileSize) + this.getWalkSpeed());
            this.movedX++;
            this.setDirection("right");
        } else if (this.movedY < this.moveAble && this.movedX == moveAble) {
            this.setPixelPosY((this.getTilePosY() * Game.scaledTileSize) + this.getWalkSpeed());
            this.movedY++;
            this.setDirection("down");
        } else if (this.movedX > 0) {
            this.setPixelPosX((this.getTilePosX() * Game.scaledTileSize) - this.getWalkSpeed());
            this.movedX--;
            this.setDirection("left");
        } else {
            this.setPixelPosY((this.getTilePosY() * Game.scaledTileSize) - this.getWalkSpeed());
            this.movedY--;
            this.setDirection("up");
        }
    }

    public void interact(Graphics2D renderer, Player player){
        if(GameControlHandler.interact){
            player.collisionEntity = false;
            switch (GameControlHandler.dialog){
                case 1 -> {
                    GameUI.drawDialog(renderer, "สวัสดีชั้นชื่อ Mongo เป็นพ่อค้า");
                }
                case 2 -> {
                    GameUI.drawDialog(renderer, "ว่าไงนะแกว่าชั้นว่าหน้าโฉดเกินจะเป็นพ่อค้างั้นหรอ");
                }
                case 3 -> {
                    GameUI.drawDialog(renderer, "บังอาจ!!!!");
                }
                case 4 -> {
                    GameUI.drawDialog(renderer, "...");
                }
                default -> {
                    GameControlHandler.dialog  = 0;
                    player.collisionEntity = true;
                }
            }
        }

    }
    public void setMap(String[] maps){
        this.map = maps;
    }
    public String[] getMap(){
        return this.map;
    }
}
