package main;

import java.awt.*;

public class GameUI {
    private static Font normalFont;
    private Font titleFont;

    public GameUI(Graphics2D renderer) {
        this.loadFonts();
    }

    private void loadFonts() {
        this.setNormalFont(new Font("2005_iannnnnCPU", Font.PLAIN, 40));
        this.setTitleFont(new Font("2005_iannnnnCPU", Font.PLAIN, 128));
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
        renderer.setFont(this.getTitleFont());
        renderer.setColor(Color.WHITE);
        String titleText = "Crescent Town";
        int titleTextWidth = (int)renderer.getFontMetrics().getStringBounds(titleText, renderer).getWidth();
        renderer.drawString(titleText, Game.width/2 - titleTextWidth/2, Game.height/4);
    }

    private void setNormalFont(Font normalFont) {
        GameUI.normalFont = normalFont;
    }

    public static Font getNormalFont() {
        return normalFont;
    }

    public void setTitleFont(Font titleFont) {
        this.titleFont = titleFont;
    }

    public Font getTitleFont() {
        return titleFont;
    }
}
