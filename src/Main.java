import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        List<Florist> florists = new ArrayList<>();
        florists = Florist.loadProductsFromFile("resources/florists.txt", florists);

        for (Florist florist : florists) {
            System.out.println(florist.getName());
        }

        boolean exit = false;
        do {
            switch (getOption()) {
                case 1 -> addFlorist(florists);
                case 2 -> addTree(florists);
                case 3 -> addFlower(florists);
                case 4 -> addDecoration(florists);
                case 5 -> removeTree(florists);
                case 6 -> removeFlower(florists);
                case 7 -> removeDecoration(florists);
                case 8 -> printStock(Ticket.getProducts());
                case 9 -> stockWithQuantities(florists);
                case 10 -> stockWithPrices(florists);
                //case 11 -> createTicket(florist);
                //case 12 -> ticketHistory();
                case 13 -> totalSales(Ticket.getPurchases());
                case 0 -> {
                    System.out.println("Exit the application");
                    exit = true;
                }
            }
        } while (!exit);
    }

    public static byte getOption() {
        final byte MINIMUM = 0;
        final byte MAXIMUM = 13;
        byte option;

        do {
            System.out.println("\nMAIN MENU");
            System.out.println("1. Create florist");
            System.out.println("2. Add tree");
            System.out.println("3. Add flower");
            System.out.println("4. Add decoration");
            System.out.println("5. Remove tree");
            System.out.println("6. Remove flower");
            System.out.println("7. Remove decoration");
            System.out.println("8. View all products");
            System.out.println("9. View stock (quantities)");
            System.out.println("10. View total product value");
            System.out.println("11. Create purchase ticket");
            System.out.println("12. Show history tickets");
            System.out.println("13. Total billing");
            System.out.println("0. Exit\n");
            option = input.nextByte();
            input.nextLine();
            if (option < MINIMUM || option > MAXIMUM) {
                System.out.println("Choose a valid option");
            }
        } while (option < MINIMUM || option > MAXIMUM);
        return option;
    }

    private static void addFlorist(List<Florist> florists) {
        System.out.println("What is the Florist's name?");
        String name = input.nextLine();

        Florist florist= existFlorist(name, florists);
        if(florist == null){
            florist = new Florist(name);
            florists.add(florist);


            // Write to file
            try (FileWriter fw = new FileWriter("resources/florists.txt", true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println(name);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("The florist " + name + " has been created successfully.");


        } else{
            System.out.println("The florist " + name + " already exist!!");
        }
    }

    private static Florist existFlorist(String name, List<Florist> florists) {
        for (Florist florist : florists) {
            if (florist.getName().equalsIgnoreCase(name)) {
                return florist;
            }
        }
        return null;

    }
    private static Florist selectFloristByName(List<Florist> florists) {
        System.out.println("Select a florist by entering its name:");
        for (Florist florist : florists) {
            System.out.println(florist.getName());
        }

        String name = input.nextLine();
        for (Florist florist : florists) {
            if (florist.getName().equalsIgnoreCase(name)) {
                return florist;
            }
        }
        System.out.println("No florist found with the name " + name);
        return null;
    }

    private static void addTree(List<Florist> florists) {
        Florist florist = selectFloristByName(florists);

        if (florist != null) {
            System.out.print("Enter the tree's name: ");
            String treeName = input.nextLine();
            System.out.print("Enter the tree's price: ");
            double treePrice = input.nextDouble();
            System.out.print("Enter the number of trees: ");
            int stock = input.nextInt();
            System.out.print("Enter the tree's height: ");
            double treeHeight = input.nextDouble();
            input.nextLine();

            Tree tree = new Tree(treeName, treePrice, stock, treeHeight);
            florist.addProduct(tree);
            // save to file
            Florist.writeProductsToFile(florists, "resources/florists.txt");
        }
    }

    private static void addFlower(List<Florist> florists) {
        Florist florist = selectFloristByName(florists);

        if (florist != null) {
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
            Florist.writeProductsToFile(florists, "resources/florists.txt");
        }

    }

    private static void addDecoration(List<Florist> florists) {
        Florist florist = selectFloristByName(florists);

        if (florist != null) {
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
            //System.out.println(Ticket.getProducts());
            Florist.writeProductsToFile(florists, "resources/florists.txt");
        }
    }

    private static void removeDecoration(List<Florist> florists) {
        Florist florist = selectFloristByName(florists);

        if(florist != null) {
            System.out.print("Enter the name of the decoration to remove: ");
            String decorationName = input.nextLine();
            System.out.print("Enter the number of units to remove: ");
            int unitsToRemove = input.nextInt();
            input.nextLine(); // Consume the newline character

            Decoration decoration = florist.getDecorationByName(decorationName);
            if (decoration != null) {
                int currentStock = decoration.getStock();
                if (unitsToRemove <= currentStock) {
                    decoration.setStock(currentStock - unitsToRemove);
                    System.out.println(unitsToRemove + " units of the decoration " + decoration.getName() + " were removed.");
                } else {
                    System.out.println("There are not enough units of that decoration in the stock.");
                }
            } else {
                System.out.println("The decoration with that name was not found in the catalog.");
            }
        }
    }

    private static void removeFlower(List<Florist> florists) {
        Florist florist = selectFloristByName(florists);

        if(florist != null) {
            System.out.print("Enter the name of the flower to remove: ");
            String flowerName = input.nextLine();
            System.out.print("Enter the number of units to remove: ");
            int unitsToRemove = input.nextInt();
            input.nextLine(); // Consume the newline character

            Flower flower = florist.getFlowerByName(flowerName);
            if (flower != null) {
                int currentStock = flower.getStock();
                if (unitsToRemove <= currentStock) {
                    flower.setStock(currentStock - unitsToRemove);
                    System.out.println(unitsToRemove + " units of the flower " + flower.getName() + " were removed.");
                } else {
                    System.out.println("There are not enough units of that flower in the stock.");
                }
            } else {
                System.out.println("The flower with that name was not found in the catalog.");
            }
        }
    }

    private static void removeTree(List<Florist> florists) {
        Florist florist = selectFloristByName(florists);

        if (florist != null) {

            System.out.print("Enter the name of the tree to remove: ");
            String treeName = input.nextLine();
            System.out.print("Enter the number of units to remove: ");
            int unitsToRemove = input.nextInt();
            input.nextLine(); // Consume the newline character

            Tree tree = florist.getTreeByName(treeName);
            if (tree != null) {
                int currentStock = tree.getStock();
                if (unitsToRemove <= currentStock) {
                    tree.setStock(currentStock - unitsToRemove);
                    System.out.println(unitsToRemove + " units of the tree " + tree.getName() + " were removed.");
                } else {
                    System.out.println("There are not enough units of that tree in the stock.");
                }
            } else {
                System.out.println("The tree with that name was not found in the catalog.");
            }
        }
    }

    private static void printStock(List<Florist> florists) {
        Florist florist = selectFloristByName(florists);

        if (florist != null) {
            System.out.println(florist.getName() + "Florist's Stock:");

            System.out.println("Trees:");
            for (Tree tree : florist.getTrees()) {
                System.out.println(tree);
            }

            System.out.println("Flowers:");
            for (Flower flower : florist.getFlowers()) {
                System.out.println(flower);
            }

            System.out.println("Decorations:");
            for (Decoration decoration : florist.getDecorations()) {
                System.out.println(decoration);
            }
        }
    }

    private static void stockWithQuantities(List<Florist> florists) {
        Florist selectedFlorist = selectFloristByName(florists);
        selectedFlorist.calculateStockQuantities();
    }

    private static void stockWithPrices(List<Florist> florists) {
        Florist selectedFlorist = selectFloristByName(florists);
        double totalProductValue = selectedFlorist.calculateTotalStockValue();
        System.out.println("Total value of the products: " + totalProductValue);
    }

    /**
    private static void createTicket(List<Florist> florists) {
        Florist selectedFlorist = selectFloristByName(florists);
        boolean addProducts = true;
        Ticket ticket = new Ticket();

        while (addProducts) {
            System.out.println("Enter the name of the product:");
            String productName = input.nextLine();
            System.out.println("Enter the quantity of the product:");
            int quantity = input.nextInt();
            input.nextLine(); // Consume the newline character

            // Search the product by its name in Florist
            Florist foundProduct = selectedFlorist.findProductByName(productName);

            if (foundProduct != null && quantity > 0) {
                // Add the product to the ticket
                ticket.addPurchases(foundProduct, quantity);
                System.out.println("Product added to the ticket.");
                foundProduct.reduceStock(quantity);

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
        selectedFlorist.addTicket(ticket);
    }


    private static void ticketHistory(List<Florist> florists) {
        Florist selectedFlorist = selectFloristByName(florists);
        selectedFlorist.viewTicketHistory();
    }

    **/
    public static void totalSales(List<Florist> florists) {
        double totalSales = 0.0;

        for (Florist florist : florists) {
            totalSales += florist.getTotalSales();
        }

        System.out.println("Total sales are: " + totalSales + "€");
    }

}