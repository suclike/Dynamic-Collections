package com.jnape.dynamiccollection.operation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@SuppressWarnings("unchecked")
public class PermuteTest {

    @Test
    public void shouldPermuteEmptyList() {
        assertEquals(new ArrayList<List<Object>>(), Permute.permutations(new ArrayList<Object>()));
    }

    @Test
    public void shouldPermuteSingleElementList() {
        assertEquals(asList(asList(1)), Permute.permutations(asList(1)));
    }

    @Test
    public void shouldPermuteAnyNumberOfElements() {
        List<Integer> oneTwoThree = asList(1, 2, 3);

        assertEquals(asList(
                asList(1, 2, 3),
                asList(1, 3, 2),
                asList(2, 1, 3),
                asList(2, 3, 1),
                asList(3, 2, 1),
                asList(3, 1, 2)
        ), Permute.permutations(oneTwoThree));
    }

    @Test
    public void shouldPermuteDuplicateItemsAsIfTheyWereUnique() {
        List<Integer> oneOne = asList(1, 1);

        assertEquals(asList(
                asList(1, 1),
                asList(1, 1)
        ), Permute.permutations(oneOne));
    }
}
