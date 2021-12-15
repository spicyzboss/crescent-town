package inventory;

public class Item {
    private String name;
    private double buyPrice;
    private double sellPrice;

    public Item(String name, double buyPrice, double sellPrice) {
        this.setName(name);
        this.setBuyPrice(buyPrice);
        this.setSellPrice(sellPrice);
    }

    @Override
    public String toString() {
        return "{name=" + name + ", buyPrice=" + buyPrice + ", sellPrice=" + sellPrice + "}";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }
}
