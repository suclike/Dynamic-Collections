package org.jnape.dynamiccollection.operator;

import org.jnape.dynamiccollection.datatype.Partition;
import org.jnape.dynamiccollection.lambda.Function;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static testsupport.assertion.ReflectionAssert.assertReflectionEquals;

public class PartitionerTest {

    @Mock
    private Function<Integer, Boolean> partitionFunction;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void shouldConstruct() {
        new Partitioner();
    }

    @Test
    public void shouldPartitionCollectionIntoTruesAndFalses() {
        Partitioner partitioner = new Partitioner();

        Collection<Integer> oneThroughTen = asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Collection<Integer> evens = asList(2, 4, 6, 8, 10);
        Collection<Integer> odds = asList(1, 3, 5, 7, 9);

        for (Integer even : evens)
            when(partitionFunction.apply(even)).thenReturn(true);

        for (Integer odd : odds)
            when(partitionFunction.apply(odd)).thenReturn(false);

        assertReflectionEquals(new Partition<Integer>(evens, odds), partitioner.partition(oneThroughTen, partitionFunction));
    }
}
