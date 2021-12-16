import bagel.Image;
import bagel.Input;
import bagel.Window;
import bagel.util.Point;
import bagel.util.Rectangle;
import java.util.Random;

/**
 * This class is inherited from the abstract superclass of the DynamicElement,
 * It includes the implementation of the weapons(e.g Rock and Bomb) object in the game of shadow flap
 *
 * @author Junzhi Ning
 * @version 1.0
 */
public class Weapon extends DynamicElement implements Movable{
    /**
     * Image of the weapon
     */
    private final Image WEAPON;
    /**
     * Parameters of the weapon properties
     */
    private final int SHOOTINGDURATION;
    private final boolean ALLOWEDTOATTACKSTEEL;
    private double SHOOTINGSPEED = 5;
    private final int SHOOTINGDURATIONROCK = 25;
    private final int SHOOTINGDURATIONBOMB = 50;
    private final int LEVEL1 = 1;
    /**
     * motion variables
     */
    static double currentMovingSpeed = 0;
    private double existenceDurationofCounter;
    /**
     * Indicators
     */
    private boolean Attached;
    private boolean OnFire;
    /**
     * Random Generators of the weapon position
     */
    private final Random rand;
    private final int UPPERBOUNDFORPOSITIONLEVEL1 = 400;
    private final int UPPERBOUNDFORTYPES = 2;
    private final int ADJUSTINGPOSITIONY = 100;

    /**
     * Constructor
     *
     * @param Level :  This is parameter to indicate which level the game belongs to
     */
    public Weapon(int Level) {
        super(Level);
        super.setX(Window.getWidth());

        // Randomly generated the y values
        rand = new Random();
        super.setY(rand.nextInt(UPPERBOUNDFORPOSITIONLEVEL1) + ADJUSTINGPOSITIONY);

        // Load the weapon image and weapon properties accordingly
        if(rand.nextInt(UPPERBOUNDFORTYPES)==LEVEL1){
            WEAPON = new Image("res/level-1/rock.png");
            SHOOTINGDURATION = SHOOTINGDURATIONROCK;
            ALLOWEDTOATTACKSTEEL = false;
        }else {
            WEAPON = new Image("res/level-1/bomb.png");
            SHOOTINGDURATION = SHOOTINGDURATIONBOMB;
            ALLOWEDTOATTACKSTEEL = true;
        }

        OnFire = false;
        Attached = false;
        existenceDurationofCounter = 0;
    }
    /**
     * Implementation of Drawings of weapon
     * @param image: This is parameter to refer an image
     */
    @Override
    public void DrawObject(Image image) {
        image.draw(super.getX(),super.getY());
    }

    /**
     * Update Behaviour of a weapon in a game
     * @param frameCounter:  This is the parameter to record the frame count of the game since the start
     * @param input: This is the parameter to pass the input of the user
     */
    @Override
    public void update(long frameCounter, Input input) {

        // make sure the weapon is still exists
        // when off-screen completely, the weapon disappears
        if(existenceDurationofCounter > SHOOTINGDURATION || super.getX() < -WEAPON.getWidth() ){
            super.setObjectExistence(false);
            OnFire = false;
        }
        // Count for the duration of counter
        if(OnFire){
            existenceDurationofCounter++;
        }
        // If the weapon is used or shot out of the range, weapon disappears.
        if(!super.isObjectExistence()){
            return;
        }

        // when the weapon is not attached by the bird
        updatePosition(input);

        DrawObject(WEAPON);
    }
    /**
     * Update the position of the weapon object
     * @param input: This is the parameter to pass the input of the user
     */
    @Override
    public void updatePosition(Input input) {
        // If Onfire, then it moves from left to right
        if(OnFire){
            super.setX(super.getX() + SHOOTINGSPEED);
        } else if (!Attached){
            // movea in the same direction as the pipesets
            super.setX(super.getX() - currentMovingSpeed);
        }
    }

    /**
     * Detection of pipes with the weapon
     *
     * @param pipeset : This is the parameter to refer to an object of Pipes class
     * @return boolean : This returns the  indicator if the collision happens
     */
    public boolean detectCollisionPipes(Pipes pipeset) {
        // Check for collision
        return this.getBox().intersects(pipeset.getTopBox()) ||
                this.getBox().intersects(pipeset.getBottomBox());
    }

    /**
     * Getters and Setters
     *
     * @return the box
     */
    public Rectangle getBox() {
        return WEAPON.getBoundingBoxAt(new Point(super.getX(), super.getY()));
    }

    /**
     * Is allowedtoattacksteel boolean.
     *
     * @return the boolean
     */
    public boolean isALLOWEDTOATTACKSTEEL() {
        return ALLOWEDTOATTACKSTEEL;
    }

    /**
     * Sets speed.
     *
     * @param Speed the speed
     */
    public static void setSpeed(double Speed) {currentMovingSpeed = Speed;}

    /**
     * Sets attached.
     *
     * @param attached the attached
     */
    public void setAttached(boolean attached) {
        Attached = attached;
    }

    /**
     * Is on fire boolean.
     *
     * @return the boolean
     */
    public boolean isOnFire() {
        return OnFire;
    }

    /**
     * Sets on fire.
     *
     * @param onFire the on fire
     */
    public void setOnFire(boolean onFire) {
        OnFire = onFire;
    }
    public boolean isAttached() {
        return Attached;
    }
}
