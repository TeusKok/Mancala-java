package mancala.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayableBowlTest {
    @Test
    public void PlayableBowlHasOwner(){
        int[] board = {4,4,4,4,4,4, 0 ,4,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        assertNotNull(bowl1.getOwner());
    }

    @Test
    public void TestTake2StepsIsNeighbourOfNeighbour(){
        int[] board = {4,4,4,4,4,4, 0 ,4,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        Bowl bowl3 = bowl1.getBowlFromDistance(2);
        assertEquals(bowl3,bowl1.getNeighbour().getNeighbour());

    }
    @Test
    public void CreatingPlayableBowlCreates5NeighbouringBowls(){
        int[] board = {4,4,4,4,4,4, 0 ,4,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        Bowl bowl6 =bowl1.getBowlFromDistance(5);
        assertNotNull(bowl6);
        assertEquals(bowl6.getType(),"Playable Bowl");
    }

    @Test
    public void CreatingPlayableBowlCreates5NeighbouringBowlsAndThenAKalaha(){
        int[] board = {4,4,4,4,4,4, 0 ,4,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        Bowl bowl6 =bowl1.getBowlFromDistance(5);
        Bowl kalaha = bowl6.getNeighbour();

        assertEquals(kalaha.getType(),"Kalaha");
    }

    @Test
    public void CreatingPlayableBowlCreatesBoardWhere8thBowlHasDifferentOwnerThanBowl1(){
        int[] board = {4,4,4,4,4,4, 0 ,4,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        Bowl bowl8 =bowl1.getBowlFromDistance(7);
        assertNotEquals(bowl8.getOwner(),bowl1.getOwner());
    }

    @Test
    public void CreatingPlayableBowlCreatesBoardWhere2ndBowlHasSameOwnerASBowl1(){
        int[] board = {4,4,4,4,4,4, 0 ,4,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        Bowl bowl2 =bowl1.getNeighbour();
        assertEquals(bowl2.getOwner(),bowl1.getOwner());
    }


    @Test
    public void testBowl14stepsFromFirstBowlIsFirstBowl(){
        int[] board = {4,4,4,4,4,4, 0 ,4,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        Bowl alsoBowl1 =bowl1.getBowlFromDistance(14);
        assertEquals(bowl1,alsoBowl1);
    }

    @Test
    public void CreatedPlayableBowlStartsWith4Stones(){
        int[] board = {4,4,4,4,4,4, 0 ,4,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        assertEquals(4,bowl1.getStones());
    }

    @Test
    public void CreatedPlayableBowlsNeighbourStartsWith4Stones(){
        int[] board = {4,4,4,4,4,4, 0 ,4,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        Bowl bowl2 = bowl1.getNeighbour();
        assertEquals(4,bowl2.getStones());
    }

    @Test
    public void AfterAllBowlsAreCreatedBothKalahasHave0Stones(){
        int[] board = {4,4,4,4,4,4, 0 ,4,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        Bowl kalaha = bowl1.getBowlFromDistance(6);
        Bowl kalaha2 = bowl1.getBowlFromDistance(13);

        assertEquals(0,kalaha.getStones());
        assertEquals(0,kalaha2.getStones());
    }

    @Test
    public void TestDoMoveEmptiesBowl(){
        int[] board = {4,4,4,4,4,4, 0 ,4,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        bowl1.doMove();
        assertEquals(0,bowl1.getStones());
    }

    @Test
    public void TestDoMoveAddsOneStoneToNeighbourBowl(){
        int[] board = {4,4,4,4,4,4, 0 ,4,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        Bowl neighbour = bowl1.getNeighbour();
        bowl1.doMove();

        assertEquals(5,neighbour.getStones());
    }

    @Test
    public void TestDoMoveOnBowl1With4StonesAddsOneStoneToNeighbourBowlsUpToNeighbour5(){
        int[] board = {4,4,4,4,4,4, 0 ,4,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        Bowl bowl5 = bowl1.getBowlFromDistance(4);
        bowl1.doMove();

        assertEquals(5,bowl5.getStones());
    }

    @Test
    public void TestDoMoveOnBowl1With4StonesAddsNoStoneToBowl6(){
        int[] board = {4,4,4,4,4,4, 0 ,4,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        Bowl bowl6 = bowl1.getBowlFromDistance(5);
        bowl1.doMove();

        assertEquals(4,bowl6.getStones());
    }

    @Test
    public void TestDoMoveAddsNoStoneToOpponentKalaha(){
        int[] board = {4,4,4,4,4,4, 0 ,4,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        PlayableBowl bowl6 = (PlayableBowl) bowl1.getBowlFromDistance(5);
        Kalaha opponentKalaha = (Kalaha)bowl1.getBowlFromDistance(13);
        bowl6.addStones(4);
        bowl6.doMove();

        assertEquals(0,opponentKalaha.getStones());
    }

    @Test
    public void TestDoMoveAddsNoStoneToOpponentKalahaButToItsNeighbourInstead(){
        int[] board = {4,4,4,4,4,4, 0 ,4,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        PlayableBowl bowl6 = (PlayableBowl) bowl1.getBowlFromDistance(5);
        Kalaha opponentKalaha = (Kalaha)bowl1.getBowlFromDistance(13);
        bowl6.addStones(4);
        bowl6.doMove();

        assertEquals(5,opponentKalaha.getNeighbour().getStones());
    }

    @Test
    public void TestGenerateBoardAccordingToSetupArray(){
        int[] board = {5,3,4,4,4,2, 2 ,4,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        Bowl kalaha = bowl1.getBowlFromDistance(6);

        assertEquals(5,bowl1.getStones());
        assertEquals(3,bowl1.getNeighbour().getStones());
        assertEquals(2,kalaha.getStones());
    }

    @Test
    public void TestWhenLastStoneOfMoveEndsInNonEmptyPlayableBowlOnActivePlayersSidePlayersSwitchTurn(){
        int[] board = {4,4,4,4,4,4, 0 ,4,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        Player player1 = bowl1.getOwner();
        bowl1.doMove();

        assertFalse(player1.isPlayerActive());
    }
    @Test
    public void TestWhenLastStoneOfMoveEndsInNonEmptyPlayableBowlOnNonActivePlayersSidePlayersSwitchTurn(){
        int[] board = {4,4,4,4,4,4, 0 ,4,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        PlayableBowl bowl6 = (PlayableBowl)bowl1.getBowlFromDistance(5);
        Player player1 = bowl1.getOwner();
        bowl6.doMove();

        assertFalse(player1.isPlayerActive());
    }

    @Test
    public void TestWhenLastStoneOfMoveEndsInEmptyPlayableBowlOnNonActivePlayersSidePlayersSwitchTurn(){
        int[] board = {4,4,4,4,4,4, 0 ,4,4,0,4,4,4, 4};
        PlayableBowl bowl1 = new PlayableBowl(board);
        PlayableBowl bowl6 = (PlayableBowl)bowl1.getBowlFromDistance(5);
        Player player1 = bowl1.getOwner();
        bowl6.doMove();

        assertFalse(player1.isPlayerActive());
    }

    @Test
    public void TestWhenLastStoneOfMoveEndsInKalahaPlayersDontSwitchTurn(){
        int[] board = {4,4,4,4,4,4, 0 ,4,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        PlayableBowl bowl3 = (PlayableBowl)bowl1.getBowlFromDistance(2);
        Player player1 = bowl1.getOwner();
        bowl3.doMove();

        assertTrue(player1.isPlayerActive());
    }

    @Test
    public void TestDoingMoveWhereGameEndsAfterMoveBothPlayersTurnInactive(){
        int[] board = {4,4,4,4,4,4, 0 ,0,0,0,0,0,0, 24};
        PlayableBowl bowl1 = new PlayableBowl(board);
        Player player1 = bowl1.getOwner();
        bowl1.doMove();

        assertFalse(player1.isPlayerActive());
        assertEquals(player1.isPlayerActive(),player1.getOpponent().isPlayerActive());
    }

    @Test
    public void TestDoingMoveWhereGameEndsAfterMoveIfLastStoneInKalahaBothPlayersTurnInactive(){
        int[] board = {0,0,0,0,0,1, 0 ,0,1,0,0,0,0, 24};
        PlayableBowl bowl1 = new PlayableBowl(board);
        PlayableBowl bowl6 = (PlayableBowl) bowl1.getBowlFromDistance(5);
        Player player1 = bowl1.getOwner();
        bowl6.doMove();

        assertFalse(player1.isPlayerActive());
        assertEquals(player1.isPlayerActive(),player1.getOpponent().isPlayerActive());
    }

    @Test
    public void TestDoingMoveWhereLastStoneEndInEmptyBowlOnActivePlayersSideThatBowlGetsEmptied(){
        int[] board = {1,0,0,1,1,0, 0 ,4,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        bowl1.doMove();

        assertEquals(0,bowl1.getNeighbour().getStones());
    }

    @Test
    public void TestDoingMoveWhereLastStoneEndInEmptyBowlOnNonActivePlayersSideThatBowlDoesNotGetEmptied(){
        int[] board = {7,0,0,1,1,0, 0 ,0,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        PlayableBowl bowl8 = (PlayableBowl) bowl1.getBowlFromDistance(7);
        bowl1.doMove();

        assertEquals(1,bowl8.getStones());
    }

    @Test
    public void TestFindKalahaDistanceGetsDistanceFromPlayableBowlToKalahaOfSamePlayer(){
        int[] board = {1,0,0,1,1,0, 0 ,4,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        int distance = bowl1.getKalahaDistance();
        assertEquals(6,distance);
    }

    @Test
    public void TestWhenMoveEndsInEmptyBowlOnActivePlayerSideOppositeOpponentBowlIsEmptied(){
        int[] board = {1,0,0,1,1,0, 0 ,4,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        Bowl bowl12 = bowl1.getBowlFromDistance(11);
        bowl1.doMove();

        assertEquals(0,bowl12.getStones());
    }

    @Test
    public void TestWhenMoveEndsInEmptyBowlOnActivePlayerSideOppositeOpponentBowlsContentsPlus1AreAddedToActiveKalaha(){
        int[] board = {1,0,0,1,1,0, 0 ,4,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        Bowl kalaha = bowl1.getBowlFromDistance(6);
        bowl1.doMove();

        assertEquals(5,kalaha.getStones());
    }

    @Test
    public void TestDoMoveOnEmptyBowlOfActivePlayerThrowsAppropriateException(){
        int[] board = {1,0,0,1,1,0, 0 ,4,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        PlayableBowl bowl2 = (PlayableBowl) bowl1.getNeighbour();
        assertThrows(DoingMoveFromEmptyBowlException.class, bowl2::doMove);
    }

    @Test
    public void TestDoMoveOnBowlOfNonActivePlayerThrowsAppropriateException(){
        int[] board = {1,0,0,1,1,0, 0 ,4,4,4,4,4,4, 0};
        PlayableBowl bowl1 = new PlayableBowl(board);
        PlayableBowl bowl8 = (PlayableBowl) bowl1.getBowlFromDistance(7);
        assertThrows(DoingMoveFromOpponentsBowl.class, bowl8::doMove);
    }

    @Test
    public void TestGetWinnerReturnsWinnerWhenGameIsOverAndBowlOwnerHasWon(){
        int[] board = {0,0,0,0,0,1, 4 ,4,4,4,4,4,4, 2};
        PlayableBowl bowl1 = new PlayableBowl(board);
        PlayableBowl bowl6 = (PlayableBowl) bowl1.getBowlFromDistance(5);
        bowl6.doMove();
        Bowl kalaha1 = bowl1.getBowlFromDistance(6);

        assertEquals(kalaha1.GetOwnersGameResult(), Bowl.gameResult.WINNER);

    }

    @Test
    public void TestGetWinnerReturnsGameNotOverWhenGameIsNotOver(){
        int[] board = {0,1,0,0,0,1, 4 ,4,4,4,4,4,4, 2};
        PlayableBowl bowl1 = new PlayableBowl(board);
        PlayableBowl bowl2 = (PlayableBowl) bowl1.getNeighbour();
        bowl2.doMove();

        assertEquals(bowl1.GetOwnersGameResult(), Bowl.gameResult.GAMENOTOVER);

    }

    @Test
    public void TestGetWinnerReturnsTiedWhenGameIsATie(){
        int[] board = {0,0,0,0,0,1, 1 ,4,4,4,4,4,4, 2};
        PlayableBowl bowl1 = new PlayableBowl(board);
        PlayableBowl bowl6 = (PlayableBowl) bowl1.getBowlFromDistance(5);
        bowl6.doMove();

        assertEquals(bowl1.GetOwnersGameResult(), Bowl.gameResult.TIED);

    }

    @Test
    public void TestGetWinnerReturnsLoserWhenGameIsOverAndBowlOwnerHasLost(){
        int[] board = {0,0,0,0,0,1, 4 ,0,0,2,0,0,0, 6};
        PlayableBowl bowl1 = new PlayableBowl(board);
        PlayableBowl bowl6 = (PlayableBowl) bowl1.getBowlFromDistance(5);
        bowl6.doMove();

        assertEquals(bowl1.GetOwnersGameResult(), Bowl.gameResult.LOSER);

    }









}
