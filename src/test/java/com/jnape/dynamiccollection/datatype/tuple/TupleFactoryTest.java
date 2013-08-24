package com.jnape.dynamiccollection.datatype.tuple;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TupleFactoryTest {

    @Test
    public void shouldCreateTuple2() {
        assertEquals(
                new Tuple2<String, String>("foo", "bar"),
                TupleFactory.tuple("foo", "bar")
        );
    }

    @Test
    public void shouldCreateTuple3() {
        assertEquals(
                new Tuple3<Integer, String, Long>(3, "foo", 1L),
                TupleFactory.tuple(3, "foo", 1L)
        );
    }

    @Test
    public void shouldCreateTuple4() {
        assertEquals(
                new Tuple4<Character, Integer, String, Long>('a', 3, "foo", 1L),
                TupleFactory.tuple('a', 3, "foo", 1L)
        );
    }

}
