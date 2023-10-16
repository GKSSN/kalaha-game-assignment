package com.bol.assignment.kalaha.service;

import com.bol.assignment.kalaha.domain.Game;
import com.bol.assignment.kalaha.util.GameTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Jayabalan
 * @project kalaha-service
 */
@ExtendWith(MockitoExtension.class)
class SaveGameStateTest {

    @Mock
    private Game game;

    @InjectMocks
    private SaveGameState saveGameState;

    @BeforeEach
    void setup(){
        game = GameTestUtil.getValidGameInput();
    }

    @Test
    void testSetterGetter_Success(){
        saveGameState.setGameState(game);
        Game savedGame = saveGameState.getGameState();
        assertNotNull(savedGame);
    }
}
