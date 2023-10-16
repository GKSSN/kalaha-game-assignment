package com.bol.assignment.kalaha.validator;

import com.bol.assignment.kalaha.exception.impl.PlayersInfoNullOrEmptyException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.bol.assignment.kalaha.utils.Constants.PLAYERS_INFO_IS_NULL_OR_PLAYERS_NAME_IS_EMPTY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Jayabalan
 * @project kalaha-service
 */
@ExtendWith(MockitoExtension.class)
class PlayerValidatorTest {

    @Test
    void validatePlayersInfo_Throw_PlayersInfoNullOrEmpty(){
        PlayersInfoNullOrEmptyException exp = assertThrows(PlayersInfoNullOrEmptyException.class,
                ()->PlayerValidator.isValidPlayerInfo(null));
        assertEquals(PLAYERS_INFO_IS_NULL_OR_PLAYERS_NAME_IS_EMPTY, exp.getMessage());
    }

}
