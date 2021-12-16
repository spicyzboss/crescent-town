package object;

import java.io.Serializable;

public abstract class PassiveTypeObject extends Object {
    public PassiveTypeObject(String fileName){
        super(fileName);
        passiveInit();
    }
    public void passiveInit(){
        this.setType("passive");
        this.image = loadImage(this.fileName.replace(" ", "_"));
    }

}
