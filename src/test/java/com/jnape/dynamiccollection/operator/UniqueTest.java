package com.jnape.dynamiccollection.operator;

import com.jnape.dynamiccollection.DynamicCollection;
import com.jnape.dynamiccollection.list.DynamicArrayList;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UniqueTest {

    @Test
    public void shouldRemoveNonUniqueElements() {
        DynamicCollection<Integer> numbers = new DynamicArrayList<Integer>(1, 2, 2, 3, 4, 5, 5);
        assertEquals(new DynamicArrayList<Integer>(1, 2, 3, 4, 5), Unique.unique(numbers));

        DynamicCollection<String> strings = new DynamicArrayList<String>("a", "a", "b", "c", "d", "c");
        assertEquals(new DynamicArrayList<String>("a", "b", "c", "d"), Unique.unique(strings));
    }
}
