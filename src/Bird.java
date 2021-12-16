import bagel.*;
import bagel.Image;
import bagel.Window;
import bagel.util.Point;
import bagel.util.Rectangle;
import java.lang.Math;

/**
 * This class is inherited from the abstract superclass of the DynamicElement.
 * It includes the implementation of the bird object in the game of shadow flap
 */
public class Bird extends DynamicElement implements Movable{
    /**
     * The images of the bird used in the game
     */
    private final Image WING_DOWN_IMAGE;
    private final Image WING_UP_IMAGE;
    /**
     * Constants supported for the functionality of the bird
     */
    private final double INITIAL_X = 200;
    private final double FLY_SIZE = 6;
    private final double FALL_SIZE = 0.4;
    private final double INITIAL_Y = 350;
    private final double Y_TERMINAL_VELOCITY = 10;
    private final double SWITCH_FRAME = 10;
    /**
     *  Spatial Variables for rendering and collision detection
     */
    private double yVelocity;
    private Rectangle boundingBox;
    /**
     * weapon attached
     */
    private Weapon weapon;
    private boolean heldweapon;

    /**
     * default constructor
     *
     * @param level : This is the parameter to record the level of the game
     */
    public Bird(int level) {

        super(level);
        super.setX(INITIAL_X);
        super.setY(INITIAL_Y);
        yVelocity = 0;
        heldweapon = false;
        weapon = null;
        // Randomly generate the position of the bird
        if (super.getLevel() == 0){
            WING_DOWN_IMAGE = new Image("res/level-0/birdWingDown.png");
            WING_UP_IMAGE = new Image("res/level-0/birdWingUp.png");
        }else{
            WING_DOWN_IMAGE = new Image("res/level-1/birdWingDown.png");
            WING_UP_IMAGE = new Image("res/level-1/birdWingUp.png");
        }
        boundingBox = WING_DOWN_IMAGE.getBoundingBoxAt(new Point(super.getX(),super.getY()));
    }
    /**
     * Update the position of the bird in the game
     * @param input: This is the parameter to pass the input of the user
     */
    public void updatePosition(Input input) {
        if (input.wasPressed(Keys.SPACE)) {
            yVelocity = -FLY_SIZE;
            WING_DOWN_IMAGE.draw(super.getX(),super.getY());
        }
        else {
            yVelocity = Math.min(yVelocity + FALL_SIZE, Y_TERMINAL_VELOCITY);
        }
        super.setY(super.getY() + yVelocity);
    }
    /**
     *  Update the behaviour of the bird
     * @param frameCount:  This is the parameter to record the frame count of the game since the start
     * @param input: This is the parameter to pass the input of the user
     */
    @Override
    public void update(long frameCount, Input input) {
        updatePosition(input);

        // Check if the weapon is shot
        System.out.println(heldweapon);
        if(heldweapon){
            weapon.setY(super.getY());
            weapon.setX(super.getX() + WING_UP_IMAGE.getWidth()/2.0);
            if(input.wasPressed(Keys.S)){
                weapon.setOnFire(true);
                weapon = null;
                heldweapon = false;
            }
        }
        // Draw the bird images of wing up and wing down
        if (frameCount % SWITCH_FRAME == 0) {
            DrawObject(WING_UP_IMAGE);
            boundingBox = WING_UP_IMAGE.getBoundingBoxAt(new Point(super.getX(),super.getY()));
        } else {
            DrawObject(WING_DOWN_IMAGE);
            boundingBox = WING_DOWN_IMAGE.getBoundingBoxAt(new Point(super.getX(),super.getY()));
        }
    }
    /**
     * Implementation of rendering the bird correctly on the game window
     * @param image: This is the parameter to refer the object of the bird class
     */
    @Override
    public void DrawObject(Image image) {image.draw(super.getX(),super.getY());}

    /**
     * Is out of bound boolean.
     *
     * @return the boolean
     */
// OutofBound detection for the bird
    public boolean isOutOfBound() {
        return (super.getY() > Window.getHeight()) || (super.getY() < 0)
                || (super.getX() > Window.getWidth()) || (super.getX() < 0);
    }

    /**
     * Reposition the bird at the initial position as at the same of the game
     *
     * @return boolean : This returns if the reposition of the bird is successful or not
     */
    public boolean Reposition() {
        super.setX(INITIAL_X);
        super.setY(INITIAL_Y);
        return true;
    }

    /**
     * detect the collision between the bird and the pipes
     *
     * @param pipeset : This is the parameter to refer the object of the pipes class
     * @return boolean : This returns the indicators if the collision happens between one pipeset and the bird
     */
    public boolean detectCollisionPipes(Pipes pipeset) {
        // Check if the pipe is steel and if there is flames shot out, then check collision with the flames as well
        if(pipeset.isSTEELPIPE() && pipeset.getFlames().isOnflame()){
            Flames flames = pipeset.getFlames();
            if((boundingBox.intersects(flames.getUpBox()))||(boundingBox.intersects(flames.getBottomBox()))){
                return true;
            }
        }
        // check for collision of the pipeset
        return boundingBox.intersects(pipeset.getTopBox()) || boundingBox.intersects(pipeset.getBottomBox());
    }

    /**
     * detect the collision between the bird and a weapon
     *
     * @param weapon : This is the parameter to refer the object of the weapon class
     * @return boolean : This returns the indicators if the collision happens between one pipeset and the bird
     */
    public boolean detectCollisionWeapon(Weapon weapon){
        return boundingBox.intersects(weapon.getBox());
    }

    /**
     * Establish the association relationship with a weapon
     *
     * @param weapon : This is the parameter to refer the object of the weapon class
     */
    public void attachedWeapon(Weapon weapon){
        this.weapon = weapon;
        this.weapon.setAttached(true);
        heldweapon = true;
    }
    /**
     * Getters and Setters
     */
    public double getY() {return super.getY();}
    public double getX() {return super.getX();}

    /**
     * Is heldweapon boolean.
     *
     * @return the boolean
     */
    public boolean isHeldweapon() {return heldweapon;}
}