package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Function;
import org.junit.Test;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AllTest {

    @Test
    public void shouldReturnTrueIfAllElementsMatch() {
        Collection<Integer> evens = asList(2, 4, 6, 8, 10);

        Function<Integer, Boolean> areEven = new Function<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer integer) {
                return integer % 2 == 0;
            }
        };

        assertTrue(All.all(evens, areEven));
    }

    @Test
    public void shouldReturnFalseIfOneElementDoesNotMatch() {
        Collection<Boolean> truesAndFalses = asList(true, true, false, true, true);

        Function<Boolean, Boolean> areTrue = new Function<Boolean, Boolean>() {
            @Override
            public Boolean apply(Boolean bool) {
                return bool;
            }
        };

        assertFalse(All.all(truesAndFalses, areTrue));
    }

    @Test
    public void shouldReturnFalseIfNoElementsMatch() {
        Collection<Boolean> allFalses = asList(false, false, false);

        Function<Boolean, Boolean> areTrue = new Function<Boolean, Boolean>() {
            @Override
            public Boolean apply(Boolean bool) {
                return bool;
            }
        };

        assertFalse(All.all(allFalses, areTrue));
    }
}
