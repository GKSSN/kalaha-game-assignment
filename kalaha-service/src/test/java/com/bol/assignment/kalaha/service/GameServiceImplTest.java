package com.bol.assignment.kalaha.service;

import com.bol.assignment.kalaha.domain.Board;
import com.bol.assignment.kalaha.domain.Game;
import com.bol.assignment.kalaha.domain.Player;
import com.bol.assignment.kalaha.domain.PlayersInfo;
import com.bol.assignment.kalaha.exception.impl.InvalidPitIndexException;
import com.bol.assignment.kalaha.exception.impl.PlayersInfoNullOrEmptyException;
import com.bol.assignment.kalaha.rules.GameProcessor;
import com.bol.assignment.kalaha.service.impl.GameServiceImpl;
import com.bol.assignment.kalaha.util.GameTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.bol.assignment.kalaha.util.GameTestUtil.PLAYER_A_INVALID_PIT_INDEX;
import static com.bol.assignment.kalaha.util.GameTestUtil.PLAYER_A_VALID_PIT_INDEX;
import static com.bol.assignment.kalaha.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


/**
 * The above class is a unit test class for the GameServiceImpl class in a Java application.
 */
@ExtendWith(MockitoExtension.class)
public class GameServiceImplTest {

    public static final int EXPECTED_INDEX = 8;

    @Mock
    SaveGameState saveGameState;

    @Mock
    List<GameProcessor> gameProcessors;

    @InjectMocks
    GameServiceImpl gameServiceImpl;

    /**
     * The function tests whether a game can be started with player information.
     */
    @Test
    void startGameWithPlayerInfo_GameStarted(){

        PlayersInfo playersInfo = new PlayersInfo();
        playersInfo.setPlayerAName(GameTestUtil.PLAYER_A_NAME);
        playersInfo.setPlayerBName(GameTestUtil.PLAYER_B_NAME);

        Game actualGame  = gameServiceImpl.startGame(playersInfo);

        Game expectedGame = new Game(new Board());
        expectedGame.setPlayerList(List.of(new Player(GameTestUtil.PLAYER_A_NAME, BIG_PIT_INDEX_PLAYER_A),
                new Player(GameTestUtil.PLAYER_B_NAME, BIG_PIT_INDEX_PLAYER_B)));
        expectedGame.setStarted(Boolean.TRUE);

        assertStartGame(expectedGame, actualGame);

    }

    /**
     * The function tests that starting a game with null player information throws a PlayersInfoNullOrEmptyException.
     */
    @Test
    void startGameWithPlayerInfoNull_Throw_PlayersInfoNullOrEmptyException(){

        PlayersInfoNullOrEmptyException exception =  assertThrows(PlayersInfoNullOrEmptyException.class,
                ()-> gameServiceImpl.startGame(null));
        assertEquals(PLAYERS_INFO_IS_NULL_OR_PLAYERS_NAME_IS_EMPTY, exception.getMessage());
    }

    /**
     * The function tests that starting a game with an empty player A name throws a PlayersInfoNullOrEmptyException.
     */
    @Test
    void startGameWithPlayerAEmpty_Throw_PlayersInfoNullOrEmptyException(){

        PlayersInfo playersInfo = new PlayersInfo();
        playersInfo.setPlayerAName("");
        playersInfo.setPlayerBName(GameTestUtil.PLAYER_B_NAME);

        PlayersInfoNullOrEmptyException exception =  assertThrows(PlayersInfoNullOrEmptyException.class,
                ()-> gameServiceImpl.startGame(playersInfo));

        assertEquals(PLAYERS_INFO_IS_NULL_OR_PLAYERS_NAME_IS_EMPTY, exception.getMessage());
    }

    /**
     * The function tests that starting a game with an empty player B name throws a PlayersInfoNullOrEmptyException.
     */
    @Test
    void startGameWithPlayerBEmpty_Throw_PlayersInfoNullOrEmptyException(){
        PlayersInfo playersInfo = new PlayersInfo();
        playersInfo.setPlayerAName(GameTestUtil.PLAYER_A_NAME);
        playersInfo.setPlayerBName("");

        PlayersInfoNullOrEmptyException exception =  assertThrows(PlayersInfoNullOrEmptyException.class, ()->
            gameServiceImpl.startGame(playersInfo));

        assertEquals(PLAYERS_INFO_IS_NULL_OR_PLAYERS_NAME_IS_EMPTY, exception.getMessage());
    }

    /**
     * This function tests the playGameWithValidGameInput method in the gameService class.
     */
    @Test
    void playGameWithValidGameInput_ReturnGameState(){

        Game currentGameState = GameTestUtil.getCurrentGameState();

        when(saveGameState.getGameState()).thenReturn(currentGameState);

        Game actualGameState  = gameServiceImpl.sowStones(PLAYER_A_VALID_PIT_INDEX);

        assertNotNull(actualGameState);
        assertEquals(EXPECTED_INDEX, actualGameState.getCurrentIndex());

    }

    /**
     * The function tests that an InvalidPitIndexException is thrown when trying to play a game with an invalid pit index.
     */
    @Test
    void playGameWithInvalidPitIndex_Throw_InvalidGameException(){

        Game gameInput = GameTestUtil.getGameInputInvalidPitIndex();

        when(saveGameState.getGameState()).thenReturn(gameInput);

        InvalidPitIndexException exception = assertThrows(InvalidPitIndexException.class,
                ()-> gameServiceImpl.sowStones(PLAYER_A_INVALID_PIT_INDEX));

        assertEquals(INVALID_PIT_INDEX, exception.getMessage());

    }

    /**
     * The function asserts that the expected and actual games have the same number of players, a current player is not
     * null, and the game is started.
     *
     * @param expectedGame The expectedGame parameter is an instance of the Game class that represents the expected state
     * of the game. It is used for comparison with the actualGame parameter to ensure that the game has started correctly.
     * @param actualGame The actual game object that is being tested.
     */
    private void assertStartGame(Game expectedGame, Game actualGame) {
        assertEquals(expectedGame.getPlayerList().size(),actualGame.getPlayerList().size());
        assertNotNull(actualGame.getCurrentPlayer());
        assertEquals(expectedGame.isStarted(),actualGame.isStarted());
    }
}
