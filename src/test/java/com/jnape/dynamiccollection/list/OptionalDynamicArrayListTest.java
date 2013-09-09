package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.datatype.option.None;
import com.jnape.dynamiccollection.datatype.option.Option;
import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;

import static com.jnape.dynamiccollection.datatype.option.OptionFactory.some;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static testsupport.assertion.InheritanceAssert.assertThat;

@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class OptionalDynamicArrayListTest {

    @Test
    @SuppressWarnings("unchecked")
    public void shouldConstructAndPopulateFromVarArgs() {
        OptionalDynamicArrayList<Integer> optionalDynamicArrayList = new OptionalDynamicArrayList<Integer>(some(1), some(2), some(3), some(4));

        assertEquals(4, optionalDynamicArrayList.size());
        assertEquals(some(1), optionalDynamicArrayList.get(0));
        assertEquals(some(2), optionalDynamicArrayList.get(1));
        assertEquals(some(3), optionalDynamicArrayList.get(2));
        assertEquals(some(4), optionalDynamicArrayList.get(3));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldConstructAndPopulateFromArray() {
        OptionalDynamicArrayList<Integer> optionalDynamicArrayList = new OptionalDynamicArrayList<Integer>(new Option[]{some(1), some(2), some(3)});

        assertEquals(3, optionalDynamicArrayList.size());
        assertEquals(some(1), optionalDynamicArrayList.get(0));
        assertEquals(some(2), optionalDynamicArrayList.get(1));
        assertEquals(some(3), optionalDynamicArrayList.get(2));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldConstructAndPopulateFromIterator() {
        Iterator<Option<String>> iterator = asList(some("foo"), some("bar"), some("baz")).iterator();
        OptionalDynamicArrayList optionalDynamicArrayList = new OptionalDynamicArrayList<String>(iterator);

        assertEquals(3, optionalDynamicArrayList.size());
        assertEquals(some("foo"), optionalDynamicArrayList.get(0));
        assertEquals(some("bar"), optionalDynamicArrayList.get(1));
        assertEquals(some("baz"), optionalDynamicArrayList.get(2));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldConstructAndPopulateFromCollection() {
        Collection<Option<Integer>> numbers = asList(some(1), some(2), some(3));

        OptionalDynamicArrayList optionalDynamicArrayList = new OptionalDynamicArrayList(numbers);

        assertEquals(3, optionalDynamicArrayList.size());
        assertEquals(some(1), optionalDynamicArrayList.get(0));
        assertEquals(some(2), optionalDynamicArrayList.get(1));
        assertEquals(some(3), optionalDynamicArrayList.get(2));
    }

    @Test
    public void shouldHaveExpectedPolymorphisms() {
        assertThat(OptionalDynamicArrayList.class)
                .isA(DynamicArrayList.class);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldFlatten() {
        OptionalDynamicArrayList<String> options = new OptionalDynamicArrayList<String>(
                some("foo"),
                some("bar"),
                new None<String>(),
                some("baz")
        );

        assertEquals(asList("foo", "bar", "baz"), options.flatten());
    }
}
