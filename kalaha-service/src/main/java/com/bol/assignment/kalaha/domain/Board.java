package com.bol.assignment.kalaha.domain;

import com.bol.assignment.kalaha.utils.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * The `Board` class represents a game board with pits and provides methods to access the number of stones in each pit.
 */
@Component
public class Board {

    @Getter
    private int[] pits = new int[Constants.TOTAL_PIT_COUNT];

    @Setter
    @Getter
    @JsonIgnore
    private boolean filled = Boolean.FALSE;

    /**
     * The function returns the number of stones in a pit given its index.
     *
     * @param index The index parameter represents the index of the pit from which you want to retrieve the stone count.
     * @return The method is returning the value of the element at the specified index in the "pits" array.
     */
    public int getStoneCountByIndex(final int index) {
        return pits[index];
    }

}
