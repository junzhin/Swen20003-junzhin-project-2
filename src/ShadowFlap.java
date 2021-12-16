import bagel.*;

/**
 * SWEN20003 Project 2, Semester 2, 2021
 *
 * @author Junzhi Ning This is the main class that runs the game//
 */
public class ShadowFlap extends AbstractGame {

    // Set up the objects of levels in the game
   private Level0 level0;
   private Level1 level1;
    // Indicator to signpost the level up in the game
   private boolean LevelUp;

    /**
     * Instantiates a new Shadow flap.
     */
    public ShadowFlap() {
        super(1024, 768, "ShadowFlap");
        level0 = new Level0();
        level1 = new Level1();
        LevelUp =  false;
    }

    /**
     * The entry point for the program.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        ShadowFlap game = new ShadowFlap();
        game.run();
    }

    /**
     * Performs a state update.
     * allows the game to exit when the escape key is pressed.
     */
    @Override
    public void update(Input input) {

        // Detect the close of the game window
        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        }
        // Run the level 0 of the game
        if(!LevelUp){
            LevelUp = level0.update(input);
        }
        //Run the level 1 of the game
        if(LevelUp){
            level1.update(input);
        }
    }
}


