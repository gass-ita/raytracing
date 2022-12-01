package Objects.Solids;

import java.util.ArrayList;

import Math.Ray;
import Math.Vector3;
import Objects.Material;
import Objects.Transform;

public class Plane extends Solid{
    Vector3 p1, p2, p3;

    public Plane (Transform t, Vector3 p2, Vector3 p3, Material m){
        super(t, m);
        this.p1 = t.getPosition();
        this.p2 = p2;
        this.p3 = p3;
    }

    public Plane(Plane plane){
        super(plane.getTransform().copy(), plane.getMaterial().copy());
        this.p1 = plane.getTransform().getPosition().copy();
        this.p2 = plane.p2.copy();
        this.p3 = plane.p3.copy();
    }

    //TRANSLATE 
    public void translate(Vector3 v){
        p1 = p1.add(v);
        p2 = p2.add(v);
        p3 = p3.add(v);
    }


    @Override
    public Solid copy() {
        return new Plane(this);
    }

    @Override
    public Vector3 getNormal(Vector3 point) {
        if(isInSolid(point)){
            Vector3 normal = p2.subtract(p1).cross(p3.subtract(p1));
            normal = normal.normalize();
            return normal;
        }
        return null;
    }

    @Override
    public boolean isInSolid(Vector3 point) {
        if(point == null){
            return false;
        }
        //return if the point is on the surface of the plane
        double error = 0.0001;

        Vector3 normal = p2.subtract(p1).cross(p3.subtract(p1));
        normal = normal.normalize();

        Vector3 relativePoint = point.subtract(p1);

        double result = normal.dot(relativePoint);

        return result <= error && result >= -error;
    }

    @Override
    public Vector3 getIntersection(Ray ray) {
        Vector3 normal = getNormal(p1);
        if(normal == null){
            return null;
        }
        double t = -normal.dot(ray.getOrigin().subtract(p1)) / normal.dot(ray.getDirection());
        if(t < 0){
            return null;
        }
        Vector3 intersection = ray.getOrigin().add(ray.getDirection().multiply(t));
        if(isInSolid(intersection)){
            return intersection;
        }
        return null;
    }

    @Override
    public double getDistance(Ray ray) {
        Vector3 intersection = getIntersection(ray);
        if(intersection == null){
            return Double.POSITIVE_INFINITY;
        }
        return intersection.subtract(ray.getOrigin()).magnitude();
    }

    public ArrayList<Vector3> getPoints(){
        ArrayList<Vector3> points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        return points;
    }

    
    @Override
    public String toString() {
        return "Plane [p1=" + p1 + ", p2=" + p2 + ", p3=" + p3 + "]";
    }

   
    
}
