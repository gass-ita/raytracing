import java.util.ArrayList;

import Math.Vector3;
import Objects.GObject;
import Objects.Light;
import Objects.Solids.Solid;

/**
 * Scene
 */
public class Scene {
    private final Vector3 camera = Vector3.ZERO;
    private Vector3 cameraMovement = Vector3.ZERO;
    private Vector3 cameraRotation = Vector3.ZERO;

    private ArrayList<Solid> solids = new ArrayList<Solid>();
    private ArrayList<Light> lights = new ArrayList<Light>();
    private ArrayList<GObject> objects = new ArrayList<GObject>();

    private ArrayList<Solid> movedSolids = new ArrayList<Solid>();
    private ArrayList<Light> movedLights = new ArrayList<Light>();
    private ArrayList<GObject> movedObjects = new ArrayList<GObject>();


    //constructor
    public Scene( ArrayList<Solid> solids, ArrayList<Light> lights){
        this.solids = solids;
        this.lights = lights;
        this.objects.addAll(solids);
        this.objects.addAll(lights);

        update();
    }

    /* private void updateMovedSolids(){
        movedSolids.clear();
        for(Solid solid : solids){
            movedSolids.add(solid.copy());
        }
        for(Solid solid : movedSolids){
            solid.translate(Vector3.inverse(cameraMovement));
        }
    }

    private void updateMovedLights(){
        movedLights.clear();
        for(Light light : lights){
            movedLights.add(light.copy());
        }
        for(Light light : movedLights){
            light.translate(Vector3.inverse(cameraMovement));
        }
    } */

    private void update(){
        movedLights.clear();
        movedSolids.clear();
        movedObjects.clear();

        for(GObject object : objects){
            movedObjects.add(object.copy());
        }

        for(GObject object : movedObjects){
            object.translate(Vector3.inverse(cameraMovement));
            if( object instanceof Solid){
                movedSolids.add((Solid) object);
            }else if( object instanceof Light){
                movedLights.add((Light) object);
            }
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

    public ArrayList<GObject> getMovedObjects(){
        return movedObjects;
    }

    public void moveCamera(Vector3 movement){
        cameraMovement = Vector3.add(cameraMovement, movement);
        update();
    }

    public void rotateCamera(Vector3 rotation){
        cameraRotation = Vector3.add(cameraRotation, rotation);
        update();
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