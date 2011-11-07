package org.jnape.dynamiccollection.list;

import org.jnape.dynamiccollection.list.operator.CartesianMultiplier;
import org.jnape.dynamiccollection.operator.Collector;
import org.jnape.dynamiccollection.list.operator.Concatenator;
import org.jnape.dynamiccollection.operator.IterativeExecutor;
import org.jnape.dynamiccollection.operator.Transformer;
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

        assertReflectionEquals(new Concatenator(), operationProvider.concatenator());
        assertReflectionEquals(new CartesianMultiplier(), operationProvider.cartesianMultiplier());
        assertReflectionEquals(new IterativeExecutor(), operationProvider.iterativeExecutor());
        assertReflectionEquals(new Collector(), operationProvider.collector());
        assertReflectionEquals(new Transformer(), operationProvider.transformer());
    }
}
