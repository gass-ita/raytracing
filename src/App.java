import Math.Ray;
import Math.RayHit;
import Math.Vector3;
import Objects.Color;
import Objects.Light;
import Objects.Material;
import Objects.Transform;
import Objects.Solids.Ellipsoid;
import Objects.Solids.Plane;
import Objects.Solids.Solid;


import java.util.ArrayList;


import javax.swing.JFrame;
import javax.swing.text.PlainDocument;

import Debug.Debug;


public class App {
    public static void main(String[] args) throws Exception {

        
        
        
        ArrayList<Solid> solids = new ArrayList<Solid>();
        ArrayList<Light> lights = new ArrayList<Light>();

        Plane p = new Plane(new Transform(new Vector3(0, -10, 0)), new Vector3(0, -10, 1), new Vector3(1, -10, 0), new Material(new Color(255, 0, 255)));

        //print p points
        Debug.log("p points:");
        for(Vector3 point : p.getPoints()){
            Debug.log(point);
        }

        Ray r = new Ray(Vector3.UP, Vector3.DOWN);
        RayHit rh = new RayHit(p, r);
        Debug.log(rh.getMaterial().getColor());

        
        solids.add(p);

        Ellipsoid e = new Ellipsoid(new Transform(new Vector3(0, -5, 5), new Vector3(5, 5, 5)), new Material(new Color(255, 0, 0)));
        Ellipsoid e2 = new Ellipsoid(new Transform(new Vector3(0, -15, 5), new Vector3(5, 5, 5)), new Material(new Color(255, 0, 0)));
        
        solids.add(e);
        solids.add(e2);

        lights.add(new Light(new Vector3(0, 0, 10), 10));

        Renderer renderer = new Renderer(500, 500, 110, solids, lights);
        JFrame frame = new JFrame();
        
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        //modify the renderer w and h to match the frame when the frame is resized
        frame.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                renderer.setSize(frame.getWidth(), frame.getHeight());
                renderer.setWidth(frame.getWidth());
                renderer.setHeight(frame.getHeight());
            }
        });
        

        frame.add(renderer);
        frame.addKeyListener(renderer);
        renderer.start();
        

        

        

        
        
        

        
    }
}
