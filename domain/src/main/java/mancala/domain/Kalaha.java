package mancala.domain;

public class Kalaha extends Bowl {
    public Kalaha(PlayableBowl origin,Player owner, int counter){
        super(owner);
        this.setTag("Kalaha");
        if(counter ==6) {
            this.setNeighbour(new PlayableBowl(origin,owner.getOpponent(), counter+1));

        }
        else this.setNeighbour(origin);
    }

    public Kalaha(PlayableBowl origin,Player owner, int counter,int[] board){
        super(owner);
        this.setTag("Kalaha");
        this.setStartingStones(board[counter]);
        if(counter==6) {
            this.setNeighbour(new PlayableBowl(origin,owner.getOpponent(), counter+1,board));
        }
        else this.setNeighbour(origin);
    }


    public void takeOnePassRemainder(int numberOfStones) {
        if(this.getOwner().isPlayerActive()) {
            this.addStones(1);
            if (numberOfStones > 1) {
                this.getNeighbour().takeOnePassRemainder(numberOfStones - 1);
            }
        }
        else{
            this.getNeighbour().takeOnePassRemainder(numberOfStones);
        }

    }
}
