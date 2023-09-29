package mancala.domain;

public class PlayableBowl extends Bowl{

    public PlayableBowl(){
        int counter = 1;
        this.setStartingStones(4);

        this.setNeighbour(new PlayableBowl(this,this.getOwner(),counter));

    }
    public PlayableBowl(PlayableBowl origin,Player owner, int counter){
        super(owner);

        this.setStartingStones(4);
        if(counter==5||counter==12) {
            this.setNeighbour(new Kalaha(origin,owner,counter+1));
        }
        else {
            this.setNeighbour(new PlayableBowl(origin,owner, counter+1));
        }
    }

    public PlayableBowl(int[] board){
        super();
        int counter = 1;

        this.setStartingStones(board[0]);
        this.setNeighbour(new PlayableBowl(this,this.getOwner(),counter,board));

    }

    public PlayableBowl(PlayableBowl origin,Player owner, int counter,int[] board){
        super(owner);

        this.setStartingStones(board[counter]);
        if(counter==5||counter==12) {
            this.setNeighbour(new Kalaha(origin,owner,counter+1,board));
        }
        else {
            this.setNeighbour(new PlayableBowl(origin,owner, counter+1,board));
        }
    }

    public void doMove()  {

            CheckValidMove();
            int NumberOfStones = this.emptyBowl();
            this.getNeighbour().takeOnePassRemainder(NumberOfStones);
            this.checkActivePlayerBowlsEmpty();

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
        kalaha.addStones(this.emptyBowl());
        int stealableStones = this.getBowlFromDistance(2*kalahaDistance).emptyBowl();
        kalaha.addStones(stealableStones);
    }


    public int getKalahaDistance() {
        int counter =0;
        return this.getKalahaDistance(counter);
    }

    public int getKalahaDistance(int counter) {
        return (this.getNeighbour()).getKalahaDistance(counter+1);
    }

    public String getType(){
        return "Playable Bowl";
    }


}
