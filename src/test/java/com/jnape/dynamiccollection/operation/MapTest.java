package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Function;
import org.junit.Test;

import java.util.ArrayList;

import static com.jnape.dynamiccollection.list.DynamicArrayList.list;
import static org.junit.Assert.assertEquals;

public class MapTest {

    private static final Function<Integer, Integer> DOUBLE = new Function<Integer, Integer>() {
        @Override
        public Integer apply(Integer n) {
            return n * 2;
        }
    };

    private static final Function<Integer, Boolean> LESS_THAN_FOUR = new Function<Integer, Boolean>() {
        @Override
        public Boolean apply(Integer n) {
            return n < 4;
        }
    };

    @Test
    public void shouldMapOverCollection() {
        assertEquals(list(2, 4, 6), Map.map(list(1, 2, 3), DOUBLE));
    }

    @Test
    public void shouldMapOverCollectionWhilePredicate() {
        assertEquals(list(2), Map.mapWhile(list(1, 2, 3, 4, 5), DOUBLE, LESS_THAN_FOUR));
    }

    @Test
    public void shouldDoNothingForEmptyCollection() {
        Map.map(new ArrayList<Integer>(), DOUBLE);
    }
}
