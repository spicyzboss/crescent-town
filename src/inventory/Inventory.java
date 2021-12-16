package inventory;

import entity.Human;
import item.Item;

import java.io.Serializable;
import java.util.*;

public class Inventory implements Serializable {
    private ArrayList<Item> inventory;
    private int maxInventorySize;

    public Inventory(int maxInventorySize) {
        this.inventory = new ArrayList<>();
        this.setMaxInventorySize(maxInventorySize);
    }

    public void setMaxInventorySize(int maxInventorySize) {
        this.maxInventorySize = maxInventorySize;
    }

    public int getMaxInventorySize() {
        return maxInventorySize;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void transferItem(Human human, Item item) {
        if (item != null) {
            human.getInventory().addItem(item);
            this.removeItem(item);
        }
    }

    public void addItem(Item item) {
        if (this.getInventory().size() < this.getMaxInventorySize()) {
            this.getInventory().add(item);
        }
    }

    public void removeItem(Item item) {
        this.getInventory().remove(item);
    }

    public Item getItem(int index) {
        return this.getInventory().get(index);
    }

    public int getSize() {
        return this.getInventory().size();
    }
}
