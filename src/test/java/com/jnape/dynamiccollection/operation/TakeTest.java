package com.jnape.dynamiccollection.operation;


import org.junit.Test;

import static com.jnape.dynamiccollection.operation.Take.take;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class TakeTest {

    @Test
    public void shouldTakeItemsFromIterable() {
        Iterable<Integer> iterable = asList(1, 2, 3, 4, 5, 6);

        assertEquals(asList(1, 2, 3), take(3, iterable));
    }
}
