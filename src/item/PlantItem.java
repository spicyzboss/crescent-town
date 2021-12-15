package item;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PlantItem extends Item {
    private int growthState;

    public PlantItem(String name, double buyPrice, double sellPrice, int growthState) {
        super(name, buyPrice, sellPrice);
        this.setGrowthState(growthState);
        loadImage();
    }

    public void setGrowthState(int growthState) {
        this.growthState = growthState;
    }

    public int getGrowthState() {
        return growthState;
    }

    public void loadImage() {
        try {
            this.setImage(ImageIO.read(new File("res/plant/" + this.getName().toLowerCase().replaceAll(" ", "_") + ".png")));
            System.out.println("\u001B[32m" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + " - LOADED: plant " + this.getName().toLowerCase().replaceAll(" ", "_") + "\u001B[0m");
        } catch (IOException e) {
            System.out.println("\u001B[31m" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + " - ERROR: plant " + this.getName().toLowerCase().replaceAll(" ", "_") + " not found" + "\u001B[0m");
        }
    }
}
