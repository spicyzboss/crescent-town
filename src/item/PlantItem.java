package item;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class PlantItem extends Item {
    // growth duration in frame unit
    // 1 second = 60 frame
    // 1 day in game = 28800 frame = 8 mins
    private int growthDuration;

    public PlantItem(String name, double buyPrice, double sellPrice, int growthDuration) {
        super(name, buyPrice, sellPrice);
        this.setGrowthDuration(growthDuration);
        this.loadImage();
    }

    public void loadImage() {
        try {
            this.setImage(ImageIO.read(new File("res/plant/" + this.getName().toLowerCase().replaceAll(" ", "_") + ".png")));
            System.out.println("\u001B[32m" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + " - LOADED: plant " + this.getName().toLowerCase().replaceAll(" ", "_") + "\u001B[0m");
        } catch (IOException e) {
            System.out.println("\u001B[31m" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + " - ERROR: plant " + this.getName().toLowerCase().replaceAll(" ", "_") + " not found" + "\u001B[0m");
        }
    }

    public int getGrowthDuration() {
        return growthDuration;
    }

    public void setGrowthDuration(int growthDuration) {
        this.growthDuration = growthDuration;
    }
}
