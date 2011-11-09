package org.jnape.dynamiccollection.list;

import org.jnape.dynamiccollection.operator.*;
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
        assertReflectionEquals(new Reducer(), operationProvider.reducer());
        assertReflectionEquals(new Transformer(), operationProvider.transformer());
        assertReflectionEquals(new ElementExcluder(), operationProvider.elementExcluder());
        assertReflectionEquals(new Partitioner(), operationProvider.partitioner());
    }
}
