package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.DyadicFunction;
import com.jnape.dynamiccollection.lambda.IndexedFunction;
import com.jnape.dynamiccollection.lambda.MonadicFunction;
import com.jnape.dynamiccollection.lambda.Predicate;
import com.jnape.dynamiccollection.list.NumericDynamicArrayList;
import org.junit.Test;

import java.util.ArrayList;

import static com.jnape.dynamiccollection.lambda.builtin.accumulator.Multiply.multiply;
import static com.jnape.dynamiccollection.list.factory.DynamicListFactory.list;
import static com.jnape.dynamiccollection.operation.Map.map;
import static com.jnape.dynamiccollection.operation.Map.mapWhile;
import static org.junit.Assert.assertEquals;

public class MapTest {

    private static final MonadicFunction<Integer, Integer>       DOUBLE         = new MonadicFunction<Integer, Integer>() {
        @Override
        public Integer apply(Integer n) {
            return n * 2;
        }
    };
    private static final DyadicFunction<Number, Integer, Number> TIMES_INDEX    = new IndexedFunction<Integer, Number>() {
        @Override
        public Number apply(Number index, Integer integer) {
            return multiply(integer, index);
        }
    };
    private static final Predicate<Integer>                      LESS_THAN_FOUR = new Predicate<Integer>() {
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
    public void shouldMapOverCollectionWithIndex() {
        assertEquals(new NumericDynamicArrayList(0, 2, 6), map(list(1, 2, 3), TIMES_INDEX));
    }

    @Test
    public void shouldMapOverCollectionWhilePredicate() {
        assertEquals(list(2), mapWhile(list(1, 2, 3, 4, 5), DOUBLE, LESS_THAN_FOUR));
    }

    @Test
    public void shouldDoNothingForEmptyCollection() {
        assertEquals(new ArrayList<Integer>(), map(new ArrayList<Integer>(), DOUBLE));
    }
}

