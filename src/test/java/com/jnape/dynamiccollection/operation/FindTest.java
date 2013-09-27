package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;
import com.jnape.dynamiccollection.lambda.monadic.Predicate;
import org.junit.Test;

import java.util.Collection;

import static com.jnape.dynamiccollection.datatype.option.Some.some;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class FindTest {

    public static final MonadicFunction<String, Boolean> THREE_LETTER_WORD = new Predicate<String>() {
        @Override
        public Boolean apply(String string) {
            return string.length() == 3;
        }
    };

    @Test
    public void shouldFindFirstValueMatchingPredicate() {
        Collection<String> words = asList("zero", "one", "two");
        assertEquals(some("one"), Find.find(THREE_LETTER_WORD, words));
    }

    @Test
    public void shouldReturnNoneIfNoElementsMatchPredicate() {
        Collection<String> words = asList("three", "four", "five");
        assertEquals(com.jnape.dynamiccollection.datatype.option.None.<String>none(), Find.find(THREE_LETTER_WORD, words));
    }
}
