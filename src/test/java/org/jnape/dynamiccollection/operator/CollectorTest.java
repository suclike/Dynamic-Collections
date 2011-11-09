package org.jnape.dynamiccollection.operator;

import org.jnape.dynamiccollection.lambda.Function;
import org.jnape.dynamiccollection.list.DynamicArrayList;
import org.jnape.dynamiccollection.list.DynamicList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CollectorTest {

    @Mock
    private Function<Integer, Boolean> collectionFunction;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void shouldConstruct() {
        new Collector();
    }

    @Test
    public void shouldCollectSpecificElements() {
        Collector collector = new Collector();

        Collection<Integer> oneThroughSix = asList(1, 2, 3, 4, 5, 6);

        when(collectionFunction.apply(1)).thenReturn(false);
        when(collectionFunction.apply(3)).thenReturn(false);
        when(collectionFunction.apply(5)).thenReturn(false);

        when(collectionFunction.apply(2)).thenReturn(true);
        when(collectionFunction.apply(4)).thenReturn(true);
        when(collectionFunction.apply(6)).thenReturn(true);

        assertEquals(asList(2, 4, 6), collector.collect(oneThroughSix, collectionFunction));
    }

    @Test
    public void shouldReturnEmptyListIfNoItemsMatch() {
        Collector collector = new Collector();

        DynamicList<Integer> oneThroughFive = new DynamicArrayList<Integer>(1, 2, 3, 4, 5);
        when(collectionFunction.apply(anyInt())).thenReturn(false);

        assertEquals(new DynamicArrayList<Integer>(), collector.collect(oneThroughFive, collectionFunction));
    }
}
