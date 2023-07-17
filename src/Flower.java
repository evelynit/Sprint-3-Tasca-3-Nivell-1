public class Flower extends Product{
    private String color;

    // Constructor

    public Flower(String name, double price, int stock, String color){
        super(name, price, stock);
        this.color = color;
    }

    // Getter and Setter

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Flor [nom=" + getName() + ", preu=" + getPrice() + ", stock=" + getStock() + ", color=" + color + "]";
    }

}
