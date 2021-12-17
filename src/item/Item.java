package item;

import entity.Player;
import main.Game;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class Item implements Serializable {
    private String name;
    private transient BufferedImage image;
    private double buyPrice;
    private double sellPrice;
    private int spriteNumber;
    private String type;

    public Item(String name, double buyPrice, double sellPrice) {
        this.setName(name);
        this.setBuyPrice(buyPrice);
        this.setSellPrice(sellPrice);
        this.setSpriteNumber(0);
    }
    public abstract void active(Player player);

    abstract void loadImage();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getSpriteNumber() {
        return spriteNumber;
    }

    public void setSpriteNumber(int spriteNumber) {
        this.spriteNumber = spriteNumber;
    }

    public BufferedImage getSprite(int spriteNumber) {
        return this.getImage().getSubimage(spriteNumber * Game.tileSize, 0, Game.tileSize, Game.tileSize);
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }



}
