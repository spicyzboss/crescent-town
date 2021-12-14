package main;

import java.awt.*;

public class GameUI {
    private static Font normalFont;

    public GameUI() {
        new GameFonts();
        this.loadFonts();
    }

    private void loadFonts() {
        this.setNormalFont(new Font("2005_iannnnnCPU", Font.PLAIN, 40));
    }

    public void draw(Graphics2D renderer) {
        renderer.setFont(this.getNormalFont());
        renderer.setColor(Color.WHITE);
        String name = "บ็อบไงไม่รู้หรอ?";
        int stringSize = (int)renderer.getFontMetrics().getStringBounds(name, renderer).getWidth();
        renderer.drawString(name, Game.width/2 - stringSize/2, Game.height/2 - Game.scaledTileSize);
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
        renderer.setFont(getNormalFont());
        renderer.drawString(dialogMessage, Game.width/8 + Game.tileSize, (int)((Game.height*10D/16D) + Game.tileSize*(3/2D)));
    }

    private void setNormalFont(Font normalFont) {
        this.normalFont = normalFont;
    }

    public static Font getNormalFont() {
        return normalFont;
    }
}
