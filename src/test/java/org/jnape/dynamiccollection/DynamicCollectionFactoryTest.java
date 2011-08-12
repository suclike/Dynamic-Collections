package org.jnape.dynamiccollection;

import org.jnape.dynamiccollection.list.DynamicArrayList;
import org.junit.Test;
import testsupport.Item;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class DynamicCollectionFactoryTest {

    private static final Item A = new Item();
    private static final Item B = new Item();
    private static final Item C = new Item();

    private static final Collection<Character> LETTERS = asList('A', 'B', 'C');
    private static final Collection<Integer> NUMBERS = asList(1, 2, 3);
    private static final Collection<Item> ITEMS = asList(A, B, C);

    @Test
    public void shouldProvideDynamicCollectionImplementationForCollection() {
        assertEquals(new DynamicArrayList<Character>(LETTERS), DynamicCollectionFactory.with(LETTERS));
        assertEquals(new DynamicArrayList<Integer>(NUMBERS), DynamicCollectionFactory.with(NUMBERS));
        assertEquals(new DynamicArrayList<Item>(ITEMS), DynamicCollectionFactory.with(ITEMS));
    }

    @Test
    public void shouldProvideDynamicListWithoutArguments() {
        assertEquals(new DynamicArrayList(), DynamicCollectionFactory.list());
    }

    @Test
    public void shouldProvideDynamicListFromCollection() {
        assertEquals(new DynamicArrayList<Character>(LETTERS), DynamicCollectionFactory.list(LETTERS));
        assertEquals(new DynamicArrayList<Integer>(NUMBERS), DynamicCollectionFactory.list(NUMBERS));
        assertEquals(new DynamicArrayList<Item>(ITEMS), DynamicCollectionFactory.list(ITEMS));
    }

    @Test
    public void shouldProvideDynamicListFromArray() {
        Item[] items = new Item[]{A, B, C};
        assertEquals(new DynamicArrayList<Item>(items), DynamicCollectionFactory.list(items));

        Character[] letters = new Character[]{'A', 'B', 'C'};
        assertEquals(new DynamicArrayList<Character>(letters), DynamicCollectionFactory.list(letters));

        Integer[] numbers = new Integer[]{1, 2, 3};
        assertEquals(new DynamicArrayList<Integer>(numbers), DynamicCollectionFactory.list(numbers));
    }

    @Test
    public void shouldProvideDynamicListFromVarArgs() {
        assertEquals(new DynamicArrayList<Integer>(1, 2, 3), DynamicCollectionFactory.list(1, 2, 3));
        assertEquals(new DynamicArrayList<Character>('A', 'B', 'C'), DynamicCollectionFactory.list('A', 'B', 'C'));
        assertEquals(new DynamicArrayList<Item>(A, B, C), DynamicCollectionFactory.list(A, B, C));
    }
}
