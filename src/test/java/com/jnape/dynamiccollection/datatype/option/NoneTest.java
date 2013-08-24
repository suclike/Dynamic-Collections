package com.jnape.dynamiccollection.datatype.option;

import com.jnape.dynamiccollection.lambda.MonadicFunction;
import org.junit.Test;

import static com.jnape.dynamiccollection.lambda.factory.FunctionFactory.always;
import static org.junit.Assert.assertEquals;

public class NoneTest {

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionForGet() {
        new None().get();
    }

    @Test
    public void shouldReturnSomeElseForOrElse() {
        assertEquals(new Some<String>("foo"), new None<String>().orElse("foo"));
    }

    @Test
    public void shouldMapToNone() {
        None<String> none = new None<String>();
        MonadicFunction<Object, Integer> toInt = always(0);
        assertEquals(new None<Integer>(), none.map(toInt));
    }

    @Test
    public void shouldGetElseForGetOrElse() {
        assertEquals("bar", new None<String>().getOrElse("bar"));
    }
}
