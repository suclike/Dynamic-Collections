package com.jnape.dynamiccollection.operation;

import org.junit.Test;
import testsupport.Item;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static testsupport.ItemFixture.*;

public class ConcatenateTest {

    @Test
    public void shouldConcatenateTwoCollections() {
        Collection<Item> a = asList(A);
        Collection<Item> bAndC = asList(B, C);
        Collection<Item> empty = new ArrayList<Item>();

        assertEquals(asList(A, B, C), Concatenate.concatenate(a, bAndC));
        assertEquals(asList(B, C, A), Concatenate.concatenate(bAndC, a));
        assertEquals(asList(B, C), Concatenate.concatenate(empty, bAndC));
        assertEquals(asList(B, C), Concatenate.concatenate(bAndC, empty));
    }
}
