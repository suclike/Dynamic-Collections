package com.jnape.dynamiccollection.operation;

import org.junit.Test;

import java.util.Collection;

import static com.jnape.dynamiccollection.list.DynamicArrayList.list;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class WithoutTest {

    @Test
    public void shouldExcludeSpecificElementsFromCollection() {
        Collection<Integer> oneThroughFive = asList(1, 2, 3, 4, 5);
        assertEquals(asList(2, 4), Without.without(oneThroughFive, list(1, 3, 5)));
    }

    @Test
    public void shouldRemoveDuplicatesOfExclusion() {
        Collection<Integer> oneThroughTenWithDuplicates = asList(1, 2, 3, 3, 3, 4, 5, 5, 6, 7, 7, 8, 9, 10);

        assertEquals(
                asList(1, 2, 4, 6, 8, 9, 10),
                Without.without(oneThroughTenWithDuplicates, list(3, 5, 7))
        );
    }

    @Test
    public void shouldHandleHavingNoValidExclusions() {
        Collection<String> women = asList("Sally", "Samantha");
        assertEquals(women, Without.without(women, list("Bob", "Bill")));
    }

    @Test
    public void shouldHandleNull() {
        Collection<String> withNulls = asList("with nulls", null, null, null);
        assertEquals(asList("with nulls"), Without.without(withNulls, list(new Object[]{null})));
    }
}
