package object;

import entity.Player;
import main.GameControlHandler;

import java.awt.*;
import java.io.Serializable;

public class Chest extends ActiveTypeObject implements Serializable {

    public Chest(String name) {
        super(name);
    }

    @Override
    public void interact(Graphics2D renderer, Player player) {
        if(GameControlHandler.interact){
            this.updateState();
        }
        System.out.println(GameControlHandler.interact);
        this.reset();
    }
}
