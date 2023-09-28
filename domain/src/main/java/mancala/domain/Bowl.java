package mancala.domain;

public class Bowl {
    private Player owner;
    private int stones;
    private Bowl neighbour;
    private String tag;

    public Bowl(){
        this.owner = new Player();


    }

    public Bowl(Player owner){
        this.owner = owner;
    }

   void setTag(String tag){
        this.tag = tag;
    }

    String getTag(){
        return this.tag;
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


    int getStones(){
        return stones;

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
        if(this.getTag().equals("Kalaha")&&!this.owner.isPlayerActive()){
            return this.getNeighbour();
        }
        else return this.getNeighbour().findFirstBowlOfActivePlayer();
    }

    public void checkActivePlayerBowlsEmpty() {
        Bowl firstPlayableBowlOfActivePlayer = this.findFirstBowlOfActivePlayer();
        int counter = 0;
        if(firstPlayableBowlOfActivePlayer.checkYourBowlsEmpty(counter)){
            firstPlayableBowlOfActivePlayer.getOwner().doGameOver();
        }

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
