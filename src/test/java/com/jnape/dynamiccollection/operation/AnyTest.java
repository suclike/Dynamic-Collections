package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.monadic.Predicate;
import org.junit.Test;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AnyTest {

    @Test
    public void shouldReturnTrueIfOneMatch() {
        Collection<Integer> mostlyOdd = asList(1, 3, 5, 6, 7);

        Predicate<Integer> isEven = new Predicate<Integer>() {
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

        Predicate<Boolean> isTrue = new Predicate<Boolean>() {
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

        Predicate<String> isEmpty = new Predicate<String>() {
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

        Predicate<String> isNotEmpty = new Predicate<String>() {
            @Override
            public Boolean apply(String string) {
                return string.length() > 0;
            }
        };
        Predicate<String> whileDoesNotStartWithAlso = new Predicate<String>() {
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

        Predicate<Integer> greaterThanSix = new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer integer) {
                return integer > 6;
            }
        };
        Predicate<Integer> whileLessThanFour = new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer integer) {
                return integer < 4;
            }
        };

        assertFalse(Any.anyWhile(oneThroughTen, greaterThanSix, whileLessThanFour));
    }
}
