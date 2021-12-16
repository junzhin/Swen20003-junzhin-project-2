import bagel.Input;

/**
 * The fundamental class of all game elements in the game of shadow flap
 *
 * @author Junzhi Ning
 * @version 1.0
 */
public abstract class GameElement {
    /**
     * the abstract methods for updating of the game element behaviour
     *
     * @param frameCounter the frame counter
     * @param input        the input
     */
    public abstract void update(long frameCounter, Input input);
}
