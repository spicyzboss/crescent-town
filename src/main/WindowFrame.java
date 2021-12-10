package main;

import javax.swing.*;
import java.awt.*;

public class WindowFrame extends JFrame {
    public WindowFrame() {
        super("Crescent Town");
        Map map = new Map();
        this.setContentPane(map);
        this.pack();
        Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dm.width/2 - Map.width/2 , dm.height/2 - Map.height/2);
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
