import java.util.ArrayList;
import java.util.Vector;

import Math.Vector3;
import Objects.Light;
import Objects.Solids.Solid;

/**
 * Scene
 */
public class Scene {
    //the camera position is fixed
    Vector3 camera = Vector3.ZERO;
    Vector3 cameraMovement = Vector3.ZERO;
    Vector3 cameraRotation = Vector3.ZERO;

    ArrayList<Solid> solids = new ArrayList<Solid>();
    ArrayList<Light> lights = new ArrayList<Light>();

    //constructor
    public Scene( ArrayList<Solid> solids, ArrayList<Light> lights){
        this.solids = solids;
        this.lights = lights;
    }

    public void addSolid(Solid solid){
        solids.add(solid);
    }

    public void addLight(Light light){
        lights.add(light);
    }

    public ArrayList<Solid> getMovedSolids(){
        //return the solids with the camera position added to them
        ArrayList<Solid> solids = new ArrayList<Solid>();
        for(Solid solid : this.solids){
            Solid solidCopy = solid.copy();
            solidCopy.getTransform().setPosition(Vector3.add(solidCopy.getTransform().getPosition(), camera));
        }
        return solids;
    }

    public ArrayList<Light> getMovedLights(){
        ArrayList<Light> lights = new ArrayList<Light>();
        for(Light light : this.lights){
            Light lightCopy = light.copy();
            lightCopy.getTransform().setPosition(Vector3.add(lightCopy.getTransform().getPosition(), camera));
        }
        return lights;
    }

    public void moveCamera(Vector3 movement){
        cameraMovement = Vector3.add(cameraMovement, movement);
    }

    public void rotateCamera(Vector3 rotation){
        cameraRotation = Vector3.add(cameraRotation, rotation);
    }
    

}