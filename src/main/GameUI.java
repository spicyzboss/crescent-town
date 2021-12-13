package main;

import java.awt.*;

public class GameUI {
    private Font normalFont;

    public GameUI() {
        new GameFonts();
        this.setNormalFont(new Font("2005_iannnnnCPU", Font.PLAIN, 40));
    }

    public void draw(Graphics2D renderer) {
        renderer.setFont(this.getNormalFont());
        renderer.setColor(Color.WHITE);
        String name = "บ็อบไงไม่รู้หรอ?";
        int stringSize = (int)renderer.getFontMetrics().getStringBounds(name, renderer).getWidth();
        renderer.drawString(name, Game.width/2 - stringSize/2, Game.height/2 - Game.scaledTileSize);
    }

    public void setNormalFont(Font normalFont) {
        this.normalFont = normalFont;
    }

    public Font getNormalFont() {
        return normalFont;
    }
}
