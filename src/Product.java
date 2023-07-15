public abstract class Product {
    protected double price;

    public Product(double price) {
        if(price < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
