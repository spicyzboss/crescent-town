package tile;

import java.io.File;
import java.util.*;

public class TileManager {
    public static HashMap<String, Tile> tile;

    public TileManager() {
        tile = new HashMap<>();
        loadTile();
    }

    public void loadTile() {
        File folder = new File("src/resource/tile");
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            addTile(fileEntry.getName().replace(".png", ""));
        }
    }

    private void addTile(String filename) {
        tile.put(filename, new Tile(filename));
    }

    public static Tile getTile(String name) {
        return tile.get(name);
    }
}