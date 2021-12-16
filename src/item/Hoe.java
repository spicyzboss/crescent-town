package item;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hoe extends Item {
    public Hoe() {
        super("Hoe", 0, 0);
        this.loadImage();
    }

    @Override
    void loadImage() {
        try {
            this.setImage(ImageIO.read(new File("res/item/" + this.getName().toLowerCase().replaceAll(" ", "_") + ".png")));
            System.out.println("\u001B[32m" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + " - LOADED: item " + this.getName().toLowerCase().replaceAll(" ", "_") + "\u001B[0m");
        } catch (IOException e) {
            System.out.println("\u001B[31m" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + " - ERROR: item " + this.getName().toLowerCase().replaceAll(" ", "_") + " not found" + "\u001B[0m");
        }
    }
}
