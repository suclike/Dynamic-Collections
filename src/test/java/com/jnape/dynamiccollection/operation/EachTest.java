package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.IndexedProcedure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class EachTest {

    @Mock private IndexedProcedure<Character> procedure;

    @Test
    public void shouldIterateOverEachElementAndIndex() {
        Collection<Character> letters = asList('a', 'b', 'c', 'd', 'e');

        Each.each(letters, procedure);

        InOrder inOrder = inOrder(procedure);
        inOrder.verify(procedure).execute(0, 'a');
        inOrder.verify(procedure).execute(1, 'b');
        inOrder.verify(procedure).execute(2, 'c');
        inOrder.verify(procedure).execute(3, 'd');
        inOrder.verify(procedure).execute(4, 'e');
    }
}
