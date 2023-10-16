package com.bol.assignment.kalaha.exception.handler;

import com.bol.assignment.kalaha.exception.impl.InvalidPitIndexException;
import com.bol.assignment.kalaha.exception.impl.PlayersInfoNullOrEmptyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The GameGlobalExceptionHandler class handles exceptions related to method argument validation and players' information
 * being null or empty.
 */
@ControllerAdvice
public class GameGlobalExceptionHandler {

    /**
     * The function handles an exception for when the players' information is null or empty.
     *
     * @param exp The parameter "exp" is an instance of the PlayersInfoNullOrEmptyException class, which is an exception
     * that is thrown when the players' information is null or empty.
     * @return The method is returning a ResponseEntity object with a status of HttpStatus.BAD_REQUEST and a body
     * containing the message from the PlayersInfoNullOrEmptyException.
     */
    @ExceptionHandler(value = PlayersInfoNullOrEmptyException.class)
    public ResponseEntity<String> handlePlayersInfoNullOrEmptyException(PlayersInfoNullOrEmptyException exp){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exp.getMessage());
    }

    @ExceptionHandler(value = InvalidPitIndexException.class)
    public ResponseEntity<String> handleInvalidPitIndexException(InvalidPitIndexException exp){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exp.getMessage());
    }
}
