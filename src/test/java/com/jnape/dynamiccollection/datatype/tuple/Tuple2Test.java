package com.jnape.dynamiccollection.datatype.tuple;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static testsupport.assertion.InheritanceAssert.assertThat;

public class Tuple2Test {

    @Test
    public void shouldHaveExpectedPolymorphism() {
        assertThat(Tuple2.class)
                .isA(Map.Entry.class);
    }

    @Test
    public void shouldGetSlot1ForGetKey() {
        assertEquals("foo", new Tuple2<String, String>("foo", "bar").getKey());
    }

    @Test
    public void shouldGetSlot2ForGetValue() {
        assertEquals("bar", new Tuple2<String, String>("foo", "bar").getValue());
    }

    @Test(expected = IllegalStateException.class)
    public void shouldDisallowSettingValue() {
        new Tuple2<String, String>("foo", "bar").setValue("baz");
    }
}
