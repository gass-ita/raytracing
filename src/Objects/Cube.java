package Objects;

import Math.Ray;
import Math.Vector3;

public class Cube extends Solid{
    
    static final double DEFAULT_SIZE = 1;


    private double width;
    private double height;
    private double depth;

    public Cube(){
        super();
        this.width = DEFAULT_SIZE;
        this.height = DEFAULT_SIZE;
        this.depth = DEFAULT_SIZE;
    }
    public Cube(Transform transform){
        super(transform);
        this.width = DEFAULT_SIZE;
        this.height = DEFAULT_SIZE;
        this.depth = DEFAULT_SIZE;
    }
    public Cube(Transform transform, Material material){
        super(transform, material);
        this.width = DEFAULT_SIZE;
        this.height = DEFAULT_SIZE;
        this.depth = DEFAULT_SIZE;
    }
    public Cube(Solid solid){
        super(solid.getTransform(), solid.getMaterial());
        this.width =   DEFAULT_SIZE;
        this.height = DEFAULT_SIZE;
        this.depth =  DEFAULT_SIZE;
    }
    public Cube(double width, double height, double depth){
        super();
        this.width = width;
        this.height = height;
        this.depth = depth;
    }
    public Cube(Transform transform, double width, double height, double depth){
        super(transform);
        this.width = width;
        this.height = height;
        this.depth = depth;
    }
    public Cube(Transform transform, Material material, double width, double height, double depth){
        super(transform, material);
        this.width = width;
        this.height = height;
        this.depth = depth;
    }
    public Cube(Solid solid, double width, double height, double depth){
        super(solid.getTransform(), solid.getMaterial());
        this.width = width;
        this.height = height;
        this.depth = depth;
    }
    public Cube(Cube cube){
        super(cube.getTransform(), cube.getMaterial());
        this.width = cube.getWidth();
        this.height = cube.getHeight();
        this.depth = cube.getDepth();
    }
    public Cube copy(){
        return new Cube(this);
    }
    public void setWidth(double width){
        this.width = width;
    }
    public void setHeight(double height){
        this.height = height;
    }
    public void setDepth(double depth){
        this.depth = depth;
    }
    public double getWidth(){
        return width;
    }
    public double getHeight(){
        return height;
    }
    public double getDepth(){
        return depth;
    }

    //TODO: FIX ME
    @Override
    public Vector3 getNormal(Vector3 point){
        Vector3 normal = new Vector3();
        double x = point.getX();
        double y = point.getY();
        double z = point.getZ();
        double w = width / 2 + transform.getPosition().getX();
        double h = height / 2 + transform.getPosition().getY();
        double d = depth / 2  + transform.getPosition().getZ();
        if(x == w || x == -w){
            normal.setX(x);
        }
        if(y == h || y == -h){
            normal.setY(y);
        }
        if(z == d || z == -d){
            normal.setZ(z);
        }
        return normal.normalize();
    }
    @Override
    public Vector3 getIntersection(Ray ray) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public double getDistance(Ray ray) {
        Vector3 intersection = getIntersection(ray);
        if(intersection == null){
            return Double.MAX_VALUE;
        }
        return intersection.distance(ray.getOrigin());
    }


}
    

