package com.bol.assignment.kalaha.service;

import com.bol.assignment.kalaha.domain.Game;
import org.springframework.stereotype.Service;

@Service
public class SaveGameState {

    Game gameState;

    public SaveGameState(Game game){
        this.gameState = game;
    }

    /**
     * The function returns the current state of the game.
     *
     * @return The method is returning the current state of the game.
     */
    public Game getGameState(){
        return this.gameState;
    }

    /**
     * The function sets the game state of an object.
     *
     * @param game The "game" parameter is an object of type "Game".
     */
    public void setGameState(Game game){
        this.gameState = game;
    }

}
