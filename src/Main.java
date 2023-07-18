import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Florist florist = new Florist("Cielo");
        Tree tree = new Tree("Olive", 40.0, 40, 3);
        florist.addProduct(tree);
        Flower flower = new Flower("Rose", 2.5, "Red", 20);
        florist.addProduct(flower);
        Decoration decoration = new Decoration("flowerpot", 10, 30, "Plastic");
        florist.addProduct(decoration);

        boolean exit = false;
        do {
            switch (getOption()) {
                case 1 -> addFlorist();
                case 2 -> addTree();
                case 3 -> addFlower();
                case 4 -> addDecoration();
                case 5 -> removeTree(Ticket.getProducts());
                case 6 -> removeFlower(Ticket.getProducts());
                case 7 -> removeDecoration(Ticket.getProducts());
                case 8 -> printStock(Ticket.getProducts());
                case 9 -> stockWithQuantities(florist);
                case 10 -> stockWithPrices(florist);
                case 11 -> createTicket(florist);
                case 12 -> ticketHistory();
                case 13 -> totalSales(Ticket.getPurchases());
                case 0 -> {
                    System.out.println("Exit the application");
                    exit = true;
                }
            }
        } while (!exit);
    }

    public static byte getOption(){

        byte option;
        final byte MINIMUM = 0;
        final byte MAXIMUM = 13;

        do{
            System.out.println("\nMAIN MENU");
            System.out.println("1. Create Florist");
            System.out.println("2. Add Tree");
            System.out.println("3. Add Flower");
            System.out.println("4. Add Decoration");
            System.out.println("5. Remove Tree");
            System.out.println("6. Remove Flower");
            System.out.println("7. Remove Decoration");
            System.out.println("8. View all products");
            System.out.println("9. View Stock with quantities");
            System.out.println("10. View total product value in the florist");
            System.out.println("11. Create Purchase Ticket");
            System.out.println("12. Show list of old tickets");
            System.out.println("13. View total billing (€)");
            System.out.println("0. Exit the application\n");
            option = input.nextByte();
            input.nextLine();
            if(option < MINIMUM || option > MAXIMUM){
                System.out.println("Choose a valid option");
            }
        }while(option < MINIMUM || option > MAXIMUM);
        return option;
    }

    private static Florist addFlorist() {
        System.out.println("What is the Florist's name?");
        String name = input.nextLine();
        return new Florist(name);
    }

    private static void addTree() {
        Florist florist = addFlorist();

        System.out.print("Enter the tree's name: ");
        String treeName = input.nextLine();
        System.out.print("Enter the tree's price: ");
        double treePrice = input.nextDouble();
        System.out.print("Enter the number of trees: ");
        int stock = input.nextInt();
        System.out.print("Enter the tree's height: ");
        double treeHeight = input.nextDouble();

        Tree tree = new Tree(treeName, treePrice, stock, treeHeight);
        florist.addProduct(tree);
    }

    private static void addFlower() {
        Florist florist = addFlorist();

        System.out.print("Enter the flower's name: ");
        String flowerName = input.nextLine();
        System.out.print("Enter the flower's price: ");
        double flowerPrice = input.nextDouble();
        input.nextLine();
        System.out.print("Enter the flower's color: ");
        String color = input.nextLine();
        System.out.print("Enter the number of flowers: ");
        int stock = input.nextInt();

        Flower flower = new Flower(flowerName, flowerPrice, color, stock);
        florist.addProduct(flower);
    }

    private static void addDecoration() {
        Florist florist = addFlorist();

        System.out.print("Enter the article's name: ");
        String name = input.nextLine();
        System.out.print("Enter the article's price: ");
        double treePrice = input.nextDouble();
        System.out.print("Enter the quantity: ");
        int stock = input.nextInt();
        input.nextLine();
        System.out.print("Enter the material: ");
        String material = input.nextLine();

        Decoration decoration = new Decoration(name, treePrice, stock, material);
        florist.addProduct(decoration);
        System.out.println(Ticket.getProducts());
    }

    private static void removeDecoration(ArrayList<Florist> products) {
        System.out.print("Enter the name of the article to remove: ");
        String articleName = input.nextLine();
        System.out.print("Enter the number of units to remove: ");
        int unitsToRemove = input.nextInt();
        input.nextLine(); // Consume the newline character

        for (Florist decoration : Ticket.getProducts()) {
            if (decoration instanceof Decoration && decoration.getName().equalsIgnoreCase(articleName)) {
                int currentStock = ((Decoration) decoration).getStock();

                if (unitsToRemove <= currentStock) {
                    ((Decoration) decoration).setStock(currentStock - unitsToRemove);
                    System.out.println(unitsToRemove + " units of the article " + decoration.getName() + " were removed.");
                } else {
                    System.out.println("There are not enough units of that article in the stock.");
                }
                return;
            }
        }

        System.out.println("The article with that name was not found in the catalog.");
    }

    private static void removeFlower(ArrayList<Florist> products) {
        System.out.print("Enter the name of the flower to remove: ");
        String flowerName = input.nextLine();
        System.out.print("Enter the number of units to remove: ");
        int unitsToRemove = input.nextInt();
        input.nextLine(); // Consume the newline character

        for (Florist flower : Ticket.getProducts()) {
            if (flower instanceof Flower && flower.getName().equalsIgnoreCase(flowerName)) {
                int currentStock = ((Flower) flower).getStock();

                if (unitsToRemove <= currentStock) {
                    ((Flower) flower).setStock(currentStock - unitsToRemove);
                    System.out.println(unitsToRemove + " units of the flower " + flower.getName() + " were removed.");
                } else {
                    System.out.println("There are not enough units of that flower in the stock.");
                }
                return;
            }
        }
        System.out.println("The flower with that name was not found in the catalog.");
    }

    private static void removeTree(ArrayList<Florist> products) {
        System.out.print("Enter the name of the tree to remove: ");
        String treeName = input.nextLine();
        System.out.print("Enter the number of units to remove: ");
        int unitsToRemove = input.nextInt();
        input.nextLine(); // Consume the newline character

        for (Florist tree : Ticket.getProducts()) {
            if (tree instanceof Tree && tree.getName().equalsIgnoreCase(treeName)) {
                int currentStock = ((Tree) tree).getStock();

                if (unitsToRemove <= currentStock) {
                    ((Tree) tree).setStock(currentStock - unitsToRemove);
                    System.out.println(unitsToRemove + " units of the tree " + tree.getName() + " were removed.");
                } else {
                    System.out.println("There are not enough units of that tree in the stock.");
                }
                return;
            }
        }
        System.out.println("The tree with that name was not found in the catalog.");
    }

    private static void printStock(ArrayList<Florist> products) {
        System.out.println("Florist's Stock:");

        System.out.println("Trees:");
        for (Florist product : products) {
            if (product instanceof Tree) {
                System.out.println(product);
            }
        }

        System.out.println("Flowers:");
        for (Florist product : products) {
            if (product instanceof Flower) {
                System.out.println(product);
            }
        }

        System.out.println("Decorations:");
        for (Florist product : products) {
            if (product instanceof Decoration) {
                System.out.println(product);
            }
        }
    }
    private static void stockWithQuantities(Florist florist) {
        florist.calculateStockQuantities(Ticket.getProducts());
    }

    private static void stockWithPrices(Florist florist) {
        double totalProductValue = florist.calculateTotalStockValue(Ticket.getProducts());
        System.out.println("Total value of the products: " + totalProductValue);
    }

    private static void createTicket(Florist florist) {
        boolean addProducts = true;
        Ticket ticket = new Ticket();

        while (addProducts) {
            System.out.println("Enter the name of the product:");
            String productName = input.nextLine();
            System.out.println("Enter the quantity of the product:");
            int quantity = input.nextInt();
            input.nextLine(); // Consume the newline character

            // Search the product by its name in Florist
            Florist foundProduct = florist.findProductByName(productName);

            if (foundProduct != null && quantity > 0) {
                // Add the product to the ticket
                ticket.addPurchases(foundProduct);
                System.out.println("Product added to the ticket.");
                foundProduct.reduceStock(foundProduct, quantity);

                System.out.println("Do you want to add another product? (Y/N)");
                String option = input.nextLine();
                if (option.equalsIgnoreCase("N")) {
                    addProducts = false;
                }
            } else {
                System.out.println("The product was not found in the Florist or the quantity is invalid.");
            }
        }

        ticket.calculateTotal();
    }

    private static void ticketHistory() {
        Ticket.viewTicketHistory(Ticket.getPurchases());
    }

    public static void totalSales(ArrayList<Florist> purchases) {
        double totalSales = 0.0;

        for (Florist purchase : purchases) {
            Ticket ticket = purchase.getTicket();
            double totalTicket = ticket.calculateTotal();
            totalSales += totalTicket;
        }

        System.out.println("Total sales are: " + totalSales + "€");
    }
}