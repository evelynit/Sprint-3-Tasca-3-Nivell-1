import java.util.ArrayList;
import java.util.List;

public class Ticket {
    private List<Product> products = new ArrayList<>();
    private double total = 0.0;

    public void addProducto(Product p) {
        products.add(p);
        total += p.getPrice();
    }

    public double getTotal() {
        return total;
    }
}
