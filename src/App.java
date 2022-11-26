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

        lights.add(new Light(new Transform(new Vector3(1000, 1000, 100)), 100));
        objects.add(new Sphere(new Transform(new Vector3(500, 600, 100)), new Material(new Color(255, 0, 0)), 100));

        int w = 1000;
        int h = 1000;

        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        Vector3 lightDirection = new Vector3(1, -1, 0).normalize();

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                Ray ray = new Ray(new Vector3(i, h - j, 0), Vector3.FORWARD);
                ArrayList<GObject> ObjectsInRay = new ArrayList<>();
                for (GObject obj : objects) {
                    Vector3 collisionPoint = obj.getIntersection(ray);
                    if (collisionPoint != null) {
                        Color c = obj.getMaterial(collisionPoint).getColor();
                        ObjectsInRay.add(obj);
                        Vector3 normal = obj.getNormal(collisionPoint);
                        double p = Vector3.dot(normal, lightDirection);
                        if (p < 0)
                            p = 0;
                        c = c.multiply(p);
                        image.setRGB(i, j, c.getRGB());
                    }
                }

            }
        }

        javax.imageio.ImageIO.write(image, "png", new java.io.File("image.png"));

    }
}
