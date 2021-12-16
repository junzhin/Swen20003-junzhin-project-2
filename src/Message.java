import bagel.Font;
import bagel.Window;
import bagel.util.Point;

/**
 * This class is individual class specifically designed for outputting the messages in the game
 *
 * @author Junzhi Ning
 * @version 1.0
 */
public class Message{
    /**
     * Messages types
     */
    private final String INSTRUCTION_MSG = "PRESS SPACE TO START";
    private final String GAME_OVER_MSG = "GAME OVER!";
    private final String CONGRATS_MSG = "CONGRATULATIONS!";
    private final String SCORE_MSG = "SCORE: ";
    private final String FINAL_SCORE_MSG = "FINAL SCORE: ";
    private final String LEVEL_UP_MSG = "LEVEL-UP!";
    private final String LEVEL1_SHOOTING_MSG = "PRESS 'S' TO SHOOT";
    /**
     *  Set up the font
     */
    private final int FONT_SIZE = 48;
    private final Font FONT = new Font("res/font/slkscr.ttf", FONT_SIZE);
    /**
     * Positions Constants
     */
    private final int SCORE_MSG_OFFSET = 75;
    private final int SHOOTING_MSG_OFFSET = 68;
    private final double  ORIGINALXSCORE = 100;
    private final double ORIGINALYSCORE = 100;
    private final Point SCORE_POSITION = new Point(ORIGINALXSCORE,ORIGINALYSCORE);

    /**
     * Rendering the instruction message before the start of game at level0
     */
    public void renderInstructionScreenLevel0() {
        FONT.drawString(INSTRUCTION_MSG, (Window.getWidth()/2.0-(FONT.getWidth(INSTRUCTION_MSG)/2.0)),
                (Window.getHeight()/2.0-(FONT_SIZE/2.0)));
    }

    /**
     * Rendering the instruction message before the start of game at level1
     */
    public void renderInstructionScreenLevel1() {
        FONT.drawString(INSTRUCTION_MSG, (Window.getWidth()/2.0-(FONT.getWidth(INSTRUCTION_MSG)/2.0)),
                (Window.getHeight()/2.0-(FONT_SIZE/2.0)));
        FONT.drawString(LEVEL1_SHOOTING_MSG, (Window.getWidth()/2.0-(FONT.getWidth(LEVEL1_SHOOTING_MSG)/2.0)),
                (Window.getHeight()/2.0-(FONT_SIZE/2.0))+SHOOTING_MSG_OFFSET);
    }

    /**
     * Rendering the message if the game is lost
     *
     * @param score : This is the parameter to record the score of the game
     */
    public void renderGameOverScreen(int score) {
        FONT.drawString(GAME_OVER_MSG, (Window.getWidth()/2.0-(FONT.getWidth(GAME_OVER_MSG)/2.0)), (
                Window.getHeight()/2.0-(FONT_SIZE/2.0)));
        String finalScoreMsg = FINAL_SCORE_MSG + score;
        FONT.drawString(finalScoreMsg, (Window.getWidth()/2.0-(FONT.getWidth(finalScoreMsg)/2.0)),
                (Window.getHeight()/2.0-(FONT_SIZE/2.0))+SCORE_MSG_OFFSET);
    }

    /**
     * Rendering the message if the game is won
     *
     * @param score : This is the parameter to record the score of the game
     */
    public void renderWinScreen(int score) {
        FONT.drawString(CONGRATS_MSG, (Window.getWidth()/2.0-(FONT.getWidth(CONGRATS_MSG)/2.0)),
                (Window.getHeight()/2.0-(FONT_SIZE/2.0)));
        String finalScoreMsg = FINAL_SCORE_MSG + score;
        FONT.drawString(finalScoreMsg, (Window.getWidth()/2.0-(FONT.getWidth(finalScoreMsg)/2.0)),
                (Window.getHeight()/2.0-(FONT_SIZE/2.0))+SCORE_MSG_OFFSET);
    }

    /**
     * Rendering the message if the game is leveled up from level0 to level1
     */
    public void renderLevelUpScreen() {
        FONT.drawString(LEVEL_UP_MSG, (Window.getWidth()/2.0-(FONT.getWidth(LEVEL_UP_MSG)/2.0)),
                (Window.getHeight()/2.0-(FONT_SIZE/2.0)));
    }

    /**
     * Rendering the score on runtime of the game
     *
     * @param score : This is the parameter to record the score of the game
     */
    public void renderScore(int score) {
        String scoreMsg = SCORE_MSG + score;
        FONT.drawString(scoreMsg, SCORE_POSITION.x,SCORE_POSITION.y);
    }

}
