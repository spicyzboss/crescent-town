package object;

import entity.Player;
import item.PlantItem;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Plant extends Object {
    private  PlantItem plant;

    public Plant(PlantItem plant) {
        super(plant.getName());
        this.setPlant(plant);
        this.setImage(plant.getSprite(0));
    }

    @Override
    public void interact(Graphics2D renderer, Player player) {

    }

    public PlantItem getPlant() {
        return plant;
    }

    public void setPlant(PlantItem plant) {
        this.plant = plant;
    }
}
