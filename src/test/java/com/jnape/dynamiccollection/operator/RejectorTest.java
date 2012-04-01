package com.jnape.dynamiccollection.operator;

import com.jnape.dynamiccollection.DynamicCollection;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.list.DynamicArrayList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyChar;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RejectorTest {

    @Mock private Function<Character, Boolean> rejectorFunction;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void shouldConstruct() {
        new Rejector();
    }

    @Test
    public void shouldReturnCollectionWithoutRejectedElements() {
        Rejector rejector = new Rejector();

        Collection<Character> letters = asList('a', 'b', 'c');

        when(rejectorFunction.apply('a')).thenReturn(true);
        when(rejectorFunction.apply('b')).thenReturn(false);
        when(rejectorFunction.apply('c')).thenReturn(false);

        DynamicCollection<Character> consonants = new DynamicArrayList<Character>('b', 'c');
        assertEquals(consonants, rejector.reject(letters, rejectorFunction));
    }

    @Test
    public void shouldReturnEmptyCollectionIfAllElementsRejected() {
        Rejector rejector = new Rejector();

        Collection<Character> letters = asList('a', 'e', 'i', 'o', 'u');

        when(rejectorFunction.apply(anyChar())).thenReturn(true);

        DynamicCollection<Character> empty = new DynamicArrayList<Character>();
        assertEquals(empty, rejector.reject(letters, rejectorFunction));
    }
}
