package entity;

import main.Game;
import tile.MainMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.awt.Transparency.TRANSLUCENT;

public class Object{
    private String name;
    protected boolean isActive = false;
    public BufferedImage activeImage, deactiveImage;
    public Rectangle solidArea;
    private double pixelPosX, pixelPosY;
    private double tilePosX, tilePosY;

    public Object(String name){
        this.name = name;
        objectInit();
    }

    public void objectInit(){
        this.solidArea = new Rectangle(0, 0, Game.scaledTileSize, Game.scaledTileSize);
        this.deactiveImage = loadImage(this.name.replace(" ", "_"));
        this.activeImage = loadImage(this.name.replace(" ", "_").concat("_active"));
    }

    public BufferedImage loadImage(String file) {
        try {
            BufferedImage image = ImageIO.read(new File("src/resource/object/" + file + ".png"));
            System.out.println("\u001B[32m" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + " - LOADED: object " + file + "\u001B[0m");
            return image;
        } catch (IOException e) {
            System.out.println("\u001B[31m" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + " - ERROR: object " + file + "\u001B[0m");
        }
        return null;
    }

    public void update(){

    }

    public void draw(Graphics2D renderer, Player player) {
        // get direction of npc
        // get pos npc should be on current scenario
        double screenX = this.getPixelPosX() - MainMap.sceneX;
        double screenY = this.getPixelPosY() - MainMap.sceneY;

        // check if npc is on scenario
        if (this.getPixelPosX() + Game.scaledTileSize > player.getPixelPosX() - player.getScreenPosX()
                && this.getPixelPosX() - Game.scaledTileSize < player.getPixelPosX() + player.getScreenPosX()
                && this.getPixelPosY() + Game.scaledTileSize > player.getPixelPosY() - player.getScreenPosY()
                && this.getPixelPosY() - Game.scaledTileSize < player.getPixelPosY() + player.getScreenPosY()){
            renderer.setColor(new Color(55, 55, 55, 0));
            if(Player.interactObj != null)
                if(Player.interactObj.equals(this))
                    renderer.setColor(Color.ORANGE);
            this.solidArea.setRect((int) screenX, (int) screenY, Game.scaledTileSize, Game.scaledTileSize);
            renderer.fillRect(this.solidArea.x, this.solidArea.y, this.solidArea.width, this.solidArea.height);
            renderer.drawImage(this.getActiveImage(), (int) screenX, (int) screenY, Game.scaledTileSize, Game.scaledTileSize, null);
        }
    }


    public BufferedImage getActiveImage(){
        if(isActive){
            return activeImage;
        }
        return deactiveImage;
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

}
