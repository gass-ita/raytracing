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

    ArrayList<Solid> movedSolids = new ArrayList<Solid>();
    ArrayList<Light> movedLights = new ArrayList<Light>();

    //constructor
    public Scene( ArrayList<Solid> solids, ArrayList<Light> lights){
        this.solids = solids;
        this.lights = lights;

        updateMovedSolids();
        updateMovedLights();
    }

    private void updateMovedSolids(){
        movedSolids.clear();
        for(Solid solid : solids){
            movedSolids.add(solid.copy());
        }
        for(Solid solid : movedSolids){
            solid.translate(Vector3.inverse(cameraMovement));
            //solid.rotate(cameraRotation);
        }
    }

    private void updateMovedLights(){
        movedLights.clear();
        for(Light light : lights){
            movedLights.add(light.copy());
        }
        for(Light light : movedLights){
            light.translate(Vector3.inverse(cameraMovement));
            //light.rotate(cameraRotation);
        }
    }

    public void addSolid(Solid solid){
        solids.add(solid);
    }

    public void addLight(Light light){
        lights.add(light);
    }

    public ArrayList<Solid> getMovedSolids(){
        return this.movedSolids;
    }

    public ArrayList<Light> getMovedLights(){
        return this.movedLights;
    }

    public void moveCamera(Vector3 movement){
        cameraMovement = Vector3.add(cameraMovement, movement);
        updateMovedSolids();
        updateMovedLights();
    }

    public void rotateCamera(Vector3 rotation){
        cameraRotation = Vector3.add(cameraRotation, rotation);
        updateMovedSolids();
        updateMovedLights();
    }

    public Vector3 getCamera(){
        return camera;
    }

    public Vector3 getCameraMovement(){
        return cameraMovement;
    }

    public Vector3 getCameraRotation(){
        return cameraRotation;
    }

    

}