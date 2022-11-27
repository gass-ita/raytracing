package Objects;

import Math.Ray;
import Math.Vector3;

public abstract class GObject {
    static final Transform DEFAULT_TRANSFORM = new Transform();

    protected Transform transform;
    

    public GObject(){
        this.transform = DEFAULT_TRANSFORM;
    }


    public GObject(Transform transform){
        this.transform = transform;
    }

    

    public GObject(GObject object){
        this.transform = object.getTransform();
    }

    abstract public Vector3 getNormal(Vector3 point);
    abstract public Vector3 getIntersection(Ray ray);
    abstract public double getDistance(Ray ray);
    

    public void translate(Vector3 translation){
        this.transform.translate(translation);
    }

   
    public void setTransform(Transform transform){
        this.transform = transform;
    }


    public Transform getTransform(){
        return transform;
    }

    public boolean intersect(Ray ray){
        return getIntersection(ray) != null;
    }

    
    
}
    

