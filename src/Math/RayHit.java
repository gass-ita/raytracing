package Math;

import Objects.Material;
import Objects.Solids.Solid;
import Objects.GObject;

public class RayHit {
    GObject object;
    Vector3 intersection;
    Ray ray;
    Material material;
    double distance;
    Vector3 normal;

    public RayHit(GObject s, Ray r){
        this.object = s;
        this.ray = r;
        this.intersection = null;
        calculate();
    }

    private void calculate() {
        this.intersection = object.getIntersection(ray);
        if(intersection != null){
            this.distance = Vector3.distance(ray.getOrigin(), intersection);
            if(object instanceof Solid){
                this.normal = ((Solid)object).getNormal(intersection);
                this.material = ((Solid)object).getMaterial();
            }
        }

    }

    public GObject getObject() {
        return object;
    }

    public Vector3 getIntersection() {
        return intersection;
    }

    public Ray getRay() {
        return ray;
    }

    public Material getMaterial() {
        return material;
    }

    public boolean isHit() {
        return intersection != null;
    }

    public double getDistance() {
        return distance;
    }

    public Vector3 getNormal() {
        return normal;
    }

    public void setSolid(GObject object) {
        this.object = object;
        calculate();
    }

    public void setRay(Ray ray) {
        this.ray = ray;
        calculate();
    }


    
}
