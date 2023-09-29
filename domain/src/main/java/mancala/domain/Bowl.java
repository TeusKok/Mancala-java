package mancala.domain;

public class Bowl {
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



    public void setStartingStones(int startingAmount){
        this.stones = startingAmount;
    }

    Player getOwner() {
        return this.owner;
    }

    Bowl getNeighbour(){
        return this.neighbour;
    }

    void setNeighbour(Bowl bowl){
        neighbour = bowl;
    }

    public Bowl getBowlFromDistance(int steps){
        Bowl tempBowl = this;
        for(int i = 0; i<steps;i++){
            tempBowl = tempBowl.getNeighbour();
        }
        return tempBowl;
    }

    String getType(){
        return "Bowl";
    }

    int getStones(){
        return stones;

    }

    int getKalahaDistance(int counter) {
        return 1;
    }

    private void setStones(int newAmount){
        stones=newAmount;
    }

    int emptyBowl(){
        int numberOfStones = this.getStones();
        this.setStones(0);
        return numberOfStones;
    }

    void addStones(int amount) {
        if (amount > 0) {
            setStones(this.stones + amount);
        }
    }

    void takeOnePassRemainder(int numberOfStones) {



    }

    public Bowl findFirstBowlOfActivePlayer() {
        return this.getNeighbour().findFirstBowlOfActivePlayer();
    }

    public void checkActivePlayerBowlsEmpty() {
        Bowl firstPlayableBowlOfActivePlayer = this.findFirstBowlOfActivePlayer();
        int counter = 0;
        if(firstPlayableBowlOfActivePlayer.checkYourBowlsEmpty(counter)){
            firstPlayableBowlOfActivePlayer.getOwner().doGameOver();
        }

    }

    public gameResult GetOwnersGameResult() {
        if(IsGameOver()) return this.getNeighbour().GetOwnersGameResult();
        else return gameResult.GAMENOTOVER;
    }


    public boolean IsGameOver(){
        return !this.getOwner().isPlayerActive()&&!this.getOwner().getOpponent().isPlayerActive();

    }



    private boolean checkYourBowlsEmpty(int counter){
        if(this.getStones()>0){
            return false;
        }
        else{
            if(counter<5){
                return this.getNeighbour().checkYourBowlsEmpty(counter+1);
            }
            else{
                return true;
            }
        }
    }
}
