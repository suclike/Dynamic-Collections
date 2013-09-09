package com.jnape.dynamiccollection.list.factory;

import com.jnape.dynamiccollection.datatype.option.None;
import com.jnape.dynamiccollection.datatype.tuple.Tuple2;
import com.jnape.dynamiccollection.list.DynamicList;
import com.jnape.dynamiccollection.list.OptionalDynamicArrayList;
import org.junit.Test;
import testsupport.Item;

import java.util.*;

import static com.jnape.dynamiccollection.datatype.option.OptionFactory.some;
import static com.jnape.dynamiccollection.lambda.factory.FunctionFactory.always;
import static com.jnape.dynamiccollection.list.factory.DynamicListFactory.*;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.internal.matchers.IsCollectionContaining.hasItems;
import static testsupport.ItemFixture.*;

public class DynamicListFactoryTest {

    @Test
    public void shouldCreateEmptyDynamicList() {
        DynamicList<Object> dynamicList = list();
        assertEquals(0, dynamicList.size());
    }

    @Test
    public void shouldCreateDynamicListFromCollection() {
        Collection<Item> items = asList(A, B, C);

        DynamicList<Item> dynamicList = list(items);

        assertEquals(3, dynamicList.size());
        assertEquals(A, dynamicList.get(0));
        assertEquals(B, dynamicList.get(1));
        assertEquals(C, dynamicList.get(2));
    }

    @Test
    public void shouldCreateDynamicListFromVarArgs() {
        DynamicList<Item> dynamicList = list(B, C, A, C);

        assertEquals(4, dynamicList.size());
        assertEquals(B, dynamicList.get(0));
        assertEquals(C, dynamicList.get(1));
        assertEquals(A, dynamicList.get(2));
        assertEquals(C, dynamicList.get(3));
    }

    @Test
    public void shouldCreateDynamicListFromIterator() {
        Iterator<Item> iterator = asList(A, B, C).iterator();
        DynamicList<Item> dynamicList = list(iterator);

        assertEquals(3, dynamicList.size());
        assertEquals(A, dynamicList.get(0));
        assertEquals(B, dynamicList.get(1));
        assertEquals(C, dynamicList.get(2));
    }

    @Test
    public void shouldCreateNumericDynamicListInIncrementsOfOneIfNoIncrementProvided() {
        assertEquals(numbers(1, 2, 3, 4, 5), fromTo(1, 5));
    }

    @Test
    public void shouldCreateNumericDynamicListFromNumbersInTermsOfHighestNumericalPrecedence() {
        assertEquals(numbers(1l, 2l, 3l, 4l, 5l), fromTo(1l, 5l, 1));
        assertEquals(numbers(1d, 3d, 5d), fromTo(1, 5d, 2));
        assertEquals(numbers((short) 1), fromTo((short) 1, (byte) 2, 2f));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfGivenIncrementOfZeroOrLess() {
        fromTo(1, 10, 0);
    }

    @Test
    public void shouldCreateListsFromPrimitiveArrays() {
        assertEquals(
                DynamicListFactory.<Byte>list((byte) 1, (byte) 2, (byte) 3),
                list(new byte[]{1, 2, 3})
        );

        assertEquals(
                DynamicListFactory.<Short>list((short) 1, (short) 2, (short) 3),
                list(new short[]{1, 2, 3})
        );

        assertEquals(
                DynamicListFactory.<Integer>list(1, 2, 3),
                list(new int[]{1, 2, 3})
        );

        assertEquals(
                DynamicListFactory.<Long>list(1L, 2L, 3L),
                list(new long[]{1, 2, 3})
        );

        assertEquals(
                DynamicListFactory.<Float>list(1F, 2F, 3F),
                list(new float[]{1, 2, 3})
        );

        assertEquals(
                DynamicListFactory.<Double>list(1D, 2D, 3D),
                list(new double[]{1, 2, 3})
        );

        assertEquals(
                DynamicListFactory.<Character>list('a', 'b', 'c'),
                list(new char[]{'a', 'b', 'c'})
        );

        assertEquals(
                DynamicListFactory.<Boolean>list(true, false, true),
                list(new boolean[]{true, false, true})
        );
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldCreateDynamicListOfTuplesFromSetOfMapEntries() {
        Collection<Map.Entry<String, Integer>> entries = new HashMap<String, Integer>() {{
            put("Key 1", 1);
            put("Key 2", 2);
            put("Key 3", 3);
        }}.entrySet();

        List<Tuple2<String, Integer>> actual = tuples(entries);
        assertEquals(3, actual.size());
        assertThat(actual, hasItems(
                new Tuple2<String, Integer>("Key 1", 1),
                new Tuple2<String, Integer>("Key 2", 2),
                new Tuple2<String, Integer>("Key 3", 3)
        ));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldCreateDynamicListOfTuplesFromMapUsingEntrySet() {
        Map<String, Integer> map = new TreeMap<String, Integer>() {{
            put("Key 1", 1);
            put("Key 2", 2);
            put("Key 3", 3);
        }};

        assertEquals(list(
                new Tuple2<String, Integer>("Key 1", 1),
                new Tuple2<String, Integer>("Key 2", 2),
                new Tuple2<String, Integer>("Key 3", 3)
        ), tuples(map));
    }

    @Test
    public void shouldCreateDynamicListOfIterativeFunctionApplicationResults() {
        assertEquals(asList(1, 1, 1, 1, 1), doTimes(5, always(1)));
    }

    @Test
    public void shouldHandleZeroFunctionApplications() {
        assertEquals(new ArrayList<String>(), doTimes(0, always("foo")));
    }

    @Test
    public void shouldHandleNegativeFunctionApplications() {
        assertEquals(new ArrayList<Character>(), doTimes(-1, always('a')));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void shouldCreateDynamicListOfOptionsFromValues() {
        assertEquals(new OptionalDynamicArrayList<String>(
                some("foo"),
                some("bar"),
                new None<String>(),
                some("baz"),
                new None<String>(),
                new None<String>()
        ), options("foo", "bar", null, "baz", null, null));
    }

}
