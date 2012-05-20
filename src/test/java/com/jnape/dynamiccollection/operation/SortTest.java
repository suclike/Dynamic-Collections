package com.jnape.dynamiccollection.operation;

import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class SortTest {

    @Test
    public void shouldSortListOfComparables() {
        List<Integer> numbers = asList(3, 1, 2, 5, 4);
        assertEquals(asList(1, 2, 3, 4, 5), Sort.sort(numbers));
    }

    @Test
    public void shouldUseDefensiveCopyAndLeaveOriginalListUntouched() {
        List<Character> letters = asList('b', 'd', 'c', 'a', 'e');
        Sort.sort(letters);
        assertEquals(asList('b', 'd', 'c', 'a', 'e'), letters);
    }
}
