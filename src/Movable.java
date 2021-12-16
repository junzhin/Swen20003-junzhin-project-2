import bagel.Input;

/**
 * This interface includes the methods in which a class required upon
 * the property of "movable".
 *
 * @author Junzhi Ning
 * @version 1.0
 */
public interface Movable {
    /**
     * The method update the position when receiving the keyboard input
     *
     * @param input : This is the parameter to pass the input of the user
     */
    public abstract void updatePosition(Input input);
}
