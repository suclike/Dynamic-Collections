package org.jnape.dynamiccollection.list.operation;

import org.jnape.dynamiccollection.DynamicCollection;
import org.jnape.dynamiccollection.lambda.Procedure;
import org.jnape.dynamiccollection.list.DynamicArrayList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.MockitoAnnotations.initMocks;

public class EachTest {

    @Mock
    private Procedure<Integer> procedure;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void shouldConstruct() {
        new Each();
    }

    @Test
    public void shouldIterativelyApplyFunctionToEachElementInDynamicCollection() {
        Each each = new Each();
        DynamicCollection<Integer> oneTwoThree = new DynamicArrayList<Integer>(1, 2, 3);

        each.iterativelyApply(oneTwoThree, procedure);

        InOrder inOrder = inOrder(procedure);
        inOrder.verify(procedure).execute(1);
        inOrder.verify(procedure).execute(2);
        inOrder.verify(procedure).execute(3);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void shouldDoNothingForEmptyDynamicCollection() {
        Each each = new Each();
        DynamicCollection<Integer> emptyCollection = new DynamicArrayList<Integer>();

        each.iterativelyApply(emptyCollection, procedure);

        verifyZeroInteractions(procedure);
    }
}
