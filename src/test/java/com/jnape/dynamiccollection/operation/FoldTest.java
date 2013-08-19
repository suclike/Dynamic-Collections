package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Accumulator;
import org.junit.Test;

import java.util.List;

import static com.jnape.dynamiccollection.list.factory.DynamicListFactory.list;
import static org.junit.Assert.assertEquals;

public class FoldTest {

    @Test
    public void shouldFoldLeft() {
        Accumulator<String, Character> combineCharacters = new Accumulator<String, Character>() {
            @Override
            public String apply(String accumulation, Character character) {
                return accumulation + character;
            }
        };

        List<Character> characters = list('H', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd', '!');
        assertEquals("Hello, world!", Fold.foldLeft(characters, "", combineCharacters));
    }

    @Test
    public void shouldFoldRight() {
        Accumulator<Long, Integer> product = new Accumulator<Long, Integer>() {
            @Override
            public Long apply(Long accumulation, Integer integer) {
                return accumulation * integer;
            }
        };

        List<Integer> numbers = list(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);
        assertEquals((Long) 359251200L, Fold.foldRight(numbers, 99L, product));
    }
}
