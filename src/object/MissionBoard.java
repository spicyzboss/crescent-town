package object;

import entity.Player;
import main.GameControlHandler;
import main.GameUI;

import java.awt.*;

public class MissionBoard extends ActiveTypeObject {


    public MissionBoard(String fileName, boolean hasActiveImage) {
        super(fileName, hasActiveImage);
    }

    @Override
    public void interact(Graphics2D renderer, Player player) {
        if(GameControlHandler.interact){
            this.isActive = true;
        }
        if(GameControlHandler.objState) {
            player.isInteracting = true;
            GameUI.drawDialog(renderer, "Nothing...!");
        }
        else {
            player.isInteracting = false;
            this.reset();
        }
    }
}
