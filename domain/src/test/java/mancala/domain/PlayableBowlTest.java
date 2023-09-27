package mancala.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayableBowlTest {
    @Test
    public void PlayableBowlHasOwner(){
        PlayableBowl bowl1 = new PlayableBowl();
        assertNotNull(bowl1.getOwner());
    }

    @Test
    public void testTake2StepsIsNeighbourOfNeighbour(){
        PlayableBowl bowl1 = new PlayableBowl();
        Bowl bowl3 = bowl1.getBowlFromDistance(2);
        assertEquals(bowl3,bowl1.getNeighbour().getNeighbour());

    }
    @Test
    public void CreatingPlayableBowlCreates5NeighbouringBowls(){
        PlayableBowl bowl1 = new PlayableBowl();
        Bowl bowl6 =bowl1.getBowlFromDistance(5);
        assertNotNull(bowl6);
        assertEquals(bowl6.getTag(),"Playable Bowl");
    }

    @Test
    public void CreatingPlayableBowlCreates5NeighbouringBowlsAndThenAKalaha(){
        PlayableBowl bowl1 = new PlayableBowl();
        Bowl bowl6 =bowl1.getBowlFromDistance(5);
        Bowl kalaha = bowl6.getNeighbour();
        assertNotNull(kalaha);
        assertEquals(kalaha.getTag(),"Kalaha");

    }

    @Test
    public void CreatingPlayableBowlCreatesBoardWhere7thBowlHasDifferentOwnerThanBowl1(){
        PlayableBowl bowl1 = new PlayableBowl();
        Bowl bowl8 =bowl1.getBowlFromDistance(7);
        assertNotEquals(bowl8.getOwner(),bowl1.getOwner());
    }



}
