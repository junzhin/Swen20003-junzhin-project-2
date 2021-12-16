import bagel.Image;
import bagel.Input;

/**
 * This class is inherited from the StationaryElement to implement the functionality of LifeBar in the game
 *
 * @author Junzhi Ning
 * @version 1.0
 */
public class LifeBar extends StationaryElement {

    /**
     *Images and GapParameters between life images
     */
    private final Image FULLLIFEIMAGE = new Image("res/level/fullLife.png");
    private final Image NOTLIFEIMAGE = new Image("res/level/noLife.png");
    private final double GAPFORLIFEIMAGE = 50.0;

    /**
     * Parameters for losing the game and others
     */
    private final int CURRENTMAXLIFE;
    private final int LEVEL1MAX = 6;
    private final int LEVEL0MAX = 3;
    private int currentLife;
    private final double INITIALPOSITIONX = 100;
    private final double INITIALPOSITIONY = 15;

    /**
     * Parameters for losing the game and others
     *
     * @param Level : This is the parameter to pass the input of the user
     */
    public LifeBar(int Level){
        super.setX(INITIALPOSITIONX);
        super.setY(INITIALPOSITIONY);

        if(Level == 1){
            CURRENTMAXLIFE = LEVEL1MAX;
        }else{
            CURRENTMAXLIFE = LEVEL0MAX;
        }
        currentLife = CURRENTMAXLIFE;
    }

    /**
     * Update the behaviour of the lifebar in the game
     * @param frameCounter : This is the parameter to record the framecount of the game since the start of game
     * @param input:  This is the parameter to pass the input of the user
     */
    @Override
    public void update(long frameCounter, Input input) {
        DrawObject();
    }

    /**
     * Implementation of rendering the lifebar correctly on the game window
     */
    @Override
    public void DrawObject() {

        for(int i = 0; i < CURRENTMAXLIFE; i++){
            if(i < currentLife){
                FULLLIFEIMAGE.drawFromTopLeft(super.getX() + i * GAPFORLIFEIMAGE,super.getY());
            }else {
                NOTLIFEIMAGE.drawFromTopLeft(super.getX() + i * GAPFORLIFEIMAGE,super.getY());
            }
        }
    }

    /**
     * Decrease the life when called and return true if all lives are used
     *
     * @return boolean : this returns the indicator of the lives are used up
     */
    public boolean decreaseLife(){
        if(currentLife - 1 <= 0){
            currentLife--;
            return true;
        }
        currentLife--;
        return false;
    }
}
