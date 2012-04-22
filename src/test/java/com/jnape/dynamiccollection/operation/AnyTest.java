package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Function;
import org.junit.Test;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AnyTest {

    @Test
    public void shouldReturnTrueIfOneMatch() {
        Collection<Integer> mostlyOdd = asList(1, 3, 5, 6, 7);

        Function<Integer, Boolean> isEven = new Function<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer integer) {
                return integer % 2 == 0;
            }
        };

        assertTrue(Any.any(mostlyOdd, isEven));
    }

    @Test
    public void shouldReturnTrueIfMultipleMatches() {
        Collection<Boolean> mostlyTrues = asList(true, true, true, false, true);

        Function<Boolean, Boolean> isTrue = new Function<Boolean, Boolean>() {
            @Override
            public Boolean apply(Boolean bool) {
                return bool;
            }
        };

        assertTrue(Any.any(mostlyTrues, isTrue));
    }

    @Test
    public void shouldReturnFalseIfNoMatches() {
        Collection<String> noEmpties = asList("not empty", "also not empty");

        Function<String, Boolean> isEmpty = new Function<String, Boolean>() {
            @Override
            public Boolean apply(String string) {
                return string.isEmpty();
            }
        };

        assertFalse(Any.any(noEmpties, isEmpty));
    }
}
