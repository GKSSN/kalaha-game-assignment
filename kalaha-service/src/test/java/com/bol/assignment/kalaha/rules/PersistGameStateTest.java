package com.bol.assignment.kalaha.rules;

import com.bol.assignment.kalaha.domain.Game;
import com.bol.assignment.kalaha.rules.impl.PersistGameState;
import com.bol.assignment.kalaha.service.SaveGameState;
import com.bol.assignment.kalaha.util.GameTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * The class `PersistGameStateTest` is a JUnit test class that tests the functionality of the `PersistGameState` class.
 */
@ExtendWith(MockitoExtension.class)
class PersistGameStateTest {

    @InjectMocks
    PersistGameState persistGameState;

    @Mock
    SaveGameState saveGameState;

    Game game;

    /**
     * The setup method is used to initialize the game object before each test case.
     */
    @BeforeEach
    void setup(){
        persistGameState = new PersistGameState(saveGameState);
        game = GameTestUtil.getValidGameInput();
    }

    /**
     * The function "saveGame_Success" saves the game state successfully.
     */
    @Test
    void saveGame_Success(){
        persistGameState.execute(game);
        verify(saveGameState, times(1)).setGameState(any(Game.class));
    }
}
