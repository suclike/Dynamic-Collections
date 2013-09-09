package com.jnape.dynamiccollection.datatype.option;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;
import org.junit.Test;

import static com.jnape.dynamiccollection.datatype.option.None.none;
import static com.jnape.dynamiccollection.datatype.option.Some.some;
import static com.jnape.dynamiccollection.lambda.niladic.builtin.Always.always;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class NoneTest {

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionForGet() {
        none().get();
    }

    @Test
    public void shouldReturnSomeElseForOrElse() {
        assertEquals(some("foo"), None.<String>none().orElse("foo"));
    }

    @Test
    public void shouldMapToNone() {
        MonadicFunction<Object, Integer> toInt = always(0);
        assertEquals(None.<Integer>none(), none().map(toInt));
    }

    @Test
    public void shouldGetElseForGetOrElse() {
        assertEquals("bar", None.<String>none().getOrElse("bar"));
    }

    @Test
    public void shouldAlwaysReturnTheSameInstanceRegardlessOfParameterization() {
        assertSame(None.<String>none(), None.<Integer>none());
    }
}
