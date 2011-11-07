package org.jnape.dynamiccollection.list.operator;

import org.jnape.dynamiccollection.list.DynamicArrayList;
import org.junit.Test;
import testsupport.Item;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static testsupport.ItemFixture.*;

public class ConcatenatorTest {

    @Test
    public void shouldConstruct() {
        new Concatenator();
    }

    @Test
    public void shouldConcatenateTwoCollectionsAndReturnDynamicList() {
        Concatenator concatenator = new Concatenator();

        Collection<Item> a = asList(A);
        Collection<Item> bAndC = asList(B, C);
        Collection<Item> empty = new ArrayList<Item>();

        assertEquals(new DynamicArrayList<Item>(A, B, C), concatenator.concatenate(a, bAndC));
        assertEquals(new DynamicArrayList<Item>(B, C, A), concatenator.concatenate(bAndC, a));
        assertEquals(new DynamicArrayList<Item>(B, C), concatenator.concatenate(empty, bAndC));
        assertEquals(new DynamicArrayList<Item>(B, C), concatenator.concatenate(bAndC, empty));
    }
}
