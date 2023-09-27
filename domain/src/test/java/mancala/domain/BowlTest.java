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
}
