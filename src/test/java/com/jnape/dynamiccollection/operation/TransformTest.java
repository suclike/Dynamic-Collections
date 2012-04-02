package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.DynamicCollection;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.list.DynamicArrayList;
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
public class TransformTest {

    @Mock private Function<Integer, String> transformer;

    @Test
    public void shouldTransformDynamicCollection() {
        Collection<Integer> collection = asList(1, 2, 3);

        when(transformer.apply(1)).thenReturn("1");
        when(transformer.apply(2)).thenReturn("2");
        when(transformer.apply(3)).thenReturn("3");

        DynamicCollection<String> expected = new DynamicArrayList<String>("1", "2", "3");

        assertEquals(expected, Transform.transform(collection, transformer));
    }

    @Test
    public void shouldDoNothingForEmptyCollection() {
        Collection<Integer> emptyCollection = new ArrayList<Integer>();

        Transform.transform(emptyCollection, transformer);
        verifyZeroInteractions(transformer);
    }
}
