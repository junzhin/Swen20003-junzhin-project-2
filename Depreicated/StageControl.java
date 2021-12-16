import bagel.Input;

public class StageControl extends GameElement{
    private int CurrentStage;
    private boolean Win;
    private boolean Lost;
    private boolean StartofGame;


    public boolean isWinorNot(int gameScore){
        return true;
    }

    public boolean isLostOrNot(int gameScore){
        return true;
    }

    public int messageOutputTypes(){
        return 1;
    }

    public void update(long frameCount, Input input) {

    }
}
