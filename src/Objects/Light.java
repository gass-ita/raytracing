package Objects;

import Math.Ray;
import Math.Vector3;

public class Light extends GObject{
    static final double DEFAULT_INTENSITY = 1;

    private double intensity;

    public Light(){
        super();
        this.intensity = DEFAULT_INTENSITY;
    }

    public Light(Vector3 position, double intensity){
        super(new Transform(position));
        this.intensity = intensity;
    }


    public Light(Transform transform, double intensity){
        super(transform);
        this.intensity = intensity;
    }

    public Light(Light light){
        super(light.getTransform().copy());
        this.intensity = light.getIntensity();
    }



    

    @Override
    public Vector3 getIntersection(Ray ray) {
        //ray point intersection
        double error = .1;
        
        Vector3 rayPoint = ray.getOrigin();
        Vector3 rayDirection = ray.getDirection();
        Vector3 lightPosition = getTransform().getPosition();

        double t = Vector3.dot(Vector3.subtract(lightPosition, rayPoint), rayDirection);
        if(t < 0){
            return null;
        }
        Vector3 p = Vector3.add(rayPoint, Vector3.multiply(rayDirection, t));
        double y = Vector3.subtract(p, lightPosition).magnitude();
        if(y > error){
            return null;
        }
        return p;
    }

    public double getIntensity(){
        return intensity;
    }

    public void setIntensity(double intensity){
        this.intensity = intensity;
    }

    @Override
    public double getDistance(Ray ray) {
        Vector3 intersection = getIntersection(ray);
        if(intersection != null){
            return Vector3.subtract(ray.getOrigin(), intersection).magnitude();
        }
        return Double.MAX_VALUE;
    }

    @Override
    public Light copy() {
        return new Light(this);
    }

    @Override
    public String toString() {
        return "Light [intensity=" + intensity + ", transform=" + transform + "]";
    }
    
}
