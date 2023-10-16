package com.bol.assignment.kalaha.validator;

import com.bol.assignment.kalaha.domain.PlayersInfo;
import com.bol.assignment.kalaha.exception.impl.PlayersInfoNullOrEmptyException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static com.bol.assignment.kalaha.utils.Constants.PLAYERS_INFO_IS_NULL_OR_PLAYERS_NAME_IS_EMPTY;

/**
 * The PlayerValidator class checks if the PlayersInfo object is valid by ensuring it is not null and that the player names
 * are not empty.
 */
@Service
public class PlayerValidator {

    private PlayerValidator(){}

    /**
     * The function checks if the PlayersInfo object is null or if the player names are empty, and throws an exception if
     * either condition is true.
     *
     * @param playersInfo The parameter "playersInfo" is an object of type "PlayersInfo".
     */
    public static void isValidPlayerInfo(PlayersInfo playersInfo){
        if(Objects.isNull(playersInfo) || StringUtils.isEmpty(playersInfo.getPlayerAName())
                || StringUtils.isEmpty(playersInfo.getPlayerBName())){
            throw new PlayersInfoNullOrEmptyException(PLAYERS_INFO_IS_NULL_OR_PLAYERS_NAME_IS_EMPTY);
        }
    }

}
