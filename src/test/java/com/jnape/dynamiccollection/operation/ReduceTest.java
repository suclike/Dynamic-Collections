package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Accumulator;
import org.junit.Test;

import java.util.List;

import static com.jnape.dynamiccollection.DynamicCollectionFactory.list;
import static org.junit.Assert.assertEquals;

public class ReduceTest {

    @Test
    public void shouldReduce() {
        Accumulator<Integer, Integer> sum = new Accumulator<Integer, Integer>() {
            @Override
            public Integer apply(Integer accumulation, Integer integer) {
                return accumulation + integer;
            }
        };

        List<Integer> oneThroughTen = list(1, 2, 3, 4, 5);
        assertEquals((Integer) 15, Reduce.reduce(oneThroughTen, sum));
    }

    @Test
    public void shouldReduceOnListWithOneElement() {
        Accumulator<String, String> commaDelimit = new Accumulator<String, String>() {
            @Override
            public String apply(String accumulation, String s) {
                return accumulation + ", " + s;
            }
        };

        List<String> strings = list("1", "2", "3");
        assertEquals("1, 2, 3", Reduce.reduce(strings, commaDelimit));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void shouldThrowExceptionIfReduceOnEmptyList() {
        Accumulator<Object, Object> accumulator = new Accumulator<Object, Object>() {
            @Override
            public Object apply(Object accumulation, Object o) {
                return null;
            }
        };

        List<Object> emptyList = list();
        Reduce.reduce(emptyList, accumulator);
    }
}
