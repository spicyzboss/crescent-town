package tile;

import entity.NPC;
import entity.Player;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class MainMap {
    public ArrayList<TileMap> tileMaps;
    public ArrayList<NPC> npcs;
    public int mapWidth, mapHeight;
    public static int sceneX, sceneY;

    public MainMap(int mapWidth, int mapHeight) {
        tileMaps = new ArrayList<TileMap>();
        npcs = new ArrayList<NPC>();
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        loadMap();
    }

    public void addNpc(NPC npc){
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

    public int getSceneX() {
        return sceneX;
    }

    public void setSceneX(int sceneX) {
        this.sceneX = sceneX;
    }

    public int getSceneY() {
        return sceneY;
    }

    public void setSceneY(int sceneY) {
        this.sceneY = sceneY;
    }

    public void draw(Graphics2D renderer, int layer){
        for (int row = 0; row < this.getTileMaps().get(layer).mapHeight; row++) {
            for (int col = 0; col < this.getTileMaps().get(layer).mapWidth; col++) {
                int tileNumber = this.getTileMaps().get(layer).map[row][col];
                Tile tile = TileManager.getTileByNumber(tileNumber);
                if (tile != null) {
                    BufferedImage tileImage = tile.getImage();
                    // draw tile in screen
                    renderer.drawImage(tileImage,(col * Game.scaledTileSize) - sceneX, (row * Game.scaledTileSize) - sceneY, Game.scaledTileSize, Game.scaledTileSize, null);
                }
            }
        }
    }

    public void render(Graphics2D renderer, Player player){
        // find current scenario
        setSceneX((int)player.getPixelPosX() - (int)player.getScreenPosX());
        setSceneY((int)player.getPixelPosY() - (int)player.getScreenPosY());

        for (int layer = 0; layer < this.getTileMaps().size() -1 ; layer++){
            // draw map in each layer
            this.draw(renderer, layer);
        }
        for (NPC npc : npcs) {
            // draw each npc
            npc.draw(renderer, player);
        }
        player.draw(renderer);
    }
}
