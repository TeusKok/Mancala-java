package mancala.domain;

public class Kalaha extends Bowl {
    public Kalaha(PlayableBowl origin,Player owner, int counter){
        super(owner);

        if(counter ==6) {
            this.setNeighbour(new PlayableBowl(origin,owner.getOpponent(), counter+1));

        }
        else this.setNeighbour(origin);
    }

    public Kalaha(PlayableBowl origin,Player owner, int counter,int[] board){
        super(owner);

        this.setStartingStones(board[counter]);
        if(counter==6) {
            this.setNeighbour(new PlayableBowl(origin,owner.getOpponent(), counter+1,board));
        }
        else this.setNeighbour(origin);
    }

    public Bowl findFirstBowlOfActivePlayer() {
        if(!this.getOwner().isPlayerActive()) {
            return this.getNeighbour();
        }
        else return this.getNeighbour().findFirstBowlOfActivePlayer();
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
    @Override
    public gameResult GetOwnersGameResult(){
        Bowl kalaha2 = this.getBowlFromDistance(7);
        if(kalaha2.getStones()>this.getStones()){
            return gameResult.LOSER;
        }else {
            if (kalaha2.getStones() == this.getStones()) {
                return gameResult.TIED;
            } else {
                return gameResult.WINNER;
            }
        }
    }



    @Override
    public int getKalahaDistance(int counter) {
            return counter;
    }

    public String getType(){
        return "Kalaha";
    }
}
