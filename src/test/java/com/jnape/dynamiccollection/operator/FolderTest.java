package com.jnape.dynamiccollection.operator;

import com.jnape.dynamiccollection.lambda.HigherOrderFunction;
import org.junit.Test;

import java.util.List;

import static com.jnape.dynamiccollection.DynamicCollectionFactory.list;
import static org.junit.Assert.assertEquals;

public class FolderTest {

    private static final List<Integer> INTEGERS = list(10, 9, 8, 7, 6, 5, 4, 3, 2, 1);

    private static final HigherOrderFunction<Integer, Integer> PLUS_ACCUMULATION_MODULO_ELEMENT = new HigherOrderFunction<Integer, Integer>() {
        @Override
        public Integer apply(Integer integer, Integer accumulation) {
            return accumulation + (accumulation % integer);
        }
    };

    private static final List<Character> CHARACTERS = list('H', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd', '!');

    private static final HigherOrderFunction<Character, String> ACCUMULATION_PLUS_ELEMENT = new HigherOrderFunction<Character, String>() {
        @Override
        public String apply(Character character, String accumulation) {
            return accumulation + character;
        }
    };

    @Test
    public void shouldExist() {
        new Folder();
    }

    @Test
    public void shouldFoldRight() {
        Folder folder = new Folder();

        assertEquals((Integer) 120, folder.foldRight(INTEGERS, 99, PLUS_ACCUMULATION_MODULO_ELEMENT));
        assertEquals("Hello, world!", folder.foldRight(CHARACTERS, "", ACCUMULATION_PLUS_ELEMENT));
    }

    @Test
    public void shouldFoldLeft() {
        Folder folder = new Folder();

        assertEquals((Integer) 114, folder.foldLeft(INTEGERS, 99, PLUS_ACCUMULATION_MODULO_ELEMENT));
        assertEquals("!dlrow ,olleH", folder.foldLeft(CHARACTERS, "", ACCUMULATION_PLUS_ELEMENT));
    }
}
