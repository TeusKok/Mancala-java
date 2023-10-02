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

    public PlayableBowl(PlayableBowl origin,Player owner, int counter,int[] board){
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
        int NumberOfStones = this.takeAllStonesFromBowl();
        this.getNeighbour().takeOnePassRemainder(NumberOfStones);
        this.doGameOverIfActivePlayerSideEmpty();
    }

    public void CheckValidMove() throws DoingMoveFromEmptyBowlException, DoingMoveFromOpponentsBowl {
        if(!this.getOwner().isPlayerActive()) throw new DoingMoveFromOpponentsBowl();
        if(this.getStones()==0) throw new DoingMoveFromEmptyBowlException();

    }

    public void takeOnePassRemainder(int numberOfStones) {
        this.addStones(1);
        if(numberOfStones>1){
            this.getNeighbour().takeOnePassRemainder(numberOfStones-1);
        }
        else{
            if(this.getStones()==1&&this.getOwner().isPlayerActive()){
                this.doSteal();
            }
            this.getOwner().switchActivityAndTellOpponent();
        }
    }

    private void doSteal() {
        int kalahaDistance = this.getKalahaDistance();
        Bowl kalaha = this.getBowlFromDistance(kalahaDistance);
        kalaha.addStones(this.takeAllStonesFromBowl());
        int stealableStones = this.getBowlFromDistance(2*kalahaDistance).takeAllStonesFromBowl();
        kalaha.addStones(stealableStones);
    }
}
