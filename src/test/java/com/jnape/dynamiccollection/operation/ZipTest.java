package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.datatype.tuple.Tuple2;
import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;
import org.junit.Test;

import java.util.List;

import static com.jnape.dynamiccollection.datatype.tuple.TupleFactory.tuple;
import static com.jnape.dynamiccollection.list.factory.DynamicListFactory.list;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

@SuppressWarnings("unchecked")
public class ZipTest {

    @Test
    public void shouldZipTwoListsTogether() {
        List<Integer> odds = list(1, 3, 5);
        List<Integer> evens = list(2, 4, 6);

        assertEquals(asList(
                tuple(1, 2),
                tuple(3, 4),
                tuple(5, 6)
        ), Zip.zip(odds, evens));
    }

    @Test
    public void shouldZipTwoListsOfSameSize() {
        List<Integer> odds = asList(1, 3);
        List<Integer> evens = asList(2, 4);

        assertEquals(asList(
                tuple(1, 2),
                tuple(3, 4)
        ), Zip.zip(odds, evens));
    }

    @Test
    public void shouldZipTwoListsOfDifferentSizesNotExceedingElementsOfSmallestList() {
        List<Character> abc = asList('a', 'b', 'c');
        List<Character> oneTwo = asList('1', '2');

        assertEquals(asList(tuple('a', '1'), tuple('b', '2')), Zip.zip(abc, oneTwo));
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
