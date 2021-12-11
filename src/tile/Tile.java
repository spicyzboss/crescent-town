package tile;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tile {
    private BufferedImage image;
    private boolean collision = true;
    public Tile(String s){
        this.setImage(s);
    }
    public Tile(String s, boolean collision){
        this.setImage(s);
        this.collision = collision;
    }
    public boolean isCollision(){
        return collision;
    }
    public void setImage(String s){
        try {
            this.image = ImageIO.read(new File(s));
        } catch (IOException e) {
            System.out.println(s);
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
         return image;
    }
}
