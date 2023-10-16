package com.bol.assignment.kalaha.rules.impl;

import com.bol.assignment.kalaha.domain.Game;
import com.bol.assignment.kalaha.domain.Player;
import com.bol.assignment.kalaha.rules.GameProcessor;
import com.bol.assignment.kalaha.utils.Constants;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.bol.assignment.kalaha.utils.Constants.PITS_PER_PLAYER;

/**
 * The `LastSwonInOwnEmptyPit` class is a Java service that captures stones from the current and opposite pit into the
 * player's own big pit if the last stone was placed in an empty pit owned by the player.
 */
@Service
public class LastSwonInOwnEmptyPit implements GameProcessor {

    /**
     * The function captures stones from the current and opposite pit into the player's big pit if the game is not
     * completed.
     *
     * @param game The "game" parameter is an instance of the Game class.
     */
    @Override
    public void execute(final Game game) {
        if (!game.isCompleted()) {
            captureStonesFromCurrentAndOppositePitIntoOwnBigPit(game);
        }
    }

    /**
     * The function captures stones from the current pit and the opposite pit into the player's big pit in a game.
     *
     * @param game The "game" parameter is an instance of the Game class. It represents the current state of the game,
     * including the board, players, and their pits.
     */
    private void captureStonesFromCurrentAndOppositePitIntoOwnBigPit(final Game game) {
        int lastIndex = game.getLastIndex();

        List<Integer> bigPitIndexes = game.getPlayerList().stream().map(Player::getBigPitIndex).collect(Collectors.toList());

        if (!bigPitIndexes.contains(lastIndex) && lastSwonPitIsOwnPit(game, lastIndex)) {

            int lastSwonPitStoneCount = game.getBoard().getStoneCountByIndex(lastIndex);

            if (lastSwonPitStoneCount == 1) {
                int oppositePitIndex = Math.abs(lastIndex - PITS_PER_PLAYER * 2);
                int oppositePitStoneCount = game.getBoard().getStoneCountByIndex(oppositePitIndex);

                int[] pits = game.getBoard().getPits();

                pits[game.getCurrentPlayer().getBigPitIndex()] += lastSwonPitStoneCount + oppositePitStoneCount;
                pits[lastIndex] = 0;
                pits[oppositePitIndex] = 0;
            }
        }
    }

    /**
     * The function checks if the last stone was placed in the current player's own pit.
     *
     * @param game The "game" parameter is an instance of the Game class, which represents the current state of the game
     * being played. It contains information such as the current player, the board configuration, and the number of stones
     * in each pit.
     * @param lastIndex The `lastIndex` parameter represents the index of the last pit that was swon (sown) with stones in
     * the game.
     * @return The method is returning a boolean value.
     */
    private boolean lastSwonPitIsOwnPit(final Game game, final int lastIndex) {
        int bigPit = game.getCurrentPlayer().getBigPitIndex();

        return lastIndex < bigPit && lastIndex >= bigPit - Constants.PITS_PER_PLAYER;
    }

    /**
     * The function getOrder() returns the order of the object.
     *
     * @return The method is returning the integer value 2.
     */
    @Override
    public int getOrder() {
        return 2;
    }
}
