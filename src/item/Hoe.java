package item;

import entity.Player;
import main.Game;
import tile.Map;
import tile.Tile;
import tile.TileManager;
import tile.TileMap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Hoe extends Item {
    private int choosenTilePosX;
    private int choosenTilePosY;
    public Hoe() {
        super("Hoe", 0, 0);
        this.loadImage();
    }

    public void active(Player player){
        int[] caltivatable = {5, 6, 7, 13, 14, 15, 21, 22, 23};
        player.checkFowardTile();
        if(Arrays.binarySearch(caltivatable, player.getForwardTile().getTileNumber()) >= 0){
            TileMap tileMap = player.getCurrentMap().tileMaps.get(1);
            tileMap.map[player.getFowardTilePosY()][player.getFowardTilePosX()] = 117;
        }
        player.getControlHandler().activeItem = false;
    }

    @Override
    void loadImage() {
        try {
            this.setImage(ImageIO.read(new File("res/item/" + this.getName().toLowerCase().replaceAll(" ", "_") + ".png")));
            System.out.println("\u001B[32m" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + " - LOADED: item " + this.getName().toLowerCase().replaceAll(" ", "_") + "\u001B[0m");
        } catch (IOException e) {
            System.out.println("\u001B[31m" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + " - ERROR: item " + this.getName().toLowerCase().replaceAll(" ", "_") + " not found" + "\u001B[0m");
        }
    }
}
