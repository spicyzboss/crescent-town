package tile;

import entity.Player;
import main.Map;

import java.awt.*;

public class CollisionChecker {

    private static Graphics2D g;

    public CollisionChecker(Graphics2D g){
        this.g = g;
    }

    public static boolean checkTile(Player player) {
        MainMap map = player.getCurrentMap();
        int mapToScreenX = map.posX * Map.scaledTileSize;
        int mapToScreenY = map.posY * Map.scaledTileSize;

        int screenToPlayerLeft = player.solidArea.x;
        int screeenToPlayerRight = player.solidArea.x + player.solidArea.width;
        int screenToPlayerTop = player.solidArea.y;
        int screeenToPlayerBot = player.solidArea.y + player.solidArea.height;

        int playerTop = (mapToScreenY + screenToPlayerTop + player.getScreenY()) / Map.scaledTileSize;
        int playerBot = (mapToScreenY + screeenToPlayerBot + player.getScreenY()) / Map.scaledTileSize;
        int playerLeft = (mapToScreenX + screenToPlayerLeft + player.getScreenX()) / Map.scaledTileSize;
        int playerRight = (mapToScreenX + screeenToPlayerRight + player.getScreenX()) / Map.scaledTileSize;

        System.out.println("screenY: " + mapToScreenX + ", screenY: " + mapToScreenY);

        System.out.println("top: " + playerTop);
        System.out.println("bot: " + playerBot);
        System.out.println("left: " + playerLeft);
        System.out.println("right: " + playerRight);
        System.out.println(player.getScreenX() + ", " + player.getScreenY());

        System.out.println(player.getDirection());
        int x = -(map.posX*Map.scaledTileSize+player.getScreenX());
        int y = -(map.posY*Map.scaledTileSize+player.getScreenY());

        TileMap[] tileMaps = map.getTileMaps();
        boolean[] state = new boolean[2];
        switch (player.getDirection()) {
            case "up" -> {
                checkCol(playerTop, playerLeft, playerRight, x, y, tileMaps, state);
            }
            case "down" -> {
                checkCol(playerBot, playerLeft, playerRight, x, y, tileMaps, state);
            }
            case "left" -> {
                checkRow(playerTop, playerBot, playerLeft, x, y, tileMaps, state);
            }
            case "right" -> {
                checkRow(playerTop, playerBot, playerRight, x, y, tileMaps, state);
            }
        }
        System.out.println("\u001B[32m"+(state[0] || state[1])+" state[]: "+state[0] +", " + state[1]+"\u001B[0m");
        return state[0] || state[1];
    }

    private static void checkRow(int playerTop, int playerBot, int playerRight, int x, int y, TileMap[] tileMaps, boolean[] state) {
        for (int layer = 0; layer < tileMaps.length; layer++) {
            Tile tile1 = TileManager.getTileByRc(tileMaps[layer].map[playerTop][playerRight]);
            Tile tile2 = TileManager.getTileByRc(tileMaps[layer].map[playerBot][playerRight]);
            if (tile1 != null) {
                state[0] = tile1.isCollision();
            }
            if (tile2 != null) {
                state[1] = tile2.isCollision();
            }
        }
        if (state[0]) {
            g.drawImage(TileManager.getTile(150).getImage(), x + playerRight * Map.scaledTileSize, y + playerTop * Map.scaledTileSize, Map.scaledTileSize, Map.scaledTileSize, null);
        }
        if (state[1]) {
            g.drawImage(TileManager.getTile(150).getImage(), x + playerRight * Map.scaledTileSize, y + playerBot * Map.scaledTileSize, Map.scaledTileSize, Map.scaledTileSize, null);
        }
    }

    private static void checkCol(int playerTop, int playerLeft, int playerRight, int x, int y, TileMap[] tileMaps, boolean[] state) {
        for (int layer = 0; layer < tileMaps.length; layer++) {
            Tile tile1 = TileManager.getTileByRc(tileMaps[layer].map[playerTop][playerLeft]);
            Tile tile2 = TileManager.getTileByRc(tileMaps[layer].map[playerTop][playerRight]);
            if (tile1 != null) {
                state[0] = tile1.isCollision();
            }
            if (tile2 != null) {
                state[1] = tile2.isCollision();
            }
        }
        if (state[0]) {
            g.drawImage(TileManager.getTile(150).getImage(), x + playerLeft * Map.scaledTileSize, y + playerTop * Map.scaledTileSize, Map.scaledTileSize, Map.scaledTileSize, null);
        }
        if (state[1]) {
            g.drawImage(TileManager.getTile(150).getImage(), x + playerRight * Map.scaledTileSize, y + playerTop * Map.scaledTileSize, Map.scaledTileSize, Map.scaledTileSize, null);
        }
    }


}
