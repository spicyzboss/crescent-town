package object;

import entity.Player;
import item.PlantItem;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Plant extends Object implements Runnable {
    private PlantItem plant;
    private int growth;
    private Thread plantThread;
    private int growthPerStage;
    private int growthStage;

    public Plant(PlantItem plant) {
        super(plant.getName());
        this.setPlant(plant);
        this.setImage(plant.getSprite(0));
        this.setGrowthPerStage(this.getPlant().getGrowthDuration()/(this.getPlant().getMaxGrowthState() - 1));
        this.setGrowthStage(0);
        plantThread = new Thread(this);
        plantThread.start();
    }

    @Override
    public void interact(Graphics2D renderer, Player player) {
        if (this.isCollectable()) {
            if (player.getInventory().isAvailable()) {
//                player.getInventory().addItem();
            }
        }
    }

    public PlantItem getPlant() {
        return plant;
    }

    public void setPlant(PlantItem plant) {
        this.plant = plant;
    }

    private void update() {
        if (this.getGrowth() < this.getPlant().getGrowthDuration()) {
            this.setGrowth(this.getGrowth() + 1);
            this.setGrowthStage(this.getGrowth()/this.getGrowthPerStage());
            this.setImage(this.getPlant().getSprite(this.getGrowthStage()));
        }
    }

    public boolean isCollectable() {
        return this.getPlant().getMaxGrowthState() == this.getGrowthStage();
    }

    @Override
    public void run() {
        double refreshInterval =  Math.pow(10, 9) / Game.FPS; // capture refresh rate to max FPS in nanosecond

        // Game loop theory
        while (plantThread != null) {
            update();
            try {
                Thread.sleep((long) ((refreshInterval/Math.pow(10, 9)) * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getGrowth() {
        return growth;
    }

    public void setGrowth(int growth) {
        this.growth = growth;
    }

    public int getGrowthPerStage() {
        return growthPerStage;
    }

    public void setGrowthPerStage(int growthPerStage) {
        this.growthPerStage = growthPerStage;
    }

    public int getGrowthStage() {
        return growthStage;
    }

    public void setGrowthStage(int growthStage) {
        this.growthStage = growthStage;
    }
}
