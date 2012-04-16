package com.jnape.dynamiccollection.operation;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class JoinTest {

    @Test
    public void shouldJoinListOfStringsWithCombiner() {
        List<String> strings = asList("The", "rain", "in", "Spain");
        assertEquals("The rain in Spain", Join.join(strings, " "));

        List<String> beat = asList("one", "two", "three");
        assertEquals("one and a two and a three", Join.join(beat, " and a "));
    }

    @Test
    public void shouldJoinListOfAnyType() {
        List<?> increments = asList(1, 2d, 3.5f, "4");
        assertEquals("1 < 2.0 < 3.5 < 4", Join.join(increments, " < "));
    }
}
