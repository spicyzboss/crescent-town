package object;

import entity.Player;
import main.GameControlHandler;

import java.awt.*;

public class Chest extends ActiveTypeObject {

    public Chest(String name, boolean hasActiveImage) {
        super(name, hasActiveImage);
    }

    @Override
    public void interact(Graphics2D renderer, Player player) {
        if(player.getControlHandler().interact){
            this.updateState();
        }
        this.resetPlayer(player);

    }

}
