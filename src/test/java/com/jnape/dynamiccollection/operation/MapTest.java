package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.lambda.Predicate;
import org.junit.Test;

import java.util.ArrayList;

import static com.jnape.dynamiccollection.list.factory.DynamicListFactory.list;
import static com.jnape.dynamiccollection.operation.Map.map;
import static com.jnape.dynamiccollection.operation.Map.mapWhile;
import static org.junit.Assert.assertEquals;

public class MapTest {

    private static final Function<Integer, Integer> DOUBLE         = new Function<Integer, Integer>() {
        @Override
        public Integer apply(Integer n) {
            return n * 2;
        }
    };
    private static final Predicate<Integer>         LESS_THAN_FOUR = new Predicate<Integer>() {
        @Override
        public Boolean apply(Integer n) {
            return n < 4;
        }
    };

    @Test
    public void shouldMapOverCollection() {
        assertEquals(list(2, 4, 6), map(list(1, 2, 3), DOUBLE));
    }

    @Test
    public void shouldMapOverCollectionWhilePredicate() {
        assertEquals(list(2), mapWhile(list(1, 2, 3, 4, 5), DOUBLE, LESS_THAN_FOUR));
    }

    @Test
    public void shouldDoNothingForEmptyCollection() {
        map(new ArrayList<Integer>(), DOUBLE);
    }
}
