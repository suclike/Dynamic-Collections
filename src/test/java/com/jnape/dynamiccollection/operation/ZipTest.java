package com.jnape.dynamiccollection.operation;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.jnape.dynamiccollection.list.DynamicArrayList.list;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@SuppressWarnings("unchecked")
public class ZipTest {

    @Test
    public void shouldZipJustOneList() {
        List<Integer> oneThroughFive = list(1, 2, 3, 4, 5);

        assertEquals(list(
                list(1),
                list(2),
                list(3),
                list(4),
                list(5)
        ), Zip.zip(oneThroughFive));
    }

    @Test
    public void shouldZipTwoListsOfSameSize() {
        List<Integer> odds = asList(1, 3);
        List<Integer> evens = asList(2, 4);

        assertEquals(list(
                list(1, 2),
                list(3, 4)
        ), Zip.zip(odds, evens));
    }

    @Test
    public void shouldZipTwoListsOfDifferentSizesNotExceedingElementsOfSmallestList() {
        List<Character> abc = asList('a', 'b', 'c');
        List<Character> oneTwo = asList('1', '2');

        assertEquals(list(list('a', '1'), list('b', '2')), Zip.zip(abc, oneTwo));
    }

    @Test
    public void shouldZipThreeOrMoreLists() {
        List<Object> odds = Arrays.<Object>asList(1, 3, 5);
        List<Object> evens = Arrays.<Object>asList(2, 4, 6);
        List<Object> abc = Arrays.<Object>asList('a', 'b', 'c');

        assertEquals(list(
                list(1, 2, 'a'),
                list(3, 4, 'b'),
                list(5, 6, 'c')
        ), Zip.zip(odds, evens, abc));
    }
}
