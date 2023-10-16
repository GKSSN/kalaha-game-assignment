package com.bol.assignment.kalaha.rules.impl;

import com.bol.assignment.kalaha.domain.Game;
import com.bol.assignment.kalaha.domain.Player;
import com.bol.assignment.kalaha.rules.GameProcessor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static com.bol.assignment.kalaha.utils.Constants.PITS_PER_PLAYER;

/**
 * The `DeclareWinner` class is a Java service that implements the `GameProcessor` interface and is responsible for
 * determining the winner of a game based on the current state of the game board.
 */
@Service
public class DeclareWinner implements GameProcessor {

    /**
     * The function checks if a game is completed and sets the winner if it is.
     *
     * @param game The "game" parameter is an instance of the Game class.
     */
    @Override
    public void execute(final Game game) {
        if (!game.isCompleted()) {
            boolean gameCompleted = isGameCompleted(game);
            if (gameCompleted) {
                setWinner(game);
                game.setCompleted(Boolean.TRUE);
                game.setStarted(Boolean.FALSE);
            }
        }
    }

    /**
     * The function checks if the game is completed by determining if either player has empty pits and placing remaining
     * stones in the opponent's big pit.
     *
     * @param game The "game" parameter is an instance of the Game class.
     * @return The method is returning a boolean value indicating whether the game is completed or not.
     */
    public boolean isGameCompleted(final Game game) {

        boolean gameCompleted = Boolean.FALSE;

        List<Integer> sortedBigPitIndexes = game.getPlayerList().stream().map(Player::getBigPitIndex).sorted().collect(Collectors.toList());

        Integer playerABigPitIndex = sortedBigPitIndexes.get(0);
        Integer playerBBigPitIndex = sortedBigPitIndexes.get(1);

        boolean playerAHasEmptyPits = checkPlayerHasEmptyPits(game, playerABigPitIndex);

        if (playerAHasEmptyPits) {
            placeRemainingStonesToOpponentBigPit(game, playerBBigPitIndex);
            gameCompleted = Boolean.TRUE;
        } else {
            boolean isPlayerBHasEmptyHouses = checkPlayerHasEmptyPits(game, playerBBigPitIndex);
            if (isPlayerBHasEmptyHouses) {
                placeRemainingStonesToOpponentBigPit(game, playerABigPitIndex);
                gameCompleted = Boolean.TRUE;
            }
        }
        return gameCompleted;
    }

    /**
     * The function checks if a player has any empty pits.
     *
     * @param game The "game" parameter is an instance of the Game class. It represents the current state of the game being
     * played.
     * @param bigPitIndex The `bigPitIndex` parameter represents the index of the big pit in the game board.
     * @return The method is returning a boolean value.
     */
    private boolean checkPlayerHasEmptyPits(final Game game, final Integer bigPitIndex) {
        int[] playerPits = Arrays.copyOfRange(game.getBoard().getPits(), bigPitIndex - PITS_PER_PLAYER, bigPitIndex);
        return Arrays.stream(playerPits).sum() == 0;
    }


    /**
     * The function places the remaining stones from a player's pits into their big pit.
     *
     * @param game The "game" parameter is an object of the Game class. It represents the current state of the game,
     * including the board and the players.
     * @param playerWithNonEmptyPitsBigPitIndex The playerWithNonEmptyPitsBigPitIndex parameter represents the index of the
     * big pit belonging to the player who still has non-empty pits.
     */
    private void placeRemainingStonesToOpponentBigPit(final Game game, final Integer playerWithNonEmptyPitsBigPitIndex) {
        int[] pits = game.getBoard().getPits();

        int[] playerPits = Arrays.copyOfRange(pits, playerWithNonEmptyPitsBigPitIndex - PITS_PER_PLAYER, playerWithNonEmptyPitsBigPitIndex);

        int sumOfStones = Arrays.stream(playerPits).sum();
        pits[playerWithNonEmptyPitsBigPitIndex] += sumOfStones;

        cleanUpPits(playerWithNonEmptyPitsBigPitIndex, pits);
    }

    /**
     * The function cleanUpPits sets all the pits of a player to 0, starting from the index of the player's big pit minus
     * the number of pits per player.
     *
     * @param playerWithNonEmptyPitsBigPitIndex The playerWithNonEmptyPitsBigPitIndex parameter represents the index of the
     * big pit for the player who has non-empty pits.
     * @param pits An array representing the pits in the game. Each element in the array represents the number of stones in
     * a pit.
     */
    private void cleanUpPits(final Integer playerWithNonEmptyPitsBigPitIndex, final int[] pits) {
        for (int i = playerWithNonEmptyPitsBigPitIndex - PITS_PER_PLAYER; i < playerWithNonEmptyPitsBigPitIndex; i++) {
            pits[i] = 0;
        }
    }

    /**
     * The function sets the winner of a game based on the number of stones in each player's big pit.
     *
     * @param game The "game" parameter is an instance of the Game class.
     */
    private void setWinner(final Game game) {
        TreeMap<Integer, String> storeValue2PlayerName = game.getPlayerList().stream()
                .collect(Collectors.toMap(player -> game.getBoard().getStoneCountByIndex(player.getBigPitIndex()),
                        Player::getName,
                        (a, b) -> a, TreeMap::new));

        if (storeValue2PlayerName.size() == 1) {
            game.setWinner("Game Tied");

        } else {
            String winner = storeValue2PlayerName.pollLastEntry().getValue();
            game.setWinner(winner);
        }
    }

    /**
     * The function getOrder() returns the order of a specific item.
     *
     * @return The method is returning the integer value 4.
     */
    @Override
    public int getOrder() {
        return 4;
    }
}
