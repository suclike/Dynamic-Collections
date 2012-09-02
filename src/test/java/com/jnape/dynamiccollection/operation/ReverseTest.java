package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.list.DynamicList;
import org.junit.Test;

import java.util.List;

import static com.jnape.dynamiccollection.list.DynamicArrayList.list;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

public class ReverseTest {

    @Test
    public void shouldReverseList() {
        List<String> abc = asList("a", "b", "c");
        assertEquals(list("c", "b", "a"), Reverse.reverse(abc));

        List<Integer> oneTwoThree = asList(1, 2, 3);
        assertEquals(list(3, 2, 1), Reverse.reverse(oneTwoThree));
    }

    @Test
    public void shouldNotTouchInputList() {
        DynamicList<Character> input = list('a', 'b', 'c');
        assertNotSame(input, Reverse.reverse(input));
    }
}
