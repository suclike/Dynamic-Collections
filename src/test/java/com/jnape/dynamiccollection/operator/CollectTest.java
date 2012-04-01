package com.jnape.dynamiccollection.operator;

import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.list.DynamicArrayList;
import com.jnape.dynamiccollection.list.DynamicList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CollectTest {

    @Mock private Function<Integer, Boolean> collector;

    @Test
    public void shouldConstruct() {
        new Collect();
    }

    @Test
    public void shouldCollectSpecificElements() {
        Collection<Integer> oneThroughSix = asList(1, 2, 3, 4, 5, 6);

        when(collector.apply(1)).thenReturn(false);
        when(collector.apply(3)).thenReturn(false);
        when(collector.apply(5)).thenReturn(false);

        when(collector.apply(2)).thenReturn(true);
        when(collector.apply(4)).thenReturn(true);
        when(collector.apply(6)).thenReturn(true);

        assertEquals(asList(2, 4, 6), Collect.collect(oneThroughSix, collector));
    }

    @Test
    public void shouldReturnEmptyListIfNoItemsMatch() {
        DynamicList<Integer> oneThroughFive = new DynamicArrayList<Integer>(1, 2, 3, 4, 5);
        when(collector.apply(anyInt())).thenReturn(false);

        assertEquals(new DynamicArrayList<Integer>(), Collect.collect(oneThroughFive, collector));
    }
}
