package org.jnape.dynamiccollection.set;

import org.junit.Test;
import testsupport.Item;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static testsupport.ItemFixture.*;

@SuppressWarnings({"MismatchedQueryAndUpdateOfCollection"})
public class DynamicHashSetTest {

    @Test
    public void shouldConstructWithoutArgs() {
        DynamicHashSet dynamicHashSet = new DynamicHashSet();
        assertEquals(dynamicHashSet.size(), 0);
    }

    @Test
    public void shouldConstructAndPopulateFromCollection() {
        Collection<Item> items = asList(A, B, C);
        DynamicHashSet<Item> dynamicHashSet = new DynamicHashSet<Item>(items);

        assertEquals(3, dynamicHashSet.size());
        assertTrue(dynamicHashSet.contains(A));
        assertTrue(dynamicHashSet.contains(B));
        assertTrue(dynamicHashSet.contains(C));
    }

    @Test
    public void shouldConstructAndPopulateFromArray() {
        Item[] items = new Item[]{B, C};
        DynamicHashSet<Item> dynamicHashSet = new DynamicHashSet<Item>(items);

        assertEquals(2, dynamicHashSet.size());
        assertTrue(dynamicHashSet.contains(B));
        assertTrue(dynamicHashSet.contains(C));
    }

    @Test
    public void shouldConstructAndPopulateFromVarArgs() {
        DynamicHashSet<Item> dynamicHashSet = new DynamicHashSet<Item>(C, B, A);

        assertEquals(3, dynamicHashSet.size());
        assertTrue(dynamicHashSet.contains(C));
        assertTrue(dynamicHashSet.contains(B));
        assertTrue(dynamicHashSet.contains(A));
    }
}
