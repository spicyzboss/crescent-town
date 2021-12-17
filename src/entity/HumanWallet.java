package entity;

import main.Game;

public class HumanWallet {
    private double money;

    public HumanWallet() {
        this(0);
    }

    public HumanWallet(double initialMoney) {
        this.setMoney(initialMoney);
    }

    public void transfer(Human human, double amount) {
        if (amount > 0) {
            if (this.getMoney() >= amount) {
                human.getWallet().setMoney(human.getWallet().getMoney() + amount);
                this.setMoney(this.getMoney() - amount);
            } else {
                Game.playSoundEffect("cancel");
            }
        }
    }

    public void receiveMoney(double amount){
        if(amount > 0) {
            this.setMoney(this.getMoney() + amount);
        } else {
            Game.playSoundEffect("cancel");
        }
    }

    public void reduceMoney(double amount) {
        if (amount > 0) {
            this.setMoney(this.getMoney() - amount);
        } else {
            Game.playSoundEffect("cancel");
        }
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }
}
