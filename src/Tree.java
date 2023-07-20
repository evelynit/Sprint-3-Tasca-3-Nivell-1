public class Tree extends Florist{
    private String name;
    private double price;
    private int stock;
    private double height;

    // Constructor
    public Tree(String name, double price, int stock, double height) {
        super(name);
        this.name = name;
        this.price = price;
        this.stock  = stock;
        this.height = height;
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

    public int setStock(int stock) {
        this.stock = stock;
        return stock;
    }
    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Tree," + name + "," + price + "," + stock + "," + height;
    }
}
