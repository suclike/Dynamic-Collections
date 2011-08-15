package org.jnape.dynamiccollection.set;

import org.jnape.dynamiccollection.DynamicCollection;
import org.junit.Test;
import testsupport.Item;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

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

    @Test
    public void shouldPolymorphToDynamicCollection() {
        DynamicCollection dynamicCollection = new DynamicHashSet();
    }

    @Test
    public void shouldPolymorphToDynamicSet() {
        DynamicSet dynamicSet = new DynamicHashSet();
    }

    @Test
    public void shouldPolymorphToCollection() {
        Collection collection = new DynamicHashSet();
    }

    @Test
    public void shouldPolymorphToSet() {
        Set set = new DynamicHashSet();
    }

    @Test
    public void shouldPolymorphToHashSet() {
        HashSet hashSet = new DynamicHashSet();
    }

    @Test
    public void shouldConcatenateAnotherCollectionAfterLastElement() {
        DynamicHashSet<Item> a = new DynamicHashSet<Item>(A);
        DynamicHashSet<Item> bAndC = new DynamicHashSet<Item>(B, C);
        DynamicHashSet<Item> empty = new DynamicHashSet<Item>();

        assertEquals(new DynamicHashSet<Item>(A, B, C), a.concat(bAndC));
        assertEquals(new DynamicHashSet<Item>(B, C, A), bAndC.concat(a));
        assertEquals(new DynamicHashSet<Item>(A), empty.concat(a));
        assertEquals(new DynamicHashSet<Item>(B, C), bAndC.concat(empty));
    }
}
