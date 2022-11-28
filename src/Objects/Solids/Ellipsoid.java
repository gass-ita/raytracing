
package Objects.Solids;

import Math.Ray;
import Math.Vector3;
import Objects.Material;
import Objects.Transform;

public class Ellipsoid extends Solid {

    public Ellipsoid(){
        super();
    }

    public Ellipsoid(Transform transform){
        super(transform);
    }

    public Ellipsoid(Transform transform, Material material){
        super(transform, material);
    }

    public Ellipsoid(Ellipsoid ellipsoid){
        super(ellipsoid.getTransform(), ellipsoid.getMaterial());
    }





    @Override
    public Vector3 getNormal(Vector3 point) {
        if(isInSolid(point)){
            Vector3 normal = point.subtract(getTransform().getPosition());
            normal = normal.divide(getTransform().getScale());
            return normal;
        }
        return null;
    }

    @Override
    public boolean isInSolid(Vector3 point) {
        if(point == null){
            return false;
        }
        //return if the point is on the surface of the ellipsoid
        double error = 0.0001;

        Vector3 center = transform.getPosition();
        Vector3 radius = transform.getScale();

        Vector3 relativePoint = point.subtract(center);

        double x = relativePoint.getX() / radius.getX();
        double y = relativePoint.getY() / radius.getY();
        double z = relativePoint.getZ() / radius.getZ();

        double result = x*x + y*y + z*z;

        return result <= 1 + error && result >= 1 - error;
        }

    @Override
    public Vector3 getIntersection(Ray ray) {
        //move the center of the ellipsoid to the origin
        Vector3 origin = Vector3.subtract(ray.getOrigin(), transform.getPosition());
        Vector3 direction = ray.getDirection();
        //scale the ray to the unit sphere
        origin = Vector3.divide(origin, transform.getScale());
        direction = Vector3.divide(direction, transform.getScale());
        //calculate the intersection
        double a = direction.getX() * direction.getX() + direction.getY() * direction.getY() + direction.getZ() * direction.getZ();
        double b = 2 * (origin.getX() * direction.getX() + origin.getY() * direction.getY() + origin.getZ() * direction.getZ());
        double c = origin.getX() * origin.getX() + origin.getY() * origin.getY() + origin.getZ() * origin.getZ() - 1;
        double discriminant = b * b - 4 * a * c;
        if(discriminant < 0){
            return null;
        }
        double t1 = (-b + Math.sqrt(discriminant)) / (2 * a);
        double t2 = (-b - Math.sqrt(discriminant)) / (2 * a);
        double t = Math.min(t1, t2);
        if(t < 0){
            return null;
        }
        //scale the intersection back to the ellipsoid
        Vector3 intersection = Vector3.add(origin, Vector3.multiply(direction, t));
        intersection = Vector3.multiply(intersection, transform.getScale());
        //move the intersection back to the ellipsoid's position
        intersection = Vector3.add(intersection, transform.getPosition());
        return intersection;
        
    }

    @Override
    public double getDistance(Ray ray) {
        Vector3 intersection = getIntersection(ray);
        if(intersection == null){
            return Double.POSITIVE_INFINITY;
        }
        return Vector3.subtract(intersection, ray.getOrigin()).magnitude();

    }
        
}