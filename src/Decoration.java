public class Decoration extends Product{

    public enum Material{
        MADERA, PLASTICO
    }

    private Material material;

    public Decoration(double price, Material material){
        super(price);
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
