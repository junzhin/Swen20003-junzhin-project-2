import bagel.Input;

/**
 * This class is inherited from the GameElement to implement the functionality of the game score
 *
 * @author Junzhi Ning
 * @version 1.0
 */
public class GameScore extends GameElement{
    /**
     * parameters for the winning conditions
     */
    private final int LEVEL1SCORING = 30;
    private final int LEVEL0SCORING = 10;
    private final int LEVEL;
    private int CurrentScore;
    private boolean WinIndicator;

    /**
     * Constructor
     *
     * @param level :  This is the parameter to record the level of the game
     */
    public GameScore(int level){
        CurrentScore = 0;
        WinIndicator = false;
        LEVEL = level;
    }
    /**
     * Update the behaviour of the score in the game
     * @param frameCounter: This is the parameter to record the frameCounter of the game since the start
     * @param input: This is the parameter to pass the input of the user
     */
    @Override
    public void update(long frameCounter, Input input){
        if(LEVEL == 0 && CurrentScore >= LEVEL0SCORING){
            WinIndicator = true;
        }else if(LEVEL == 1 && CurrentScore >= LEVEL1SCORING){
            WinIndicator = true;
        }
    }

    /**
     * the method allows outside class to add the score by 1
     */
    public void increaseScore(){
        CurrentScore++;
    }

    /**
     * Getters and Setters
     *
     * @return the current score
     */
    public int getCurrentScore() {
        return CurrentScore;
    }

    /**
     * Is win indicator boolean.
     *
     * @return the boolean
     */
    public boolean isWinIndicator() {
        return WinIndicator;
    }


}