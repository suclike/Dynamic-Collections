package org.jnape.dynamiccollection.lambda;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class FunctionTest {

    @Test
    public void shouldConstruct() {
        Function function = new Function() {
            @Override
            public Object apply(Object o) {
                return null;
            }
        };
    }

    @Test
    public void shouldApply() {
        Function<Integer, Integer> squared = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer * integer;
            }
        };

        Integer expected = 4;
        Integer actual = squared.apply(2);

        assertEquals(expected, actual);
    }
}
