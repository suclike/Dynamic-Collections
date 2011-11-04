package org.jnape.dynamiccollection.list.operation;

import org.jnape.dynamiccollection.list.DynamicArrayList;
import org.junit.Test;
import testsupport.Item;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static testsupport.ItemFixture.*;

public class ConcatenationTest {

    @Test
    public void shouldConstruct() {
        new Concatenation();
    }

    @Test
    public void shouldConcatenateTwoCollectionsAndReturnDynamicList() {
        Concatenation concatenation = new Concatenation();

        Collection<Item> a = asList(A);
        Collection<Item> bAndC = asList(B, C);
        Collection<Item> empty = new ArrayList<Item>();

        assertEquals(new DynamicArrayList<Item>(A, B, C), concatenation.execute(a, bAndC));
        assertEquals(new DynamicArrayList<Item>(B, C, A), concatenation.execute(bAndC, a));
        assertEquals(new DynamicArrayList<Item>(B, C), concatenation.execute(empty, bAndC));
        assertEquals(new DynamicArrayList<Item>(B, C), concatenation.execute(bAndC, empty));
    }
}
