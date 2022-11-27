import Math.Ray;
import Math.Vector3;
import Objects.Color;
import Objects.Cube;
import Objects.GObject;
import Objects.Light;
import Objects.Material;
import Objects.Sphere;
import Objects.Transform;
import java.awt.image.BufferedImage;
import java.nio.channels.Pipe;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        Sphere c = new Sphere(new Transform(new Vector3(0, 0, 10)));

        

        System.out.println(c.getNormal(new Vector3(0, 0, 0)));


    }
}
