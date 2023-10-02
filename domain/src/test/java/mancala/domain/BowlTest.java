package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BowlTest {

    @Test
    public void testPositiveNumberOfStoneCanBeAddedToBowl(){
        PlayableBowl bowl1 = new PlayableBowl();
        bowl1.addStones(3);
        assertEquals(7,bowl1.getStones());
    }

    @Test
    public void AddingNegativeNumberOfStonesToBowlDoesNotChangeStateOfBowl(){
        PlayableBowl bowl1 = new PlayableBowl();
        bowl1.addStones(-3);
        assertEquals(4,bowl1.getStones());
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
        bowl1.doGameOverIfActivePlayerSideEmpty();

        assertFalse(bowl1.getOwner().isPlayerActive());
        assertFalse(bowl1.getOwner().getOpponent().isPlayerActive());
    }
}
