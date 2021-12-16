import bagel.Image;

/**
 * This class is the abstract class inherited from
 * GameElement class, it provides more specified version of
 * the class for the elements in the game that are dynamic
 * through the running of the game
 */
public abstract class DynamicElement extends GameElement{

    /**
     * Indicator to see the object if it is existent.
     */
    private boolean ObjectExistence;
    /**
     *  Position of the object
     */
    private double x;
    private double y;
    /**
     * Level in which the object is in
     */
    private int level;

    /**
     * Constructor
     *
     * @param Level :  This is the parameter that record the leve of the game
     */
    public DynamicElement(int Level){
        ObjectExistence = true;
        x = 0;
        y = 0;
        level = Level;
    }

    /**
     * Prototype for the drawing method
     *
     * @param image :  This is the parameter to refer the image of the object
     */
    public abstract void DrawObject(Image image);

    /**
     * Getters and Setters
     *
     * @param objectExistence the object existence
     */
    public void setObjectExistence(boolean objectExistence) {ObjectExistence = objectExistence;}

    /**
     * Is object existence boolean.
     *
     * @return the boolean
     */
    public boolean isObjectExistence() {return ObjectExistence;}

    /**
     * Gets x.
     *
     * @return the x
     */
    public double getX() {return x;}

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(double x) {
        this.x = x;
    }

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

    /**
     * Gets level.
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets level.
     *
     * @param level the level
     */
    public void setLevel(int level) {
        this.level = level;
    }
}
