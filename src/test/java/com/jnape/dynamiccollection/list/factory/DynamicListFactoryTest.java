package com.jnape.dynamiccollection.list.factory;

import com.jnape.dynamiccollection.list.DynamicList;
import org.junit.Test;
import testsupport.Item;

import java.util.Collection;
import java.util.Iterator;

import static com.jnape.dynamiccollection.list.factory.DynamicListFactory.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static testsupport.ItemFixture.*;

public class DynamicListFactoryTest {

    @Test
    public void shouldHaveFactoryMethodThatRequiresNoArgs() {
        DynamicList<Object> dynamicList = list();
        assertEquals(0, dynamicList.size());
    }

    @Test
    public void shouldHaveFactoryMethodThatPopulatesFromCollection() {
        Collection<Item> items = asList(A, B, C);

        DynamicList<Item> dynamicList = list(items);

        assertEquals(3, dynamicList.size());
        assertEquals(A, dynamicList.get(0));
        assertEquals(B, dynamicList.get(1));
        assertEquals(C, dynamicList.get(2));
    }

    @Test
    public void shouldHaveFactoryMethodThatPopulatesFromVarArgs() {
        DynamicList<Item> dynamicList = list(B, C, A, C);

        assertEquals(4, dynamicList.size());
        assertEquals(B, dynamicList.get(0));
        assertEquals(C, dynamicList.get(1));
        assertEquals(A, dynamicList.get(2));
        assertEquals(C, dynamicList.get(3));
    }

    @Test
    public void shouldHaveFactoryMethodThatPopulatesFromIterator() {
        Iterator<Item> iterator = asList(A, B, C).iterator();
        DynamicList<Item> dynamicList = list(iterator);

        assertEquals(3, dynamicList.size());
        assertEquals(A, dynamicList.get(0));
        assertEquals(B, dynamicList.get(1));
        assertEquals(C, dynamicList.get(2));
    }

    @Test
    public void shouldHaveFactoryMethodThatCreatesRangeOfNumbersInIncrementsOfNWithTypeOfHighestPrecedenceOfFromAndTo() {
        assertEquals(numbers(1l, 2l, 3l, 4l, 5l), fromTo(1l, 5l, 1));
        assertEquals(numbers(1d, 3d, 5d), fromTo(1, 5d, 2));
        assertEquals(numbers((short) 1), fromTo((short) 1, (byte) 2, 2f));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfFromToWithIncrementOfZeroOrLess() {
        fromTo(1, 10, 0);
    }

    @Test
    public void shouldHaveFactoryMethodThatCreatesRangeOfNumbersInIncrementsOfOneIfNoIncrementProvided() {
        assertEquals(numbers(1, 2, 3, 4, 5), fromTo(1, 5));
    }
}
