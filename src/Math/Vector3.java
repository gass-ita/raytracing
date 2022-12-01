package Math;

public class Vector3 {
    
    public final static Vector3 FORWARD = new Vector3(0, 0, 1);
    public final static Vector3 ZERO = new Vector3(0, 0, 0);
    public final static Vector3 BACK = new Vector3(0, 0, -1);
    public final static Vector3 UP = new Vector3(0, 1, 0);
    public final static Vector3 DOWN = new Vector3(0, -1, 0);
    public final static Vector3 RIGHT = new Vector3(1, 0, 0);
    public final static Vector3 LEFT = new Vector3(-1, 0, 0);

    final static double DEFAULT_X = 0;
    final static double DEFAULT_Y = 0;
    final static double DEFAULT_Z = 0;

    private double x, y, z;

    public Vector3() {
        this(DEFAULT_X, DEFAULT_Y, DEFAULT_Z);
    }

    public Vector3(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }


    public Vector3(Vector3 origin, Vector3 position) {
        this(position.x - origin.x, position.y - origin.y, position.z - origin.z);
    }

    public Vector3(Vector3 vector3) {
        this(vector3.x, vector3.y, vector3.z);
    }

    

    public static Vector3 add(Vector3 v1, Vector3 v2){
        return new Vector3(v1.getX() + v2.getX(), v1.getY() + v2.getY(), v1.getZ() + v2.getZ());
    }

    public static Vector3 subtract(Vector3 v1, Vector3 v2){
        return new Vector3(v1.getX() - v2.getX(), v1.getY() - v2.getY(), v1.getZ() - v2.getZ());
    }

    public static Vector3 multiply(Vector3 v1, Vector3 v2){
        return new Vector3(v1.getX() * v2.getX(), v1.getY() * v2.getY(), v1.getZ() * v2.getZ());
    }

    public static Vector3 multiply(Vector3 v1, double scalar){
        return new Vector3(v1.getX() * scalar, v1.getY() * scalar, v1.getZ() * scalar);
    }

    public static Vector3 divide(Vector3 v1, Vector3 v2){
        return new Vector3(v1.getX() / v2.getX(), v1.getY() / v2.getY(), v1.getZ() / v2.getZ());
    }

    public static Vector3 divide(Vector3 v1, double scalar){
        return new Vector3(v1.getX() / scalar, v1.getX() / scalar, v1.getZ() / scalar);
    }

    public static Vector3 normalize(Vector3 v){
        double length = Math.sqrt(v.getX() * v.getX() + v.getY() * v.getY() + v.getZ() * v.getZ());
        return new Vector3(v.getX() / length, v.getY() / length, v.getZ() / length);
    }

    public static double dot(Vector3 v1, Vector3 v2){
        return v1.getX() * v2.getX() + v1.getY() * v2.getY() + v1.getZ() * v2.getZ();
    }
    
    public static Vector3 cross(Vector3 v1, Vector3 v2){
        return new Vector3(v1.getY() * v2.getZ() - v1.getZ() * v2.getY(), v1.getZ() * v2.getX() - v1.getX() * v2.getZ(), v1.getX() * v2.getY() - v1.getY() * v2.getX());
    }

    public static double magnitude(Vector3 v){
        return Math.sqrt(v.getX() * v.getX() + v.getY() * v.getY() + v.getZ() * v.getZ());
    }
    
    public static double distance(Vector3 p, Vector3 position) {
        return Math.sqrt(Math.pow(p.getX() - position.getX(), 2) + Math.pow(p.getY() - position.getY(), 2) + Math.pow(p.getZ() - position.getZ(), 2));
    }

    public static Vector3 inverse(Vector3 vector) {
        return new Vector3(-vector.getX(), -vector.getY(), -vector.getZ());
    }


    /* PUBLIC METHODS */


    public Vector3 add(Vector3 v1){
        return add(this, v1);
    }

    
    public Vector3 normalize(){
        return normalize(this);
    }
    
    public Vector3 subtract(Vector3 v1){
        return subtract(this, v1);
    }
    
    public Vector3 multiply(Vector3 v1){
        return multiply(this, v1);
    }

    public Vector3 multiply(double scalar){
        return multiply(this, scalar);
    }
    
    public Vector3 divide(Vector3 v1){
        return divide(this, v1);
    }
    
    public Vector3 divide(double scalar){
        return divide(this, scalar);
    }
    
    public double dot(Vector3 v1){
        return dot(this, v1);
    }
    
    public Vector3 cross(Vector3 v1){
        return cross(this, v1);
    }
    
    public double magnitude(){
        return magnitude(this);
    }

    public double distance(Vector3 position) {
        return distance(this, position);
    }

    /* SETTERS AND GETTERS */

    public void setX(double newX){
        
        x = newX;
    }

    public void setY(double newY){
        
        y = newY;
    }

    public void setZ(double newZ){
        
        z = newZ;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double getZ(){
        return z;
    }

    @Override
    public String toString() {
        return "Vector3 [x=" + x + ", y=" + y + ", z=" + z + "]";
    }

    public Vector3 copy() {
        return new Vector3(this);
    }

    

   




}
