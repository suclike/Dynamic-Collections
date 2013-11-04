package com.jnape.dynamiccollection.datatype.option;

import org.junit.Test;

import static com.jnape.dynamiccollection.datatype.option.Option.option;
import static com.jnape.dynamiccollection.datatype.option.Some.some;
import static org.junit.Assert.*;

public class OptionTest {

    private static final Object ANYTHING = new Object();

    @Test
    public void shouldCreateSomeFromNonNullValue() {
        assertEquals(some("foo"), option("foo"));
    }

    @Test
    public void shouldCreateNoneFromNullValue() {
        assertEquals(None.<Integer>none(), option((Integer) null));
    }

    @Test
    public void shouldKnowSomeIsSome() {
        assertTrue(some(ANYTHING).isSome());
    }

    @Test
    public void shouldKnowSomeIsNotNone() {
        assertFalse(some(ANYTHING).isNone());
    }

    @Test
    public void shouldKnowNoneIsNone() {
        assertTrue(None.<Object>none().isNone());
    }

    @Test
    public void shouldKnowNoneIsNotSome() {
        assertFalse(None.<Object>none().isSome());
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowIfNone() {
        None.none().orThrow(new IllegalStateException());
    }

    @Test
    public void shouldNotThrowIfSome() {
        assertEquals("thing", some("thing").orThrow(new IllegalStateException()));
    }
}
