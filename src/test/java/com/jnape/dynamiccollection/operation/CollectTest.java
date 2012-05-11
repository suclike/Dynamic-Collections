package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Function;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        List<Integer> oneThroughFive = asList(1, 2, 3, 4, 5);
        when(collector.apply(anyInt())).thenReturn(false);

        assertEquals(new ArrayList<Integer>(), Collect.collect(oneThroughFive, collector));
    }
}
