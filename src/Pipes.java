import bagel.*;
import bagel.util.Point;
import bagel.util.Rectangle;
import java.util.Random;

/**
 * This class is inherited from the abstract superclass of the DynamicElement.
 * It includes the implementation of the pipeset object in the game of shadow flap
 *
 * @author Junzhi Ning
 * @version 1.0
 */
public class Pipes extends DynamicElement implements Movable{

    /**
     * Pipe image
     */
    private final Image PIPE_IMAGE;
    /**
     * Common Control Parameters
     */
    private final int PIPE_GAP = 168;
    private final int LEVEL0 = 0;
    private final int LEVEL1 = 1;
    /**
     * variables related to motion
     */
    static double CurrentSpeed;
    private double TOP_PIPE_Y ;
    private double BOTTOM_PIPE_Y;
    private boolean passedByBird;
    /**
     * DrawOption
     */
    private final DrawOptions ROTATOR = new DrawOptions().setRotation(Math.PI);
    /**
     * Random Position utility
     */
    private Random rand;
    private final int UPPERBOUNDFORPOSITIONLEVEL0 = 3;
    private final int UPPERBOUNDFORPOSITIONLEVEL1 = 400;
    private final int UPPERBOUNDFORPIPETYPES = 2;
    private final int ADJUSTINGFACTORFORLEVEL1TOPPIPE = 100;
    /**
     * Flames Feature and Steel pipes
     */
    private Flames flames;
    private final boolean STEELPIPE;

    /**
     * The Constructor for the class
     *
     * @param level : This is the parameter to indicate which level the game belongs to
     */
    public Pipes(int level) {

        super(level);
        super.setX(Window.getWidth());
        rand = new Random();
        RandomlyGenerateYValues();
        passedByBird = false;

        // Random Uniformly Generator of the object
        if(level == LEVEL0 || rand.nextInt(UPPERBOUNDFORPIPETYPES) == UPPERBOUNDFORPIPETYPES - 1){
            //Add the random generator for pipe and types
            PIPE_IMAGE = new Image("res/level/plasticPipe.png");
            STEELPIPE = false;
        }else{
            //Add the random generator for pipe positions and types
            PIPE_IMAGE = new Image("res/level-1/steelPipe.png");
            STEELPIPE = true;
        }

        // Generate the flames object for the steel pipe
        flames = null;
        if(STEELPIPE){
            // give the upper pipe position to flames
            flames = new Flames(level ,super.getX(),TOP_PIPE_Y+PIPE_IMAGE.getHeight()/2.0, PIPE_GAP);
        }
    }

    /**
     * Generate the Y-values for PipeSet randomly depending on the level of the game
     */
    private void RandomlyGenerateYValues(){

        int int_random = 0;
        double CurrentTopPipePosition = 0;
        // generate the position of top pipe from 3 sets at Level0
        if(super.getLevel() == LEVEL0) {
            int_random = rand.nextInt(UPPERBOUNDFORPOSITIONLEVEL0) + 1;
            // Conversion of 1,2,3 to 100, 300, 500
            CurrentTopPipePosition = ((double)int_random*2-1)*100;
        // generate the position of top pipe between y values of 100 and 500 at Level1 from the interval of [0,399]
        } else if(super.getLevel() == LEVEL1){
            CurrentTopPipePosition  = rand.nextInt(UPPERBOUNDFORPOSITIONLEVEL1) + ADJUSTINGFACTORFORLEVEL1TOPPIPE;
        }
        // Update the position of the upper and lower pipers accordingly
        TOP_PIPE_Y = CurrentTopPipePosition - Window.getHeight()/2.0;
        BOTTOM_PIPE_Y = PIPE_GAP + Window.getHeight()/2.0 + CurrentTopPipePosition ;
    }

    /**
     * Updating the behaviour of the pipeset
     * @param frameCounter: This is the parameter to record the frame count of the game since the start
     * @param input: This is the parameter to pass the input of the user
     */
    @Override
    public void update(long frameCounter, Input input) {
        // when off-screen completely, the pipe disappears with optional flames
        if(super.getX() < -PIPE_IMAGE.getWidth()){
            super.setObjectExistence(false);
            if(STEELPIPE){
                flames.setObjectExistence(false);
            }
        }
        // Make sure the pipeset is existed. Otherwise, no update behaviour
        if(!super.isObjectExistence()){
            return;
        }
        // Updating the position of the game
        updatePosition(input);
        DrawObject(PIPE_IMAGE);
        // Update the position of flames and render on the screen
        if(STEELPIPE){
            flames.setX(super.getX());
            flames.update(frameCounter,input);
        }
    }

    /**
     * Implementation of rendering the pipes correctly on the game window
     * @param object: This is the parameter to refer to an object of a class
     */
    @Override
    public void DrawObject(Image object) {
        PIPE_IMAGE.draw(super.getX(), TOP_PIPE_Y);
        PIPE_IMAGE.draw(super.getX(), BOTTOM_PIPE_Y, ROTATOR);
    }

    /**
     * Update the position of the pipe set
     * @param input: This is the parameter to pass the input of the user
     */
    @Override
    public void updatePosition(Input input) {
        super.setX(super.getX() - CurrentSpeed);
    }

    /**
     * Getters and Setters
     *
     * @return the top box
     */
    public Rectangle getTopBox() {
        return PIPE_IMAGE.getBoundingBoxAt(new Point(super.getX(), TOP_PIPE_Y));
    }

    /**
     * Gets bottom box.
     *
     * @return the bottom box
     */
    public Rectangle getBottomBox() {
        return PIPE_IMAGE.getBoundingBoxAt(new Point(super.getX(), BOTTOM_PIPE_Y));
    }

    /**
     * Is steelpipe boolean.
     *
     * @return the boolean
     */
    public boolean isSTEELPIPE() {
        return STEELPIPE;
    }

    /**
     * Gets flames.
     *
     * @return the flames
     */
    public Flames getFlames() {
        return flames;
    }

    /**
     * Is passed by bird boolean.
     *
     * @return the boolean
     */
    public boolean isPassedByBird() {
        return passedByBird;
    }

    /**
     * Sets passed by bird.
     *
     * @param passedByBird the passed by bird
     */
    public void setPassedByBird(boolean passedByBird) {
        this.passedByBird = passedByBird;
    }

    /**
     * Sets current speed.
     *
     * @param speed the speed
     */
    public static void setCurrentSpeed(double speed) {
        CurrentSpeed =  speed;
    }

}
