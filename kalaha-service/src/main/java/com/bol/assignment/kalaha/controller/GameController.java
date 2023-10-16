package com.bol.assignment.kalaha.controller;

import com.bol.assignment.kalaha.domain.Game;
import com.bol.assignment.kalaha.domain.PlayersInfo;
import com.bol.assignment.kalaha.service.GameService;
import com.bol.assignment.kalaha.utils.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The GameController class is used to control the game logic and flow.
 */

@RestController
@RequestMapping(Constants.GAME_URL)
@CrossOrigin(origins = "http://localhost:4200")
public class GameController {

    private GameService gameService;

    public GameController(final GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * The startGame function in a Java controller class starts a game using the provided player information and returns
     * the created game object.
     *
     * @param playersInfo The playersInfo parameter is an object of type PlayersInfo, which is annotated with @RequestBody
     * to indicate that it will be received as a request body in the HTTP POST request. The @Valid annotation is used to
     * validate the playersInfo object based on any validation constraints defined in the PlayersInfo class.
     * @return The method is returning a ResponseEntity object containing a Game object and an HTTP status code of OK.
     */
    @PostMapping(path = Constants.START_GAME_API)
    @Operation(summary = "To Create the game from player information", description = "Game should started")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Game has created"),
            @ApiResponse(responseCode = "400", description = "Missing information in the request")})
    public ResponseEntity<Game> startGame(@RequestBody PlayersInfo playersInfo) {
        return new ResponseEntity<>(gameService.startGame(playersInfo), HttpStatus.OK);
    }

    /**
     * The function `sowStones` is a POST request handler that takes an integer parameter `currentIndex`, validates it, and
     * returns a ResponseEntity containing the result of the `sowStones` method from the `gameService` object.
     *
     * @param currentIndex The currentIndex parameter is an Integer that represents the index of the pit from which the
     * stones will be sown in the game.
     * @return The method is returning a ResponseEntity object containing a Game object and an HTTP status code.
     */
    @Operation(summary = "Receive current index to sow stones", description = "Sown stones success")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sown stones success"),
            @ApiResponse(responseCode = "400", description = "Missing information in the request")})
    @PostMapping(path = Constants.PLAY_GAME_API)
    public ResponseEntity<Game> sowStones(@RequestParam Integer currentIndex) {
        return new ResponseEntity<>(gameService.sowStones(currentIndex), HttpStatus.OK);
    }
}
