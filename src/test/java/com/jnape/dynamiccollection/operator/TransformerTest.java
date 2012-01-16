package com.jnape.dynamiccollection.operator;

import com.jnape.dynamiccollection.DynamicCollection;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.list.DynamicArrayList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class TransformerTest {

    @Mock
    private Function<Integer, String> transformationFunction;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void shouldConstruct() {
        new Transformer();
    }

    @Test
    public void shouldTransformDynamicCollection() {
        Transformer transformer = new Transformer();

        Collection<Integer> collection = asList(1, 2, 3);

        when(transformationFunction.apply(1)).thenReturn("1");
        when(transformationFunction.apply(2)).thenReturn("2");
        when(transformationFunction.apply(3)).thenReturn("3");

        DynamicCollection<String> expected = new DynamicArrayList<String>("1", "2", "3");

        assertEquals(expected, transformer.transform(collection, transformationFunction));
    }

    @Test
    public void shouldDoNothingForEmptyCollection() {
        Transformer transformer = new Transformer();
        Collection<Integer> emptyCollection = new ArrayList<Integer>();

        transformer.transform(emptyCollection, transformationFunction);
        verifyZeroInteractions(transformationFunction);
    }
}
