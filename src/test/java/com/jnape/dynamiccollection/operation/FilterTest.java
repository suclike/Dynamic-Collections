package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.monadic.Predicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.jnape.dynamiccollection.operation.Filter.filter;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FilterTest {

    @Mock private Predicate<Integer> filterer;

    @Test
    public void shouldFilterSpecificElements() {
        Collection<Integer> oneThroughSix = asList(1, 2, 3, 4, 5, 6);

        when(filterer.apply(1)).thenReturn(false);
        when(filterer.apply(3)).thenReturn(false);
        when(filterer.apply(5)).thenReturn(false);

        when(filterer.apply(2)).thenReturn(true);
        when(filterer.apply(4)).thenReturn(true);
        when(filterer.apply(6)).thenReturn(true);

        assertEquals(asList(2, 4, 6), filter(oneThroughSix, filterer));
    }

    @Test
    public void shouldReturnEmptyListIfNoItemsMatch() {
        List<Integer> oneThroughFive = asList(1, 2, 3, 4, 5);
        when(filterer.apply(anyInt())).thenReturn(false);

        assertEquals(new ArrayList<Integer>(), filter(oneThroughFive, filterer));
    }
}
