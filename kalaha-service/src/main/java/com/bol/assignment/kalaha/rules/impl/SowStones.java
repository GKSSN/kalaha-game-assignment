package com.bol.assignment.kalaha.rules.impl;

import com.bol.assignment.kalaha.domain.Game;
import com.bol.assignment.kalaha.domain.Player;
import com.bol.assignment.kalaha.exception.impl.InvalidPitIndexException;
import com.bol.assignment.kalaha.rules.GameProcessor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

import static com.bol.assignment.kalaha.utils.Constants.*;

/**
 * The `SowStones` class is a Java implementation of a game processor that sows stones in pits according to the game rules.
 */
@Service
public class SowStones implements GameProcessor {

    /**
     * The function checks if a game is not completed and if the picked pit index is valid, and then sows stones in the
     * game. If the conditions are not met, it throws an exception.
     *
     * @param game The "game" parameter is an instance of the Game class.
     */
    @Override
    public void execute(final Game game) {
        if (!game.isCompleted() && isValidPitPicked(game, game.getCurrentIndex())) {
            sowStones(game);
        }else{
            throw new InvalidPitIndexException(INVALID_PIT_INDEX);
        }
    }


    /**
     * The function `sowStones` takes a `Game` object as input and distributes the stones from the current pit to the
     * subsequent pits, skipping the opponent's big pit.
     *
     * @param game The "game" parameter is an instance of the Game class. It represents the current state of the game being
     * played.
     */
    private void sowStones(final Game game) {

        int[] pits = game.getBoard().getPits();

        int currentIndex = game.getCurrentIndex();

        int stoneCount = pits[currentIndex];

        int opponentBigPitIndex = getOpponentBigPitIndex(game);

        pits[currentIndex] = 0;

        while (stoneCount > 0) {

            currentIndex++;

            if (currentIndex >= TOTAL_PIT_COUNT) {
                currentIndex = 0;
            }

            if (opponentBigPitIndex == currentIndex) {
                continue;
            }

            pits[currentIndex]++;
            stoneCount--;

            game.setLastIndex(currentIndex);
        }
    }

    /**
     * The function returns the index of the big pit belonging to the opponent player in a game.
     *
     * @param game The "game" parameter is an instance of the Game class.
     * @return The method is returning an integer value, which is the index of the big pit belonging to the opponent player
     * in the given game.
     */
    private int getOpponentBigPitIndex(Game game) {
        return game.getPlayerList().stream().map(Player::getBigPitIndex)
                .filter(bigPitIndex -> bigPitIndex != game.getCurrentPlayer().getBigPitIndex())
                .collect(Collectors.toList())
                .get(0);
    }

    /**
     * The function checks if a picked pit is valid based on the current player's big pit index and the number of pits per
     * player.
     *
     * @param game The "game" parameter is an instance of the Game class, which represents the current state of the game
     * being played.
     * @param pickedPit The index of the pit that the player has picked.
     * @return The method returns a boolean value indicating whether the picked pit is valid or not.
     */
    boolean isValidPitPicked(Game game, int pickedPit) {
        int bigPitIndex = game.getCurrentPlayer().getBigPitIndex();
        return pickedPit < bigPitIndex && pickedPit >= bigPitIndex - PITS_PER_PLAYER;
    }

    /**
     * The function getOrder() returns an integer value of 1.
     *
     * @return The method is returning an integer value of 1.
     */
    @Override
    public int getOrder() {
        return 1;
    }
}
