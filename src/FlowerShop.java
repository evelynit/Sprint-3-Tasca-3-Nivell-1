import java.util.ArrayList;
import java.util.List;

public class FlowerShop {
    private String name;
    private ArrayList<Tree> trees;
    private ArrayList<Flower> flowers;
    private ArrayList<Decoration> decorations;
    private double valueTotalStock;

    // Constructor

    public FlowerShop(String name){
        this.name = name;
        this.trees = new ArrayList<>();
        this.flowers = new ArrayList<>();
        this.decorations = new ArrayList<>();
        this.valueTotalStock = 0.0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getValueTotalStock() {
        return valueTotalStock;
    }

    public void setValueTotalStock(double valueTotalStock) {
        this.valueTotalStock = valueTotalStock;
    }

    // Métodos para añadir y retirar productos
    public void addTree(Tree tree) {
        this.trees.add(tree);
        updateValueTotalStock();
    }

    public void addFlower(Flower flower) {
        this.flowers.add(flower);
        updateValueTotalStock();
    }

    public void addDecoration(Decoration decoration) {
        this.decorations.add(decoration);
        updateValueTotalStock();
    }

    public void removeTree(Tree tree) {
        this.trees.remove(tree);
        updateValueTotalStock();
    }

    public void removeFlower(Flower flower) {
        this.flowers.remove(flower);
        updateValueTotalStock();
    }

    public void removeDecoration(Decoration decoration) {
        this.decorations.remove(decoration);
        updateValueTotalStock();
    }

    // Actualizar el valor total del stock
    private void updateValueTotalStock() {
        double value = 0.0;
        for (Tree tree : this.trees) {
            value += tree.getPrice();
        }
        for (Flower flower : this.flowers) {
            value += flower.getPrice();
        }
        for (Decoration decoration : this.decorations) {
            value += decoration.getPrice();
        }
        this.valueTotalStock = value;
    }

    // Imprimir el stock
    public void printStock() {
        System.out.println("Stock de la floristeria " + this.name + ":");
        System.out.println("Arbres: " + this.trees.size());
        for (Tree tree : this.trees) {
            System.out.println(tree);
        }
        System.out.println("Flors: " + this.flowers.size());
        for (Flower flower : this.flowers) {
            System.out.println(flower);
        }
        System.out.println("Decoracions: " + this.decorations.size());
        for (Decoration decoration : this.decorations) {
            System.out.println(decoration);
        }
        System.out.println("Valor total del stock: " + this.valueTotalStock);
    }

}
