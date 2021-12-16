import bagel.Input;
import java.util.ArrayList;

/**
 * This class is inherited from the abstract superclass Levelprototype
 * and implements the details of the level1 in the shadow flap game
 *
 * @author Junzhi Ning
 * @version 2.0
 */
public class Level1 extends Levelprototype{
    /**
     * Additional  Game Elements for level1
     */
    private ArrayList<Weapon> weapons;
    private long weaponsCount;

    /**
     * Constructor
     */
    public Level1() {
        super(1);
        // Game elements for level 1 only
        weapons = new ArrayList<>();
        weaponsCount = 0;
    }

    /**
     * Implement every frame of the level 1 for the shadow flap game
     * @param input: This is the parameter to pass the input of the user
     * @return boolean:  This returns the indicator of the game of this has ended
     */
    @Override
    public boolean update(Input input) {
        // update of the background and the start-up screen of the game for any levels
        super.update(input);

        // the game is active
        if (super.isGameOn() && !super.getGameScore().isWinIndicator()) {
            // main update for all levels when the game is active
            updateForGameOn(input);

            // Extra implementations for level 1
            // Randomly generate the new weapon at the fixed interval
            generateWeapon();

            // Checking for picking up a weapon
            WeaponAttaching();

            // Checking the collision between weapons and any pipeset if the weapon is shot out by the bird
            weaponCollision(input);
            }

        return false;
    }

    /**
     * Generate single weapon to the arraylist
     */
    private void generateWeapon(){
        if((super.getCurrentDistanceTravelled() - super.getOCCURENCEDISTANCE()/2) /
                super.getOCCURENCEDISTANCE() > weaponsCount) {
            weapons.add(new Weapon(super.getLEVEL()));
            weaponsCount++;
        }
    }

    /**
     * Checking for any weapon can be attached to the bird
     */
    private void WeaponAttaching(){
        if(!super.getBird().isHeldweapon()){
            for(Weapon weapon: weapons){
                if(!weapon.isAttached() && !weapon.isOnFire() && super.getBird().detectCollisionWeapon(weapon)){
                    super.getBird().attachedWeapon(weapon);
                }
            }
        }

    }
    /**
     * Detection of the collision between weapons and pipesets
     * @param input: This is the parameter to pass the input of the users
     */
    private void weaponCollision(Input input){
        for(Weapon weapon: weapons){
            // update the weapon condition
            weapon.update(super.getFrameCount(),input);
            // make sure the weapon is active and shot by the bird
            if(weapon.isOnFire() && weapon.isObjectExistence()){
                for(Pipes pipeset:super.getPipesets()) {
                    // make sure the pipeset exists and detect for any collision with the weapon
                    if(!pipeset.isPassedByBird() && weapon.detectCollisionPipes(pipeset)){
                        // Weapon is used up after shooting once the collision is detected
                        weapon.setObjectExistence(false);
                        weapon.setOnFire(false);
                        // make sure the weapon destroys the correct type of pipeset, then increase the game score
                        if((pipeset.isSTEELPIPE() && weapon.isALLOWEDTOATTACKSTEEL()) || !pipeset.isSTEELPIPE()){
                            pipeset.setObjectExistence(false);
                            pipeset.setPassedByBird(true);
                            super.getGameScore().increaseScore();
                        }
                    }
                }
            }
        }
    }
}
