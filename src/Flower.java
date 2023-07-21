public class Flower extends Florist{
    private String name;
    private double price;
    private int stock;
    private String color;

    // Constructor
    public Flower(String name, double price, String color, int stock) {
        super(name);
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.color = color;
    }

    // Getter and Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return name + "," + color + "," + price + "," + stock;
    }
}
