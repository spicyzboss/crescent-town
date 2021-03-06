package item;

import entity.Player;
import main.MapManager;
import object.Plant;

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
    private int maxGrowthState;
    private boolean isGrowth = false;

    public PlantItem(String name, double buyPrice, double sellPrice, int growthDuration, int maxGrowthState) {
        super(name, buyPrice, sellPrice);
        this.setGrowthDuration(growthDuration);
        this.setMaxGrowthState(maxGrowthState);
        this.loadImage();
        this.setType("plant");
    }

    @Override
    public void active(Player player) {
        if(!this.isGrowth()) {
            player.checkFowardTile();
            if (player.getForwardTile().getTileNumber() == 117) {
                if (MapManager.maps.get("village").tileMaps.get(2).map[player.getFowardTilePosY()][player.getFowardTilePosX()] != -2) {
                    Plant plant = new Plant(this);
                    plant.setTilePosX(player.getFowardTilePosX());
                    plant.setTilePosY(player.getFowardTilePosY());
                    plant.setMap("village");
                    plant.setType("active");
                    MapManager.objects.add(plant);
                    player.getInventory().removeItem(this);
                }
                MapManager.maps.get("village").tileMaps.get(2).map[player.getFowardTilePosY()][player.getFowardTilePosX()] = -2;
            }
        }
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

    public int getMaxGrowthState() {
        return maxGrowthState;
    }

    public void setMaxGrowthState(int maxGrowthState) {
        this.maxGrowthState = maxGrowthState;
    }

    public void setGrowth(boolean growth) {
        isGrowth = growth;
    }

    public boolean isGrowth() {
        return isGrowth;
    }
}
