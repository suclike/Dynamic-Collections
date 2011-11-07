package org.jnape.dynamiccollection.operator;

import org.jnape.dynamiccollection.DynamicCollection;
import org.jnape.dynamiccollection.lambda.Function;
import org.jnape.dynamiccollection.list.DynamicArrayList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyChar;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ReducerTest {

    @Mock
    private Function<Character, Boolean> function;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void shouldConstruct() {
        new Reducer();
    }

    @Test
    public void shouldReduceCollectionToNonMatchingElements() {
        Reducer reducer = new Reducer();

        Collection<Character> letters = asList('a', 'b', 'c');

        when(function.apply('a')).thenReturn(true);
        when(function.apply('b')).thenReturn(false);
        when(function.apply('c')).thenReturn(false);

        DynamicCollection<Character> consonants = new DynamicArrayList<Character>('b', 'c');
        assertEquals(consonants, reducer.reduce(letters, function));
    }

    @Test
    public void shouldReduceCollectionToNothingIfLogical() {
        Reducer reducer = new Reducer();

        Collection<Character> letters = asList('a', 'e', 'i', 'o', 'u');

        when(function.apply(anyChar())).thenReturn(true);

        DynamicCollection<Character> empty = new DynamicArrayList<Character>();
        assertEquals(empty, reducer.reduce(letters, function));
    }
}
