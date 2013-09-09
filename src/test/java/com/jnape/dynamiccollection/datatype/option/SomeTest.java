package com.jnape.dynamiccollection.datatype.option;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;
import org.junit.Test;

import static com.jnape.dynamiccollection.lambda.niladic.builtin.Always.always;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class SomeTest {

    @Test
    public void shouldGetValueForGet() {
        assertEquals("foo", new Some<String>("foo").get());
    }

    @Test
    public void shouldReturnSelfForOrElse() {
        Some<String> some = new Some<String>("foo");
        assertSame(some, some.orElse(""));
    }

    @Test
    public void shouldMapToSomeValue() {
        Some<String> some = new Some<String>("foo");
        MonadicFunction<Object, Integer> toInt = always(0);
        assertEquals(new Some<Integer>(0), some.map(toInt));
    }

    @Test
    public void shouldGetValueForGetOrElse() {
        assertEquals("foo", new Some<String>("foo").getOrElse("bar"));
    }
}
