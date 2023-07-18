public class Decoration extends Florist{
    private String name;
    private double price;
    private int stock;
    private String material;

    public Decoration(String name, double price, int stock, String material) {
        super(name);
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.material = material;
    }

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

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        if (material.equalsIgnoreCase("wood") || material.equalsIgnoreCase("plastic")) {
            this.material = material;
        } else {
            throw new IllegalArgumentException("Material can only be 'wood' or 'plastic'.");
        }
    }

    @Override
    public String toString() {
        return "Item:" + name + "/ Material:" + material + "/ Price:" + price + " €/ Stock: " + stock;
    }

}
