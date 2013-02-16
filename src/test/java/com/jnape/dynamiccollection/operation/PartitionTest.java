package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Predicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static testsupport.assertion.ReflectionAssert.assertReflectionEquals;

@RunWith(MockitoJUnitRunner.class)
public class PartitionTest {

    @Mock private Predicate<Integer> partitioner;

    @Test
    public void shouldPartitionCollectionIntoTruesAndFalses() {
        Collection<Integer> oneThroughTen = asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Collection<Integer> evens = asList(2, 4, 6, 8, 10);
        Collection<Integer> odds = asList(1, 3, 5, 7, 9);

        for (Integer even : evens)
            when(partitioner.apply(even)).thenReturn(true);

        for (Integer odd : odds)
            when(partitioner.apply(odd)).thenReturn(false);

        assertReflectionEquals(new com.jnape.dynamiccollection.datatype.Partition<Integer>(evens, odds), Partition.partition(oneThroughTen, partitioner));
    }
}
