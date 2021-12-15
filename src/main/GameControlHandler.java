package main;

import entity.Player;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GameControlHandler implements KeyListener {
    public static boolean arrowKeyPressed;
    public boolean upKeyPressed;
    public boolean downKeyPressed;
    public boolean leftKeyPressed;
    public boolean rightKeyPressed;
    public boolean scaleUp;
    public boolean scaleDown;
    public static boolean move;
    public static boolean interact = false;
    public static int dialog;
    public static boolean objState;
    public ArrayList<Boolean> numbers = new ArrayList<Boolean>(Arrays.asList(false, false, false, false, false, false, false, false, false));

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        switch (Game.globalState) {
            case PLAY -> {
                if (0x25 <= e.getKeyCode() && e.getKeyCode() <= 0x28) {
                    arrowKeyPressed = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    upKeyPressed = true;
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    downKeyPressed = true;
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    leftKeyPressed = true;
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    rightKeyPressed = true;
                } else if (e.getKeyCode() == KeyEvent.VK_F) {
                    Game.adjustTileSize(1);
                    scaleUp = true;
                } else if (e.getKeyCode() == KeyEvent.VK_G) {
                    Game.adjustTileSize(2);
                    scaleDown = true;
                } else if (e.getKeyCode() == KeyEvent.VK_M) {
                    move = true;
                } else if (KeyEvent.VK_1 <= e.getKeyCode() && e.getKeyCode() <= KeyEvent.VK_9) {
                    numbers.set(e.getKeyCode() - KeyEvent.VK_1, true);
                }
            }
            case PAUSE, INTRO -> {

            }
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (Game.globalState) {
            case INTRO -> {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (GameUI.getTitleSelect() == 1) {
                        Game.playSoundEffect("select");
                        Game.globalState = Game.gameState.PLAY;
                        // sleep for listen to "select" sound effect
                        try {
                            Thread.sleep(250);
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                        Game.playBGM("beach_house");
                    } else if (GameUI.getTitleSelect() == 2) {
                        if (Game.loadedSave) {
                            Game.playSoundEffect("select");
                        } else {
                            Game.playSoundEffect("cancel");
                        }
                    } else if (GameUI.getTitleSelect() == 3) {
                        Game.playSoundEffect("select");
                        System.exit(0);
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    int oldSelect = GameUI.getTitleSelect();
                    GameUI.setTitleSelect(Math.max(GameUI.getTitleSelect() - 1, 1));
                    if (oldSelect != GameUI.getTitleSelect()) {
                        Game.playSoundEffect("change");
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    int oldSelect = GameUI.getTitleSelect();
                    GameUI.setTitleSelect(Math.min(GameUI.getTitleSelect() + 1, 3));
                    if (oldSelect != GameUI.getTitleSelect()) {
                        Game.playSoundEffect("change");
                    }
                }
            }
            case PLAY -> {
                if (0x25 <= e.getKeyCode() && e.getKeyCode() <= 0x28) {
                    arrowKeyPressed = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    upKeyPressed = false;
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    downKeyPressed = false;
                } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    leftKeyPressed = false;
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    rightKeyPressed = false;
                } else if (e.getKeyCode() == KeyEvent.VK_F) {
                    scaleUp = false;
                } else if (e.getKeyCode() == KeyEvent.VK_G) {
                    scaleDown = false;
                } else if (e.getKeyCode() == KeyEvent.VK_M) {
                    move = false;
                } else if (e.getKeyCode() == KeyEvent.VK_Z) {
                    interact = true;
                    if (Player.interactEntity != null)
                        dialog++;
                    if (Player.interactObj != null)
                        objState = !objState;
                } else if (KeyEvent.VK_1 <= e.getKeyCode() && e.getKeyCode() <= KeyEvent.VK_9) {
                    numbers.set(e.getKeyCode() - KeyEvent.VK_1, false);
                }
            }
            case PAUSE -> {

            }
        }
    }
}
