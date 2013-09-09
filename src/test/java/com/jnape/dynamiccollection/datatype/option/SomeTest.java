package com.jnape.dynamiccollection.datatype.option;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;
import org.junit.Test;

import static com.jnape.dynamiccollection.datatype.option.Some.some;
import static com.jnape.dynamiccollection.lambda.niladic.builtin.Always.always;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class SomeTest {

    @Test
    public void shouldGetValueForGet() {
        assertEquals("foo", some("foo").get());
    }

    @Test
    public void shouldReturnSelfForOrElse() {
        Some<String> some = some("foo");
        assertSame(some, some.orElse(""));
    }

    @Test
    public void shouldMapToSomeValue() {
        Some<String> some = some("foo");
        MonadicFunction<Object, Integer> toInt = always(0);
        assertEquals(some(0), some.map(toInt));
    }

    @Test
    public void shouldGetValueForGetOrElse() {
        assertEquals("foo", some("foo").getOrElse("bar"));
    }

    @Test
    public void shouldHaveStaticFactoryMethod() {
        assertEquals(new Some<Integer>(10), some(10));
    }
}
