package com.bol.assignment.kalaha.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The Game class represents a game with a board, players, and various game states and properties.
 */
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Component
public class Game {

    private Board board;

    @Setter
    private List<Player> playerList;

    @Setter
    private boolean started = Boolean.FALSE;

    @Setter
    private boolean completed = Boolean.FALSE;

    @Setter
    private String winner;

    @Setter
    private int currentIndex;

    @Setter
    @JsonIgnore
    private int lastIndex;

    @Setter
    private Player currentPlayer;

    public Game(Board board) {
        this.board = board;
    }
}
