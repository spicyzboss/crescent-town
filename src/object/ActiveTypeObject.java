package object;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class ActiveTypeObject extends Object implements Serializable {
    public boolean isActive;
    public BufferedImage activeImage, deactiveImage;
    public ActiveTypeObject(String fileName){
        super(fileName);
        activeTypeInit();
    }

    private void activeTypeInit(){
        this.setType("active");
        this.isActive = false;
        this.deactiveImage = this.loadImage(this.fileName.replace(" ", "_"));
        this.activeImage = this.loadImage(this.fileName.replace(" ", "_").concat("_active"));
        this.setImage(deactiveImage);
    }

    public void updateState(){
        this.isActive = !isActive;
        this.setImage(isActive ? activeImage : deactiveImage);
    }

}
