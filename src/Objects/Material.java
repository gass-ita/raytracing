package Objects;

public class Material {
    //default values
    static final Color DEFAULT_COLOR = new Color(0, 0, 0);
    static final double DEFAULT_REFLECTIVITY = 0;

    
    private Color color;
    private double reflectivity;

    
    
    public Material(){
        this.color = DEFAULT_COLOR;
        this.reflectivity = DEFAULT_REFLECTIVITY;
    }


    public Material(Color color, double reflectivity){
        this.color = color;
        this.reflectivity = reflectivity;
    }

    public Material(Color color){
        this.color = color;
        this.reflectivity = DEFAULT_REFLECTIVITY;
    }

    public Material(double reflectivity){
        this.color = DEFAULT_COLOR;
        this.reflectivity = reflectivity;
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
