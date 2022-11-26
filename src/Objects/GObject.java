package Objects;

import Math.Ray;
import Math.Vector3;

public abstract class GObject {
    protected Transform transform;
    protected Material material;

    public GObject(){
        this.transform = new Transform();
        this.material = new Material();
    }

    public GObject(Transform transform, Material material){
        this.transform = transform;
        this.material = material;
    }

    public GObject(Transform transform){
        this.transform = transform;
        this.material = new Material();
    }

    public GObject(Material material){
        this.transform = new Transform();
        this.material = material;
    }

    public GObject(GObject object){
        this.transform = object.getTransform();
        this.material = object.getMaterial();
    }

    abstract public Vector3 getNormal(Vector3 point);
    abstract public Vector3 getIntersection(Ray ray);
    

    public void translate(Vector3 translation){
        this.transform.translate(translation);
    }

   
    public void setTransform(Transform transform){
        this.transform = transform;
    }

    public void setMaterial(Material material){
        this.material = material;
    }

    public Transform getTransform(){
        return transform;
    }

    public Material getMaterial(){
        return material;
    }

    public Material getMaterial(Vector3 p){
        return material;
    }

    public boolean intersect(Ray ray){
        return getIntersection(ray) != null;
    }
    
}
    

