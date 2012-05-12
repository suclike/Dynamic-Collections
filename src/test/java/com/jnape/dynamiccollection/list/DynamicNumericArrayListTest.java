package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.DynamicCollection;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static testsupport.assertion.InheritanceAssert.assertThat;

@SuppressWarnings({"MismatchedQueryAndUpdateOfCollection"})
public class DynamicNumericArrayListTest {

    @Test
    public void shouldConstructWithoutArgs() {
        DynamicNumericArrayList dynamicNumericArrayList = new DynamicNumericArrayList();

        assertEquals(0, dynamicNumericArrayList.size());
    }

    @Test
    public void shouldConstructAndPopulateFromCollection() {
        Collection<Integer> integers = asList(1, 2, 3);

        DynamicNumericArrayList<Integer> dynamicNumericArrayList = new DynamicNumericArrayList<Integer>(integers);

        assertEquals(3, dynamicNumericArrayList.size());
        assertEquals((Integer) 1, dynamicNumericArrayList.get(0));
        assertEquals((Integer) 2, dynamicNumericArrayList.get(1));
        assertEquals((Integer) 3, dynamicNumericArrayList.get(2));
    }

    @Test
    public void shouldConstructAndPopulateFromArray() {
        Float[] floats = {1.0f, 2.0f};
        DynamicNumericArrayList<Float> dynamicNumericArrayList = new DynamicNumericArrayList<Float>(floats);

        assertEquals(2, dynamicNumericArrayList.size());
        assertEquals((Float) 1.0f, dynamicNumericArrayList.get(0));
        assertEquals((Float) 2.0f, dynamicNumericArrayList.get(1));
    }

    @Test
    public void shouldConstructAndPopulateFromVarArgs() {
        DynamicNumericArrayList<Double> dynamicNumericArrayList = new DynamicNumericArrayList<Double>(2d, 4d, 6d);

        assertEquals(3, dynamicNumericArrayList.size());
        assertEquals((Double) 2d, dynamicNumericArrayList.get(0));
        assertEquals((Double) 4d, dynamicNumericArrayList.get(1));
        assertEquals((Double) 6d, dynamicNumericArrayList.get(2));
    }

    @Test
    public void shouldConstructAndPopulateFromIterator() {
        Iterator<Long> iterator = asList(1l, 2l, 3l).iterator();
        DynamicNumericArrayList<Long> dynamicNumericArrayList = new DynamicNumericArrayList<Long>(iterator);

        assertEquals(3, dynamicNumericArrayList.size());
        assertEquals((Long) 1l, dynamicNumericArrayList.get(0));
        assertEquals((Long) 2l, dynamicNumericArrayList.get(1));
        assertEquals((Long) 3l, dynamicNumericArrayList.get(2));
    }

    @Test
    public void shouldHaveExpectedPolymorphisms() {
        assertThat(DynamicNumericArrayList.class)
                .isA(DynamicCollection.class)
                .isA(DynamicList.class)
                .isA(DynamicArrayList.class)
                .isA(Collection.class)
                .isA(List.class)
                .isA(ArrayList.class);
    }
}
