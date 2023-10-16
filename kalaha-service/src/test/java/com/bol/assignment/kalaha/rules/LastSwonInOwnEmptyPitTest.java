package com.bol.assignment.kalaha.rules;

import com.bol.assignment.kalaha.domain.Game;
import com.bol.assignment.kalaha.rules.impl.LastSwonInOwnEmptyPit;
import com.bol.assignment.kalaha.util.GameTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.bol.assignment.kalaha.util.GameTestUtil.PLAYER_A_BIG_PIT_INDEX;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The class LastSwonInOwnEmptyPitTest contains unit tests for the LastSwonInOwnEmptyPit class in a Java program.
 */
@ExtendWith(MockitoExtension.class)
public class LastSwonInOwnEmptyPitTest {

    public static final int PLAYER_A_LAST_SWON_PIT_INDEX = 11;
    public static final int PLAYER_A_LAST_SWON_PIT_OPPSITE_SIDE_INDEX = 1;
    public static final int PLAYER_A_LAST_SWON_PIT_INDEX_STONE_COUNT_ONE = 1;
    public static final int PLAYER_A_LAST_SWON_PIT_OPPONENT_INDEX_STONE_COUNT = 8;
    public static final int EXPECTED_PLAYER_A_BIG_PIT_VALUE = PLAYER_A_LAST_SWON_PIT_INDEX_STONE_COUNT_ONE+PLAYER_A_LAST_SWON_PIT_OPPONENT_INDEX_STONE_COUNT;
    public static final int PLAYER_A_LAST_SWON_PIT_INDEX_STONE_COUNT_GREATER_THAN_ONE = 2;
    public static final int EXPECTED_PLAYER_A_BIG_PIT_VALUE_ZERO = 0;

    Game game;

    LastSwonInOwnEmptyPit lastSwonInOwnEmptyPit = new LastSwonInOwnEmptyPit();

    /**
     * The setup method is used to initialize the game object before each test case.
     */
    @BeforeEach
    void setup(){
        game = GameTestUtil.getValidGameInput();
    }

    /**
     * The function tests if the stones in the last swon pit and its opposite pit are moved to the player's big pit.
     */
    @Test
    void lastSwonInOwnEmptyPit_OwnPitAndOppositePitStones_MovedToOwnBigPit(){

        game.getBoard().getPits()[PLAYER_A_LAST_SWON_PIT_INDEX] = PLAYER_A_LAST_SWON_PIT_INDEX_STONE_COUNT_ONE;
        game.getBoard().getPits()[PLAYER_A_LAST_SWON_PIT_OPPSITE_SIDE_INDEX] = PLAYER_A_LAST_SWON_PIT_OPPONENT_INDEX_STONE_COUNT;
        game.setLastIndex(PLAYER_A_LAST_SWON_PIT_INDEX);

        lastSwonInOwnEmptyPit.execute(game);

        assertEquals(EXPECTED_PLAYER_A_BIG_PIT_VALUE,game.getBoard().getPits()[PLAYER_A_BIG_PIT_INDEX]);
    }

    /**
     * The function tests that when the last stone is placed in the player's own non-empty pit, the value of the player's
     * big pit remains unchanged.
     */
    @Test
    void lastSwonInOwnNonEmptyPit_OwnBigPitUnChange(){
        game.getBoard().getPits()[PLAYER_A_LAST_SWON_PIT_INDEX] = PLAYER_A_LAST_SWON_PIT_INDEX_STONE_COUNT_GREATER_THAN_ONE;
        game.getBoard().getPits()[PLAYER_A_LAST_SWON_PIT_OPPSITE_SIDE_INDEX] = PLAYER_A_LAST_SWON_PIT_OPPONENT_INDEX_STONE_COUNT;
        game.setLastIndex(PLAYER_A_LAST_SWON_PIT_INDEX);

        lastSwonInOwnEmptyPit.execute(game);

        assertEquals(EXPECTED_PLAYER_A_BIG_PIT_VALUE_ZERO,game.getBoard().getPits()[PLAYER_A_BIG_PIT_INDEX]);
    }

    /**
     * The function tests that when the last stone is placed in an opponent's non-empty pit, the player's big pit remains
     * unchanged.
     */
    @Test
    void lastSwonInOpponentNonEmptyPit_OwnBigPitUnChange(){
        game.getBoard().getPits()[PLAYER_A_LAST_SWON_PIT_INDEX] = PLAYER_A_LAST_SWON_PIT_OPPONENT_INDEX_STONE_COUNT;
        game.getBoard().getPits()[PLAYER_A_LAST_SWON_PIT_OPPSITE_SIDE_INDEX] = PLAYER_A_LAST_SWON_PIT_INDEX_STONE_COUNT_ONE;
        game.setLastIndex(PLAYER_A_LAST_SWON_PIT_OPPSITE_SIDE_INDEX);

        lastSwonInOwnEmptyPit.execute(game);

        assertEquals(EXPECTED_PLAYER_A_BIG_PIT_VALUE_ZERO,game.getBoard().getPits()[PLAYER_A_BIG_PIT_INDEX]);
    }

    /**
     * The function tests that when the last stone is placed in an opponent's empty pit, the player's big pit remains
     * unchanged.
     */
    @Test
    void lastSwonInOpponentEmptyPit_OwnBigPitUnChange(){
        game.getBoard().getPits()[PLAYER_A_LAST_SWON_PIT_INDEX] = PLAYER_A_LAST_SWON_PIT_OPPONENT_INDEX_STONE_COUNT;
        game.getBoard().getPits()[PLAYER_A_LAST_SWON_PIT_OPPSITE_SIDE_INDEX] = PLAYER_A_LAST_SWON_PIT_INDEX_STONE_COUNT_GREATER_THAN_ONE;
        game.setLastIndex(PLAYER_A_LAST_SWON_PIT_OPPSITE_SIDE_INDEX);

        lastSwonInOwnEmptyPit.execute(game);

        assertEquals(EXPECTED_PLAYER_A_BIG_PIT_VALUE_ZERO,game.getBoard().getPits()[PLAYER_A_BIG_PIT_INDEX]);
    }
}
