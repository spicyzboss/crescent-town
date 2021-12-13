package tile;

import entity.Player;
import main.Map;

import java.awt.*;
import java.util.ArrayList;

public class CollisionChecker {
    public static Graphics2D renderer;

    public CollisionChecker(Graphics2D renderer){
        CollisionChecker.renderer = renderer;
    }

    public static boolean checkTile(Player player) {
        MainMap map = player.getCurrentMap();
        int mapToScreenX = (int)player.getTilePosX() * Map.scaledTileSize;
        int mapToScreenY = (int)player.getTilePosY() * Map.scaledTileSize;

        int screenToPlayerLeft = Map.width/2 - Map.scaledTileSize/2;
        int screenToPlayerRight = Map.width/2 - Map.scaledTileSize/2 + Map.scaledTileSize;
        int screenToPlayerTop = Map.height/2 + Map.scaledTileSize/4;
        int screenToPlayerBot = Map.height/2 + Map.scaledTileSize/4 + Map.scaledTileSize/4;

//        int playerTop = (mapToScreenY + screenToPlayerTop + player.getScreenY()) / Map.scaledTileSize;
//        int playerBot = (mapToScreenY + screenToPlayerBot + player.getScreenY()) / Map.scaledTileSize;
//        int playerLeft = (mapToScreenX + screenToPlayerLeft + player.getScreenX()) / Map.scaledTileSize;
//        int playerRight = (mapToScreenX + screenToPlayerRight + player.getScreenX()) / Map.scaledTileSize;
//
//        int x = -((int)player.getTilePosX()*Map.scaledTileSize+player.getScreenX());
//        int y = -((int)player.getTilePosY()*Map.scaledTileSize+player.getScreenY());

        ArrayList<TileMap> tileMaps = map.getTileMaps();
        boolean[] state = new boolean[2];
        switch (player.getDirection()) {
            case "up" -> {
//                Tile tile1 = TileManager.getTileByNumber(tileMaps[layer].map[playerTop][playerRight]);
    //            Tile tile2 = TileManager.getTileByNumber(tileMaps[layer].map[playerBot][playerRight]);
    //            if (tile1 != null) {
    ////                state[0] = tile1.isCollision();
    //            }
    //            if (tile2 != null) {
    ////                state[1] = tile2.isCollision();
    //            }
            }
            case "down" -> {
//                checkCol(playerBot, playerLeft, playerRight, x, y, tileMaps, state);
            }
            case "left" -> {
//                checkRow(playerTop, playerBot, playerLeft, x, y, tileMaps, state);
            }
            case "right" -> {
//                checkRow(playerTop, playerBot, playerRight, x, y, tileMaps, state);
            }
        }
        return state[0] || state[1];
    }

    private static void checkRow(int playerTop, int playerBot, int playerRight, int x, int y, TileMap[] tileMaps, boolean[] state) {
        for (int layer = 0; layer < tileMaps.length; layer++) {

        }
        if (state[0]) {
            renderer.drawImage(TileManager.getTile("not_use").getImage(), x + playerRight * Map.scaledTileSize, y + playerTop * Map.scaledTileSize, Map.scaledTileSize, Map.scaledTileSize, null);
        }
        if (state[1]) {
            renderer.drawImage(TileManager.getTile("not_use").getImage(), x + playerRight * Map.scaledTileSize, y + playerBot * Map.scaledTileSize, Map.scaledTileSize, Map.scaledTileSize, null);
        }
    }

    private static void checkCol(int playerTop, int playerLeft, int playerRight, int x, int y, TileMap[] tileMaps, boolean[] state) {
        for (int layer = 0; layer < tileMaps.length; layer++) {
            Tile tile1 = TileManager.getTileByNumber(tileMaps[layer].map[playerTop][playerLeft]);
            Tile tile2 = TileManager.getTileByNumber(tileMaps[layer].map[playerTop][playerRight]);
            if (tile1 != null) {
//                state[0] = tile1.isCollision();
            }
            if (tile2 != null) {
//                state[1] = tile2.isCollision();
            }
        }
        if (state[0]) {
            renderer.drawImage(TileManager.getTile("not_use").getImage(), x + playerLeft * Map.scaledTileSize, y + playerTop * Map.scaledTileSize, Map.scaledTileSize, Map.scaledTileSize, null);
        }
        if (state[1]) {
            renderer.drawImage(TileManager.getTile("not_use").getImage(), x + playerRight * Map.scaledTileSize, y + playerTop * Map.scaledTileSize, Map.scaledTileSize, Map.scaledTileSize, null);
        }
    }
}
