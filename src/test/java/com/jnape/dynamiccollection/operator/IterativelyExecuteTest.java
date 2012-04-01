package com.jnape.dynamiccollection.operator;

import com.jnape.dynamiccollection.DynamicCollection;
import com.jnape.dynamiccollection.lambda.Procedure;
import com.jnape.dynamiccollection.list.DynamicArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class IterativelyExecuteTest {

    @Mock private Procedure<Integer> procedure;

    @Test
    public void shouldIterativelyExecuteProcedureOnEachElementInDynamicCollection() {
        DynamicCollection<Integer> oneTwoThree = new DynamicArrayList<Integer>(1, 2, 3);

        IterativelyExecute.iterativelyExecute(oneTwoThree, procedure);

        InOrder inOrder = inOrder(procedure);
        inOrder.verify(procedure).execute(1);
        inOrder.verify(procedure).execute(2);
        inOrder.verify(procedure).execute(3);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldDoNothingForEmptyDynamicCollection() {
        DynamicCollection<Integer> emptyCollection = new DynamicArrayList<Integer>();

        IterativelyExecute.iterativelyExecute(emptyCollection, procedure);

        verifyZeroInteractions(procedure);
    }
}
