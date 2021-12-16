package entity;

import main.Game;
import main.GameControlHandler;
import main.GameUI;
import org.w3c.dom.ls.LSOutput;

import java.awt.*;
import java.util.ArrayList;

public class NPCMerchant extends NPC {

    public Rectangle saleArea;
    public boolean onSale = false;
    public ArrayList<String> saleDialog;

    public NPCMerchant(String name, String gender) {
        super(name, gender, "merchant");
        NPCMerchantInit();

    }
    private void NPCMerchantInit(){
        this.saleArea = new Rectangle(this.solidArea.x, this.solidArea.y+Game.scaledTileSize , Game.scaledTileSize, Game.scaledTileSize/2);
        this.saleDialog = new ArrayList<>();
    }

    @Override
    public void update() {
        super.update();
        saleArea.setLocation(this.solidArea.x, this.solidArea.y+Game.scaledTileSize);
    }

    public void addSaleDialog(String text){
        this.saleDialog.add(text);
    }

    public String getSaleDialog(int index) {
        return saleDialog.get(index);
    }

    @Override
    public void interact(Graphics2D renderer, Player player) {
        if (player.getControlHandler().interact) {
            if (onSale) {
                switch (player.getDirection()) {
                    case "up" -> this.setDirection("down");
                    case "down" -> this.setDirection("up");
                    case "left" -> this.setDirection("right");
                    case "right" -> this.setDirection("left");
                }
                if (!this.saleDialog.isEmpty() && GameControlHandler.dialog != 0) {
                    if (GameControlHandler.dialog-1 < this.saleDialog.size()) {
                        GameUI.drawDialog(renderer, this.getSaleDialog(GameControlHandler.dialog-1));
                    } else {
                        Game.globalState = Game.gameState.SELLING;
                        GameControlHandler.dialog = 0;
                        player.collisionNPC = true;
                        player.isInteracting = false;
                        player.getControlHandler().interact = false;
                        this.onSale = false;
                    }
                } else {
                    GameUI.drawDialog(renderer, "...");
                    GameControlHandler.dialog = 0;
                    player.collisionNPC = true;
                    player.isInteracting = false;
                    onSale = false;
                }
            } else {
                super.interact(renderer, player);
            }
        }

    }
}
