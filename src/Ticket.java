import java.util.ArrayList;

public class Ticket {

    private static int ticketCounter = 0;
    private int ticketNumber;
    private static ArrayList<Florist> purchases;
    private static ArrayList<Florist> products;

    static {
        products = new ArrayList<>();
    }
    static {
        purchases = new ArrayList<>();
    }

    public static ArrayList<Florist> getProducts() {
        return products;
    }

    public Ticket() {
        this.ticketNumber = ++ticketCounter;
        purchases = new ArrayList<>();
    }

    public void addPurchases(Florist foundProduct) {
        purchases.add(foundProduct);
    }

    public static void viewTicketHistory(ArrayList<Florist> purchases) {
        System.out.println("Ticket History:");
        for (int i = 0; i < purchases.size(); i++) {
            Florist ticket = purchases.get(i);
            System.out.println("Ticket #" + (i + 1));
            ticket.viewTicketDetail();
            System.out.println(); // Line break to separate tickets
        }
    }

    public static double calculateTotal() {
        int totalProducts = purchases.size();
        double totalPrice = 0.0;

        System.out.println("Items on the ticket:");
        for (Florist product : purchases) {
            if (product instanceof Flower flower) {
                System.out.println("- Flower: " + flower.getName() + ": Price: " + flower.getPrice() + "€");
                totalPrice += flower.getPrice();
            } else if (product instanceof Tree tree) {
                System.out.println("- Tree: " + tree.getName() + ": Price: " + tree.getPrice() + "€");
                totalPrice += tree.getPrice();
            } else if (product instanceof Decoration decoration) {
                System.out.println("- Decoration: " + decoration.getName() + ": Price: " + decoration.getPrice() + "€");
                totalPrice += decoration.getPrice();
            }
        }

        System.out.println("Total products in the ticket: " + totalProducts);
        System.out.println("Total price in the ticket: " + totalPrice);

        return totalPrice;
    }

    public void setPurchases(ArrayList<Florist> purchases) {
        Ticket.purchases = purchases;
    }

    public static ArrayList<Florist> getPurchases() {
        return purchases;
    }

    public static void setProducts(ArrayList<Florist> products) {
        Ticket.products = products;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

}
