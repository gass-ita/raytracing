import Math.Ray;
import Math.Vector3;
import Objects.Color;
import Objects.GObject;
import Objects.Light;
import Objects.Material;
import Objects.Sphere;
import Objects.Transform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {

        ArrayList<GObject> objects = new ArrayList<GObject>();
        ArrayList<Light> lights = new ArrayList<Light>();

        lights.add(new Light(new Transform(new Vector3(0, 0, 0)), 100));

        objects.add(new Sphere(new Transform(new Vector3(500, 500, 100)), new Material(new Color(255, 0, 0)), 100));

        BufferedImage image = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);

        for(int x = 0; x < 1000; x++){
            for(int y = 0; y < 1000; y++){
                Ray ray = new Ray(new Vector3(x, y, 0), new Vector3(0, 0, 1));
                Vector3 intersection = null;
                GObject object = null;
                for(GObject o : objects){
                    Vector3 i = o.getIntersection(ray);
                    if(i != null){
                        if(intersection == null){
                            intersection = i;
                            object = o;
                        }else{
                            if(Vector3.subtract(i, ray.getOrigin()).magnitude() < Vector3.subtract(intersection, ray.getOrigin()).magnitude()){
                                intersection = i;
                                object = o;
                            }
                        }
                    }
                }
                if(intersection != null){
                    Color color = new Color(0, 0, 0);
                    for(Light light : lights){
                        Vector3 lightDirection = Vector3.subtract(light.getTransform().getPosition(), intersection);
                        Ray lightRay = new Ray(intersection, lightDirection);
                        boolean blocked = false;
                        for(GObject o : objects){
                            if(o != object){
                                if(o.getIntersection(lightRay) != null){
                                    blocked = true;
                                    break;
                                }
                            }
                        }
                        if(!blocked){
                            double intensity = light.getIntensity() / (lightDirection.magnitude() * lightDirection.magnitude());
                            color = Color.add(color, Color.multiply(object.getMaterial().getColor(), intensity));
                        }
                    }
                    image.setRGB(x, y, color.getRGB());
                }
            }
        }

        javax.imageio.ImageIO.write(image, "png", new java.io.File("image.png"));
        
    } 
}
