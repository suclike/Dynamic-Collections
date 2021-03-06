package com.jnape.dynamiccollection.operation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.jnape.dynamiccollection.list.factory.DynamicListFactory.list;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;

@SuppressWarnings("unchecked")
public class InGroupsOfTest {

    @Test
    public void shouldGetEvenlyDistributedListOfGroups() {
        List<Integer> oneAndTwo = asList(1, 2);
        List<Integer> threeAndFour = asList(3, 4);
        List<Integer> fiveAndSix = asList(5, 6);

        List<Integer> oneThroughSix = new ArrayList<Integer>();
        oneThroughSix.addAll(oneAndTwo);
        oneThroughSix.addAll(threeAndFour);
        oneThroughSix.addAll(fiveAndSix);

        assertEquals(list(oneAndTwo, threeAndFour, fiveAndSix), InGroupsOf.inGroupsOf(oneThroughSix, 2));
    }

    @Test
    public void shouldGetNonFullGroupIfTotalElementCountNotEvenlyDivisibleByElementsPerGroup() {
        List<Integer> oneTwoThree = asList(1, 2, 3);
        List<Integer> fourFiveSix = asList(4, 5, 6);
        List<Integer> seven = asList(7);

        List<Integer> oneThroughSeven = new ArrayList<Integer>();
        oneThroughSeven.addAll(oneTwoThree);
        oneThroughSeven.addAll(fourFiveSix);
        oneThroughSeven.addAll(seven);

        assertEquals(list(oneTwoThree, fourFiveSix, seven), InGroupsOf.inGroupsOf(oneThroughSeven, 3));
    }

    @Test
    public void shouldGetSingleListOfAllElementsIfElementsPerGroupGreaterThanTotalElements() {
        List<Integer> oneThroughFive = asList(1, 2, 3, 4, 5);

        List<List<Integer>> expected = new ArrayList<List<Integer>>();
        expected.add(oneThroughFive);

        assertEquals(expected, InGroupsOf.inGroupsOf(oneThroughFive, 6));
    }

    @Test
    public void shouldGetEmptyListIfZeroElements() {
        assertEquals(new ArrayList<List<Object>>(), InGroupsOf.inGroupsOf(emptyList(), 2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfElementsPerGroupLessThanOne() {
        InGroupsOf.inGroupsOf(new ArrayList<Object>(), -12);
    }
}
