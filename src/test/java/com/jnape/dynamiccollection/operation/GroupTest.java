package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Predicate;
import org.junit.Test;

import java.util.Collection;

import static com.jnape.dynamiccollection.list.DynamicArrayList.list;
import static com.jnape.dynamiccollection.list.NumericDynamicArrayList.numbers;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class GroupTest {

    private static final Predicate<Integer> MAPPER = new Predicate<Integer>() {
        @Override
        public Boolean apply(Integer integer) {
            return integer % 2 == 0;
        }
    };

    @Test
    public void shouldGroupCollectionUsingMappedValueEquality() {
        Collection<Integer> oneThroughTen = asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        assertEquals(list(numbers(1, 3, 5, 7, 9), numbers(2, 4, 6, 8, 10)), Group.group(oneThroughTen, MAPPER));
    }
}
