package com.jnape.dynamiccollection.operation;

import org.junit.Test;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class UniqueTest {

    @Test
    public void shouldRemoveNonUniqueElements() {
        Collection<Integer> numbers = asList(1, 1, 1, 2, 2, 3, 4, 5, 5);
        assertEquals(asList(1, 2, 3, 4, 5), Unique.unique(numbers));
    }
}
