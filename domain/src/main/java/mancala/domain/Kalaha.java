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
}
