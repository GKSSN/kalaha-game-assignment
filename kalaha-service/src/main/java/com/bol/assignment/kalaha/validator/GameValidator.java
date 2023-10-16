package com.bol.assignment.kalaha.validator;

import com.bol.assignment.kalaha.domain.Game;
import com.bol.assignment.kalaha.exception.impl.InvalidPitIndexException;
import org.springframework.stereotype.Service;

import static com.bol.assignment.kalaha.utils.Constants.INVALID_PIT_INDEX;
import static com.bol.assignment.kalaha.utils.Constants.PITS_PER_PLAYER;

/**
 * The GameValidator class checks if a given current index is valid for a game.
 */
@Service
public class GameValidator {

    private GameValidator(){}

    /**
     * The function checks if a given current index is valid within the context of a game.
     *
     * @param game An instance of the Game class, which represents the current state of the game being played.
     * @param currentIndex The currentIndex parameter represents the index of the pit that the current player wants to move
     * stones from.
     */
    public static void isValidCurrentIndex(Game game, Integer currentIndex){

        int bigPitIndex = game.getCurrentPlayer().getBigPitIndex();

        if(!(currentIndex < bigPitIndex && currentIndex >= bigPitIndex - PITS_PER_PLAYER)){
            throw new InvalidPitIndexException(INVALID_PIT_INDEX);
        }

    }

}
