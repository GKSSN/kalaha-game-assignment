package com.bol.assignment.kalaha.controller;

import com.bol.assignment.kalaha.domain.Game;
import com.bol.assignment.kalaha.domain.PlayersInfo;
import com.bol.assignment.kalaha.exception.impl.InvalidPitIndexException;
import com.bol.assignment.kalaha.exception.impl.PlayersInfoNullOrEmptyException;
import com.bol.assignment.kalaha.service.GameService;
import com.bol.assignment.kalaha.util.GameTestUtil;
import com.bol.assignment.kalaha.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The above class is a test class for the GameController class in a Java Spring MVC application.
 */
@WebMvcTest
public class GameControllerTest {

    public static final int VALID_PIT_INDEX = 9;
    public static final int INVALID_PIT_INDEX = -2;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    GameService gameService;

    Game game;

    /**
     * The setup method is used to initialize the game object before each test case.
     */
    @BeforeEach
    void setup(){
        game = GameTestUtil.getValidGameInput();
    }

    /**
     * This function tests the startGame method in the gameServiceImpl class by mocking the HTTP request and verifying the
     * response status.
     */
    @Test
    void startGameWithValidPlayersInfo_GameStarted() throws Exception {

        when(gameService.startGame(any(PlayersInfo.class))).thenReturn(game);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                        .post(Constants.GAME_URL + Constants.START_GAME_API)
                        .content(GameTestUtil.asJsonString(GameTestUtil.getPlayersInfo()))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();
        assertNotNull(mvcResult);
        verify(gameService,times(1)).startGame(any(PlayersInfo.class));
    }

    /**
     * The function tests that starting a game with null player information throws a PlayersInfoNullOrEmptyException.
     */
    @Test
    void startGameWithPlayersInfoNull_Throw_PlayersInfoNullOrEmptyException() throws Exception {

        when(gameService.startGame(ArgumentMatchers.any(PlayersInfo.class))).thenThrow(PlayersInfoNullOrEmptyException.class);
        mockMvc.perform(MockMvcRequestBuilders
                        .post(Constants.GAME_URL + Constants.START_GAME_API)
                        .content(GameTestUtil.asJsonString(null))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isBadRequest());
        verify(gameService,times(0)).startGame(any(PlayersInfo.class));
    }

    /**
     * The function tests the playGameWithValidGame method by mocking the sowStones method and performing a POST request to
     * the game URL with the current index query parameter and valid pit index.
     */
    @Test
    void playGameWithValidGame_ReturnGameState() throws Exception {
        when(gameService.sowStones(any(Integer.class))).thenReturn(game);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(Constants.GAME_URL + Constants.PLAY_GAME_API + GameTestUtil.CURRENT_INDEX_QUERY_PARAM)
                        .content(GameTestUtil.asJsonString(VALID_PIT_INDEX))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();

        verify(gameService,times(1)).sowStones(anyInt());
    }

    /**
     * The function tests that an InvalidPitIndexException is thrown when playing a game with an invalid pit index.
     */
    @Test
    void playGameWithInvalidPitIndex_Throw_InvalidPitIndexException() throws Exception {
        when(gameService.startGame(ArgumentMatchers.any(PlayersInfo.class))).thenThrow(InvalidPitIndexException.class);
        mockMvc.perform(MockMvcRequestBuilders
                        .post(Constants.GAME_URL + Constants.PLAY_GAME_API)
                        .content(GameTestUtil.asJsonString(INVALID_PIT_INDEX))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        verify(gameService,times(0)).sowStones(anyInt());
    }

}
