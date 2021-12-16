package object;

import entity.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Plant extends Object {
    private int growthDuaration;

    public Plant(String name, int growthDuaration) {
        super(name);
        this.growthDuaration = growthDuaration;
    }



    @Override
    public void interact(Graphics2D renderer, Player player) {

    }
}
