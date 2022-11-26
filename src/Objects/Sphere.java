package Objects;

import Math.Ray;
import Math.Vector3;

public class Sphere extends GObject{

    private double radius;

    public Sphere(){
        super();
        this.radius = 1;
    }

    public Sphere(Transform transform, Material material, double radius){
        super(transform, material);
        this.radius = radius;
    }

    public Sphere(Transform transform, double radius){
        super(transform);
        this.radius = radius;
    }


    @Override
    public Vector3 getNormal(Vector3 point) {
        return point.subtract(transform.getPosition()).normalize();
    }

    //formulas taken from 
    //https://youtu.be/HFPlKQGChpE
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

    
}
