package com.bol.assignment.kalaha.rules;

import com.bol.assignment.kalaha.domain.Game;
import org.springframework.core.Ordered;

// The code is defining a Java interface called `GameProcessor` that extends the `Ordered` interface.
public interface GameProcessor extends Ordered {

    /**
     * The execute function takes a Game object as a parameter and performs some action on it.
     *
     * @param game The "game" parameter is an object of the Game class. It represents an instance of the game that you want
     * to execute.
     */
    void execute(final Game game);

}
