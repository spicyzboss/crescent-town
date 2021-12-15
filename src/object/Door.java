package object;

import entity.Interactable;
import entity.Player;
import main.Game;
import main.GameControlHandler;

import java.awt.*;

public class Door extends Object implements Interactable {
    public String destination;
    public int spawnPointX, spawnPointY;
    public Door(String name) {
        super(name);
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getSpawnPointX() {
        return spawnPointX;
    }

    public void setSpawnPointX(int spawnPointX) {
        this.spawnPointX = spawnPointX;
    }

    public int getSpawnPointY() {
        return spawnPointY;
    }

    public void setSpawnPointY(int spawnPointY) {
        this.spawnPointY = spawnPointY;
    }

    @Override
    public void interact(Graphics2D renderer, Player player) {
        if(GameControlHandler.interact){
            player.setTilePosX(spawnPointX);
            player.setTilePosY(spawnPointY);
            player.setCurrentMapByName(this.getDestination());
            player.collisionObj = true;
            GameControlHandler.interact = false;
        }
    }
}
