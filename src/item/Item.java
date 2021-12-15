package item;

import main.Game;

import java.awt.image.BufferedImage;

public abstract class Item {
    private String name;
    private BufferedImage image;
    private double buyPrice;
    private double sellPrice;
    private int spriteNumber;

    public Item(String name, double buyPrice, double sellPrice) {
        this.setName(name);
        this.setBuyPrice(buyPrice);
        this.setSellPrice(sellPrice);
        this.setSpriteNumber(0);
    }

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
}
