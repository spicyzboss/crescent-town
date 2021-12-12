package tile;

import entity.Player;
import main.Map;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class MainMap {
//    TileMap[] tileMaps;
    public ArrayList<TileMap> tileMaps;

    public MainMap() {
        tileMaps = new ArrayList<TileMap>();

        loadMap();
    }

    private void loadMap() {
        this.addMap("village_layer_1", 100, 100);
        this.addMap("village_layer_2", 100, 100);
        this.addMap("village_layer_3", 100, 100);
        this.addMap("village_layer_4", 100, 100);
        this.addMap("village_layer_collision", 100, 100);
    }

    private void addMap(String mapName, int width, int height) {
        this.getTileMaps().add(new TileMap(mapName, width, height));
    }

    public ArrayList<TileMap> getTileMaps() {
        return tileMaps;
    }

    public void draw(Graphics2D renderer, Player player, int layer){
        for (int row = 0; row < this.getTileMaps().get(layer).mapHeight; row++) {
            for (int col = 0; col < this.getTileMaps().get(layer).mapWidth; col++) {
                int tileNumber = this.getTileMaps().get(layer).map[row][col];
                Tile tile = TileManager.getTileByNumber(tileNumber);
                if (tile != null) {
                    BufferedImage tileImage = tile.getImage();
                    // draw tile in screen
                    renderer.drawImage(tileImage,(col * Map.scaledTileSize) - (int)player.getPixelPosX() + (int)player.getScreenPosX(), (row * Map.scaledTileSize) - (int)player.getPixelPosY() + (int)player.getScreenPosY(), Map.scaledTileSize, Map.scaledTileSize, null);
                }
            }
        }
    }

    public void render(Graphics2D renderer, Player player){
        for (int layer = 0; layer < this.getTileMaps().size(); layer++){
            // draw map in each layer
            this.draw(renderer, player, layer);
        }
        player.draw(renderer);
    }
}
