package com.bol.assignment.kalaha.service.impl;

import com.bol.assignment.kalaha.domain.Board;
import com.bol.assignment.kalaha.domain.Game;
import com.bol.assignment.kalaha.domain.Player;
import com.bol.assignment.kalaha.domain.PlayersInfo;
import com.bol.assignment.kalaha.rules.GameProcessor;
import com.bol.assignment.kalaha.service.GameService;
import com.bol.assignment.kalaha.service.SaveGameState;
import com.bol.assignment.kalaha.utils.Constants;
import com.bol.assignment.kalaha.validator.GameValidator;
import com.bol.assignment.kalaha.validator.PlayerValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The GameServiceImpl class is a service implementation that handles the logic for starting a game and sowing stones in a
 * game.
 */

@Service
public class GameServiceImpl implements GameService {

    private final List<GameProcessor> gameProcessors;

    private Game game;

    private final SaveGameState saveGameState;

    public GameServiceImpl(Game game, List<GameProcessor> gameProcessors,
                    SaveGameState saveGameState) {
        this.game = game;
        this.gameProcessors = gameProcessors;
        this.saveGameState = saveGameState;

    }

    /**
     * The startGame function initializes a new game, sets up the board with stones in each pit, and saves the game state.
     *
     * @param playersInfo The `playersInfo` parameter is an object of type `PlayersInfo` which contains information about
     * the players participating in the game. It likely includes the names of the players (playerAName and playerBName).
     * @return The method is returning a Game object.
     */
    @Override
    public Game startGame(final PlayersInfo playersInfo) {

        PlayerValidator.isValidPlayerInfo(playersInfo);

        game = new Game(new Board());
        game.setPlayerList(List.of(new Player(playersInfo.getPlayerAName(), Constants.BIG_PIT_INDEX_PLAYER_A),
                new Player(playersInfo.getPlayerBName(), Constants.BIG_PIT_INDEX_PLAYER_B)));

        Board board = game.getBoard();
        int[] pits = board.getPits();

        for (int i = 0; i < pits.length; i++) {
            if (i != Constants.BIG_PIT_INDEX_PLAYER_A && i != Constants.BIG_PIT_INDEX_PLAYER_B) {
                pits[i] = Constants.STONES_PER_PIT;
            }
        }

        board.setFilled(Boolean.TRUE);
        game.setStarted(Boolean.TRUE);
        game.setCurrentPlayer(game.getPlayerList().get(0));

        saveGameState.setGameState(game);

        return game;
    }

    /**
     * This function updates the current index of a game, executes game processors, and returns the updated game state.
     *
     * @param currentIndex The currentIndex parameter is an Integer that represents the current index of the game board
     * where stones are being sown.
     * @return The method is returning the updated game state after executing the game processors.
     */
    @Override
    public Game sowStones(final Integer currentIndex) {

        Game gameState = saveGameState.getGameState();

        GameValidator.isValidCurrentIndex(gameState, currentIndex);

        gameState.setCurrentIndex(currentIndex);

        gameProcessors.forEach(rule -> rule.execute(gameState));

        return saveGameState.getGameState();
    }

}
