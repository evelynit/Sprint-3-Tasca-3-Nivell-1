public class Decoration extends Product{

    public enum Material{
        FUSTA, PLASTIC
    }

    private Material material;

    // Getter and Setter
    public Decoration(String name, double price, int stock, Material material){
        super(name, price, stock);
        this.material = material;
    }

    public Material getMaterial() {
        return this.material;
    }
    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Decoracio [nom=" + getName() + ", preu=" + getPrice() + ", stock=" + getStock() + ", material=" + material + "]";
    }

}
