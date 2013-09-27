package com.jnape.dynamiccollection.operation;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItems;

public class ShuffleTest {

    @Test
    public void shouldShuffleElements() {
        List<Integer> numbers = asList(1, 2, 3, 4, 5);
        List<Integer> shuffled = Shuffle.shuffle(numbers);

        assertThat(shuffled, hasItems(1, 2, 3, 4, 5));
        assertFalse(shuffled.equals(numbers));
    }
}
