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
    private Function<Character, Boolean> reductionFunction;

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

        when(reductionFunction.apply('a')).thenReturn(true);
        when(reductionFunction.apply('b')).thenReturn(false);
        when(reductionFunction.apply('c')).thenReturn(false);

        DynamicCollection<Character> consonants = new DynamicArrayList<Character>('b', 'c');
        assertEquals(consonants, reducer.reduce(letters, reductionFunction));
    }

    @Test
    public void shouldReduceCollectionToNothingIfLogical() {
        Reducer reducer = new Reducer();

        Collection<Character> letters = asList('a', 'e', 'i', 'o', 'u');

        when(reductionFunction.apply(anyChar())).thenReturn(true);

        DynamicCollection<Character> empty = new DynamicArrayList<Character>();
        assertEquals(empty, reducer.reduce(letters, reductionFunction));
    }
}
