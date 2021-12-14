package entity;

import main.GameControlHandler;

import java.awt.*;

public class Chest extends Object implements Interactable{

    public Chest(String name) {
        super(name);
    }

    @Override
    public void interact(Graphics2D renderer) {
        if(GameControlHandler.interact){
            if(GameControlHandler.objState)
                isActive = true;
            else
                isActive = false;
        }
    }
}
