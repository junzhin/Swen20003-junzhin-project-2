import bagel.Input;

/**
 * This class is inherited from the abstract superclass Levelprototype
 * and implements the details of the level0 in the shadow flap game
 *
 * @author Junzhi Ning
 * @version 2.0
 */
public class Level0 extends Levelprototype{

    /**
     * Indicators and Counters of various types
     */
    private int CounterForWin;
    private boolean levelUp;

    /**
     * Constructor
     */
    public Level0(){
        super(0);
        CounterForWin = 0;
        levelUp = false;
    }
    /**
     * Implement every frame of the level 0 for the shadow flap game
     * @param input: This is the parameter to pass the input of the user
     * @return boolean: This returns the indicator if the game of this level has ended
     */
    @Override
    public boolean update(Input input){
        // update of the background and the start-up screen of the game for any levels
        levelUp = super.update(input);

        // the game is active
        if (super.isGameOn() && !super.getGameScore().isWinIndicator()) {
            // main update for all levels when the game is active
            updateForGameOn(input);
        }

        return levelUp;
    }

}
