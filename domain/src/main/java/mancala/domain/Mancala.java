package mancala.domain;



public class Mancala implements IMancala {
    private String nameOfPlayerOne;
    private String nameOfPlayerTwo;
    final private PlayableBowl firstBowl;

    public Mancala(String nameOfPlayerOne, String nameOfPlayerTwo){
        this.nameOfPlayerOne = nameOfPlayerOne;
        this.nameOfPlayerTwo = nameOfPlayerTwo;
        this.firstBowl = new PlayableBowl();

    }


    @Override
    public String getNameOfPlayerOne() {
        return this.nameOfPlayerOne;
    }

    @Override
    public String getNameOfPlayerTwo() {
        return this.nameOfPlayerTwo;
    }

    @Override
    public boolean isPlayersTurn(String name) {
        Player playerOne = this.firstBowl.getOwner();
        return ((this.nameOfPlayerOne.equals(name) && playerOne.isPlayerActive()) ||
                    (this.nameOfPlayerTwo.equals(name) && playerOne.getOpponent().isPlayerActive()));
    }

    @Override
    public void playPit(int index) {
        PlayableBowl bowlToPlay = (PlayableBowl)this.firstBowl.getBowlFromDistance(index);
        bowlToPlay.doMove();
        
    }

    @Override
    public int getStonesForPit(int index) {
        Bowl selectedBowl = this.firstBowl.getBowlFromDistance(index);
        return selectedBowl.getStones();
    }

    @Override
    public boolean isEndOfGame() {
        Player playerOne = this.firstBowl.getOwner();
        return !playerOne.isPlayerActive() && !playerOne.getOpponent().isPlayerActive();
        
    }

    @Override
    public Winner getWinner() {
        Bowl.gameResult result = this.firstBowl.GetOwnersGameResult();
        switch (result) {
            case GAMENOTOVER:
                return Winner.NO_ONE;
            
            case WINNER:
                return Winner.PLAYER_1;
                
            case LOSER:
                return Winner.PLAYER_2;
                
            default:
                return Winner.DRAW;
                
        }
    }
    
}
