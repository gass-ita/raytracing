package Objects;

public class Color {
    
    private int red;
    private int green;
    private int blue;
    
    public Color(){
        this.red = 255;
        this.green = 255;
        this.blue = 255;
    }
    
    public Color(int red, int green, int blue){
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    
    public Color(Color color){
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
    }
    
    public Color copy(){
        return new Color(this);
    }

    static public Color add(Color color1, Color color2){
        return new Color(color1.getRed() + color2.getRed(), color1.getGreen() + color2.getGreen(), color1.getBlue() + color2.getBlue());
    }

    static public Color multiply(Color color, double scalar){
        return new Color((int)(color.getRed() * scalar), (int)(color.getGreen() * scalar), (int)(color.getBlue() * scalar));
    }

    
    public Color add(Color color){
        return new Color(this.red + color.getRed(), this.green + color.getGreen(), this.blue + color.getBlue());
    }
    
    public Color multiply(Color color){
        return new Color(this.red * color.getRed(), this.green * color.getGreen(), this.blue * color.getBlue());
    }
    
    public Color multiply(double scalar){
        return new Color((int)(this.red * scalar), (int)(this.green * scalar), (int)(this.blue * scalar));
    }
    
    public void setRed(int red){
        this.red = red;
    }
    
    public void setGreen(int green){
        this.green = green;
    }
    
    public void setBlue(int blue){
        this.blue = blue;
    }
    
    public int getRed(){
        return red;
    }
    
    public int getGreen(){
        return green;
    }
    
    public int getBlue(){
        return blue;
    }
    
    public String toString(){
        return "Color: (" + red + ", " + green + ", " + blue + ")";
    }

    public int getRGB() {
        return red << 16 | green << 8 | blue;    
    }
}
