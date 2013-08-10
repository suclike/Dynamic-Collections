package com.jnape.dynamiccollection.operation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class AllSequencesOfTest {

    @Test
    public void shouldHave2SequencesOf3ElementsFor4ElementList() {
        List<Integer> fourNumbers = asList(1, 2, 3, 4);

        List<List<Integer>> expected = new ArrayList<List<Integer>>();
        expected.add(asList(1, 2, 3));
        expected.add(asList(2, 3, 4));

        assertEquals(expected, AllSequencesOf.allSequencesOf(3, fourNumbers));
    }

    @Test
    public void shouldHandleCaseWhereNEqualsListSize() {
        final List<Integer> list = asList(1, 2, 3, 4, 5);
        List<List<Integer>> expected = new ArrayList<List<Integer>>() {{
            add(list);
        }};

        assertEquals(expected, AllSequencesOf.allSequencesOf(5, list));
    }

    @Test
    public void shouldHaveNoSequencesIfNIsGreaterThanListSize() {
        List<String> emptyList = new ArrayList<String>();

        assertEquals(new ArrayList<List<String>>(), AllSequencesOf.allSequencesOf(1, emptyList));
    }
}
