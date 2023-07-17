import java.util.ArrayList;

public class Ticket {
    private ArrayList<Product> purchasedProducts;
    private double total;

    public Ticket() {
        this.purchasedProducts = new ArrayList<>();
        this.total = 0.0;
    }

    public ArrayList<Product> getPurchasedProducts() {
        return this.purchasedProducts;
    }

    public void addProduct(Product product) {
        this.purchasedProducts.add(product);
        this.total += product.getPrice();
    }

    public double getTotal() {
        return this.total;
    }

    @Override
    public String toString() {
        return "Tiquet{" +
                "productesComprats=" + purchasedProducts +
                ", totalCompra=" + total +
                '}';
    }
}
