package org.jnape.dynamiccollection.list.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SuppressWarnings({"ThrowableInstanceNeverThrown"})
public class ListWasEmptyExceptionTest {

    @Test
    public void shouldConstruct() {
        new ListWasEmptyException();
    }

    @Test
    public void shouldBeIllegalStateException() {
        boolean isIllegalStateException = IllegalStateException.class.isInstance(new ListWasEmptyException());
        assertTrue(isIllegalStateException);
    }

    @Test
    public void shouldGetMessage() {
        assertEquals("The list was empty", new ListWasEmptyException().getMessage());
    }
}
