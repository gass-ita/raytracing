package Objects;

import Math.Vector3;

public class Transform {
    private Vector3 position;
    private Vector3 scale;
    

    public Transform(){
        this.position = new Vector3();
        
    }

    
    public Transform(Vector3 position, Vector3 scale){
        this.position = position;
        this.scale = scale;
        
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


    public void setScale(Vector3 scale){
        this.scale = scale;
    }

    public Vector3 getScale(){
        return scale;
    }

    @Override
    public String toString(){
        return "Transform: " + "position: " + position + " scale: " + scale;
    }

   
}
