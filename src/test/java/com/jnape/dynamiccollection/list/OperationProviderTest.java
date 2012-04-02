package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.operator.Fold;
import org.junit.Test;

import static testsupport.assertion.ReflectionAssert.assertReflectionEquals;

public class OperationProviderTest {

    @Test
    public void shouldConstruct() {
        new OperationProvider();
    }

    @Test
    public void shouldProvideExpectedOperations() {
        OperationProvider operationProvider = new OperationProvider();

        assertReflectionEquals(new Fold(), operationProvider.folder());
    }
}
