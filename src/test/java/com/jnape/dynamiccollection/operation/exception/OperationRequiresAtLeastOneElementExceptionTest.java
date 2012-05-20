package com.jnape.dynamiccollection.operation.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static testsupport.assertion.InheritanceAssert.assertThat;

public class OperationRequiresAtLeastOneElementExceptionTest {

    @Test
    public void shouldBeRuntime() {
        assertThat(OperationRequiresAtLeastOneElementException.class).isA(RuntimeException.class);
    }

    @Test
    public void shouldHaveUsefulMessage() {
        OperationRequiresAtLeastOneElementException actual = new OperationRequiresAtLeastOneElementException();
        assertEquals("Operation requires at least one element", actual.getMessage());
    }
}
