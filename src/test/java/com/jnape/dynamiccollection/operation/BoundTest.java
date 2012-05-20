package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.operation.exception.OperationRequiresAtLeastOneElementException;
import org.junit.Test;

import java.util.Collection;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

public class BoundTest {

    @Test
    public void shouldGetMinElement() {
        Collection<Integer> numbers = asList(1, 2, 3, 4, 5, 6);

        assertEquals((Integer) 1, Bound.min(numbers));
    }

    @Test
    public void shouldGetMaxElement() {
        Collection<Float> floats = asList(
                .1f,
                1f,
                10f,
                100f
        );

        assertEquals((Float) 100f, Bound.max(floats));
    }

    @Test(expected = OperationRequiresAtLeastOneElementException.class)
    public void shouldFailIfMinOnEmptyCollection() {
        Collection<String> empty = emptyList();
        Bound.min(empty);
    }

    @Test(expected = OperationRequiresAtLeastOneElementException.class)
    public void shouldFailIfMaxOnEmptyCollection() {
        Collection<String> empty = emptyList();
        Bound.max(empty);
    }
}
