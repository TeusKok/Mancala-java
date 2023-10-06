package mancala.domain;

public class PlayableBowl extends Bowl{

    public PlayableBowl(){
        this(new int[] {4,4,4,4,4,4, 0 ,4,4,4,4,4,4, 0});
    }

    public PlayableBowl(int[] board){
        super();

        int counter = 1;
        this.addStones(board[0]);
        this.setNeighbour(new PlayableBowl(this,this.getOwner(),counter,board));
    }

    PlayableBowl(PlayableBowl origin,Player owner, int counter,int[] board){
        super(owner);

        this.addStones(board[counter]);
        if(counter==5||counter==12) {
            this.setNeighbour(new Kalaha(origin,owner,counter+1,board));
        }
        else {
            this.setNeighbour(new PlayableBowl(origin,owner, counter+1,board));
        }
    }

    public void doMove()  {
        CheckValidMove();
        int NumberOfStonesToPass = this.getStones();
        this.emptyBowl();
        this.getNeighbour().takeOnePassRemainderAndOrSwitchActiveAndOrSteal(NumberOfStonesToPass);
        this.doGameOverIfActivePlayerSideEmpty();
    }


    public void CheckValidMove() throws DoingMoveFromEmptyBowlException, DoingMoveFromOpponentsBowl {
        if(this.ownerIsInactive()) throw new DoingMoveFromOpponentsBowl();
        if(this.isEmpty()) throw new DoingMoveFromEmptyBowlException();

    }

    private boolean ownerIsInactive() {
        return !this.getOwner().isPlayerActive();
    }

    private boolean isEmpty() {
        return this.getStones() == 0;
    }

    public void takeOnePassRemainderAndOrSwitchActiveAndOrSteal(int numberOfStones) {
        this.addStones(1);
        if(numberOfStones>1){
            this.getNeighbour().takeOnePassRemainderAndOrSwitchActiveAndOrSteal(numberOfStones-1);
        }
        if(this.GotLastStone(numberOfStones)){
            if(this.WasEmptyAndOwnerIsActive()){
                this.doSteal();
            }
            this.getOwner().switchActivityAndTellOpponent();
        }
    }

    private boolean GotLastStone(int numberOfStones) {
        return numberOfStones == 1;
    }

    private boolean WasEmptyAndOwnerIsActive() {
        return this.getStones()-1 == 0 && this.getOwner().isPlayerActive();
    }

    private void doSteal() {
        int kalahaDistance = this.getKalahaDistance();
        Bowl kalaha = this.getBowlFromDistance(kalahaDistance);
        kalaha.addStones(this.getStones());
        this.emptyBowl();
        Bowl stealableBowl = this.getBowlFromDistance(2*kalahaDistance);
        int stealableStones = stealableBowl.getStones();
        stealableBowl.emptyBowl();
        kalaha.addStones(stealableStones);
    }
}
