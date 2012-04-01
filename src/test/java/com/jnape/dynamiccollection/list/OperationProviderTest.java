package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.operator.ElementExcluder;
import com.jnape.dynamiccollection.operator.Folder;
import com.jnape.dynamiccollection.operator.Partitioner;
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

        assertReflectionEquals(new ElementExcluder(), operationProvider.elementExcluder());
        assertReflectionEquals(new Partitioner(), operationProvider.partitioner());
        assertReflectionEquals(new Folder(), operationProvider.folder());
    }
}
