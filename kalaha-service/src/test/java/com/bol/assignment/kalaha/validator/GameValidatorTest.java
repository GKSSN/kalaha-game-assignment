package com.bol.assignment.kalaha.validator;

import com.bol.assignment.kalaha.domain.Game;
import com.bol.assignment.kalaha.exception.impl.InvalidPitIndexException;
import com.bol.assignment.kalaha.util.GameTestUtil;
import org.junit.jupiter.api.Test;

import static com.bol.assignment.kalaha.utils.Constants.INVALID_PIT_INDEX;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Jayabalan
 * @project kalaha-service
 */
class GameValidatorTest {

    @Test
    void validateInvalidCurrentIndex_Throw_InvalidPitIndex(){
        Game game = GameTestUtil.getValidGameInput();
        InvalidPitIndexException exp = assertThrows(InvalidPitIndexException.class,
                ()->GameValidator.isValidCurrentIndex(game,-2));
        assertEquals(INVALID_PIT_INDEX, exp.getMessage());
    }
}
