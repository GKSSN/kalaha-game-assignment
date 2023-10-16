package com.bol.assignment.kalaha.util;

import com.bol.assignment.kalaha.domain.Board;
import com.bol.assignment.kalaha.domain.Game;
import com.bol.assignment.kalaha.domain.Player;
import com.bol.assignment.kalaha.domain.PlayersInfo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;

import static com.bol.assignment.kalaha.utils.Constants.*;

/**
 * The GameTestUtil class provides utility methods for creating and setting up game objects for testing purposes.
 */
public class GameTestUtil {

    public static final String PLAYER_A_NAME = "Gopal";
    public static final String PLAYER_B_NAME = "Deepika";
    public static final int PLAYER_A_VALID_PIT_INDEX = 8;
    public static final int PLAYER_A_INVALID_PIT_INDEX = 16;
    public static final int PLAYER_A_BIG_PIT_INDEX = 13;
    public static final int PLAYER_B_BIG_PIT_INDEX = 6;
    public static final String CURRENT_INDEX_QUERY_PARAM = "?currentIndex=9";

    /**
     * The function creates a new game object, sets player details, fills pits with stones, sets current player info, and
     * sets the current index to a valid pit index for player A.
     *
     * @return The method is returning an instance of the Game class.
     */
    public static Game getValidGameInput() {

        Game game = new Game(new Board());

        setPlayerDetails(game);
        fillPitsWithStones(game);
        setCurrentPlayerInfo(game);

        game.setCurrentIndex(PLAYER_A_VALID_PIT_INDEX);

        return game;
    }

    /**
     * The function creates a new game with invalid pit index for player A.
     *
     * @return The method is returning a Game object.
     */
    public static Game getGameInputInvalidPitIndex() {

        Game game = new Game(new Board());

        setPlayerDetails(game);
        fillPitsWithStones(game);
        setCurrentPlayerInfo(game);

        game.setCurrentIndex(PLAYER_A_INVALID_PIT_INDEX);

        return game;
    }

    /**
     * The function getCurrentGameState() creates a new Game object, sets player details, fills pits with stones, sets
     * current player info, and returns the game object.
     *
     * @return The method is returning an instance of the Game class.
     */
    public static Game getCurrentGameState() {

        Game game = new Game(new Board());

        setPlayerDetails(game);
        fillPitsWithStones(game);
        setCurrentPlayerInfo(game);

        return game;
    }

    /**
     * The function sets the current player information in the game.
     *
     * @param game The "game" parameter is an instance of the Game class.
     */
    private static void setCurrentPlayerInfo(Game game) {
        Player A = new Player(PLAYER_A_NAME, BIG_PIT_INDEX_PLAYER_A);
        game.setCurrentPlayer(A);
    }

    /**
     * The function sets the player details for a game by creating two player objects and adding them to the game's player
     * list.
     *
     * @param game The "game" parameter is an instance of the Game class.
     */
    private static void setPlayerDetails(Game game) {
        Player A = new Player(PLAYER_A_NAME, BIG_PIT_INDEX_PLAYER_A);
        Player B = new Player(PLAYER_B_NAME, BIG_PIT_INDEX_PLAYER_B);

        game.setPlayerList(Arrays.asList(A, B));
    }

    /**
     * The function returns an instance of PlayersInfo with the names of two players set.
     *
     * @return The method is returning an instance of the PlayersInfo class.
     */
    public static PlayersInfo getPlayersInfo(){
        PlayersInfo playersInfo = new PlayersInfo();
        playersInfo.setPlayerAName(PLAYER_A_NAME);
        playersInfo.setPlayerBName(PLAYER_B_NAME);
        return playersInfo;
    }

    /**
     * The function fills all pits on the game board with a specified number of stones, except for the big pits.
     *
     * @param game The "game" parameter is an instance of the Game class. It represents the current state of the game being
     * played.
     */
    private static void fillPitsWithStones(Game game) {
        int[] pits = game.getBoard().getPits();
        for (int i = 0; i < pits.length; i++) {
            if (i != BIG_PIT_INDEX_PLAYER_A && i != BIG_PIT_INDEX_PLAYER_B) {
                pits[i] = STONES_PER_PIT;
            }
        }
    }

    /**
     * The function converts an object to a JSON string using the Jackson ObjectMapper.
     *
     * @param obj The "obj" parameter is an object that you want to convert to a JSON string.
     * @return The method is returning a JSON string representation of the given object.
     */
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
