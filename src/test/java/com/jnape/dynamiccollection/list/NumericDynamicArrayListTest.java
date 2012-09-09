package com.jnape.dynamiccollection.list;

import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;

import static com.jnape.dynamiccollection.list.DynamicArrayList.list;
import static com.jnape.dynamiccollection.list.NumericDynamicArrayList.numbers;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static testsupport.assertion.InheritanceAssert.assertThat;

@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class NumericDynamicArrayListTest {

    @Test
    public void shouldConstructAndPopulateFromVarArgs() {
        NumericDynamicArrayList numericDynamicArrayList = new NumericDynamicArrayList(1, 2, 3, 4);

        assertEquals(4, numericDynamicArrayList.size());
        assertEquals(1, numericDynamicArrayList.get(0));
        assertEquals(2, numericDynamicArrayList.get(1));
        assertEquals(3, numericDynamicArrayList.get(2));
        assertEquals(4, numericDynamicArrayList.get(3));
    }

    @Test
    public void shouldConstructAndPopulateFromIterator() {
        Iterator<? extends Number> iterator = asList(1f, 2l, 3).iterator();
        NumericDynamicArrayList dynamicArrayList = new NumericDynamicArrayList(iterator);

        assertEquals(3, dynamicArrayList.size());
        assertEquals(1f, dynamicArrayList.get(0));
        assertEquals(2l, dynamicArrayList.get(1));
        assertEquals(3, dynamicArrayList.get(2));
    }

    @Test
    public void shouldConstructAndPopulateFromCollection() {
        Collection<? extends Number> numbers = list(1f, (short) 2, (byte) 3);

        NumericDynamicArrayList numericDynamicArrayList = new NumericDynamicArrayList(numbers);

        assertEquals(3, numericDynamicArrayList.size());
        assertEquals(1f, numericDynamicArrayList.get(0));
        assertEquals((short) 2, numericDynamicArrayList.get(1));
        assertEquals((byte) 3, numericDynamicArrayList.get(2));
    }

    @Test
    public void shouldHaveFactoryMethodThatPopulatesFromVarArgs() {
        NumericDynamicArrayList numericDynamicArrayList = numbers(1, 2, 3, 4);

        assertEquals(4, numericDynamicArrayList.size());
        assertEquals(1, numericDynamicArrayList.get(0));
        assertEquals(2, numericDynamicArrayList.get(1));
        assertEquals(3, numericDynamicArrayList.get(2));
        assertEquals(4, numericDynamicArrayList.get(3));

    }

    @Test
    public void shouldHaveFactoryMethodThatPopulatesFromIterator() {
        Iterator<? extends Number> iterator = asList(1f, 2l, 3).iterator();

        NumericDynamicArrayList dynamicArrayList = numbers(iterator);

        assertEquals(3, dynamicArrayList.size());
        assertEquals(1f, dynamicArrayList.get(0));
        assertEquals(2l, dynamicArrayList.get(1));
        assertEquals(3, dynamicArrayList.get(2));

    }

    @Test
    public void shouldHaveFactoryMethodThatPopulatesFromCollection() {
        Collection<? extends Number> numbers = list(1f, (short) 2, (byte) 3);

        NumericDynamicArrayList numericDynamicArrayList = numbers(numbers);

        assertEquals(3, numericDynamicArrayList.size());
        assertEquals(1f, numericDynamicArrayList.get(0));
        assertEquals((short) 2, numericDynamicArrayList.get(1));
        assertEquals((byte) 3, numericDynamicArrayList.get(2));

    }

    @Test
    public void shouldHaveExpectedPolymorphisms() {
        assertThat(NumericDynamicArrayList.class)
                .isA(DynamicArrayList.class);
    }
}
