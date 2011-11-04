package org.jnape.dynamiccollection.list;

import org.jnape.dynamiccollection.list.operation.CartesianProduct;
import org.jnape.dynamiccollection.list.operation.Concatenation;
import org.jnape.dynamiccollection.list.operation.Each;
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

        assertReflectionEquals(new Concatenation(), operationProvider.concatenation());
        assertReflectionEquals(new CartesianProduct(), operationProvider.cartesianProduct());
        assertReflectionEquals(new Each(), operationProvider.each());
    }
}
