package tile;

import entity.Npc;
import entity.Player;
import main.Map;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class MainMap {
    public ArrayList<TileMap> tileMaps;
    public ArrayList<Npc> npcs;
    public int mapWidth, mapHeight;

    public MainMap(int mapWidth, int mapHeight) {
        tileMaps = new ArrayList<TileMap>();
        npcs = new ArrayList<Npc>();
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        loadMap();
    }

    public void addNpc(Npc npc){
        this.npcs.add(npc);
    }

    private void loadMap() {
        this.addMap("village_layer_1", mapWidth, mapHeight);
        this.addMap("village_layer_2", mapWidth, mapHeight);
        this.addMap("village_layer_3", mapWidth, mapHeight);
        this.addMap("village_layer_4", mapWidth, mapHeight);
        this.addMap("village_layer_collision", mapWidth, mapHeight);
    }

    private void addMap(String mapName, int width, int height) {
        this.getTileMaps().add(new TileMap(mapName, width, height));
    }

    public ArrayList<TileMap> getTileMaps() {
        return tileMaps;
    }

    public void draw(Graphics2D renderer, int sceneX, int sceneY, int layer){
        for (int row = 0; row < this.getTileMaps().get(layer).mapHeight; row++) {
            for (int col = 0; col < this.getTileMaps().get(layer).mapWidth; col++) {
                int tileNumber = this.getTileMaps().get(layer).map[row][col];
                Tile tile = TileManager.getTileByNumber(tileNumber);
                if (tile != null) {
                    BufferedImage tileImage = tile.getImage();
                    // draw tile in screen
                    renderer.drawImage(tileImage,(col * Map.scaledTileSize) - sceneX, (row * Map.scaledTileSize) - sceneY, Map.scaledTileSize, Map.scaledTileSize, null);
                }
            }
        }
    }


    public void render(Graphics2D renderer, Player player){
        // find current scenario
        int sceneX = (int)player.getPixelPosX() - (int)player.getScreenPosX();
        int sceneY = (int)player.getPixelPosY() - (int)player.getScreenPosY();

        for (int layer = 0; layer < this.getTileMaps().size(); layer++){
            // draw map in each layer
            this.draw(renderer, sceneX, sceneY, layer);
        }
        player.draw(renderer);
        for(int index = 0; index < npcs.size(); index++){
            // draw each npc
            npcs.get(index).draw(renderer, sceneX, sceneY, player);
        }
    }
}
