import bagel.Input;
import bagel.Keys;
import java.util.ArrayList;

/**
 * This class is the superclass of all levels of the game,
 * including all implementations  expected to be added on the subclasses
 *
 * @author Junzhi Ning
 * @version 2.0
 */
public abstract class Levelprototype {
    /**
     *  Game Elements for all game levels
     */
    private Bird bird;
    private ArrayList<Pipes> Pipesets;
    private LifeBar lifebar;
    private Message message;
    private GameScore gameScore;
    private BackGround backGround;
    /**
     * Indicators and Counters
     */
    private boolean Lost;
    private boolean gameOn;
    private boolean Win;
    private long frameCount;
    /**
     * Count the number of pipes generated
     */
    private long pipesCount;
    private final int LEVEL;
    private int CounterForWin;
    /**
     * Constant and Parameters for all game levels, including the
     * motion of the pipes and weapons and frequency of popping up
     * Note that: parameters are changed here to make the game more playable
     */
    private final int OCCURENCEPERIOD = 200;
    private final int BASICSPEED = 3;
    private final int OCCURENCEDISTANCE = OCCURENCEPERIOD * BASICSPEED;
    private final int LOADINGTIMEFORELEVELUP = 20;
    /**
     * Time Control Parameters
     */
    int MAXTIME = 5;
    /**
     * The Mintime.
     */
    int MINTIME = 1;
    /**
     * TimeController statistic variables for the class
     */
    private double ScalingFactor;
    private int TimeControlLevel;
    /**
     * To compute the distance travelled since the start of the game and avoid the problem of
     * the inconsistency of pipes spawning when adjusting the speed
     */
    private double currentDistanceTravelled;

    /**
     * This is the constructor of game level
     *
     * @param level :  This is the parameter to indicator which level the game belongs to
     */
    public Levelprototype(int level) {

        // Initialisation of variables
        LEVEL = level;
        Lost = false;
        Win = false;
        gameOn = false;

        pipesCount = 0;
        frameCount = 0;
        currentDistanceTravelled = 0;

        ScalingFactor = 1;
        TimeControlLevel = 1;
        CounterForWin = 0;

        bird = new Bird(level);
        Pipesets = new ArrayList<Pipes>();
        lifebar = new LifeBar(level);
        message = new Message();
        gameScore = new GameScore(level);
        backGround = new BackGround(level);
    }

    /**
     * Main update section of the game
     *
     * @param input : This is the parameter to pass the input of the user
     * @return boolean : This returns the indicator if the game has levelled up
     */
//
    public boolean update(Input input){
        backGround.update(frameCount, input);

        // Rendering the instruction before the start of the game
        if(!gameOn && !Win && !Lost){
            if(LEVEL == 0){
                message.renderInstructionScreenLevel0();
            }else{
                message.renderInstructionScreenLevel1();
            }
            // Start of the game
        if(input.wasPressed(Keys.SPACE)){
                gameOn = true;
            }
        }
        // Checking the wining condition of the game and rendering the relevant MSG, including Leveling up
        if (gameScore.isWinIndicator()) {

            gameOn = false;
            Win = true;
            // loading time before entering the level 1 if the game is currently level0
            if (LEVEL == 0) {
                message.renderLevelUpScreen();
                CounterForWin++;
                if (CounterForWin > LOADINGTIMEFORELEVELUP) {
                    return true;
                }
            }else {
                message.renderWinScreen(gameScore.getCurrentScore());
            }
        }

        // Terminate the game if it is already lost
        if (Lost) {
            message.renderGameOverScreen(gameScore.getCurrentScore());
            gameOn = false;;
        }

        return false;
    }

