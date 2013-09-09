package com.jnape.dynamiccollection.stream;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.jnape.dynamiccollection.list.factory.DynamicListFactory.list;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CapacityMonitorTest {

    @Mock
    private MonadicFunction<List<Integer>, Integer> generator;
    private List<Integer>                           list;
    private CapacityMonitor<Integer, List<Integer>> capacityMonitor;

    @Before
    public void setUp() {
        list = new ArrayList<Integer>();
        capacityMonitor = new CapacityMonitor<Integer, List<Integer>>(list, generator);
    }

    @Test
    public void shouldEnsureCapacityByUsingGeneratorToGrowCollectionSize() {
        capacityMonitor.ensureCapacity(10);
        assertEquals(10, list.size());
    }

    @Test
    public void shouldUseGeneratorToGrowList() {
        capacityMonitor.ensureCapacity(5);
        verify(generator, times(5)).apply(list);
    }

    @Test
    public void shouldOnlyGenerateValuesUpToRequiredCapacity() {
        list.addAll(list(1, 2, 3));
        capacityMonitor.ensureCapacity(10);
        verify(generator, times(7)).apply(list);
    }
}
