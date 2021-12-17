package object;

import entity.Player;
import main.GameControlHandler;
import main.GameUI;

import java.awt.*;

public class Chest extends ActiveTypeObject {

    public Chest(String name, boolean hasActiveImage) {
        super(name, hasActiveImage);
    }

    @Override
    public void interact(Graphics2D renderer, Player player) {
        if(player.getControlHandler().interact){
            this.isActive = true;
        }
        if(GameControlHandler.objState) {
            player.isInteracting = true;
            GameUI.drawDialog(renderer, "Nothing in it..." );
        }
        else {
            player.isInteracting = false;
            this.resetPlayer(player);
        }
    }

}
