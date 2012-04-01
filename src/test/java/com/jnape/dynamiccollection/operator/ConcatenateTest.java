package com.jnape.dynamiccollection.operator;

import com.jnape.dynamiccollection.list.DynamicArrayList;
import org.junit.Test;
import testsupport.Item;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static testsupport.ItemFixture.*;

public class ConcatenateTest {

    @Test
    public void shouldConcatenateTwoCollectionsAndReturnDynamicList() {
        Collection<Item> a = asList(A);
        Collection<Item> bAndC = asList(B, C);
        Collection<Item> empty = new ArrayList<Item>();

        assertEquals(new DynamicArrayList<Item>(A, B, C), Concatenate.concatenate(a, bAndC));
        assertEquals(new DynamicArrayList<Item>(B, C, A), Concatenate.concatenate(bAndC, a));
        assertEquals(new DynamicArrayList<Item>(B, C), Concatenate.concatenate(empty, bAndC));
        assertEquals(new DynamicArrayList<Item>(B, C), Concatenate.concatenate(bAndC, empty));
    }
}
