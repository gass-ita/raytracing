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
        super(light.getTransform());
        this.intensity = light.getIntensity();
    }



    @Override
    public Vector3 getNormal(Vector3 point) {
        return null;
    }

    @Override
    public Vector3 getIntersection(Ray ray) {
        Vector3 position = transform.getPosition();
        double t = Vector3.dot(Vector3.subtract(position, ray.getOrigin()), ray.getDirection());
        Vector3 p = Vector3.add(ray.getOrigin(), Vector3.multiply(ray.getDirection(), t));
        if(Vector3.subtract(p, position).magnitude() < 0.1){
            return p;
        }
        return null;
               
    }

    public double getIntensity(){
        return intensity;
    }

    public void setIntensity(double intensity){
        this.intensity = intensity;
    }

    @Override
    public double getDistance(Ray ray) {
        if(getIntersection(ray) != null){
            return Vector3.subtract(ray.getOrigin(), getIntersection(ray)).magnitude();
        }
        return Double.MAX_VALUE;
    }
    
}
