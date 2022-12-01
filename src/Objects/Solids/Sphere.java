package Objects.Solids;

import Math.Ray;
import Math.Vector3;
import Objects.Material;
import Objects.Transform;

public class Sphere extends Solid{
    static final double DEFAULT_RADIUS = 1;

    private double radius;

    public Sphere(){
        super();
        this.radius = DEFAULT_RADIUS;
    }

    public Sphere(Transform transform, double radius){
        super(transform);
        this.radius = radius;
    }

    public Sphere(Sphere sphere){
        super(sphere.getTransform().copy(), sphere.getMaterial().copy());
        this.radius = sphere.getRadius();
    }

    public Sphere(Transform transform, int radius, Material material) {
        super(transform, material);
        this.radius = radius;
    }

    public Sphere(Transform transform) {
        super(transform);
        this.radius = DEFAULT_RADIUS;
    }



    @Override
    public Vector3 getNormal(Vector3 point) {
        if(isInSolid(point)){
            return Vector3.subtract(point, getTransform().getPosition()).normalize();
        }
        return null;
    }

    @Override
    public boolean isInSolid(Vector3 point) {
        double error = 0.0001;
        return Vector3.subtract(point, getTransform().getPosition()).magnitude() - radius < error;
    }

    

    @Override
    public Vector3 getIntersection(Ray ray) {
        double t = Vector3.dot(Vector3.subtract(transform.getPosition(), ray.getOrigin()), ray.getDirection());
        Vector3 p = Vector3.add(ray.getOrigin(), Vector3.multiply(ray.getDirection(), t));
        double y = Vector3.subtract(p, transform.getPosition()).magnitude();
        if(y > radius){
            return null;
        }
        double x = Math.sqrt(Math.pow(radius, 2) - Math.pow(y, 2));
        double t1 = t - x;
        double t2 = t + x;
        if(t1 < 0 && t2 < 0){
            return null;
        }
        if(t1 < 0){
            return ray.getPoint(t2);
        }
        if(t2 < 0){
            return ray.getPoint(t1);
        }
        return ray.getPoint(Math.min(t1, t2));

        
    }

    @Override
    public double getDistance(Ray ray) {
        Vector3 intersection = getIntersection(ray);
        if(intersection != null){
            return Vector3.subtract(ray.getOrigin(), intersection).magnitude();
        }
        return Double.POSITIVE_INFINITY;
    }


    public double getRadius(){
        return radius;
    }

    public void setRadius(double radius){
        this.radius = radius;
    }

    @Override
    public Sphere copy() {
        return new Sphere(this);
    }

    @Override
    public String toString() {
        return "Sphere [radius=" + radius + ", transform=" + transform + ", material=" + material + "]";
    }

    




    

}
