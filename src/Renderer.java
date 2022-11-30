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
import Objects.Light;
import Objects.Solids.Solid;


public class Renderer extends JPanel implements KeyListener{
    private int width;
    private int height;
    private Vector3 camera;
    private Vector3 cameraSpeed;
    private double fov;
    private ArrayList<Solid> solids;
    private ArrayList<Light> lights;
    private double fps;
    private double deltaTime;
    private boolean running = false;

    public Renderer(int width, int height, Vector3 camera, double fov, ArrayList<Solid> solids, ArrayList<Light> lights){
        this.width = width;
        this.height = height;
        this.camera = camera;
        this.fov = fov;
        this.solids = solids;
        this.lights = lights;
        this.cameraSpeed = new Vector3(0, 0, 0);
        super.setSize(width, height);
    }
    

    private RayHit getClosestObject(Ray ray){
        RayHit closest = null;
        for(Solid solid : solids){
            RayHit hit = new RayHit(solid, ray);
            if(hit.isHit()){
                if(closest == null){
                    closest = hit;
                }else{
                    if(hit.getDistance() < closest.getDistance()){
                        closest = hit;
                    }
                }
            }
        }
        return closest;
    }

    private Vector3 getPixelCoordinates(int x, int y){
        double d = 1 / Math.tan(Math.toRadians(fov / 2));
        double aspectRatio = (double)width / (double)height;
        double x1 = (2 * ((x + 0.5) / width) - 1) * aspectRatio * d;
        double y1 = (1 - 2 * ((y + 0.5) / height)) * d;
        return new Vector3(x1, y1, d);
    }

    

    private BufferedImage render() {
        //get delta time
        long start = System.currentTimeMillis();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        camera = camera.add(cameraSpeed);
        
        
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Vector3 pixelCoordinates = getPixelCoordinates(x, y);
                Ray ray = new Ray(camera, pixelCoordinates);
                RayHit hit = getClosestObject(ray);
                if(hit != null){
                    Color color = hit.getMaterial().getColor();
                    Vector3 normal = hit.getNormal();
                    Vector3 lightDirection = new Vector3(0, 0, 0);
                    for(Light light : lights){
                        lightDirection = lightDirection.add(light.getTransform().getPosition().subtract(hit.getIntersection()).normalize());
                    }

                    double dot = normal.dot(lightDirection);
                    if(dot < 0){
                        dot = 0;
                    }
                    color = color.multiply(dot);

                    image.setRGB(x, y, color.getRGB());
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
        
        
        g.drawImage(render(), 0, 0, null);

        //get the inverse color of the color of the image on the top left corner
        int rgb = render().getRGB(0, 0);
        int re = 255 - ((rgb >> 16) & 0xFF);
        int gr = 255 - ((rgb >> 8) & 0xFF);
        int bl = 255 - (rgb & 0xFF);
        Color color = new Color(re, gr, bl);

        g.setColor(color.getAWTColor());

        
        
        g.drawString("FPS: " + fps, 10, 10);
        g.drawString("Position: " + camera, 10, 20);
        Ray ray = new Ray(camera, new Vector3(0, 0, 1));
        RayHit hit = getClosestObject(ray);

        if(hit != null){
            g.drawString("Distance: " + hit.getDistance(), 10, 30);
            g.drawString("Object: " + hit.getSolid(), 10, 40);
            g.drawString("Collision Point: " + hit.getIntersection(), 10, 50);
        }

        if (running) {
            repaint();
        }


    }

    public BufferedImage getImage(){
        return render();
    }


    public void renderToFile(String path) {
        try {
            ImageIO.write(render(), "png", new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double getFPS(){
        return fps;
    }

    public void setCamera(Vector3 camera) {
        this.camera = camera;
    }

    public void setFov(double fov) {
        this.fov = fov;
    }

    public void setSolids(ArrayList<Solid> solids) {
        this.solids = solids;
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

    public Vector3 getCamera() {
        return camera;
    }

    public double getFov() {
        return fov;
    }

    public ArrayList<Solid> getSolids() {
        return solids;
    }

    public void start(){
        running = true;
        repaint();
    }

    public void stop(){
        running = false;
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W){
            cameraSpeed = new Vector3(0, 0, 10).multiply(deltaTime);
        }
        if(e.getKeyCode() == KeyEvent.VK_S){
            cameraSpeed = new Vector3(0, 0, -10).multiply(deltaTime);
        }
        if(e.getKeyCode() == KeyEvent.VK_A){
            cameraSpeed = new Vector3(-10, 0, 0).multiply(deltaTime);
        }
        if(e.getKeyCode() == KeyEvent.VK_D){
            cameraSpeed = new Vector3(10, 0, 0).multiply(deltaTime);
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            cameraSpeed = new Vector3(0, 10, 0).multiply(deltaTime);
        }
        if(e.getKeyCode() == KeyEvent.VK_SHIFT){
            cameraSpeed = new Vector3(0, -10, 0).multiply(deltaTime);
        }        
    }


    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_SHIFT){
            cameraSpeed = new Vector3(0, 0, 0);
        }
            
    }


    
        
    }

    

    



    

    