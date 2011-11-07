package org.jnape.dynamiccollection.operator;

import org.jnape.dynamiccollection.list.DynamicArrayList;
import org.junit.Test;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class ElementExcluderTest {

    @Test
    public void shouldConstruct() {
        new ElementExcluder();
    }

    @Test
    public void shouldExcludeSpecificElementsFromCollection() {
        ElementExcluder elementExcluder = new ElementExcluder();

        Collection<Integer> oneThroughFive = asList(1, 2, 3, 4, 5);
        Integer[] oneThreeFive = {1, 3, 5};

        assertEquals(new DynamicArrayList<Integer>(2, 4), elementExcluder.exclude(oneThroughFive, oneThreeFive));
    }

    @Test
    public void shouldHandleHavingNoValidExclusions() {
        ElementExcluder elementExcluder = new ElementExcluder();

        Collection<String> women = asList("Sally", "Samantha");
        String[] men = {"Bob", "Bill"};

        assertEquals(new DynamicArrayList<String>(women), elementExcluder.exclude(women, men));
    }
}
