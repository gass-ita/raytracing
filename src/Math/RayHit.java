package Math;

import Objects.Material;
import Objects.Solids.Solid;

public class RayHit {
    Solid solid;
    Vector3 intersection;
    Ray ray;
    Material material;
    double distance;
    Vector3 normal;

    public RayHit(Solid s, Ray r){
        this.solid = s;
        this.ray = r;
        this.intersection = null;
        this.material = null;
        calculate();
    }

    private void calculate() {
        this.intersection = solid.getIntersection(ray);
        if(intersection != null){
            this.material = solid.getMaterial();
            this.distance = Vector3.distance(ray.getOrigin(), intersection);
            this.normal = solid.getNormal(intersection);
        }

    }

    public Solid getSolid() {
        return solid;
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

    public void setSolid(Solid solid) {
        this.solid = solid;
        calculate();
    }

    public void setRay(Ray ray) {
        this.ray = ray;
        calculate();
    }


    
}
