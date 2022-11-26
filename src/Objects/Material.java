package Objects;

public class Material {
    
    private Color color;
    private double reflectivity;

    
    
    public Material(){
        this.color = new Color();
        this.reflectivity = 0;
    }

    public Material(Color color, double reflectivity, double transparency, double refractiveIndex){
        this.color = color;
        this.reflectivity = reflectivity;
    }

    public Material(Color color, double reflectivity){
        this.color = color;
        this.reflectivity = reflectivity;
    }

    public Material(Color color){
        this.color = color;
        this.reflectivity = 0;
    }

    public Material(Material material){
        this.color = material.getColor();
        this.reflectivity = material.getReflectivity();
    }

    public void setColor(Color color){
        this.color = color;
    }

    public void setReflectivity(double reflectivity){
        this.reflectivity = reflectivity;
    }

    public Color getColor(){
        return color;
    }

    public double getReflectivity(){
        return reflectivity;
    }
}
