package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Function;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MapTest {

    @Mock private Function<Integer, String> mapper;

    @Test
    public void shouldMapCollection() {
        Collection<Integer> collection = asList(1, 2, 3);

        when(mapper.apply(1)).thenReturn("1");
        when(mapper.apply(2)).thenReturn("2");
        when(mapper.apply(3)).thenReturn("3");

        Collection<String> expected = asList("1", "2", "3");
        assertEquals(expected, Map.map(collection, mapper));
    }

    @Test
    public void shouldDoNothingForEmptyCollection() {
        Map.map(new ArrayList<Integer>(), mapper);
        verifyZeroInteractions(mapper);
    }
}
