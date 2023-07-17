public class Tree extends Product{
    private double height;

    public Tree(String name, double price, int stock, double height) {
        super(name, price, stock);
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Arbre [nom=" + getName() + ", preu=" + getPrice() + ", stock=" + getStock() + ", alcada=" + height + "]";
    }
}
