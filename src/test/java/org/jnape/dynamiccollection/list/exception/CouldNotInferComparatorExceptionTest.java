package org.jnape.dynamiccollection.list.exception;

import org.junit.Test;

import java.util.List;

import static org.jnape.dynamiccollection.DynamicCollectionFactory.list;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SuppressWarnings({"ThrowableInstanceNeverThrown"})
public class CouldNotInferComparatorExceptionTest {

    @Test
    public void shouldConstruct() {
        new CouldNotInferComparatorException(list());
    }

    @Test
    public void shouldBeRuntimeException() {
        boolean isRuntimeException = RuntimeException.class.isInstance(new CouldNotInferComparatorException(list()));
        assertTrue(isRuntimeException);
    }

    @Test
    public void shouldGetMessage() {
        List<Integer> list = list(3, 2, 1);
        CouldNotInferComparatorException couldNotInferComparatorException = new CouldNotInferComparatorException(list);
        assertEquals("Could not infer comparator for list <[3, 2, 1]>", couldNotInferComparatorException.getMessage());
    }
}
