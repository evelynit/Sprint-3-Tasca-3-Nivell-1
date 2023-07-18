import java.util.ArrayList;
public class Florist {
    private String name;
    private static ArrayList<Tree> trees;
    private static ArrayList<Flower> flowers;
    private static ArrayList<Decoration> decorations;
    private double totalStock;

    // Constructor
    public Florist(String name) {
        this.name = name;
        trees = new ArrayList<>();
        flowers = new ArrayList<>();
        decorations = new ArrayList<>();
        totalStock = totalStock;
    }


    // Getters and Setters
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public double getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(double totalStock) {
        this.totalStock = totalStock;
    }

    public static void addProduct(Florist product) {
        Ticket.getProducts().add(product);
        System.out.println("Product added to the catalog");

        int stock = 0;

        if (product instanceof Tree tree) {
            int currentStock = tree.getStock() + stock;
            tree.setStock(currentStock);
        } else if (product instanceof Flower flower) {
            flower.setStock(flower.getStock());
        } else if (product instanceof Decoration decoration) {
            decoration.setStock(decoration.getStock());
        }
    }


    // METHODS
    public void reduceStock(Florist foundProduct, int quantity) {
        if (foundProduct instanceof Tree tree) {
            tree.setStock(tree.getStock() - quantity);
        } else if (foundProduct instanceof Flower flower) {
            flower.setStock(flower.getStock() - quantity);
        } else if (foundProduct instanceof Decoration decoration) {
            decoration.setStock(decoration.getStock() - quantity);
        }
    }

    public double calculateTotalStockValue(ArrayList<Florist> products) {
        double totalStockValue = 0.0;

        for (Florist product : products) {
            if (product instanceof Tree tree) {
                totalStockValue += tree.getStock() * tree.getPrice();
            } else if (product instanceof Flower flower) {
                totalStockValue += flower.getStock() * flower.getPrice();
            } else if (product instanceof Decoration decoration) {
                totalStockValue += decoration.getStock() * decoration.getPrice();
            }
        }

        return totalStockValue;
    }

    public void calculateStockQuantities(ArrayList<Florist> products) {
        System.out.println("STOCK");

        for (Florist product : products) {
            if (product instanceof Tree tree) {
                System.out.println("TREES:\n" + (tree.getStock()));
            } else if (product instanceof Flower flower) {
                System.out.println("FLOWERS:\n" + (flower.getStock()));
            } else if (product instanceof Decoration decoration) {
                System.out.println("DECORATIONS:\n" + (decoration.getStock()));
            }
        }
    }

    public Florist findProductByName(String productName) {

        for (Florist tree : Ticket.getProducts()) {
            if (tree.getName().equalsIgnoreCase(productName)) {
                return tree;
            }
        }

        for (Florist flower : Ticket.getProducts()) {
            if (flower.getName().equalsIgnoreCase(productName)) {
                return flower;
            }
        }

        for (Florist decoration : Ticket.getProducts()) {
            if (decoration.getName().equalsIgnoreCase(productName)) {
                return decoration;
            }
        }

        return null;
    }


    // Getters and Setters for lists
    public static ArrayList<Flower> getFlowers() {
        return flowers;
    }

    public static ArrayList<Tree> getTrees() {
        return trees;
    }

    public static ArrayList<Decoration> getDecorations() {
        return decorations;
    }

    public Ticket getTicket() {

        return null;
    }

    public void viewTicketDetail() {
        Ticket.viewTicketHistory(Ticket.getPurchases());
    }

    @Override
    public String toString() {
        return "Florist:" + name;
    }

}
