package tile;

import entity.Player;
import main.Map;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MainMap{
    private TileMap[] tileMaps;
    private Map map;
    int posX = 18;
    int posY = 54;
    private Player player;

    public MainMap(String[] layers, Player player, Map map) {
        this.map = map;
        tileMaps = new TileMap[layers.length];
        this.player = player;

        for(int layer = 0; layer<layers.length; layer++){
            try {
                tileMaps[layer] = new TileMap(layers[layer]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public TileMap[] getTileMaps(){
        return tileMaps;
    }

    public void draw(Graphics2D g, int screenX, int screenY, int layer){
        int x = -(posX*Map.scaledTileSize+screenX);
        int y = -(posY*Map.scaledTileSize+screenY);
        for(int row = 0; row<tileMaps[layer].mapHeight; row++) {
            for (int col = 0; col < tileMaps[layer].mapWidth; col++) {
                int rc = tileMaps[layer].map[row][col];
                Tile tile = TileManager.getTileByRc(rc);
                BufferedImage image;
                if(tile != null){
                    image = tile.getImage();
                    g.drawImage(image,x+col*Map.scaledTileSize, y+row*Map.scaledTileSize, Map.scaledTileSize, Map.scaledTileSize, null);
//                    if(col == player.getPosX()-1 && row == player.getPosY() && tile.isCollision()) {
//                        g.drawImage(TileManager.getTile(150).getImage(), x + col * Map.scaledTileSize, y + row * Map.scaledTileSize, Map.scaledTileSize, Map.scaledTileSize, null);
//                    }
                }
            }
        }
    }


    public void render(Graphics2D g, int screenX, int screenY){
        player.setPosX(posX+Map.tileColumn/2+ player.getScreenX()/(Map.scaledTileSize));
        player.setPosY(posY+(Map.tileRow)/2+ player.getScreenY()/(Map.scaledTileSize));
        System.out.println(player.getPosX()+", "+ player.getPosY());

        for(int layer = 0; layer<tileMaps.length; layer++){
            draw(g, screenX,screenY, layer);
        }

        player.draw(g);
    }
}
