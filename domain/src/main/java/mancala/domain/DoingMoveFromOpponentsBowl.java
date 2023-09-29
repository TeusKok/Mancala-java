package mancala.domain;

public class DoingMoveFromOpponentsBowl extends RuntimeException{
    public DoingMoveFromOpponentsBowl() {
        super("You can not do a move from an opponents Bowl");
    }
}
