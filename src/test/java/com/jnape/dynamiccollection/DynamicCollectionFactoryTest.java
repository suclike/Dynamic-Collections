package com.jnape.dynamiccollection;

import com.jnape.dynamiccollection.list.DynamicArrayList;
import org.junit.Test;
import testsupport.Item;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.HashSet;
import java.util.Queue;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static testsupport.ItemFixture.*;

public class DynamicCollectionFactoryTest {

    private static final Collection<Character> LETTERS = asList('A', 'B', 'C');
    private static final Collection<Integer> NUMBERS = new HashSet<Integer>() {{
        add(1);
        add(2);
        add(3);
    }};

    @Test
    public void shouldProvideDynamicCollectionImplementationForCollection() {
        assertEquals(new DynamicArrayList<Character>(LETTERS), DynamicCollectionFactory.with(LETTERS));
    }

    @Test
    public void shouldDefaultToDynamicListImplementationForCollectionWithoutExplicitDynamicCounterpart() {
        Queue<Item> queue = new ArrayDeque<Item>();
        queue.add(A);
        queue.add(B);
        queue.add(C);

        assertEquals(new DynamicArrayList<Item>(A, B, C), DynamicCollectionFactory.with(queue));
    }

    @Test
    public void shouldProvideDynamicListWithoutArguments() {
        assertEquals(new DynamicArrayList(), DynamicCollectionFactory.list());
    }

    @Test
    public void shouldProvideDynamicListFromCollection() {
        assertEquals(new DynamicArrayList<Character>(LETTERS), DynamicCollectionFactory.list(LETTERS));
        assertEquals(new DynamicArrayList<Integer>(NUMBERS), DynamicCollectionFactory.list(NUMBERS));
    }

    @Test
    public void shouldProvideDynamicListFromArray() {
        Character[] letters = new Character[]{'A', 'B', 'C'};
        assertEquals(new DynamicArrayList<Character>(letters), DynamicCollectionFactory.list(letters));

        Integer[] numbers = new Integer[]{1, 2, 3};
        assertEquals(new DynamicArrayList<Integer>(numbers), DynamicCollectionFactory.list(numbers));
    }

    @Test
    public void shouldProvideDynamicListFromVarArgs() {
        assertEquals(new DynamicArrayList<Integer>(1, 2, 3), DynamicCollectionFactory.list(1, 2, 3));
        assertEquals(new DynamicArrayList<Character>('A', 'B', 'C'), DynamicCollectionFactory.list('A', 'B', 'C'));
    }
}
