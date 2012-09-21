package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Function;
import org.junit.Test;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class UniqueTest {

    @Test
    public void shouldRemoveNonUniqueElements() {
        Collection<Integer> numbers = asList(1, 1, 1, 2, 2, 3, 4, 5, 5);
        assertEquals(asList(1, 2, 3, 4, 5), Unique.unique(numbers));
    }

    @Test
    public void shouldRemoveNonUniqueElementsBasedOnMappedComparison() {
        Collection<String> mixedCase = asList("one", "One", "ONE", "two", "ThREE", "THREE");

        Function<String, String> lowerCase = new Function<String, String>() {
            @Override
            public String apply(String word) {
                return word.toLowerCase();
            }
        };

        assertEquals(asList("one", "two", "ThREE"), Unique.unique(mixedCase, lowerCase));
    }
}
