package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.operator.*;
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

        assertReflectionEquals(new CartesianMultiplier(), operationProvider.cartesianMultiplier());
        assertReflectionEquals(new Collector(), operationProvider.collector());
        assertReflectionEquals(new Rejector(), operationProvider.rejector());
        assertReflectionEquals(new Transformer(), operationProvider.transformer());
        assertReflectionEquals(new ElementExcluder(), operationProvider.elementExcluder());
        assertReflectionEquals(new Partitioner(), operationProvider.partitioner());
        assertReflectionEquals(new Folder(), operationProvider.folder());
    }
}
