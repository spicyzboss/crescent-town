package tile;

import entity.Player;
import main.Game;

import java.awt.*;
import java.util.ArrayList;

public class CollisionChecker {
    public static Graphics2D renderer;

    public CollisionChecker(Graphics2D renderer){
        CollisionChecker.renderer = renderer;
    }

    public static boolean checkTile(Player player) {
        MainMap map = player.getCurrentMap();
        int mapToScreenX = (int)player.getTilePosX() * Game.scaledTileSize;
        int mapToScreenY = (int)player.getTilePosY() * Game.scaledTileSize;

        int screenToPlayerLeft = Game.width/2 - Game.scaledTileSize/2;
        int screenToPlayerRight = Game.width/2 - Game.scaledTileSize/2 + Game.scaledTileSize;
        int screenToPlayerTop = Game.height/2 + Game.scaledTileSize/4;
        int screenToPlayerBot = Game.height/2 + Game.scaledTileSize/4 + Game.scaledTileSize/4;

//        int playerTop = (mapToScreenY + screenToPlayerTop + player.getScreenY()) / Game.scaledTileSize;
//        int playerBot = (mapToScreenY + screenToPlayerBot + player.getScreenY()) / Game.scaledTileSize;
//        int playerLeft = (mapToScreenX + screenToPlayerLeft + player.getScreenX()) / Game.scaledTileSize;
//        int playerRight = (mapToScreenX + screenToPlayerRight + player.getScreenX()) / Game.scaledTileSize;
//
//        int x = -((int)player.getTilePosX()*Game.scaledTileSize+player.getScreenX());
//        int y = -((int)player.getTilePosY()*Game.scaledTileSize+player.getScreenY());

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
            renderer.drawImage(TileManager.getTile("not_use").getImage(), x + playerRight * Game.scaledTileSize, y + playerTop * Game.scaledTileSize, Game.scaledTileSize, Game.scaledTileSize, null);
        }
        if (state[1]) {
            renderer.drawImage(TileManager.getTile("not_use").getImage(), x + playerRight * Game.scaledTileSize, y + playerBot * Game.scaledTileSize, Game.scaledTileSize, Game.scaledTileSize, null);
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
            renderer.drawImage(TileManager.getTile("not_use").getImage(), x + playerLeft * Game.scaledTileSize, y + playerTop * Game.scaledTileSize, Game.scaledTileSize, Game.scaledTileSize, null);
        }
        if (state[1]) {
            renderer.drawImage(TileManager.getTile("not_use").getImage(), x + playerRight * Game.scaledTileSize, y + playerTop * Game.scaledTileSize, Game.scaledTileSize, Game.scaledTileSize, null);
        }
    }
}
