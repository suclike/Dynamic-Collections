package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.datatype.option.Option;
import org.junit.Test;

import java.util.List;

import static com.jnape.dynamiccollection.datatype.option.OptionFactory.some;
import static com.jnape.dynamiccollection.list.factory.DynamicListFactory.options;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class FlattenTest {

    @Test
    public void shouldUnwrapsSomeValues() {
        assertEquals(asList(1, 2, 3), Flatten.flatten(options(1, 2, 3)));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldDiscardNones() {
        List<Option<String>> options = asList(
                new com.jnape.dynamiccollection.datatype.option.None<String>(),
                some("foo"),
                new com.jnape.dynamiccollection.datatype.option.None<String>());

        assertEquals(asList("foo"), Flatten.flatten(options));
    }
}
