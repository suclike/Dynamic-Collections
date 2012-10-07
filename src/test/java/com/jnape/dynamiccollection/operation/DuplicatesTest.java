package com.jnape.dynamiccollection.operation;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class DuplicatesTest {

    @Test
    public void shouldGetDuplicateElements() {
        assertEquals(asList(1, 4, 5), Duplicates.duplicates(asList(1, 1, 2, 3, 4, 4, 4, 5, 6, 5)));
    }
}
