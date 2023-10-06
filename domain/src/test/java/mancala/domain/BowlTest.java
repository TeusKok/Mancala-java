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
    public void TestGetBowlFromDistance2ReturnsRightBowl(){
        PlayableBowl bowl1 = new PlayableBowl();
        Bowl bowl3 = bowl1.getBowlFromDistance(2);

        assertEquals(bowl1.getNeighbour().getNeighbour(),bowl3);
    }

    @Test
    public void TestGetBowlFromDistance3ReturnsRightBowl(){
        PlayableBowl bowl1 = new PlayableBowl();
        Bowl bowl4 = bowl1.getBowlFromDistance(3);

        assertEquals(bowl1.getNeighbour().getNeighbour().getNeighbour(),bowl4);
    }

    @Test
    public void TestGetBowlFromDistance0ReturnsItself(){
        PlayableBowl bowl1 = new PlayableBowl();

        assertEquals(bowl1,bowl1.getBowlFromDistance(0));
    }

    @Test
    public void TestGetBowlFromNegativeDistanceReturnBowlFromDistanceToTheLeft(){
        PlayableBowl bowl1 = new PlayableBowl();
        Bowl bowl2 = bowl1.getNeighbour();
        assertEquals(bowl1,bowl2.getBowlFromDistance(-1));
    }
    @Test
    public void TestGetBowlFromNegative7EqualsGetBowlFrom7(){
        PlayableBowl bowl1 = new PlayableBowl();
        assertEquals(bowl1.getBowlFromDistance(7),bowl1.getBowlFromDistance(-7));
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
