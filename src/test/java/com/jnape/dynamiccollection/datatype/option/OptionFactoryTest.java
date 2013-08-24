package com.jnape.dynamiccollection.datatype.option;

import org.junit.Test;

import static com.jnape.dynamiccollection.datatype.option.OptionFactory.option;
import static org.junit.Assert.assertEquals;

public class OptionFactoryTest {

    @Test
    public void shouldCreateSomeFromNonNullValue() {
        assertEquals(new Some<String>("foo"), option("foo"));
    }

    @Test
    public void shouldCreateNoneFromNullValue() {
        assertEquals(new None<Integer>(), option((Integer) null));
    }
}
