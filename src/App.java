import Math.Ray;
import Math.Vector3;
import Objects.Color;
import Objects.GObject;
import Objects.Material;
import Objects.Sphere;
import Objects.Transform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {

        

         ArrayList<GObject> objects = new ArrayList<GObject>();
        objects.add(new Sphere(new Transform(new Vector3(500, 500, 50)), new Material(new Color(255, 0, 0)), 60));
        objects.add(new Sphere(new Transform(new Vector3(200, 500, 50)), new Material(new Color(0, 255, 0)), 100));
        // make an image of 1000x1000 pixels
        int width = 1000;
        int height = 1000;

        // create a new image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // for each pixel in the image (i, j) throw a forward ray

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Ray ray = new Ray(new Vector3(i, j, 0), new Vector3(0, 0, 1));
                Color color = new Color(0, 0, 0);
                for (GObject object : objects) {
                    if (object.intersect(ray)) {
                        Vector3 intersection = object.getIntersection(ray);
                        color = object.getMaterial(intersection).getColor();
                        break;
                    }
                }
                image.setRGB(i, j, color.getRGB());
            }
        }

        // save the image
        javax.imageio.ImageIO.write(image, "png", new java.io.File("image.png"));
    }
}
