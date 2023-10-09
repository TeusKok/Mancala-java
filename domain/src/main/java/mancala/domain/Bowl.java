package mancala.domain;

public abstract class Bowl {
    private final Player owner;
    private int stones;
    private Bowl neighbour;

    public enum gameResult{
        WINNER, LOSER, TIED, GAMENOTOVER
    }

    public Bowl(){
        this.owner = new Player();
    }

    public Bowl(Player owner){
        this.owner = owner;
    }

    Player getOwner() {
        return this.owner;
    }

    Bowl getNeighbour(){
        return this.neighbour;
    }

    void setNeighbour(Bowl bowl){
        this.neighbour = bowl;
    }

    public Bowl getBowlFromDistance(int steps){
        Bowl tempBowl = this;

        if(steps<0){
            steps = 14+steps;
        }
        steps = steps%14;

        for(int i = 0; i<steps;i++){
            tempBowl = tempBowl.getNeighbour();
        }
        return tempBowl;
    }

    public int getStones(){
        return stones;
    }

    public int getKalahaDistance() {
        int counter =0;
        return this.getClosestKalahaDistance(counter);
    }

    int getClosestKalahaDistance(int counter) {
        return (this.getNeighbour()).getClosestKalahaDistance(counter+1);
    }
    abstract Kalaha getClosestKalaha();

    abstract Bowl getOpposingBowl();

    private void setStones(int newAmount){
        stones=newAmount;
    }

    void emptyBowl(){
        this.setStones(0);
    }

    void addStones(int amount) {
        if (amount > 0) {
            setStones(this.stones + amount);
        }
    }

    abstract void takeOnePassRemainderAndOrSwitchActiveAndOrSteal(int numberOfStones) ;

    public Bowl findFirstBowlOfActivePlayer() {
        return this.getNeighbour().findFirstBowlOfActivePlayer();
    }

    public void doGameOverIfActivePlayerSideEmpty() {
        Bowl firstPlayableBowlOfActivePlayer = this.findFirstBowlOfActivePlayer();
        int counter = 0;
        if(firstPlayableBowlOfActivePlayer.checkYourBowlsEmpty(counter)){
            Bowl opponentFirstBowl = firstPlayableBowlOfActivePlayer.getBowlFromDistance(7);
            opponentFirstBowl.takeAllStonesFromTheFirstBowlAndItsNeighboursToItsKalaha(0);
            firstPlayableBowlOfActivePlayer.getOwner().doGameOver();
        }
    }

    public gameResult GetOwnersGameResult() {
        if(IsGameOver()) return this.getNeighbour().GetOwnersGameResult();
        else return gameResult.GAMENOTOVER;
    }

    private boolean IsGameOver(){
        return !this.getOwner().isPlayerActive()&&!this.getOwner().getOpponent().isPlayerActive();

    }

    private boolean checkYourBowlsEmpty(int counter){
        if(this.getStones()>0){
            return false;
        }
        else{
            return CheckNextBowlIfBowlsLeftOnThisSide(counter);
        }
    }

    private boolean CheckNextBowlIfBowlsLeftOnThisSide(int counter) {
        if(counter <5){
            return this.getNeighbour().checkYourBowlsEmpty(counter + 1);
        }
        else{
            return true;
        }
    }

    //needs to be called on first bowl of player
    private void takeAllStonesFromTheFirstBowlAndItsNeighboursToItsKalaha(int counter) {
        this.getNeighbour().addStones(this.getStones());
        this.emptyBowl();
        if(counter<5){
            this.getNeighbour().takeAllStonesFromTheFirstBowlAndItsNeighboursToItsKalaha(counter+1);
        }
    }
}
