package main;

import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GameFonts {
    public GameFonts() {
        addFont("2005_iannnnnCPU");
    }

    private void addFont(String fontName) {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/" + fontName + ".ttf")));
            System.out.println("\u001B[32m" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + " - LOADED: font " + fontName + "\u001B[0m");
        } catch (IOException | FontFormatException e) {
            System.out.println("\u001B[31m" + DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + " - ERROR: font " + fontName + " not found" + "\u001B[0m");
        }
    }
}
