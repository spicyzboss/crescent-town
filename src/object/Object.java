package object;

import entity.Interactable;
import entity.Player;
import main.Game;
import main.GameControlHandler;
import tile.Map;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Object implements Interactable {
    protected String fileName;
    private String type;
    private String map;
    public Rectangle solidArea;
    protected BufferedImage image;
    private double pixelPosX, pixelPosY, tilePosX, tilePosY;
    public Object(String fileName){
        this.fileName = fileName;
        this.solidArea = new Rectangle(0, 0, Game.scaledTileSize, Game.scaledTileSize);
    }

    public BufferedImage loadImage(String file) {
        try {
            BufferedImage image = ImageIO.read(new File("res/object/" + file + ".png"));
            System.out.println("\u001B[32m" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + " - LOADED: object " + file + "\u001B[0m");
            return image;
        } catch (IOException e) {
            System.out.println("\u001B[31m" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + " - ERROR: object " + file + "\u001B[0m");
        }
        return null;
    }

    public void draw(Graphics2D renderer, Player player) {
        // get direction of npc
        // get pos npc should be on current scenario
        double screenX = this.getPixelPosX() - Map.sceneX;
        double screenY = this.getPixelPosY() - Map.sceneY;

        // check if npc is on scenario
        if (this.getPixelPosX() + Game.scaledTileSize > player.getPixelPosX() - player.getScreenPosX()
                && this.getPixelPosX() - Game.scaledTileSize < player.getPixelPosX() + player.getScreenPosX()
                && this.getPixelPosY() + Game.scaledTileSize > player.getPixelPosY() - player.getScreenPosY()
                && this.getPixelPosY() - Game.scaledTileSize < player.getPixelPosY() + player.getScreenPosY()){
            renderer.setColor(new Color(55, 55, 55, 70));
            if(Player.interactObj != null)
                if(Player.interactObj.equals(this))
                    renderer.setColor(Color.ORANGE);
            this.solidArea.setRect((int) screenX, (int) screenY, this.solidArea.width, this.solidArea.height);
            renderer.fillRect(this.solidArea.x, this.solidArea.y, this.solidArea.width, this.solidArea.height);
            renderer.drawImage(this.getImage(), (int) screenX, (int) screenY, this.solidArea.width, this.solidArea.height, null);
        }
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
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

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public void resetPlayer(Player player){
        Player.interactObj = null;
        player.getControlHandler().interact = false;
    }

    public abstract void interact(Graphics2D renderer, Player player);
}
