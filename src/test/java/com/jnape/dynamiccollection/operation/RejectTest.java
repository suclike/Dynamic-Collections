package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Function;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyChar;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RejectTest {

    @Mock private Function<Character, Boolean> rejector;

    @Test
    public void shouldReturnCollectionWithoutRejectedElements() {
        Collection<Character> letters = asList('a', 'b', 'c');

        when(rejector.apply('a')).thenReturn(true);
        when(rejector.apply('b')).thenReturn(false);
        when(rejector.apply('c')).thenReturn(false);

        Collection<Character> consonants = asList('b', 'c');
        assertEquals(consonants, Reject.reject(letters, rejector));
    }

    @Test
    public void shouldReturnEmptyCollectionIfAllElementsRejected() {
        Collection<Character> letters = asList('a', 'e', 'i', 'o', 'u');

        when(rejector.apply(anyChar())).thenReturn(true);

        Collection<Character> empty = new ArrayList<Character>();
        assertEquals(empty, Reject.reject(letters, rejector));
    }
}
