package com.bol.assignment.kalaha.exception;

/**
 * The GameException class is a custom exception that extends the RuntimeException class and is used to handle exceptions
 * specific to a game.
 */
public class GameException extends RuntimeException{
    // The code `public GameException(String message){ super(message); }` is a constructor for the `GameException` class.
    public GameException(String message){
        super(message);
    }
}
