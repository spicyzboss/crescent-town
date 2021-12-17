package object;

import entity.Player;

import java.awt.*;

public class Bed extends ActiveTypeObject {
    public Bed(String name) {
        super(name, false);
    }

    @Override
    public void interact(Graphics2D renderer, Player player) {
        if(player.getControlHandler().interact){
            this.updateState();
            player.setEnergy(100);
        }
        this.resetPlayer(player);

    }
}
