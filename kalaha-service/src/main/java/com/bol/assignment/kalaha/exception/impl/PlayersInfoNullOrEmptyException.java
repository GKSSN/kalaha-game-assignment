package com.bol.assignment.kalaha.exception.impl;

import com.bol.assignment.kalaha.exception.GameException;

/**
 * The PlayersInfoNullOrEmptyException class is a custom exception that is thrown when the players' information is null or
 * empty in a game.
 */
public class PlayersInfoNullOrEmptyException extends GameException {

    // The code `public PlayersInfoNullOrEmptyException(String message){ super(message); }` is a constructor for the
    // `PlayersInfoNullOrEmptyException` class.
    public PlayersInfoNullOrEmptyException(String message){
        super(message);
    }
}
