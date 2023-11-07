package mancala.domain;

public class Kalaha extends Bowl {

    Kalaha(PlayableBowl origin,Player owner, int counter,int[] board){
        super(owner);

        this.addStones(board[counter]);
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

    public void takeOnePassRemainderAndOrSwitchActiveAndOrSteal(int numberOfStones) {
        if(this.getOwner().isPlayerActive()) {
            this.addStones(1);
            if (numberOfStones > 1) {
                this.getNeighbour().takeOnePassRemainderAndOrSwitchActiveAndOrSteal(numberOfStones - 1);
            }
            else{
                //Active Player goes again
            }
        }
        else{
            this.getNeighbour().takeOnePassRemainderAndOrSwitchActiveAndOrSteal(numberOfStones);
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
    int getClosestKalahaDistance(int counter) {
            return counter;
    }
}
