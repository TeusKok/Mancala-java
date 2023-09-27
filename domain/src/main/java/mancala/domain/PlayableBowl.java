package mancala.domain;

public class PlayableBowl extends Bowl{

    public PlayableBowl(){
        int counter = 1;
        this.setTag("Playable Bowl");
        this.setNeighbour(new PlayableBowl(this,this.getOwner(),counter));

    }
    public PlayableBowl(PlayableBowl origin,Player owner, int counter){
        super(owner);
        this.setTag("Playable Bowl");
        if(counter==5||counter==12) {
            this.setNeighbour(new Kalaha(origin,owner,counter+1));
        }
        else {
            this.setNeighbour(new PlayableBowl(origin,owner, counter+1));
        }
    }



}
