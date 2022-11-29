import Math.Ray;
import Math.Vector3;
import Objects.Color;
import Objects.Material;
import Objects.Transform;
import Objects.Solids.Ellipsoid;
import Objects.Solids.Solid;
import Objects.Solids.Sphere;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class App {
    public static void main(String[] args) throws Exception {
        

        ArrayList<Solid> solids = new ArrayList<>();
        //solids.add(new Ellipsoid(new Transform(new Vector3(255, 255, 100), new Vector3(10, 10, 10)), new Material(new Color(255, 0, 0))));
        solids.add(new Ellipsoid(new Transform(new Vector3(225, 255, 100), new Vector3(10, 10, 10)), new Material(new Color(0, 255, 0))));
        solids.add(new Ellipsoid(new Transform(new Vector3(255, 255, 100), new Vector3(10, 10, 10)), new Material(new Color(255, 0, 0))));
        solids.add(new Ellipsoid(new Transform(new Vector3(285, 255, 100), new Vector3(10, 10, 10)), new Material(new Color(0, 0, 255))));

        

        Renderer renderer = new Renderer(500, 500, new Vector3(0, 0, 0), 90, solids, null);
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
        renderer.setCamera(new Vector3(255, 255, 0));
            
        

        

        
        
        

        
    }
}
