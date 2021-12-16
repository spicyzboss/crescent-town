package object;

import entity.Player;
import main.GameControlHandler;

import java.awt.*;
import java.io.Serializable;

public class Door extends PassiveTypeObject{
    public String destination;
    public double spawnPointX, spawnPointY;

    public Door(String fileName) {
        super(fileName);
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getSpawnPointX() {
        return spawnPointX;
    }

    public void setSpawnPointX(double spawnPointX) {
        this.spawnPointX = spawnPointX;
    }

    public double getSpawnPointY() {
        return spawnPointY;
    }

    public void setSpawnPointY(double spawnPointY) {
        this.spawnPointY = spawnPointY;
    }

    @Override
    public void interact(Graphics2D renderer, Player player) {
        player.setTilePosX(spawnPointX);
        player.setTilePosY(spawnPointY);
        player.setCurrentMapByName(this.getDestination());
        player.collisionObj = true;
        GameControlHandler.interact = false;
        this.reset();
    }
}
