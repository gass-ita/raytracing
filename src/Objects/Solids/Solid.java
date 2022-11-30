package Objects.Solids;

import Math.Ray;
import Math.Vector3;
import Objects.GObject;
import Objects.Material;
import Objects.Transform;

public abstract class Solid extends GObject{
    
    
    static final Material DEFAULT_MATERIAL = new Material();
    
    private Material material;
    

    public Solid(){
        super();
        this.material = DEFAULT_MATERIAL;
    }

    public Solid(Transform transform){
        super(transform);
        this.material = DEFAULT_MATERIAL;
    }

    public Solid(Transform transform, Material material){
        super(transform);
        this.material = material;
    }

    public Solid(Solid solid){
        super(solid.getTransform());
        this.material = solid.getMaterial();
    }

    public abstract Solid copy();

    public Material getMaterial(){
        return material;
    }

    public void setMaterial(Material material){
        this.material = material;
    }

    abstract public Vector3 getNormal(Vector3 point);
    abstract public boolean isInSolid(Vector3 point);

    
    @Override
    abstract public Vector3 getIntersection(Ray ray);

    @Override
    abstract public double getDistance(Ray ray);
}
