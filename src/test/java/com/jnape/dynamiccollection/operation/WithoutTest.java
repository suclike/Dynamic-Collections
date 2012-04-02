package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.list.DynamicArrayList;
import org.junit.Test;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class WithoutTest {

    @Test
    public void shouldExcludeSpecificElementsFromCollection() {
        Collection<Integer> oneThroughFive = asList(1, 2, 3, 4, 5);
        Integer[] oneThreeFive = {1, 3, 5};

        assertEquals(new DynamicArrayList<Integer>(2, 4), Without.without(oneThroughFive, oneThreeFive));
    }

    @Test
    public void shouldHandleHavingNoValidExclusions() {
        Collection<String> women = asList("Sally", "Samantha");
        String[] men = {"Bob", "Bill"};

        assertEquals(new DynamicArrayList<String>(women), Without.without(women, men));
    }
}
