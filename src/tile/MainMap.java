package tile;

import entity.AssetManage;
import entity.NPC;
import entity.Object;
import entity.Player;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

public class MainMap {
    public String name;
    public ArrayList<TileMap> tileMaps;
    public ArrayList<NPC> NPCs;
    public ArrayList<Object> objects;
    public TileMap collisionTileMap;
    public int mapWidth, mapHeight;
    public static int sceneX, sceneY;

    public MainMap(String name, int mapWidth, int mapHeight) {
        this.name = name;
        tileMaps = new ArrayList<TileMap>();
        this.NPCs = new ArrayList<NPC>();
        this.objects = new ArrayList<Object>();
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        loadMap(this.name);
        AssetManage.mapSetting(this);
    }

    public void addNpc(NPC npc){
        this.NPCs.add(npc);
    }

    private void loadMap(String name) {
        File folder = new File("src/resource/map/"+name.replace(" ", "_"));
        File[] files = folder.listFiles();
        Arrays.sort(files);
        for(File layer : Objects.requireNonNull(files)){
            TileMap tileMap = new TileMap(name.replace(" ", "_")+"/"+layer.getName(), mapWidth, mapHeight);
            if(layer.getName().endsWith("collision.csv"))
                this.collisionTileMap = tileMap;
            else
                this.tileMaps.add(tileMap);
        }
    }

    public ArrayList<TileMap> getTileMaps() {
        return tileMaps;
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

        for (int layer = 0; layer < this.getTileMaps().size(); layer++){
            // draw map in each layer
            this.draw(renderer, layer);
        }
        for (NPC npc : NPCs) {
            // draw each npc on map
            npc.draw(renderer, player);
        }
        for (Object object : objects){
            // draw each object on map
            object.draw(renderer, player);
        }
        player.draw(renderer);
    }

    public void setSceneX(int sceneX) {
        MainMap.sceneX = sceneX;
    }

    public void setSceneY(int sceneY) {
        MainMap.sceneY = sceneY;
    }
}
