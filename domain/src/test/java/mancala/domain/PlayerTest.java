package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    @Test
    public void testPlayerHasOpponent(){
        Player player1 = new Player();
        assertNotNull(player1.getOpponent());

    }

    @Test
    public void testPlayersOpponentsOpponentIsPlayer(){
        Player player1 = new Player();
        Player opponent = player1.getOpponent();
        assertEquals(player1,opponent.getOpponent());
    }

    @Test
    public void testPlayerIsNotItsOwnOpponent(){
        Player player1 = new Player();
        Player opponent = player1.getOpponent();
        assertNotEquals(player1,opponent);
    }

}
