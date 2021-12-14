package main;

import javax.imageio.ImageIO;
import javax.swing.border.StrokeBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class GameUI {
    private static Font normalFont;
    private int titleSelect;
    private GameControlHandler controlHandler;
    private HashMap<String, BufferedImage> interfaces;

    public GameUI(GameControlHandler controlHandler) {
        this.loadFonts();
        this.setTitleSelect(Game.loadedSave ? 2 : 1);
        this.setControlHandler(controlHandler);
        interfaces = new HashMap<String, BufferedImage>();
        this.loadInterface("background", "introBackground");
        this.loadInterface("playerBar", "bar");
    }

    private void loadInterface(String interfaceType, String interfaceName) {
        try {
            interfaces.put(interfaceType, ImageIO.read(new File("src/resource/interface/" + interfaceName +".png")));
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

    public void update() {
        if (Game.frame % 10 == 0) {
            if (this.getControlHandler().upKeyPressed) {
                this.setTitleSelect(Math.max(this.getTitleSelect() - 1, 1));
            } else if (this.getControlHandler().downKeyPressed) {
                this.setTitleSelect(Math.min(this.getTitleSelect() + 1, 3));
            }
        }
        if (this.getControlHandler().enterKeyPressed) {
            if (this.getTitleSelect() == 1) {
                Game.globalState = Game.gameState.PLAY;
            } else if (this.getTitleSelect() == 3) {
                System.exit(0);
            }
        }
    }

    public void drawTitleScreen(Graphics2D renderer) {
        renderer.drawImage(interfaces.get("background"), 0, 0, Game.width, Game.height, null);
        renderer.setFont(new Font("2005_iannnnnCPU", Font.PLAIN, Game.tileSize*5));
        String titleText = "Crescent Town";
        int titleTextWidth = (int)renderer.getFontMetrics().getStringBounds(titleText, renderer).getWidth();
        renderer.setColor(Color.BLACK);
        renderer.drawString(titleText, Game.width/2 - titleTextWidth/2, Game.height/4 + Game.tileSize/4);
        renderer.setColor(Color.WHITE);
        renderer.drawString(titleText, Game.width/2 - titleTextWidth/2, Game.height/4);

        renderer.setFont(new Font("2005_iannnnnCPU", Font.PLAIN, (int)(Game.tileSize*(5/2D))));
        String playText = "Play";
        int playTextWidth = (int)renderer.getFontMetrics().getStringBounds(playText, renderer).getWidth();
        renderer.setColor(Color.BLACK);
        renderer.drawString(playText, Game.width/2 - playTextWidth/2, Game.height/2 + Game.tileSize/8);
        renderer.setColor(Color.WHITE);
        if (this.getTitleSelect() == 1) {
            renderer.setColor(Color.BLACK);
            renderer.drawString(">", Game.width/2 - playTextWidth/2 - Game.tileSize, Game.height/2 + Game.tileSize/8);
            renderer.drawString("<", Game.width/2 + playTextWidth/2 + Game.tileSize, Game.height/2 + Game.tileSize/8);
            renderer.setColor(Color.ORANGE);
            renderer.drawString(">", Game.width/2 - playTextWidth/2 - Game.tileSize, Game.height/2);
            renderer.drawString("<", Game.width/2 + playTextWidth/2 + Game.tileSize, Game.height/2);
        }
        renderer.drawString(playText, Game.width/2 - playTextWidth/2, Game.height/2);

        renderer.setFont(new Font("2005_iannnnnCPU", Font.PLAIN, (int)(Game.tileSize*(5/2D))));
        String loadText = "Load";
        int loadTextWidth = (int)renderer.getFontMetrics().getStringBounds(loadText, renderer).getWidth();
        renderer.setColor(Color.BLACK);
        renderer.drawString(loadText, Game.width/2 - loadTextWidth/2, (int)(Game.height*(5/8D)) + Game.tileSize/8);
        renderer.setColor(Game.loadedSave ? Color.WHITE : Color.GRAY);
        if (this.getTitleSelect() == 2) {
            renderer.setColor(Color.BLACK);
            renderer.drawString(">", Game.width/2 - loadTextWidth/2 - Game.tileSize, (int)(Game.height*(5/8D)) + Game.tileSize/8);
            renderer.drawString("<", Game.width/2 + loadTextWidth/2 + Game.tileSize, (int)(Game.height*(5/8D)) + Game.tileSize/8);
            renderer.setColor(Game.loadedSave ? Color.ORANGE : Color.GRAY);
            renderer.drawString(">", Game.width/2 - loadTextWidth/2 - Game.tileSize, (int)(Game.height*(5/8D)));
            renderer.drawString("<", Game.width/2 + loadTextWidth/2 + Game.tileSize, (int)(Game.height*(5/8D)));
        }
        renderer.drawString(loadText, Game.width/2 - loadTextWidth/2, (int)(Game.height*(5/8D)));

        renderer.setFont(new Font("2005_iannnnnCPU", Font.PLAIN, (int)(Game.tileSize*(5/2D))));
        String quitText = "Quit";
        int quitTextWidth = (int)renderer.getFontMetrics().getStringBounds(quitText, renderer).getWidth();
        renderer.setColor(Color.BLACK);
        renderer.drawString(quitText, Game.width/2 - quitTextWidth/2, (int)(Game.height*(6/8D)) + Game.tileSize/8);
        renderer.setColor(Color.WHITE);
        if (this.getTitleSelect() == 3) {
            renderer.setColor(Color.BLACK);
            renderer.drawString(">", Game.width/2 - quitTextWidth/2 - Game.tileSize, (int)(Game.height*(6/8D)) + Game.tileSize/8);
            renderer.drawString("<", Game.width/2 + quitTextWidth/2 + Game.tileSize, (int)(Game.height*(6/8D)) + Game.tileSize/8);
            renderer.setColor(Color.ORANGE);
            renderer.drawString(">", Game.width/2 - quitTextWidth/2 - Game.tileSize, (int)(Game.height*(6/8D)));
            renderer.drawString("<", Game.width/2 + quitTextWidth/2 + Game.tileSize, (int)(Game.height*(6/8D)));
        }
        renderer.drawString(quitText, Game.width/2 - quitTextWidth/2, (int)(Game.height*(6/8D)));
    }

    public void drawInterface(Graphics2D renderer) {
        renderer.drawImage(interfaces.get("playerBar"), 0, 0, Game.width, Game.tileSize * 2, null);
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

    public void setTitleSelect(int titleSelect) {
        this.titleSelect = titleSelect;
    }

    public int getTitleSelect() {
        return titleSelect;
    }
}
