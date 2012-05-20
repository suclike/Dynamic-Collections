package com.jnape.dynamiccollection;

import com.jnape.dynamiccollection.factory.DynamicListFactory;
import com.jnape.dynamiccollection.list.DynamicArrayList;
import com.jnape.dynamiccollection.list.DynamicList;
import org.junit.Test;

import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class DynamicListFactoryTest {

    public static final Collection<Character> LETTERS = asList('A', 'B', 'C');
    public static final Integer[] NUMBERS = new Integer[]{1, 2, 3};

    @Test
    public void shouldProvideDynamicListWithoutArguments() {
        assertEquals(new DynamicArrayList(), DynamicListFactory.list());
    }

    @Test
    public void shouldProvideDynamicListForCollection() {
        assertEquals(new DynamicArrayList<Character>(LETTERS), DynamicListFactory.list(LETTERS));
    }

    @Test
    public void shouldProvideDynamicListForArray() {
        assertEquals(new DynamicArrayList<Integer>(NUMBERS), DynamicListFactory.list(NUMBERS));
    }

    @Test
    public void shouldProvideDynamicListForVarArgs() {
        assertEquals(new DynamicArrayList<Integer>(1, 2, 3), DynamicListFactory.list(1, 2, 3));
    }

    @Test
    public void shouldProvideDynamicListForIterator() {
        DynamicList<Integer> dynamicList = new DynamicArrayList<Integer>(1, 2, 3);
        assertEquals(dynamicList, DynamicListFactory.list(dynamicList.iterator()));
    }
}