    /**
     * Update section of the game when it is active for all levels
     *
     * @param input : This is the parameter to pass the input of the user
     */
//
    public void updateForGameOn(Input input){

        // Check if the bird is out of bound
        if(bird.isOutOfBound()){
            Lost = lifebar.decreaseLife();
            bird.Reposition();
        }

        // Adjust the speed of the game
        adjustTimescale(input);

        // Generate the pipe set
        generatePipeset();

        // Update the currentSpeed of the entire class of pipes by timescale
        UpdateSpeedOfPipesAndWeapons();

        // check pipesets collision and update of scores
        PipeCollisionAndScoring(input);

        // Update the position of the gameElements and render them out on the screen
        bird.update(frameCount, input);
        gameScore.update(frameCount, input);
        message.renderScore(gameScore.getCurrentScore());
        lifebar.update(frameCount, input);

        frameCount++;
    }
    /**
     * Time Control of difficulty of Speed
     */
    private void increaseSpeed() {
        if(TimeControlLevel < MAXTIME){
            ScalingFactor = ScalingFactor * 1.5;
            TimeControlLevel++;
        }
    }

    /**
     * Increase the level of Time Controller
     */
    private void decreaseSpeed() {
        if(TimeControlLevel > MINTIME){
            ScalingFactor = ScalingFactor / 1.5;
            TimeControlLevel--;
        }
    }

    /**
     *Decrease the level of Time Controller
     */
    private void adjustTimescale(Input input){
        // Adjusting the difficulty of the game
        if(input.wasPressed(Keys.L)){
            increaseSpeed();
        }
        if(input.wasPressed(Keys.K)){
            decreaseSpeed();
        }
    }
    /**
     * detection of pipes with birds and update of game score if the bird passes the pipes
     */
    private void PipeCollisionAndScoring(Input input){
        for(Pipes pipeset:Pipesets){
            pipeset.update(frameCount, input);

            // Detection of the pipe
            if(bird.detectCollisionPipes(pipeset) && pipeset.isObjectExistence()){
                Lost = lifebar.decreaseLife();
                pipeset.setObjectExistence(false);
                pipeset.setPassedByBird(true);
            }

            // Detection of increasing the score and passed by the bird
            if (!pipeset.isPassedByBird() && bird.getX() > pipeset.getTopBox().right()){
                if(pipeset.isObjectExistence()){
                    gameScore.increaseScore();
                }
                pipeset.setPassedByBird(true);
            }
        }
    }
    /**
     * Generate single pipeset to the arraylist
     */
    private void generatePipeset(){
        // Generate the pipes at a fixed interval depending on current time controller level
        if(currentDistanceTravelled / OCCURENCEDISTANCE > pipesCount){
            Pipesets.add(new Pipes(LEVEL));
            pipesCount++;
        }
    }
    /**
     * This is used to update the speed of all pipes and weapons usually after adjusting time control
     */
    private void UpdateSpeedOfPipesAndWeapons(){
        Pipes.setCurrentSpeed(BASICSPEED * ScalingFactor);
        if(LEVEL == 1){
            Weapon.setSpeed(BASICSPEED * ScalingFactor);
        }
        // Update the distance travelled to ensure the pipes and weapon are rendered at the correct frequency.
        currentDistanceTravelled += ScalingFactor * BASICSPEED;
    }

    /**
     * Getters and Setters
     *
     * @return instance variables
     */
    public Bird getBird() {
        return bird;
    }

    /**
     * Gets pipesets.
     *
     * @return the pipesets
     */
    public ArrayList<Pipes> getPipesets() {
        return Pipesets;
    }

    /**
     * Gets game score.
     *
     * @return the game score
     */
    public GameScore getGameScore() {
        return gameScore;
    }

    /**
     * Gets frame count.
     *
     * @return the frame count
     */
    public long getFrameCount() {
        return frameCount;
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public int getLEVEL() {
        return LEVEL;
    }

    /**
     * Gets occurencedistance.
     *
     * @return the occurencedistance
     */
    public int getOCCURENCEDISTANCE() {
        return OCCURENCEDISTANCE;
    }

    /**
     * Gets current distance travelled.
     *
     * @return the current distance travelled
     */
    public double getCurrentDistanceTravelled() {
        return currentDistanceTravelled;
    }

    /**
     * Is game on boolean.
     *
     * @return the boolean
     */
    public boolean isGameOn() {
        return gameOn;
    }
}
