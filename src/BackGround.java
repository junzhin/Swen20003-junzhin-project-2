import bagel.Image;
import bagel.Input;
import bagel.Window;

/**
 * This class is inherited from the abstract superclass of the StationaryObeject.
 * It includes the implementation of the background  in the game of shadow flap
 *
 * @author Junzhi Ning
 * @version 1.0
 */
public class BackGround extends StationaryElement {

    /**
     *  Images for the background
     */
    private final Image BACKGROUNDLEVEL0_IMAGE = new Image("res/level-0/background.png");
    private final Image BACKGROUNDLEVEL1_IMAGE = new Image("res/level-1/background.png");
    /**
     * Other Controlling Level
     */
    private final int LEVEL;

    /**
     * Constructor
     *
     * @param level :  This is the parameter to record the level of the game
     */
    public BackGround(int level){
        LEVEL = level;
        super.setX(Window.getWidth()/2.0);
        super.setY(Window.getHeight()/2.0);
    }

    /**
     * Update the behaviour of the background during the execution of the game program
     * @param frameCounter:  This is the parameter to record the frame count of the game since the start
     * @param input: This is the parameter to pass the input of the
     */
    @Override
    public void update(long frameCounter, Input input) {
        DrawObject();
    }

    /**
     *  Implementation of rendering the wallpaper correctly on the game window
     */
    @Override
    public void DrawObject() {
        if(LEVEL == 0){
            BACKGROUNDLEVEL0_IMAGE.draw(super.getX(),super.getY());
        }else{
            BACKGROUNDLEVEL1_IMAGE.draw(super.getX(), super.getY());
        }
    }
}
