package main;

import entity.Player;
import item.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class GameUI {
    private static Font normalFont;
    private static int titleSelect;
    private HashMap<String, BufferedImage> interfaces;
    public static int shopIndex;
    public static int confirmIndex;
    public static boolean isConfirming;
    public static ArrayList<Item> items;

    ArrayList<Item> buyLists;
    public GameUI() {
        this.loadFonts();
        GameUI.setTitleSelect(Game.loadedSave ? 2 : 1);
        interfaces = new HashMap<String, BufferedImage>();
        this.loadInterface("background", "introBackground");
        this.loadInterface("coin", "coin");
        this.loadInterface("energy_0", "energy_0");
        this.loadInterface("energy_1", "energy_1");
        this.loadInterface("energy_2", "energy_2");
        this.loadInterface("energy_3", "energy_3");
        this.loadInterface("energy_4", "energy_4");
        this.loadInterface("energy_5", "energy_5");
        this.loadInterface("energy_6", "energy_6");
        this.loadInterface("energy_7", "energy_7");
        this.loadInterface("energy_8", "energy_8");
        this.loadInterface("energy_9", "energy_9");
        this.loadInterface("energy_10", "energy_10");
        this.loadInterface("energy_11", "energy_11");
        this.loadInterface("energy_12", "energy_12");
        this.loadInterface("energy_13", "energy_13");
        this.loadInterface("inventoryBar", "inventory");
        this.loadInterface("selectItem", "inventory_select");
        this.loadInterface("shop", "shop");
        this.loadInterface("shopSelect", "shop_select");
        this.loadInterface("confirm", "confirm");
        shopIndex = 0;
        confirmIndex = 0;
        isConfirming = false;
        buyLists = new ArrayList<>();
        buyLists.add(new BlueBush());
        buyLists.add(new Corn());
        buyLists.add(new Flowder());
        buyLists.add(new PinkBush());
        buyLists.add(new Lotus());
        buyLists.add(new PoiSian());
    }

    private void loadInterface(String interfaceType, String interfaceName) {
        try {
            interfaces.put(interfaceType, ImageIO.read(new File("res/interface/" + interfaceName +".png")));
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

    public void drawTitleScreen(Graphics2D renderer) {
        renderer.drawImage(interfaces.get("background"), 0, 0, Game.width, Game.height, null);

        renderer.setFont(new Font("2005_iannnnnCPU", Font.PLAIN, (int)(Game.tileSize*(5/2D))));
        String playText = "Play";
        int playTextWidth = (int)renderer.getFontMetrics().getStringBounds(playText, renderer).getWidth();
        renderer.setColor(new Color(26, 55, 45));
        renderer.drawString(playText, Game.width/2 - playTextWidth/2, Game.height/2 + Game.tileSize/4);
        renderer.setColor(new Color(153, 197, 185));
        if (GameUI.getTitleSelect() == 1) {
            renderer.setColor(new Color(26, 55, 45));
            renderer.drawString(">", Game.width/2 - playTextWidth/2 - Game.tileSize, Game.height/2 + Game.tileSize/4);
            renderer.drawString("<", Game.width/2 + playTextWidth/2 + Game.tileSize, Game.height/2 + Game.tileSize/4);
            renderer.setColor(new Color(145, 160, 37));
            renderer.drawString(">", Game.width/2 - playTextWidth/2 - Game.tileSize, Game.height/2);
            renderer.drawString("<", Game.width/2 + playTextWidth/2 + Game.tileSize, Game.height/2);
        }
        renderer.drawString(playText, Game.width/2 - playTextWidth/2, Game.height/2);

        renderer.setFont(new Font("2005_iannnnnCPU", Font.PLAIN, (int)(Game.tileSize*(5/2D))));
        String loadText = "Load";
        int loadTextWidth = (int)renderer.getFontMetrics().getStringBounds(loadText, renderer).getWidth();
        renderer.setColor(new Color(26, 55, 45));
        renderer.drawString(loadText, Game.width/2 - loadTextWidth/2, (int)(Game.height*(5/8D)) + Game.tileSize/4);
        renderer.setColor(Game.loadedSave ? new Color(153, 197, 185) : new Color(79, 118, 109));
        if (GameUI.getTitleSelect() == 2) {
            renderer.setColor(new Color(26, 55, 45));
            renderer.drawString(">", Game.width/2 - loadTextWidth/2 - Game.tileSize, (int)(Game.height*(5/8D)) + Game.tileSize/4);
            renderer.drawString("<", Game.width/2 + loadTextWidth/2 + Game.tileSize, (int)(Game.height*(5/8D)) + Game.tileSize/4);
            renderer.setColor(Game.loadedSave ? new Color(145, 160, 37) : new Color(79, 118, 109));
            renderer.drawString(">", Game.width/2 - loadTextWidth/2 - Game.tileSize, (int)(Game.height*(5/8D)));
            renderer.drawString("<", Game.width/2 + loadTextWidth/2 + Game.tileSize, (int)(Game.height*(5/8D)));
        }
        renderer.drawString(loadText, Game.width/2 - loadTextWidth/2, (int)(Game.height*(5/8D)));

        renderer.setFont(new Font("2005_iannnnnCPU", Font.PLAIN, (int)(Game.tileSize*(5/2D))));
        String quitText = "Quit";
        int quitTextWidth = (int)renderer.getFontMetrics().getStringBounds(quitText, renderer).getWidth();
        renderer.setColor(new Color(26, 55, 45));
        renderer.drawString(quitText, Game.width/2 - quitTextWidth/2, (int)(Game.height*(6/8D)) + Game.tileSize/4);
        renderer.setColor(new Color(153, 197, 185));
        if (GameUI.getTitleSelect() == 3) {
            renderer.setColor(new Color(26, 55, 45));
            renderer.drawString(">", Game.width/2 - quitTextWidth/2 - Game.tileSize, (int)(Game.height*(6/8D)) + Game.tileSize/4);
            renderer.drawString("<", Game.width/2 + quitTextWidth/2 + Game.tileSize, (int)(Game.height*(6/8D)) + Game.tileSize/4);
            renderer.setColor(new Color(145, 160, 37));
            renderer.drawString(">", Game.width/2 - quitTextWidth/2 - Game.tileSize, (int)(Game.height*(6/8D)));
            renderer.drawString("<", Game.width/2 + quitTextWidth/2 + Game.tileSize, (int)(Game.height*(6/8D)));
        }
        renderer.drawString(quitText, Game.width/2 - quitTextWidth/2, (int)(Game.height*(6/8D)));
    }

    public void drawInterface(Graphics2D renderer, Player player) {
        renderer.setFont(new Font("2005_iannnnnCPU", Font.PLAIN, Game.tileSize*2));
        renderer.setColor(Color.WHITE);

        // energy
        BufferedImage energy = interfaces.get("energy_" + (int)(player.getEnergy() / (100/13D)));
        renderer.drawImage(energy, Game.tileSize, 0, Game.tileSize * 2 * 4, Game.tileSize * 2, null);

        // money
        renderer.drawImage(interfaces.get("coin"), Game.tileSize * 2 * 12, 0, Game.tileSize * 2, Game.tileSize * 2, null);
        renderer.drawString("X " + player.getWallet().getMoney(), Game.tileSize * 2 * 13, Game.tileSize + Game.tileSize/2);

        if (!player.isInteracting) {
            renderer.drawImage(interfaces.get("inventoryBar"), Game.width/2 - Game.tileSize * 2 * 9 / 2, Game.height - Game.tileSize * 2, Game.tileSize * 2 * 9, Game.tileSize * 2, null);
            renderer.drawImage(interfaces.get("selectItem"), (Game.width/2 - Game.tileSize * 2 * 9 / 2) + (Game.tileSize * 2 * player.getSelectedItem()), Game.height - Game.tileSize * 2, Game.tileSize * 2, Game.tileSize * 2, null);
            for (int i = 0; i < player.getInventory().getSize(); i++) {
                renderer.drawImage(player.getInventory().getItem(i).getSprite(0), (Game.width/2 - Game.tileSize * 2 * 9 / 2) + (Game.tileSize * 2 * i), Game.height - Game.tileSize * 2, Game.tileSize * 2, Game.tileSize * 2, null);
            }
        }
        if (Game.globalState == Game.gameState.BUYING) {
            renderer.setFont(new Font("2005_iannnnnCPU", Font.PLAIN, Game.tileSize*3));
            renderer.setColor(Color.BLACK);
            String buy = "Buy Shop";
            int buyTextWidth = (int)renderer.getFontMetrics().getStringBounds(buy, renderer).getWidth();


            renderer.drawString(buy, Game.width / 2 - buyTextWidth/2, Game.tileSize * 2 * 2);
            renderer.drawImage(interfaces.get("shop"), Game.tileSize * 2 * 4, Game.tileSize * 2 * 3, Game.tileSize * 2 * 8, Game.tileSize * 2 * 3, null);
            renderer.drawImage(interfaces.get("shopSelect"), Game.tileSize * 2 * 4 + (Game.tileSize * 2 * (2 * (shopIndex % 4))), Game.tileSize * 2 * 3 + (int)(Game.tileSize * 2 * (3/2D) * (shopIndex / 4)), Game.tileSize * 2 * 2, (int)(Game.tileSize * 2 * (3/2D)), null);

            for (int i = 0; i < buyLists.size(); i++) {
                renderer.drawImage(buyLists.get(i).getSprite(((PlantItem)buyLists.get(i)).getMaxGrowthState() - 1), Game.tileSize * 2 * 4 + Game.tileSize * 2 * 2 * (i%4) + Game.tileSize/2, Game.tileSize * 2 * 3+(int)(Game.tileSize * 2 * (3/2D))*(i/4), (int)(Game.tileSize * 2 * (3/2D)), (int)(Game.tileSize * 2 * (3/2D)), null);
            }
            if (isConfirming) {
                this.drawConfirming(renderer, "Buy", "Cancel");
                if (GameControlHandler.confirmTransaction) {
                    if (player.getWallet().getMoney() >= buyLists.get(shopIndex).getBuyPrice() && player.getInventory().isAvailable()) {
                        player.getWallet().reduceMoney(buyLists.get(shopIndex).getBuyPrice());
                        player.getInventory().addItem(buyLists.get(shopIndex));
                    } else {
                        Game.playSoundEffect("cancel");
                    }
                    isConfirming = false;
                    GameControlHandler.confirmTransaction = false;
                    Game.globalState = Game.gameState.PLAY;
                }
            }
        } else if (Game.globalState == Game.gameState.SELLING) {
            renderer.setFont(new Font("2005_iannnnnCPU", Font.PLAIN, Game.tileSize*3));
            renderer.setColor(Color.BLACK);
            String sell = "Sell Shop";
            int sellTextWidth = (int)renderer.getFontMetrics().getStringBounds(sell, renderer).getWidth();
            renderer.drawString(sell, Game.width / 2 - sellTextWidth/2, Game.tileSize * 2 * 2);
            renderer.drawImage(interfaces.get("shop"), Game.tileSize * 2 * 4, Game.tileSize * 2 * 3, Game.tileSize * 2 * 8, Game.tileSize * 2 * 3, null);
            renderer.drawImage(interfaces.get("shopSelect"), Game.tileSize * 2 * 4 + (Game.tileSize * 2 * (2 * (shopIndex % 4))), Game.tileSize * 2 * 3 + (int)(Game.tileSize * 2 * (3/2D) * (shopIndex / 4)), Game.tileSize * 2 * 2, (int)(Game.tileSize * 2 * (3/2D)), null);

            items = player.getInventory().filter("plant", player);
            int blockWidth = Game.tileSize * 2 * 2;
            int blockHeight = (int)(Game.tileSize * 2 * (3/2D));
            int row = 0;
            int col = 0;
            for (Item item : items) {
                if (col > 3) {
                    row++;
                    col = 0;
                }
                if (((PlantItem)item).isGrowth()) {
                    renderer.drawImage(item.getSprite(0), Game.tileSize * 2 * 4 + blockWidth*col + Game.tileSize/2, Game.tileSize * 2 * 3+blockHeight*row, (int)(Game.tileSize * 2 * (3/2D)), (int)(Game.tileSize * 2 * (3/2D)), null);
                }
                col++;
            }
            if (isConfirming) {
                this.drawConfirming(renderer, "Sell", "Cancel");
                if (GameControlHandler.confirmTransaction) {
                    player.getWallet().receiveMoney(items.get(shopIndex).getSellPrice());
                    player.getInventory().removeItem(items.get(shopIndex));
                    isConfirming = false;
                    GameControlHandler.confirmTransaction = false;
                    Game.globalState = Game.gameState.PLAY;
                }
            }
        } else if (Game.globalState == Game.gameState.EXITING) {
            if (isConfirming) {
                this.drawConfirming(renderer, "Quit", "Cancel");
            }
        }
        else {
            shopIndex = 0;
            confirmIndex = 0;
        }
    }

    public void drawConfirming(Graphics2D renderer, String confirm, String reject) {
        renderer.drawImage(interfaces.get("confirm"), Game.width - (Game.tileSize * 2 * 3), Game.height - (Game.tileSize * 2 * 3), Game.tileSize * 2 * 2, Game.tileSize * 2 * 2, null);
        renderer.setFont(new Font("2005_iannnnnCPU", Font.PLAIN, Game.tileSize*2));
        if (confirmIndex == 0) {
            renderer.setColor(Color.RED);
        } else {
            renderer.setColor(Color.BLACK);
        }
        int confirmTextWidth = (int)renderer.getFontMetrics().getStringBounds(confirm, renderer).getWidth();
        renderer.drawString(confirm, Game.width - (Game.tileSize * 2 * 2) - confirmTextWidth / 2, Game.height - (Game.tileSize * 2 * 2) - (Game.tileSize / 2));

        if (confirmIndex == 1) {
            renderer.setColor(Color.RED);
        } else {
            renderer.setColor(Color.BLACK);
        }
        int rejectTextWidth = (int)renderer.getFontMetrics().getStringBounds(reject, renderer).getWidth();
        renderer.drawString(reject, Game.width - (Game.tileSize * 2 * 2) - rejectTextWidth / 2, Game.height - (Game.tileSize * 2) - (Game.tileSize / 2));
    }

    private void setNormalFont(Font normalFont) {
        GameUI.normalFont = normalFont;
    }

    public static Font getNormalFont() {
        return normalFont;
    }

    public static void setTitleSelect(int titleSelect) {
        GameUI.titleSelect = titleSelect;
    }

    public static int getTitleSelect() {
        return titleSelect;
    }
}
