package object;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public abstract class ActiveTypeObject extends Object {
    public boolean isActive, hasActiveImage;
    public BufferedImage activeImage, deactiveImage;
    public ActiveTypeObject(String fileName, boolean hasActiveImage){
        super(fileName);
        this.hasActiveImage = hasActiveImage;
        activeTypeInit();
    }

    private void activeTypeInit(){
        this.setType("active");
        this.isActive = false;
        this.deactiveImage = this.loadImage(this.fileName.replace(" ", "_"));
        if(hasActiveImage)
            this.activeImage = this.loadImage(this.fileName.replace(" ", "_").concat("_active"));
        this.setImage(deactiveImage);
    }

    public void updateState(){
        this.isActive = !isActive;
        if(hasActiveImage)
            this.setImage(isActive ? activeImage : deactiveImage);
    }

}
