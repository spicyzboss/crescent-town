package entity;

import inventory.Inventory;
import main.Game;
import main.GameControlHandler;
import main.GameUI;
import tile.Map;

import java.awt.*;
import java.util.ArrayList;

public class NPC extends Human implements Interactable {
    private String job;
    private boolean moving;
    protected int moveAble;
    protected int movedX;
    protected int movedY;
    protected int moveChangeState = 1;
    protected String pattern;
    private String map;
    public ArrayList<String> dialog;

    public NPC(String name, String gender, String job){
        this.setName(name);
        this.setGender(gender);
        this.setJob(job);
        this.setInventory(new Inventory(5));
        this.setMoving(false);
        NPCInit();
    }

    public void NPCInit(){
        this.solidArea = new Rectangle();
        this.dialog = new ArrayList<>();
        this.loadSprite(this.getName());
        this.setWalkSpeed(Game.scale / 2);
        this.setMaxActionFrame(3);
        this.setSpriteLoadTime(Game.FPS/this.getMaxActionFrame());
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isMoving() {
        return moving;
    }

    public void addDialog(String text){
        this.dialog.add(text);
    }

    public String getDialog(int index){
        return  this.dialog.get(index);
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
            g2d.setColor(Color.ORANGE);
            g2d.fillRect(this.solidArea.x, this.solidArea.y, this.solidArea.width, this.solidArea.height);
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
        if(this.collisionNPC && moving) this.move(pattern);
        this.setWalkSpeed(2* Game.scale);
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public void move(String pattern) {
        switch (pattern) {
            case "rectangle" -> {
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
            case "find direction" -> {
                if (this.movedX < this.moveAble && this.movedY == 0) {
                    this.movedX++;
                    this.setDirection("right");
                } else if (this.movedY < this.moveAble && this.movedX == moveAble) {
                    this.movedY++;
                    this.setDirection("down");
                } else if (this.movedX > 0) {
                    this.movedX--;
                    this.setDirection("left");
                } else {
                    this.movedY--;
                    this.setDirection("up");
                }
            }
            case "line-x" -> {
                if (this.movedX <= 0) {
                    moveChangeState = 1;
                    setDirection("right");

                } else if (this.movedX >= moveAble) {
                    moveChangeState = -1;
                    setDirection("left");
                }
                this.setPixelPosX((this.getTilePosX() * Game.scaledTileSize) + this.getWalkSpeed()*moveChangeState);
                movedX += moveChangeState;
            }
            case "line-y" -> {
                if (this.movedY <= 0) {
                    moveChangeState = 1;
                    setDirection("up");
                } else if (this.movedY >= moveAble) {
                    moveChangeState = -1;
                    setDirection("down");

                }
                this.setPixelPosY((this.getTilePosX() * Game.scaledTileSize) + this.getWalkSpeed()*moveChangeState);
                movedY += moveChangeState;

            }

        }
    }

    public void setMap(String maps){
        this.map = maps;
    }
    public String getMap(){
        return this.map;
    }

    public void interact(Graphics2D renderer, Player player){
        if (GameControlHandler.interact) {
            player.collisionNPC = false;
            player.isInteracting = true;
            switch (player.getDirection()) {
                case "up" -> this.setDirection("down");
                case "down" -> this.setDirection("up");
                case "left" -> this.setDirection("right");
                case "right" -> this.setDirection("left");
            }

            if(!this.dialog.isEmpty() && GameControlHandler.dialog != 0) {
                if (GameControlHandler.dialog-1 < this.dialog.size()) {
                    GameUI.drawDialog(renderer, this.getDialog(GameControlHandler.dialog-1));
                } else {
                    GameControlHandler.dialog = 0;
                    player.collisionNPC = true;
                    player.isInteracting = false;
                    GameControlHandler.interact = false;
                }
            }
            else{
                GameUI.drawDialog(renderer, "...");
                GameControlHandler.dialog = 0;
                player.collisionNPC = true;
                player.isInteracting = false;
            }
        }
    }
}
