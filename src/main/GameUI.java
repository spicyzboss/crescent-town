package main;

import entity.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class GameUI {
    private static Font normalFont;
    private static int titleSelect;
    private GameControlHandler controlHandler;
    private HashMap<String, BufferedImage> interfaces;

    public GameUI(GameControlHandler controlHandler) {
        this.loadFonts();
        GameUI.setTitleSelect(Game.loadedSave ? 2 : 1);
        this.setControlHandler(controlHandler);
        interfaces = new HashMap<String, BufferedImage>();
        this.loadInterface("background", "introBackground");
//        this.loadInterface("playerBar", "bar");
        this.loadInterface("coin", "coin");
        this.loadInterface("energy_0", "energy_0");
        this.loadInterface("energy_1", "energy_1");
        this.loadInterface("energy_2", "energy_2");
        this.loadInterface("energy_3", "energy_3");
        this.loadInterface("energy_4", "energy_4");
        this.loadInterface("energy_5", "energy_5");
        this.loadInterface("energy_6", "energy_6");
        this.loadInterface("energy_7", "energy_7");
        this.loadInterface("energy_8", "energy_8");
        this.loadInterface("energy_9", "energy_9");
        this.loadInterface("energy_10", "energy_10");
        this.loadInterface("energy_11", "energy_11");
        this.loadInterface("energy_12", "energy_12");
        this.loadInterface("energy_13", "energy_13");
        this.loadInterface("inventoryBar", "inventory");
        this.loadInterface("selectItem", "inventory_select");
        this.loadInterface("shop", "shop");
        this.loadInterface("shopSelect", "shop_select");
    }

    private void loadInterface(String interfaceType, String interfaceName) {
        try {
            interfaces.put(interfaceType, ImageIO.read(new File("res/interface/" + interfaceName +".png")));
            System.out.println("\u001B[32m" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + " - LOADED: interface " + interfaceType + " " + interfaceName + "\u001B[0m");
        } catch (IOException e) {
            System.out.println("\u001B[31m" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + " - ERROR: interface " + interfaceType + " " + interfaceName + " not found" + "\u001B[0m");
        }
    }

    private void loadFonts() {
        this.setNormalFont(new Font("2005_iannnnnCPU", Font.PLAIN, 40));
    }

    public static void drawDialog(Graphics2D renderer, String dialogMessage) {
        // Dialog background
        renderer.setColor(new Color(0, 0, 0, 200));
        renderer.fillRoundRect(Game.width/8, (int)(Game.height*10D/16D), (int)(Game.width*3D/4D), (int)(Game.height*5D/16D), 50, 50);

        // Dialog border
        renderer.setColor(Color.WHITE);
        renderer.setStroke(new BasicStroke(Game.tileSize/8));
        renderer.drawRoundRect(Game.width/8 + Game.tileSize/4, (int)(Game.height*10D/16D) + Game.tileSize/4, (int)(Game.width*3D/4D) - Game.tileSize/2, (int)(Game.height*5D/16D) - Game.tileSize/2, Game.tileSize, Game.tileSize);

        // Dialog message
        renderer.setFont(GameUI.getNormalFont());
        for (int i = 0; i < dialogMessage.split("\n").length; i++) {
            renderer.drawString(dialogMessage.split("\n")[i], Game.width/8 + Game.tileSize, (int)((Game.height*10D/16D) + Game.tileSize*(3/2D)) + Game.tileSize * i);
        }
    }

    public void drawTitleScreen(Graphics2D renderer) {
        renderer.drawImage(interfaces.get("background"), 0, 0, Game.width, Game.height, null);

        renderer.setFont(new Font("2005_iannnnnCPU", Font.PLAIN, (int)(Game.tileSize*(5/2D))));
        String playText = "Play";
        int playTextWidth = (int)renderer.getFontMetrics().getStringBounds(playText, renderer).getWidth();
        renderer.setColor(new Color(26, 55, 45));
        renderer.drawString(playText, Game.width/2 - playTextWidth/2, Game.height/2 + Game.tileSize/4);
        renderer.setColor(new Color(153, 197, 185));
        if (GameUI.getTitleSelect() == 1) {
            renderer.setColor(new Color(26, 55, 45));
            renderer.drawString(">", Game.width/2 - playTextWidth/2 - Game.tileSize, Game.height/2 + Game.tileSize/4);
            renderer.drawString("<", Game.width/2 + playTextWidth/2 + Game.tileSize, Game.height/2 + Game.tileSize/4);
            renderer.setColor(new Color(145, 160, 37));
            renderer.drawString(">", Game.width/2 - playTextWidth/2 - Game.tileSize, Game.height/2);
            renderer.drawString("<", Game.width/2 + playTextWidth/2 + Game.tileSize, Game.height/2);
        }
        renderer.drawString(playText, Game.width/2 - playTextWidth/2, Game.height/2);

        renderer.setFont(new Font("2005_iannnnnCPU", Font.PLAIN, (int)(Game.tileSize*(5/2D))));
        String loadText = "Load";
        int loadTextWidth = (int)renderer.getFontMetrics().getStringBounds(loadText, renderer).getWidth();
        renderer.setColor(new Color(26, 55, 45));
        renderer.drawString(loadText, Game.width/2 - loadTextWidth/2, (int)(Game.height*(5/8D)) + Game.tileSize/4);
        renderer.setColor(Game.loadedSave ? new Color(153, 197, 185) : new Color(79, 118, 109));
        if (GameUI.getTitleSelect() == 2) {
            renderer.setColor(new Color(26, 55, 45));
            renderer.drawString(">", Game.width/2 - loadTextWidth/2 - Game.tileSize, (int)(Game.height*(5/8D)) + Game.tileSize/4);
            renderer.drawString("<", Game.width/2 + loadTextWidth/2 + Game.tileSize, (int)(Game.height*(5/8D)) + Game.tileSize/4);
            renderer.setColor(Game.loadedSave ? new Color(145, 160, 37) : new Color(79, 118, 109));
            renderer.drawString(">", Game.width/2 - loadTextWidth/2 - Game.tileSize, (int)(Game.height*(5/8D)));
            renderer.drawString("<", Game.width/2 + loadTextWidth/2 + Game.tileSize, (int)(Game.height*(5/8D)));
        }
        renderer.drawString(loadText, Game.width/2 - loadTextWidth/2, (int)(Game.height*(5/8D)));

        renderer.setFont(new Font("2005_iannnnnCPU", Font.PLAIN, (int)(Game.tileSize*(5/2D))));
        String quitText = "Quit";
        int quitTextWidth = (int)renderer.getFontMetrics().getStringBounds(quitText, renderer).getWidth();
        renderer.setColor(new Color(26, 55, 45));
        renderer.drawString(quitText, Game.width/2 - quitTextWidth/2, (int)(Game.height*(6/8D)) + Game.tileSize/4);
        renderer.setColor(new Color(153, 197, 185));
        if (GameUI.getTitleSelect() == 3) {
            renderer.setColor(new Color(26, 55, 45));
            renderer.drawString(">", Game.width/2 - quitTextWidth/2 - Game.tileSize, (int)(Game.height*(6/8D)) + Game.tileSize/4);
            renderer.drawString("<", Game.width/2 + quitTextWidth/2 + Game.tileSize, (int)(Game.height*(6/8D)) + Game.tileSize/4);
            renderer.setColor(new Color(145, 160, 37));
            renderer.drawString(">", Game.width/2 - quitTextWidth/2 - Game.tileSize, (int)(Game.height*(6/8D)));
            renderer.drawString("<", Game.width/2 + quitTextWidth/2 + Game.tileSize, (int)(Game.height*(6/8D)));
        }
        renderer.drawString(quitText, Game.width/2 - quitTextWidth/2, (int)(Game.height*(6/8D)));
    }

    public void drawInterface(Graphics2D renderer, Player player) {
        renderer.setFont(new Font("2005_iannnnnCPU", Font.PLAIN, Game.tileSize*2));
        renderer.setColor(Color.WHITE);

        BufferedImage energy = interfaces.get("energy_" + (int)(player.getEnergy() / (100/13D)));
        renderer.drawImage(energy, Game.tileSize, 0, Game.tileSize * 2 * 4, Game.tileSize * 2, null);

        renderer.drawImage(interfaces.get("coin"), Game.tileSize * 2 * 12, 0, Game.tileSize * 2, Game.tileSize * 2, null);
        renderer.drawString("X " + player.getWallet().getMoney(), Game.tileSize * 2 * 13, Game.tileSize + Game.tileSize/2);

        if (!player.isInteracting) {
            renderer.drawImage(interfaces.get("inventoryBar"), Game.width/2 - Game.tileSize * 2 * 9 / 2, Game.height - Game.tileSize * 2, Game.tileSize * 2 * 9, Game.tileSize * 2, null);
            renderer.drawImage(interfaces.get("selectItem"), (Game.width/2 - Game.tileSize * 2 * 9 / 2) + (Game.tileSize * 2 * player.getSelectedItem()), Game.height - Game.tileSize * 2, Game.tileSize * 2, Game.tileSize * 2, null);
            for (int i = 0; i < player.getInventory().getSize(); i++) {
                renderer.drawImage(player.getInventory().getItem(i).getSprite(0), (Game.width/2 - Game.tileSize * 2 * 9 / 2) + (Game.tileSize * 2 * i), Game.height - Game.tileSize * 2, Game.tileSize * 2, Game.tileSize * 2, null);
            }

            renderer.drawImage(interfaces.get("shop"), Game.tileSize * 2 * 4, Game.tileSize * 2 * 3, Game.tileSize * 2 * 8, Game.tileSize * 2 * 3, null);
            int index = 7;
            renderer.drawImage(interfaces.get("shopSelect"), Game.tileSize * 2 * 4 + (Game.tileSize * 2 * (2 * (index % 5))), Game.tileSize * 2 * 3 + (int)(Game.tileSize * 2 * (3/2D) * (index / 5)), Game.tileSize * 2 * 2, (int)(Game.tileSize * 2 * (3/2D)), null);
        }
    }

    private void setNormalFont(Font normalFont) {
        GameUI.normalFont = normalFont;
    }

    public static Font getNormalFont() {
        return normalFont;
    }

    public void setControlHandler(GameControlHandler controlHandler) {
        this.controlHandler = controlHandler;
    }

    public GameControlHandler getControlHandler() {
        return controlHandler;
    }

    public static void setTitleSelect(int titleSelect) {
        GameUI.titleSelect = titleSelect;
    }

    public static int getTitleSelect() {
        return titleSelect;
    }
}
