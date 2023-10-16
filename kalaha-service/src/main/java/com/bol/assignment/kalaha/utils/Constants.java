package com.bol.assignment.kalaha.utils;

/**
 * The Constants class contains various constant values used in a game.
 */
public final class Constants {

    private Constants(){}

    public static final int TOTAL_PIT_COUNT = 14;

    public static final int BIG_PIT_INDEX_PLAYER_A = 13;

    public static final int BIG_PIT_INDEX_PLAYER_B = 6;

    public static final int STONES_PER_PIT = 6;

    public static final int PITS_PER_PLAYER = 6;

    public static final String GAME_URL = "/api/game";

    public static final String START_GAME_API = "/create";

    public static final String PLAY_GAME_API = "/play";

    public static final String PLAYERS_INFO_IS_NULL_OR_PLAYERS_NAME_IS_EMPTY = "The given players info is null or players name is empty";

    public static final String INVALID_GAME = "The given game is invalid";

    public static final String INVALID_PIT_INDEX = "The given pit index is invalid";

}
