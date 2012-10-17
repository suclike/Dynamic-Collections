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

    @Test
    public void shouldReturnTrueIfMatcherMatchesBeforePredicateTerminatesSearch() {
        String empty = "";
        Collection<String> empties = asList("not empty", empty, "also not empty");

        Function<String, Boolean> isNotEmpty = new Function<String, Boolean>() {
            @Override
            public Boolean apply(String string) {
                return string.length() > 0;
            }
        };
        Function<String, Boolean> whileDoesNotStartWithAlso = new Function<String, Boolean>() {
            @Override
            public Boolean apply(String string) {
                return !string.startsWith("also");
            }
        };

        assertTrue(Any.anyWhile(empties, isNotEmpty, whileDoesNotStartWithAlso));
    }

    @Test
    public void shouldReturnFalseIfMatcherDoesNotMatchBeforePredicateTerminatesSearch() {
        Collection<Integer> oneThroughTen = asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Function<Integer, Boolean> greaterThanSix = new Function<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer integer) {
                return integer > 6;
            }
        };
        Function<Integer, Boolean> whileLessThanFour = new Function<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer integer) {
                return integer < 4;
            }
        };

        assertFalse(Any.anyWhile(oneThroughTen, greaterThanSix, whileLessThanFour));
    }
}
