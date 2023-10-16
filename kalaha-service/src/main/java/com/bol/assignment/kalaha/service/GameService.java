package com.bol.assignment.kalaha.service;

import com.bol.assignment.kalaha.domain.Game;
import com.bol.assignment.kalaha.domain.PlayersInfo;

// The `GameService` interface defines two methods: `startGame` and `sowStones`.
public interface GameService {

    /**
     * The startGame function takes in playersInfo and returns a Game object.
     *
     * @param playersInfo An object that contains information about the players in the game. This could include their
     * names, scores, levels, and any other relevant information.
     * @return a variable of type "Game".
     */
    Game startGame(final PlayersInfo playersInfo);

    /**
     * The function "sowStones" takes an integer parameter "currentIndex" and returns a Game object.
     *
     * @param currentIndex The currentIndex parameter represents the current index or position in the game of SowStones.
     * @return The return type of the method "sowStones" is not specified in the given code snippet. Therefore, it is not
     * possible to determine what is being returned without further information.
     */
    Game sowStones(final Integer currentIndex);

}
