import Math.Ray;
import Math.Vector3;
import Objects.Color;
import Objects.Material;
import Objects.Transform;
import Objects.Solids.Ellipsoid;
import Objects.Solids.Sphere;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class App {
    public static void main(String[] args) throws Exception {
        Ellipsoid e = new Ellipsoid(new Transform(new Vector3(250, 350, 500), new Vector3(100, 50, 20)), new Material(new Color(255, 0, 0)));
        Ellipsoid e2 = new Ellipsoid(new Transform(new Vector3(100, 250, 200), new Vector3(50, 100, 20)), new Material(new Color(0, 255, 0)));

        ArrayList<Ellipsoid> ellipsoids = new ArrayList<Ellipsoid>();
        ellipsoids.add(e);
        ellipsoids.add(e2);

        


        BufferedImage image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        
        for(int x = 0; x < 500; x++){
            for(int y = 0; y < 500; y++){
                

                Ray ray = new Ray(new Vector3(x, 500 - y, 0), new Vector3(0, 0, 1));
                
                Color c = new Color(0, 0, 0);
                for(Ellipsoid ellipsoid : ellipsoids){
                    if(ellipsoid.intersects(ray)){
                        c = ellipsoid.getMaterial().getColor();
                        break;
                    }
                }
                image.setRGB(x, y, c.getRGB());
            }
        }

        ImageIO.write(image, "png", new File("image.png"));

        
    }
}
