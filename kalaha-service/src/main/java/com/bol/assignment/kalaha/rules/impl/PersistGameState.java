package com.bol.assignment.kalaha.rules.impl;

import com.bol.assignment.kalaha.domain.Game;
import com.bol.assignment.kalaha.rules.GameProcessor;
import com.bol.assignment.kalaha.service.SaveGameState;
import org.springframework.stereotype.Service;

/**
 * The `PersistGameState` class is a service that implements the `GameProcessor` interface and is responsible for
 * persisting the game state by using the `SaveGameState` dependency.
 */
@Service
public class PersistGameState implements GameProcessor {

    SaveGameState saveGameState;

    public PersistGameState(SaveGameState saveGameState){
        this.saveGameState = saveGameState;
    }

    /**
     * The execute function saves the current game state.
     *
     * @param game The "game" parameter is an instance of the Game class.
     */
    @Override
    public void execute(Game game) {
        saveGameState.setGameState(game);
    }

    /**
     * The function getOrder() returns the order of an object.
     *
     * @return The method is returning the integer value 5.
     */
    @Override
    public int getOrder() {
        return 5;
    }
}
