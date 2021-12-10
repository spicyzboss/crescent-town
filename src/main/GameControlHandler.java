package main;

import java.awt.event.*;

public class GameControlHandler implements KeyListener {
    public boolean upKeyPressed;
    public boolean downKeyPressed;
    public boolean leftKeyPressed;
    public boolean rightKeyPressed;
    public boolean arrowKeyPressed;

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
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
        }
    }

    public void keyReleased(KeyEvent e) {
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
        }
    }
}
