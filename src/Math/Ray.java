package Math;

public class Ray {
    Vector3 origin;
    Vector3 direction;

    public Ray(Vector3 origin, Vector3 direction){
        this.origin = origin;
        this.direction = direction;
    }

    public static Vector3 getPoint(Ray ray, double t){
        return ray.getOrigin().add(ray.getDirection().multiply(t));
    }
    
    public Vector3 getPoint(double t){
        return getPoint(this, t);
    }

    /* GETTERS AND SETTERS */   
    
    public Vector3 getOrigin(){
        return origin;
    }
    
    public Vector3 getDirection(){
        return direction;
    }

    public void setOrigin(Vector3 origin){
        this.origin = origin;
    }

    public void setDirection(Vector3 direction){
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Ray [direction=" + direction + ", origin=" + origin + "]";
    }
}
