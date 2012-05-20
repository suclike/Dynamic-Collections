package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.DynamicCollection;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static testsupport.assertion.InheritanceAssert.assertThat;

@SuppressWarnings({"MismatchedQueryAndUpdateOfCollection"})
public class ComparableDynamicArrayListTest {

    @Test
    public void shouldConstructWithoutArgs() {
        ComparableDynamicArrayList comparableDynamicArrayList = new ComparableDynamicArrayList();

        assertEquals(0, comparableDynamicArrayList.size());
    }

    @Test
    public void shouldConstructAndPopulateFromCollection() {
        Collection<Integer> integers = asList(1, 2, 3);

        ComparableDynamicArrayList<Integer> comparableDynamicArrayList = new ComparableDynamicArrayList<Integer>(integers);

        assertEquals(3, comparableDynamicArrayList.size());
        assertEquals((Integer) 1, comparableDynamicArrayList.get(0));
        assertEquals((Integer) 2, comparableDynamicArrayList.get(1));
        assertEquals((Integer) 3, comparableDynamicArrayList.get(2));
    }

    @Test
    public void shouldConstructAndPopulateFromArray() {
        Float[] floats = {1.0f, 2.0f};
        ComparableDynamicArrayList<Float> comparableDynamicArrayList = new ComparableDynamicArrayList<Float>(floats);

        assertEquals(2, comparableDynamicArrayList.size());
        assertEquals((Float) 1.0f, comparableDynamicArrayList.get(0));
        assertEquals((Float) 2.0f, comparableDynamicArrayList.get(1));
    }

    @Test
    public void shouldConstructAndPopulateFromVarArgs() {
        ComparableDynamicArrayList<Double> comparableDynamicArrayList = new ComparableDynamicArrayList<Double>(2d, 4d, 6d);

        assertEquals(3, comparableDynamicArrayList.size());
        assertEquals((Double) 2d, comparableDynamicArrayList.get(0));
        assertEquals((Double) 4d, comparableDynamicArrayList.get(1));
        assertEquals((Double) 6d, comparableDynamicArrayList.get(2));
    }

    @Test
    public void shouldConstructAndPopulateFromIterator() {
        Iterator<String> iterator = asList("a", "b", "c").iterator();
        ComparableDynamicArrayList<String> comparableDynamicArrayList = new ComparableDynamicArrayList<String>(iterator);

        assertEquals(3, comparableDynamicArrayList.size());
        assertEquals("a", comparableDynamicArrayList.get(0));
        assertEquals("b", comparableDynamicArrayList.get(1));
        assertEquals("c", comparableDynamicArrayList.get(2));
    }

    @Test
    public void shouldHaveExpectedPolymorphisms() {
        assertThat(ComparableDynamicArrayList.class)
                .isA(DynamicCollection.class)
                .isA(DynamicList.class)
                .isA(DynamicArrayList.class)
                .isA(Collection.class)
                .isA(List.class)
                .isA(ArrayList.class);
    }

    @Test
    public void shouldGetMinElement() {
        assertEquals((Float) .9f, new ComparableDynamicArrayList<Float>(.9f, 1.0f, 1.1f, 2f, 3f).min());
    }

    @Test
    public void shouldGetMaxElement() {
        assertEquals((Integer) 5, new ComparableDynamicArrayList<Integer>(1, 2, 3, 4, 5).max());
    }

    @Test
    public void shouldSort() {
        ComparableDynamicArrayList<String> names = new ComparableDynamicArrayList<String>(
                "Bob",
                "Adam",
                "Charles",
                "Eric",
                "David"
        );

        ComparableDynamicArrayList<String> sortedNames = new ComparableDynamicArrayList<String>(
                "Adam",
                "Bob",
                "Charles",
                "David",
                "Eric"
        );

        assertEquals(sortedNames, names.sort());
    }

    @Test
    public void shouldUseDefensiveCopyForSort() {
        ComparableDynamicArrayList<Integer> numbers = new ComparableDynamicArrayList<Integer>();
        assertNotSame(numbers, numbers.sort());
    }
}
