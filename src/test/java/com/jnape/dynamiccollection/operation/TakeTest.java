package com.jnape.dynamiccollection.operation;


import com.jnape.dynamiccollection.lambda.Predicate;
import org.junit.Test;

import java.util.ArrayList;

import static com.jnape.dynamiccollection.operation.Take.take;
import static com.jnape.dynamiccollection.operation.Take.takeWhile;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class TakeTest {

    public static final Iterable<Integer> NUMBERS = asList(1, 2, 3, 4, 5, 6);

    @Test
    public void shouldTakeItemsFromIterable() {
        assertEquals(asList(1, 2, 3), take(3, NUMBERS));
    }

    @Test
    public void shouldTakeZeroItems() {
        assertEquals(new ArrayList<Integer>(), take(0, NUMBERS));
    }

    @Test
    public void shouldTakeWhilePredicate() {
        Predicate<Integer> lessThanFour = new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer integer) {
                return integer < 4;
            }
        };

        assertEquals(asList(1, 2, 3), takeWhile(lessThanFour, NUMBERS));
    }

    @Test
    public void shouldTakeNoneIfPredicateFailsFirstMatch() {
        Predicate<Object> fails = new Predicate<Object>() {
            @Override
            public Boolean apply(Object anything) {
                return false;
            }
        };

        assertEquals(new ArrayList<Integer>(), takeWhile(fails, NUMBERS));
    }
}
