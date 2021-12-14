package main;

import entity.NPC;
import entity.Object;
import entity.Player;
import tile.MainMap;
import tile.TileManager;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class Game extends JPanel implements Runnable {
    /**
    * this game use refresh rate at 60 frame per second
    */
    public static int FPS = 60;

    /**
     * this game use 32 pixel size
     */
    public static int tileSize = 32;

    /**
    * integer width scale
    */
    public static int tileColumn = 16;

    /**
     * integer height scale
     */
    public static int tileRow = 10;

    /**
     * scaling integer for larger displays
     */
    public static int scale = 2;

    /**
     * scaled size of pixel in one tile
     */
    public static int scaledTileSize = tileSize * scale;

    /**
     * actual width for screen
     */
    public static int width = tileColumn * scaledTileSize;

    /**
     * actual height for screen
     */
    public static int height = tileRow * scaledTileSize;

    /**
     * frame passed in one second
     */
    public static int frame = 0;

    public enum gameState {
        INTRO,
        PLAY,
        PAUSE,
    }

    /**
     * Game state
     */
    public static gameState globalState;

    public static boolean loadedSave;

    MainMap village = new MainMap("village" ,100, 100);
    MainMap player_room = new MainMap("player room" ,25, 25);

    public static Thread gameThread; // global attribute for threading

    GameControlHandler controlHandler = new GameControlHandler();
    Player player = new Player("gongcha", controlHandler);
    GameUI ui;
    public static SoundManager sound;

    // Game constructor method for initialization
    public Game() {
        this.setFocusable(true);
        this.addKeyListener(controlHandler); // use KeyListener in GameControlHandler
        this.setPreferredSize(new Dimension(width, height)); // set size of JPanel
        this.setBackground(Color.BLACK); // set panel's background color to black
        this.setDoubleBuffered(true); // buffer for performance
        new TileManager();
        new GameFonts();
        player.setCurrentMap(village);
        globalState = gameState.INTRO;
        ui = new GameUI(controlHandler);
        sound = new SoundManager();
        initThread();
    }

    private void initThread() {
        gameThread = new Thread(this); // use this class for threading
        gameThread.start(); // assign this class to thread scheduler
    }

    public static void adjustTileSize(int state) {
        if (globalState == gameState.PLAY) {
            if (state == 1) {
                scale = Math.min(scale + 1, 4);
            } else if (state == 2) {
                scale = Math.max(scale - 1, 2);
            }
            scaledTileSize = tileSize * scale;
        }
    }

    public void run() {
        double refreshInterval =  Math.pow(10, 9) / FPS; // capture refresh rate to max FPS in nanosecond
        long lastRefreshTime = System.nanoTime(); // last refresh time in nanosecond
        long currentTime; // variable for current time in nanosecond
        double delta = 0; // a difference variable for calculation next refresh time
        int timer = 0; // timer for 1 second

        // Game loop theory
        while (gameThread != null) {
            currentTime = System.nanoTime(); // get current system time in nanosecond
            delta += (currentTime - lastRefreshTime) / refreshInterval; // capture CPU clock FPS to match game FPS
            timer += (currentTime - lastRefreshTime);
            lastRefreshTime = currentTime; // update refresh time for last refresh time

            // if delta time to 1 second update frame
            if (delta >= 1) {
                update();
                repaint();
                --delta;
                ++frame;
            }

            // if 1 second elapsed puts out loaded frame
            if (timer >= (int) Math.pow(10, 9)) {
                System.out.println(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format(LocalDateTime.now()) + " - FPS: " + frame);
                frame = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        if (globalState == gameState.PLAY) {
            player.update();
            for(NPC npc : player.getCurrentMap().NPCs){
                npc.update();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        switch (globalState) {
            case PLAY -> {
                // Draw player and map
                player.getCurrentMap().render(g2d, player);

                // Draw UI
                ui.drawInterface(g2d);
            }
            case INTRO -> {
                ui.drawTitleScreen(g2d);
            }
            default -> {
                // ui.drawPauseScreen(g2d);
            }
        }
        // Restore resource
        g2d.dispose();
    }

    public static void playBGM(String name) {
        sound.setSound(name);
        sound.playSound();
        sound.loopSound();
    }

    public static void stopBGM() {
        sound.stopSound();
    }

    public static void playSoundEffect(String name) {
        sound.setSound(name);
        sound.playSound();
    }

//    public void changeMap(){
//        switch (player.getCurrentMap().name){
//            case -> {
//            }
//        }
//    }
}
