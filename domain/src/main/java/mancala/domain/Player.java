package mancala.domain;

public class Player {
    private String tag;
    private boolean isActivePlayer;
    final private Player opponent;

    public Player(){
        this.opponent = new Player(this);
        this.isActivePlayer = true;
    }

    public Player(Player opponent){
        this.opponent = opponent;
    }

    public Player getOpponent() {
        return this.opponent;
    }

    public boolean isPlayerActive(){
        return isActivePlayer;
    }

    public void switchActivityAndTellOpponent() {
        this.isActivePlayer = !this.isActivePlayer;
        this.opponent.switchActivity();
    }

    private void switchActivity(){
        this.isActivePlayer = !this.isActivePlayer;
    }

    public void doGameOver() {
        this.isActivePlayer=false;
        this.getOpponent().isActivePlayer=false;
    }
}
