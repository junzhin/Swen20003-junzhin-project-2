/**
 * This class is the abstract class inherited from
 * GameElement class, it provides more specified version of
 * the class for the elements in the game that are stationary
 * through the running of the game
 *
 * @author Junzhi Ning
 * @version 1.0
 */
public abstract  class StationaryElement extends GameElement{
    /**
     * Positions of the objects
     */
    private double x;
    private double y;
    /**
     * Prototype for the method of drawing the object
     */
    public abstract void DrawObject();
    /**
     * Getters and Setters
     *
     * @return the x
     */
    public double getX() { return x; }
    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(double x) { this.x = x; }
    /**
     * Gets y.
     *
     * @return the y
     */
    public double getY() {
        return y;
    }
    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(double y) {
        this.y = y;
    }
}
