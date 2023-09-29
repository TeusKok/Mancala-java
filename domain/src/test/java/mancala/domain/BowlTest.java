package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BowlTest {

    @Test
    public void testBowlHasOwner(){
        Bowl bowl = new Bowl();
        assertNotNull(bowl.getOwner());
    }

    @Test
    public void testBowlHasNonNegativeNumberOfStones() {
        Bowl bowl = new Bowl();
        int numberOfStone = bowl.getStones();
        assertTrue(numberOfStone>=0);
    }

    @Test
    public void testPositiveNumberOfStoneCanBeAddedToBowl(){
        Bowl bowl = new Bowl();
        bowl.addStones(3);
        assertEquals(3,bowl.getStones());
    }

    @Test
    public void AddingNegativeNumberOfStonesToBowlDoesNotChangeStateOfBowl(){
        Bowl bowl = new Bowl();
        bowl.addStones(-3);
        assertEquals(0,bowl.getStones());
    }

    @Test
    public void TestFindFirstBowlOfActivePlayer(){
        int[] board = {4,4,4,4,4,4, 0 ,5,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        bowl1.getOwner().switchActivityAndTellOpponent();
        Bowl firstBowlActivePlayer = bowl1.findFirstBowlOfActivePlayer();
        Bowl bowl8 = bowl1.getBowlFromDistance(7);

        assertEquals(bowl8,firstBowlActivePlayer);
    }

    @Test
    public void TestFindFirstBowlOfActivePlayerWhenStartedAtSecondBowlOfActivePlayer(){
        int[] board = {4,4,4,4,4,4, 0 ,5,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        Bowl bowl2 = bowl1.getNeighbour();

        Bowl firstBowlActivePlayer = bowl2.findFirstBowlOfActivePlayer();


        assertEquals(bowl1,firstBowlActivePlayer);
    }

    @Test void TestCheckYourBowlsEmptyForActivePlayerTurnsBothPlayerInactive(){
        int[] board = {0,0,0,0,0,0, 0 ,5,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        bowl1.checkActivePlayerBowlsEmpty();

        assertFalse(bowl1.getOwner().isPlayerActive());
        assertFalse(bowl1.getOwner().getOpponent().isPlayerActive());
    }

}
