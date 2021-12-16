package main;

import entity.Player;

import java.awt.event.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class GameControlHandler implements KeyListener, Serializable {
    public static boolean arrowKeyPressed;
    public boolean upKeyPressed;
    public boolean downKeyPressed;
    public boolean leftKeyPressed;
    public boolean rightKeyPressed;
    public boolean scaleUp;
    public boolean scaleDown;
    public boolean confirmExit = false;
    public static boolean pos = false;
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
                } else if (KeyEvent.VK_1 <= e.getKeyCode() && e.getKeyCode() <= KeyEvent.VK_9) {
                    numbers.set(e.getKeyCode() - KeyEvent.VK_1, true);
                }
            }
            case BUYING, SELLING -> {
                 if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    Game.globalState = Game.gameState.PLAY;
                }
            }
            case PAUSE, INTRO -> {

            }
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (Game.globalState) {
            case INTRO -> {
                if (e.getKeyCode() == KeyEvent.VK_Z) {
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
//                            Game.loadGame();
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
                } else if (e.getKeyCode() == KeyEvent.VK_P) {
                    pos = true;
                } else if (e.getKeyCode() == KeyEvent.VK_Z) {
                    if(!(Player.interactObj == null && Player.interactNPC == null))
                        interact = true;
                    if (Player.interactNPC != null)
                        dialog++;
                    if (Player.interactObj != null)
                        objState = !objState;
                } else if (KeyEvent.VK_1 <= e.getKeyCode() && e.getKeyCode() <= KeyEvent.VK_9) {
                    numbers.set(e.getKeyCode() - KeyEvent.VK_1, false);
                } else if (e.getKeyCode() == KeyEvent.VK_X) {
                    Game.globalState = Game.gameState.EXITING;
                    GameUI.isConfirming = true;
                }
            }
            case BUYING, SELLING, EXITING -> {
                if (!GameUI.isConfirming) {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        upKeyPressed = false;
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        downKeyPressed = false;
                    } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        leftKeyPressed = false;
                        GameUI.shopIndex = Math.max(GameUI.shopIndex - 1, 0);
                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        rightKeyPressed = false;
                        GameUI.shopIndex = Math.min(GameUI.shopIndex + 1, 7);
                    } else if (e.getKeyCode() == KeyEvent.VK_Z) {
                        GameUI.isConfirming = true;
                    } else if (e.getKeyCode() == KeyEvent.VK_X) {
                        Game.globalState = Game.gameState.PLAY;
                    }
                } else {
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        GameUI.confirmIndex = 0;
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        GameUI.confirmIndex = 1;
                    } else if (e.getKeyCode() == KeyEvent.VK_Z) {
                        if (GameUI.confirmIndex == 0) {
                            // buy or sell event
                            if (Game.globalState == Game.gameState.EXITING) {
                                // save state
                                confirmExit = true;
                            }
                        } else {
                            GameUI.isConfirming = false;
                            if (Game.globalState == Game.gameState.EXITING)
                                Game.globalState = Game.gameState.PLAY;
                        }
                    }
                }
            }
            case PAUSE -> {

            }
        }
    }
}
