package com.bol.assignment.kalaha.rules;

import com.bol.assignment.kalaha.domain.Game;
import com.bol.assignment.kalaha.rules.impl.DeclareWinner;
import com.bol.assignment.kalaha.util.GameTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The class `DeclareWinnerTest` is a JUnit test class that tests the functionality of the `DeclareWinner` class.
 */
@ExtendWith(MockitoExtension.class)
public class DeclareWinnerTest {

    public static final String GAME_RESULT_TIED = "Game Tied";
    public static final int PLAYER_BIG_PIT_COUNT_EQUAL = 36;
    public static final int PLAYER_BIG_PIT_COUNT_NOT_EQUAL = 30;
    public static final int INCREMENT_TWO = 2;
    DeclareWinner declareWinner = new DeclareWinner();

    Game game;

    /**
     * The setUp() function is used to initialize the game object before each test case.
     */
    @BeforeEach
    void setUp() {
        game = GameTestUtil.getValidGameInput();
    }

    /**
     * The function tests if the game ends in a tie by setting all pits before the player's big pit to 0 and setting the
     * player's big pit count to a specific value, then checks if the game result is tied and if the game is completed.
     */
    @Test
    void declareResult_Tie(){
        int[] pits = game.getBoard().getPits();

        int playerABigPitIndex = game.getPlayerList().get(1).getBigPitIndex();

        for (int i = 0; i < playerABigPitIndex; i++) {
            pits[i] = 0;
        }
        pits[playerABigPitIndex] = PLAYER_BIG_PIT_COUNT_EQUAL;

        declareWinner.execute(game);

        assertEquals(GAME_RESULT_TIED, game.getWinner());
        assertEquals(Boolean.TRUE, game.isCompleted());
    }

    /**
     * The function tests the declareWinner.execute() method by setting the pits array to specific values and asserts that
     * the winner is player 1 and the game is completed.
     */
    @Test
    void declareResultPlayerAHasEmptyPits_Winner(){
        int[] pits = game.getBoard().getPits();

        int playerABigPitIndex = game.getPlayerList().get(0).getBigPitIndex();
        int playerBBigPitIndex = game.getPlayerList().get(1).getBigPitIndex();

        for (int i = 7; i < playerABigPitIndex; i++) {
            pits[i] = 0;
        }
        pits[playerABigPitIndex] = PLAYER_BIG_PIT_COUNT_NOT_EQUAL;
        pits[playerBBigPitIndex] = PLAYER_BIG_PIT_COUNT_NOT_EQUAL+ INCREMENT_TWO;

        declareWinner.execute(game);

        assertEquals(game.getPlayerList().get(1).getName(), game.getWinner());
        assertEquals(Boolean.TRUE, game.isCompleted());
    }


    @Test
    void declareResultPlayerBHasEmptyPits_Winner(){
        int[] pits = game.getBoard().getPits();

        int playerABigPitIndex = game.getPlayerList().get(0).getBigPitIndex();
        int playerBBigPitIndex = game.getPlayerList().get(1).getBigPitIndex();

        for (int i = 0; i < playerBBigPitIndex; i++) {
            pits[i] = 0;
        }
        pits[playerABigPitIndex] = PLAYER_BIG_PIT_COUNT_NOT_EQUAL+ INCREMENT_TWO;
        pits[playerBBigPitIndex] = PLAYER_BIG_PIT_COUNT_NOT_EQUAL;

        declareWinner.execute(game);

        assertEquals(game.getPlayerList().get(0).getName(), game.getWinner());
        assertEquals(Boolean.TRUE, game.isCompleted());
    }

}
