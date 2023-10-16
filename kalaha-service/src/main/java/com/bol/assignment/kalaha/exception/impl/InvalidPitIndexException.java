package com.bol.assignment.kalaha.exception.impl;

import com.bol.assignment.kalaha.exception.GameException;

/**
 * The InvalidPitIndexException class is a custom exception that is thrown when an invalid pit index is provided in a game.
 */
public class InvalidPitIndexException extends GameException {

    // The code `public InvalidPitIndexException(String message) { super(message); }` is the constructor of the
    // `InvalidPitIndexException` class.
    public InvalidPitIndexException(String message) {
        super(message);
    }

}
