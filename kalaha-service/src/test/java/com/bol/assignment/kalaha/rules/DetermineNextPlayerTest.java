package com.bol.assignment.kalaha.rules;

import com.bol.assignment.kalaha.domain.Game;
import com.bol.assignment.kalaha.rules.impl.DetermineNextPlayer;
import com.bol.assignment.kalaha.util.GameTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.bol.assignment.kalaha.util.GameTestUtil.PLAYER_A_BIG_PIT_INDEX;
import static com.bol.assignment.kalaha.util.GameTestUtil.PLAYER_B_BIG_PIT_INDEX;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * The DetermineNextPlayerTest class tests the behavior of the DetermineNextPlayer class in a game scenario.
 */
@ExtendWith(MockitoExtension.class)
class DetermineNextPlayerTest {

    DetermineNextPlayer determineNextPlayer =  new DetermineNextPlayer();

    Game game;

    /**
     * The setUp() function is used to initialize the game object before each test case.
     */
    @BeforeEach
    void setUp(){
        game = GameTestUtil.getValidGameInput();
    }

    /**
     * The function "lastSwonInOwnBigPitIndex_TakesAnotherTurn" tests if the player who last sowed seeds in their own big
     * pit gets another turn.
     */
    @Test
    void lastSwonInOwnBigPitIndex_TakesAnotherTurn(){
        game.setLastIndex(PLAYER_A_BIG_PIT_INDEX);
        determineNextPlayer.execute(game);
        assertEquals(game.getPlayerList().get(0), game.getCurrentPlayer());
    }

    /**
     * The function tests whether the current player changes after the last stone is placed in a non-own big pit.
     */
    @Test
    void lastSwonInNonOwnBigPitIndex_TakesAnotherTurn(){
        game.setLastIndex(PLAYER_B_BIG_PIT_INDEX);
        determineNextPlayer.execute(game);
        assertNotEquals(game.getPlayerList().get(0), game.getCurrentPlayer());
    }
}
