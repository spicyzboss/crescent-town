package object;

import entity.Player;
import main.GameControlHandler;
import main.GameUI;

import java.awt.*;

public class Sign extends ActiveTypeObject {
    private String message;

    public Sign(String fileName, boolean hasActiveImage) {
        super(fileName, hasActiveImage);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void interact(Graphics2D renderer, Player player) {
        if(GameControlHandler.interact){
            this.isActive = true;
        }
        if(GameControlHandler.objState) {
            player.isInteracting = true;
            GameUI.drawDialog(renderer, this.message);
        }
        else {
            player.isInteracting = false;
            this.reset();
        }
    }
}
