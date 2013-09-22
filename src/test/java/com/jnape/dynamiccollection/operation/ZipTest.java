package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.datatype.tuple.Tuple2;
import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.jnape.dynamiccollection.list.factory.DynamicListFactory.list;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@SuppressWarnings("unchecked")
public class ZipTest {

    @Test
    public void shouldZipJustOneList() {
        List<Integer> oneThroughFive = list(1, 2, 3, 4, 5);

        assertEquals(asList(
                asList(1),
                asList(2),
                asList(3),
                asList(4),
                asList(5)
        ), Zip.zip(oneThroughFive));
    }

    @Test
    public void shouldZipTwoListsOfSameSize() {
        List<Integer> odds = asList(1, 3);
        List<Integer> evens = asList(2, 4);

        assertEquals(asList(
                asList(1, 2),
                asList(3, 4)
        ), Zip.zip(odds, evens));
    }

    @Test
    public void shouldZipTwoListsOfDifferentSizesNotExceedingElementsOfSmallestList() {
        List<Character> abc = asList('a', 'b', 'c');
        List<Character> oneTwo = asList('1', '2');

        assertEquals(asList(asList('a', '1'), list('b', '2')), Zip.zip(abc, oneTwo));
    }

    @Test
    public void shouldZipThreeOrMoreLists() {
        List<Object> odds = Arrays.<Object>asList(1, 3, 5);
        List<Object> evens = Arrays.<Object>asList(2, 4, 6);
        List<Object> abc = Arrays.<Object>asList('a', 'b', 'c');

        assertEquals(asList(
                Arrays.<Object>asList(1, 2, 'a'),
                Arrays.<Object>asList(3, 4, 'b'),
                Arrays.<Object>asList(5, 6, 'c')
        ), Zip.zip(odds, evens, abc));
    }

    @Test
    public void shouldZipListsWithZipperFunction() {
        List<Integer> odds = asList(1, 3, 5);
        List<Integer> evens = asList(2, 4, 6);

        MonadicFunction<Tuple2<Integer, Integer>, Integer> zipper = new MonadicFunction<Tuple2<Integer, Integer>, Integer>() {
            @Override
            public Integer apply(Tuple2<Integer, Integer> tuple) {
                return tuple._1 + tuple._2;
            }
        };

        assertEquals(asList(3, 7, 11), Zip.zipWith(zipper, odds, evens));
    }
}
