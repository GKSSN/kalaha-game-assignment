package com.bol.assignment.kalaha.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * The Player class is a Java bean with a name and bigPitIndex property.
 */

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@Component
public class Player {

    private String name;

    private int bigPitIndex;

    public Player(final String name, final int bigPitIndex) {
        this.name = name;
        this.bigPitIndex = bigPitIndex;
    }
}
