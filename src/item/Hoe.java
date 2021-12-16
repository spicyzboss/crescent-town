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
    private boolean isCaltivateArea;
    private int choosenTilePosX;
    private int choosenTilePosY;
    public Hoe() {
        super("Hoe", 0, 0);
        this.loadImage();
    }

    public void active(Player player){
        checkCaltivateArea(player);
        if(this.isCaltivateArea){
            TileMap tileMap = player.getCurrentMap().tileMaps.get(1);
            tileMap.map[choosenTilePosY][choosenTilePosX] = 117;
        }
        player.getControlHandler().activeItem = false;
    }

    public void checkCaltivateArea(Player player){
        if(player.getCurrentMap().name.equals("village")){
            int[] caltivatable = {5, 6, 7, 13, 14, 15, 21, 22, 23};
            TileMap tileMap = player.getCurrentMap().tileMaps.get(1);
            Tile choosenTile = null;
            int centerX = Map.sceneX + Game.width/2;
            int centerY = Map.sceneY + Game.height/2;
            switch (player.getDirection()){
                case "up" -> {
                    choosenTilePosX = player.pixelToTile(centerX);
                    choosenTilePosY = player.pixelToTile(player.borderTop);
                    choosenTile = TileManager.getTile((tileMap.map[choosenTilePosY][choosenTilePosX])+"");
                }
                case "down" -> {
                    choosenTilePosX = player.pixelToTile(centerX);
                    choosenTilePosY = player.pixelToTile(player.borderBot);
                    choosenTile = TileManager.getTile((tileMap.map[choosenTilePosY][choosenTilePosX])+"");
                }
                case "left" -> {
                    choosenTilePosX = player.pixelToTile(player.borderLeft);
                    choosenTilePosY = player.pixelToTile(centerY);
                    choosenTile = TileManager.getTile((tileMap.map[choosenTilePosY][choosenTilePosX])+"");
                }
                case "right" -> {
                    choosenTilePosX = player.pixelToTile(player.borderRight);
                    choosenTilePosY = player.pixelToTile(centerY);
                    choosenTile = TileManager.getTile((tileMap.map[choosenTilePosY][choosenTilePosX])+"");
                }
            }
            if(choosenTile != null) {
                isCaltivateArea = Arrays.binarySearch(caltivatable, choosenTile.getTileNumber()) >= 0;
                System.out.println(Arrays.binarySearch(caltivatable, choosenTile.getTileNumber()));
                System.out.println(choosenTile.getTileNumber());
            }
        }
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
