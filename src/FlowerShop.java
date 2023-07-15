import java.util.ArrayList;
import java.util.List;

public class FlowerShop {
    private String name;
    private List<Tree> trees = new ArrayList<>();
    private List<Flower> flowers = new ArrayList<>();
    private List<Decoration> decorations = new ArrayList<>();
    private double totalPrice = 0.0;

    public FlowerShop(String name){
        this.name = name;
    }

    public void addTree(Tree tree){
        trees.add(tree);
        totalPrice += tree.getPrice();
    }

    public void addFlower(Flower flower){
        flowers.add(flower);
        totalPrice += flower.getPrice();
    }

    public void addDecoration(Decoration decoration){
        decorations.add(decoration);
        totalPrice += decoration.getPrice();
    }

    public void removeTree(Tree tree) {
        if(trees.remove(tree)) {
            totalPrice -= tree.getPrice();
        }
    }

    public void removeFlower(Flower flower) {
        if(flowers.remove(flower)) {
            totalPrice -= flower.getPrice();
        }
    }

    public void removeDecoration(Decoration decoration) {
        if(decorations.remove(decoration)) {
            totalPrice -= decoration.getPrice();
        }
    }
    public double calculateTotalPrice() {
        return totalPrice;
    }

    public void printTrees() {
        System.out.println("Arboles en stock:");
        for(Tree i : trees) {
            System.out.println("Altura: " + i.getHeight() + ", Precio: " + i.getPrice());
        }
    }

    public void printFlowers() {
        System.out.println("Flores en stock:");
        for(Flower i : flowers) {
            System.out.println("Color: " + i.getColor() + ", Precio: " + i.getPrice());
        }
    }

    public void printDecorations() {
        System.out.println("Decoraciones en stock:");
            for(Decoration i : decorations) {
            System.out.println("Material: " + i.getMaterial() + ", Precio: " + i.getPrice());
        }
    }

}
