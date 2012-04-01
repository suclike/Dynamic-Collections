package com.jnape.dynamiccollection.operator;

import com.jnape.dynamiccollection.lambda.Procedure;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class EachTest {

    @Mock private Procedure<Integer> procedure;

    @Test
    public void shouldExecuteProcedureOnEachElementInCollection() {
        Collection<Integer> oneTwoThree = asList(1, 2, 3);

        Each.each(oneTwoThree, procedure);

        InOrder inOrder = inOrder(procedure);
        inOrder.verify(procedure).execute(1);
        inOrder.verify(procedure).execute(2);
        inOrder.verify(procedure).execute(3);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldDoNothingForEmptyCollection() {
        Collection<Integer> emptyCollection = emptyList();

        Each.each(emptyCollection, procedure);

        verifyZeroInteractions(procedure);
    }
}
