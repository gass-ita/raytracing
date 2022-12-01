package Objects;

public class Color {

    public static final Color BLACK = new Color(0,0,0);

    //default values
    static final Color DEFAULT_COLOR = new Color(0, 0, 0);
    
    private int red;
    private int green;
    private int blue;
    
    public Color(){
        this.red = DEFAULT_COLOR.getRed();
        this.green = DEFAULT_COLOR.getGreen();
        this.blue = DEFAULT_COLOR.getBlue();
    }
    
    public Color(int red, int green, int blue){
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public Color(int bw){
        this.red = bw;
        this.green = bw;
        this.blue = bw;
    }
    
    public Color(Color color){
        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
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

    public java.awt.Color getAWTColor() {
        return new java.awt.Color(red, green, blue);
    }

    public Color copy() {
        return new Color(this);
    }

    
}
