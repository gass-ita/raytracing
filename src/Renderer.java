import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Math.Ray;
import Math.RayHit;
import Math.Vector3;
import Objects.Color;
import Objects.GObject;
import Objects.Light;
import Objects.Solids.Solid;

public class Renderer extends JPanel implements KeyListener {
    private int width;
    private int height;
    private double fov;
    private double fps;
    private double deltaTime;
    private boolean running = false;
    private Scene scene;

    public Renderer(int width, int height, double fov, ArrayList<Solid> solids, ArrayList<Light> lights) {
        this.width = width;
        this.height = height;
        this.fov = fov;
        this.scene = new Scene(solids, lights);

        super.setSize(width, height);
    }

    private RayHit getClosestObject(Ray ray, ArrayList<GObject> objects) {
        RayHit closest = null;
        for (GObject object : objects) {
            RayHit hit = new RayHit(object, ray);
            if (hit.isHit()) {
                if (closest == null) {
                    closest = hit;
                } else {
                    if (hit.getDistance() < closest.getDistance()) {
                        closest = hit;
                    }
                }
            }
        }
        return closest;
    }

    private Vector3 getPixelCoordinates(int x, int y) {
        double d = 1 / Math.tan(Math.toRadians(fov / 2));
        double aspectRatio = (double) width / (double) height;
        double x1 = (2 * ((x + 0.5) / width) - 1) * aspectRatio * d;
        double y1 = (1 - 2 * ((y + 0.5) / height)) * d;
        return new Vector3(x1, y1, d);
    }

    private BufferedImage render() {
        // get delta time
        long start = System.currentTimeMillis();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Vector3 pixelCoordinates = getPixelCoordinates(x, y);
                Ray ray = new Ray(scene.getCamera(), pixelCoordinates);
                ArrayList<GObject> objects = new ArrayList<GObject>();
                for (Solid solid : scene.getMovedSolids()) {
                    objects.add(solid);
                }
                RayHit hit = getClosestObject(ray, objects);

                if (hit != null) {
                    image.setRGB(x, y, hit.getMaterial().getColor().getRGB());
                } else {
                    image.setRGB(x, y, Color.BLACK.getRGB());
                }
            }
        }
        
        long end = System.currentTimeMillis();
        deltaTime = (end - start) / 1000.0;
        fps = 1 / deltaTime;
        return image;
    }

    @Override
    public void paint(Graphics g) {
        BufferedImage image = render();
        g.drawImage(image, 0, 0, null);

        // get the inverse color of the color of the image on the top left corner
        int rgb = image.getRGB(0, 0);
        int re = 255 - ((rgb >> 16) & 0xFF);
        int gr = 255 - ((rgb >> 8) & 0xFF);
        int bl = 255 - (rgb & 0xFF);
        Color color = new Color(re, gr, bl);

        g.setColor(color.getAWTColor());

        g.drawString("FPS: " + fps, 10, 10);
        g.drawString("Position: " + scene.getCameraMovement(), 10, 20);
        Ray ray = new Ray(scene.getCamera(), new Vector3(0, 0, 1));
        RayHit hit = getClosestObject(ray, scene.getMovedObjects());

        if (hit != null) {

            g.drawString("Distance: " + hit.getDistance(), 10, 30);
            g.drawString("Object: " + hit.getObject(), 10, 40);
            g.drawString("Collision Point: " + hit.getIntersection(), 10, 50);
        }

        if (running) {
            repaint();
        }

    }

    public BufferedImage getImage() {
        return render();
    }

    public void renderToFile(String path) {
        try {
            ImageIO.write(render(), "png", new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double getFPS() {
        return fps;
    }

    public void setFov(double fov) {
        this.fov = fov;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getFov() {
        return fov;
    }

    public void start() {
        running = true;
        repaint();
    }

    public void stop() {
        running = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            scene.moveCamera(new Vector3(0, 0, 5).multiply(deltaTime));
        }

        if (e.getKeyCode() == KeyEvent.VK_S) {
            scene.moveCamera(new Vector3(0, 0, -5).multiply(deltaTime));
        }

        if (e.getKeyCode() == KeyEvent.VK_A) {
            scene.moveCamera(new Vector3(-5, 0, 0).multiply(deltaTime));
        }

        if (e.getKeyCode() == KeyEvent.VK_D) {
            scene.moveCamera(new Vector3(5, 0, 0).multiply(deltaTime));
        }

        if (e.getKeyCode() == KeyEvent.VK_Q) {
            scene.moveCamera(new Vector3(0, 5, 0).multiply(deltaTime));
        }

        if (e.getKeyCode() == KeyEvent.VK_E) {
            scene.moveCamera(new Vector3(0, -5, 0).multiply(deltaTime));
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {}

}
