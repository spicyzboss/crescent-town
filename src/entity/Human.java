package entity;

import inventory.Inventory;

public abstract class Human extends Entity {
    private String gender;
    private HumanWallet wallet;
    private Inventory inventory;

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setWallet(HumanWallet wallet) {
        this.wallet = wallet;
    }

    public HumanWallet getWallet() {
        return wallet;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
