package tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tile {
    private BufferedImage image;
    private final boolean collision;
    private final String tileName;

    public Tile(String tileName) {
        this.tileName = tileName;
        this.setImage();
        this.collision = false;
    }

    public Tile(String tileName, boolean collision) {
        this.tileName = tileName;
        this.setImage();
        this.collision = collision;
    }

    public void setImage(){
        try {
            this.image = ImageIO.read(new File("src/resource/tile/" + this.tileName + ".png"));
            System.out.println("\u001B[32m" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + " - LOADED: tile " + tileName + "\u001B[0m");
        } catch (IOException e) {
            System.out.println("\u001B[31m" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + " - ERROR: tile " + tileName + " not found" + "\u001B[0m");
        }
    }

    public BufferedImage getImage() {
        return image;
    }
}
