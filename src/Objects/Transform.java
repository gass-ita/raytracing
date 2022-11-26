package Objects;

import Math.Vector3;

public class Transform {
    private Vector3 position;
    

    public Transform(){
        this.position = new Vector3();
        
    }

    public Transform(Vector3 position, Vector3 rotation, Vector3 scale){
        this.position = position;
        
    }

    public Transform(Vector3 position, Vector3 rotation){
        this.position = position;
        
    }

    public Transform(Vector3 position){
        this.position = position;
        
    }

    public Transform(Transform transform){
        this.position = transform.getPosition();
        
    }

    public Transform copy(){
        return new Transform(this);
    }

    public void translate(Vector3 translation){
        this.position = this.position.add(translation);
    }

   

    public void setPosition(Vector3 position){
        this.position = position;
    }

   

    public Vector3 getPosition(){
        return position;
    }

   
}
