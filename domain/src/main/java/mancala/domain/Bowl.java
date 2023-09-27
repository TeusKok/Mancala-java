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

    public void setTag(String tag){
        this.tag = tag;
    }

    public String getTag(){
        return this.tag;
    }

    public void setOwner(Player Owner){}

    public Player getOwner() {
        return this.owner;
    }

    public Bowl getNeighbour(){
        return this.neighbour;
    }

    public void setNeighbour(Bowl bowl){
        neighbour = bowl;
    }

    public Bowl getBowlFromDistance(int steps){
        Bowl tempBowl = this;
        for(int i = 0; i<steps;i++){
            tempBowl = tempBowl.getNeighbour();
        }
        return tempBowl;
    }


    public int getStones(){
        return stones;

    }

    private void setStones(int newAmount){
        stones=newAmount;
    }

    public void addStones(int amount) {
        if (amount > 0) {
            setStones(this.stones + amount);
        }
    }
}
