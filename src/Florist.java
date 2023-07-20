import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Florist {
    private String name;
    private static ArrayList<Tree> trees;
    private static ArrayList<Flower> flowers;
    private  static ArrayList<Decoration> decorations;
    private double totalStock;
    private  static ArrayList<Ticket> tickets;
    private double totalSales;

    // Constructor
    public Florist(String name) {
        this.name = name;
        trees = new ArrayList<>();
        flowers = new ArrayList<>();
        decorations = new ArrayList<>();
        tickets = new ArrayList<>();
        totalStock = 0;
        totalSales = 0;
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

    public Tree getTreeByName(String name) {
        for (Tree tree : this.trees) {
            if (tree.getName().equalsIgnoreCase(name)) {
                return tree;
            }
        }
        return null;
    }

    public Flower getFlowerByName(String name) {
        for (Flower flower : this.flowers) {
            if (flower.getName().equalsIgnoreCase(name)) {
                return flower;
            }
        }
        return null;
    }

    public Decoration getDecorationByName(String name) {
        for (Decoration decoration : this.decorations) {
            if (decoration.getName().equalsIgnoreCase(name)) {
                return decoration;
            }
        }
        return null;
    }

    // NEW METHODS
    public static ArrayList<Florist> loadProductsFromFile(String fileName, List<Florist> florists) {
        ArrayList<Florist> newFlorists = new ArrayList<>();

        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] parts = line.split(",");

                String floristName = parts[0];
                String productType = parts[1];

                Florist florist = findFloristByName(floristName, florists);
                if (florist == null) {
                    florist = new Florist(floristName);
                    newFlorists.add(florist);
                }

                if (productType.equals("Tree")) {
                    String name = parts[2];
                    double height = Double.parseDouble(parts[3]);
                    double price = Double.parseDouble(parts[4]);
                    int stock = Integer.parseInt(parts[5]);
                    florist.addProduct(new Tree(name, price, stock, height));
                } else if (productType.equals("Flower")) {
                    String name = parts[2];
                    String color = parts[3];
                    double price = Double.parseDouble(parts[4]);
                    int stock = Integer.parseInt(parts[5]);
                    florist.addProduct(new Flower(name, price, color, stock));
                } else if (productType.equals("Decoration")) {
                    String name = parts[2];
                    double price = Double.parseDouble(parts[3]);
                    int stock = Integer.parseInt(parts[4]);
                    String material = parts[5];
                    florist.addProduct(new Decoration(name, price, stock, material));
                }
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return newFlorists;
    }

    private static Florist findFloristByName(String name, List<Florist> florists) {
        for (Florist florist : florists) {
            if (florist.getName().equals(name)) {
                return florist;
            }
        }
        return null;
    }

    public static void writeProductsToFile(List<Florist> florists, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            for (Florist florist : florists) {
                for (Tree tree : florist.getTrees()) {
                    writer.write(florist.getName() + ",Tree," + tree.toString() + System.lineSeparator());
                }
                for (Flower flower : florist.getFlowers()) {
                    writer.write(florist.getName() + ",Flower," + flower.toString() + System.lineSeparator());
                }
                for (Decoration decoration : florist.getDecorations()) {
                    writer.write(florist.getName() + ",Decoration," + decoration.toString() + System.lineSeparator());
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
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

    public void reduceStock(Florist foundProduct, int quantity) {
        if (foundProduct instanceof Tree tree) {
            tree.setStock(tree.getStock() - quantity);
        } else if (foundProduct instanceof Flower flower) {
            flower.setStock(flower.getStock() - quantity);
        } else if (foundProduct instanceof Decoration decoration) {
            decoration.setStock(decoration.getStock() - quantity);
        }
    }

    public double calculateTotalStockValue() {
        double totalStockValue = 0.0;

        for (Tree tree : trees) {
            totalStockValue += tree.getStock() * tree.getPrice();
        }

        for (Flower flower : flowers) {
            totalStockValue += flower.getStock() * flower.getPrice();
        }

        for (Decoration decoration : decorations) {
            totalStockValue += decoration.getStock() * decoration.getPrice();
        }

        return totalStockValue;
    }

    public void calculateStockQuantities() {
        System.out.println("STOCK");

        int totalTreeStock = trees.stream().mapToInt(Tree::getStock).sum();
        System.out.println("TREES:\n" + totalTreeStock);

        int totalFlowerStock = flowers.stream().mapToInt(Flower::getStock).sum();
        System.out.println("FLOWERS:\n" + totalFlowerStock);

        int totalDecorationStock = decorations.stream().mapToInt(Decoration::getStock).sum();
        System.out.println("DECORATIONS:\n" + totalDecorationStock);
    }

    /**
    public void viewTicketDetail() {
        Ticket.viewTicketHistory(Ticket.getPurchases());
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
        totalSales += ticket.getTotal(); // Assuming Ticket has a method getTotal
    }

    public void viewTicketHistory() {
        for (Ticket ticket : tickets) {
            ticket.viewTicketDetail(); // Assuming Ticket has a method viewTicketDetails
        }
    }

     **/
    public double getTotalSales() {
        return totalSales;
    }
    @Override
    public String toString() {
        return "Florist:" + name;
    }

}
