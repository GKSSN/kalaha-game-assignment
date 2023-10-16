package com.bol.assignment.kalaha.rules.impl;

import com.bol.assignment.kalaha.domain.Game;
import com.bol.assignment.kalaha.rules.GameProcessor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * The DetermineNextPlayer class is a Java service that determines the next player in a game based on the current player
 * and game state.
 */
@Service
public class DetermineNextPlayer implements GameProcessor {
    /**
     * The function changes the current player in a game if the game is not completed and the last move was not made by the
     * current player's big pit.
     *
     * @param game The "game" parameter is an instance of the Game class. It represents the current state of the game being
     * played.
     */
    @Override
    public void execute(final Game game) {

        if (!game.isCompleted() && game.getLastIndex() != game.getCurrentPlayer().getBigPitIndex()) {
            game.setCurrentPlayer(game.getPlayerList().stream()
                    .filter(player -> player.getBigPitIndex() != game.getCurrentPlayer().getBigPitIndex())
                    .collect(Collectors.toList()).get(0));
        }
    }

    /**
     * The function getOrder() returns the order of a specific item.
     *
     * @return The method is returning the integer value 3.
     */
    @Override
    public int getOrder() {
        return 3;
    }
}
