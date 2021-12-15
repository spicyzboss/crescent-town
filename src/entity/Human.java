package entity;

public abstract class Human extends Entity {
    private String gender;
    private HumanWallet wallet;

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
}
