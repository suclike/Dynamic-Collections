package org.jnape.dynamiccollection.list.exception;

import org.junit.Test;

import static org.jnape.dynamiccollection.DynamicCollectionFactory.list;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SuppressWarnings({"ThrowableInstanceNeverThrown"})
public class ListNotSortableWithoutCustomComparatorExceptionTest {

    @Test
    public void shouldConstruct() {
        new ListNotSortableWithoutCustomComparatorException(list());
    }

    @Test
    public void shouldBeRuntimeException() {
        boolean isRuntimeException = RuntimeException.class.isInstance(new ListNotSortableWithoutCustomComparatorException(list()));
        assertTrue(isRuntimeException);
    }

    @Test
    public void shouldGetMessage() {

        ListNotSortableWithoutCustomComparatorException actual1 = new ListNotSortableWithoutCustomComparatorException(list(3, 2, 1));
        assertEquals("List cannot be sorted without custom comparator: <[3, 2, 1]>", actual1.getMessage());

        ListNotSortableWithoutCustomComparatorException actual2 = new ListNotSortableWithoutCustomComparatorException(list());
        assertEquals("List cannot be sorted without custom comparator: <[]>", actual2.getMessage());
    }
}
