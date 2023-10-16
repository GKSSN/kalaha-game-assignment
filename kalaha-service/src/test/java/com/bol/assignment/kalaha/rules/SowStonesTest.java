package com.bol.assignment.kalaha.rules;

import com.bol.assignment.kalaha.domain.Game;
import com.bol.assignment.kalaha.exception.impl.InvalidPitIndexException;
import com.bol.assignment.kalaha.rules.impl.SowStones;
import com.bol.assignment.kalaha.util.GameTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static com.bol.assignment.kalaha.utils.Constants.INVALID_PIT_INDEX;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The class SowStonesTest contains unit tests for the SowStones class in a Java program.
 */
@ExtendWith(MockitoExtension.class)
public class SowStonesTest {

    public static final int PLAYER_A_INVALID_PIT_INDEX = 4;
    public static final int OPPONENT_BIG_PIT_INDEX = 6;
    public static final int ACTIVE_PLAYER_BIG_PIT_INDEX = 13;

    Game game;

    int[] prevPitsValue;

    SowStones sowStones = new SowStones();

    /**
     * The setup method initializes the game object and stores a copy of the current pit values.
     */
    @BeforeEach
    void setup(){
        game = GameTestUtil.getValidGameInput();
        prevPitsValue = Arrays.copyOf(game.getBoard().getPits(), game.getBoard().getPits().length);
    }


    /**
     * The function tests whether the values of the pits are modified after sowing stones.
     */
    @Test
    void sowStonesFromValidPit_PitsValuesModified(){

        sowStones.execute(game);

        assertNotEquals(prevPitsValue, game.getBoard().getPits());

    }

    /**
     * The function tests that when sowing stones from a valid pit, the opponent's big pit remains unchanged.
     */
    @Test
    void sowStonesFromValidPit_OpponentBigPitUnChanged(){

        game.getBoard().getPits()[12] = 10;
        game.setCurrentIndex(12);

        int opponentBigPitIndexPreviousValue = game.getBoard().getPits()[OPPONENT_BIG_PIT_INDEX];

        sowStones.execute(game);

        assertEquals(opponentBigPitIndexPreviousValue, game.getBoard().getPits()[OPPONENT_BIG_PIT_INDEX]);

    }

    /**
     * The function tests whether the active player's big pit is increased by 1 after sowing stones from a valid pit.
     */
    @Test
    void sowStonesFromValidPit_ActivePlayerBigPitIncreasedBy_1(){
        game.getBoard().getPits()[12] = 10;
        game.setCurrentIndex(12);

        int activePlayerBigPitIndexPreviousValue = game.getBoard().getPits()[ACTIVE_PLAYER_BIG_PIT_INDEX];

        sowStones.execute(game);

        assertEquals(activePlayerBigPitIndexPreviousValue + 1, game.getBoard().getPits()[ACTIVE_PLAYER_BIG_PIT_INDEX]);

    }

    /**
     * The function tests that sowing stones from an invalid pit index throws an InvalidPitIndexException.
     */
    @Test
    void sowStonesFromInvalidPit_Throw_InvalidPitException(){

        game.setCurrentIndex(PLAYER_A_INVALID_PIT_INDEX);

        InvalidPitIndexException exception = assertThrows(InvalidPitIndexException.class,()->  sowStones.execute(game));

        assertEquals(INVALID_PIT_INDEX, exception.getMessage());

    }

}
