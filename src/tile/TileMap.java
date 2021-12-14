package tile;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TileMap {
    public int[][] map;
    public int mapWidth;
    public int mapHeight;

    public TileMap(String path, int mapWidth, int mapHeight) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        try {
            BufferedReader bt = new BufferedReader(new FileReader("res/map/" + path));
            System.out.println("\u001B[32m" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + " - LOADED: map " + path + "\u001B[0m");
            map = new int[mapHeight][mapWidth];
            String delimeter = ",";
            for (int row = 0; row < mapHeight; row++) {
                String record = bt.readLine();
                String[] field = record.split(delimeter);
                for (int col = 0; col < mapWidth; col++) {
                    map[row][col] = Integer.parseInt(field[col]);
                }
            }
        } catch (IOException e) {
            System.out.println("\u001B[31m" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + " - ERROR: map " + path + " not found" + "\u001B[0m");
        }
    }

}
