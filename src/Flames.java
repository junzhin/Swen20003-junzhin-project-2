import bagel.DrawOptions;
import bagel.Image;
import bagel.Input;
import bagel.util.Point;
import bagel.util.Rectangle;

/**
 * This class is inherited from the abstract superclass of the DynamicElement.
 * It includes the implementation of the Flames in the  game of shadow flap.
 *
 * @author Junzhi Ning
 * @version 1.0
 */
public class Flames extends DynamicElement {

    /**
     * Loading the image of the Flame
     */
    private final Image FLAME_IMAGE = new Image("res/level-1/flame.png");
    /**
     * Common Constants on the screen
     * Note that: The parameters of the flames are changed here to make the game more playable
     */
    private final int FLAMESINTERVALS = 100;
    private final int FLAMESDURATIONS = 20;
    private final int PIPE_GAP;
    /**
     * variables and indicator of Flames
     */
    private int currentLasting;
    private boolean Onflame;
    /**
     *  Drawing options for the bottom pipes
     */
    private final DrawOptions ROTATOR = new DrawOptions().setRotation(Math.PI);

    /**
     * Constructor
     *
     * @param Level : This is the parameter to record the level of the game
     * @param x     : This is the parameter to record the X position of flames
     * @param y     : This is the parameter to record the Y position of flames
     * @param Gap   : This is the parameter to contain the gap between flames
     */
    public Flames(int Level, double x, double y ,int Gap) {
        super(Level);
        super.setX(x);
        super.setY(y);
        PIPE_GAP = Gap;
        currentLasting = 0;
        Onflame = true;
    }
    /**
     * Update the behaviour of the flames in the game
     * @param frameCounter: This is the parameter to record the Frame counter of the game since the start
     * @param input: This is the parameter to pass the input of the user
     */
    @Override
    public void update(long frameCounter, Input input) {

        // the flames should come out every 20 frames
        if(!Onflame && frameCounter% FLAMESINTERVALS == 0){
            Onflame = true;
            currentLasting = 0;
        }

        // If the flames is turned on, then only last for three frames
        if(Onflame){
            if(currentLasting >= FLAMESDURATIONS){
                Onflame = false;
            }
            DrawObject(FLAME_IMAGE);
            currentLasting++;
        }
    }
    /**
     * Implementation of rendering the flames correctly on the game window
     * @param image:  This is the parameter to refer the image of the object
     */
    @Override
    public void DrawObject(Image image) {
        image.draw(super.getX(), super.getY() + FLAME_IMAGE.getHeight()/2.0);
        image.draw(super.getX(), super.getY() + PIPE_GAP - FLAME_IMAGE.getHeight()/2.0, ROTATOR);
    }

    /**
     * Return the rectangle area which can be used to detect the collision for upper flame
     *
     * @return Rectangle :  This returns the object of the rectangle that represent the objects of the upper flame
     */
    public Rectangle getUpBox() {
        return FLAME_IMAGE.getBoundingBoxAt(new Point(super.getX(), super.getY()+FLAME_IMAGE.getHeight()/2.0));
    }

    /**
     * Return the rectangle area which can be used to detect the collision for bottom flame
     *
     * @return Rectangle :  This returns the object of the rectangl that represent the objects of the upper flame
     */
    public Rectangle getBottomBox() {
        return FLAME_IMAGE.getBoundingBoxAt(new Point(super.getX(), super.getY()+PIPE_GAP - FLAME_IMAGE.getHeight()/2.0));
    }
    /**
     * Getters and Setters
     */
    @Override
    public double getX() {
        return super.getX();
    }
    @Override
    public void setX(double x) {
        super.setX(x);
    }
    @Override
    public double getY() {
        return super.getY();
    }
    @Override
    public void setY(double y) {
        super.setY(y);
    }

    /**
     * Is onflame boolean.
     *
     * @return the boolean
     */
    public boolean isOnflame() {
        return Onflame;
    }
}
