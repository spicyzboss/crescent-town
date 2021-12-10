package tile;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TileMap {
    public int[][] map;
    public int mapWidth = 100;
    public int mapHeight = 100;

    public TileMap(String s) {
        try {
            BufferedReader bt = new BufferedReader(new FileReader(s));
            map = new int[mapHeight][mapWidth];
            String delimeter = ",";
            for (int row = 0; row < mapHeight; row++) {
                String line = bt.readLine();
                String[] tokens = line.split(delimeter);
                for (int col = 0; col < mapWidth; col++) {
                    map[row][col] = Integer.parseInt(tokens[col]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
