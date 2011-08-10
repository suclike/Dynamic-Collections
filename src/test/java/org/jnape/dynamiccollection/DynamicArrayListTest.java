package org.jnape.dynamiccollection;

import org.jnape.dynamiccollection.lambda.Function;
import org.junit.Test;
import testsupport.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class DynamicArrayListTest {

    private static final Item A = new Item();
    private static final Item B = new Item();
    private static final Item C = new Item();

    @Test
    public void shouldConstructWithoutArgs() {
        DynamicArrayList dynamicArrayList = new DynamicArrayList();

        assertEquals(0, dynamicArrayList.size());
    }

    @Test
    public void shouldConstructAndPopulateFromCollection() {
        Collection<Item> items = asList(A, B, C);

        DynamicArrayList<Item> dynamicArrayList = new DynamicArrayList<Item>(items);

        assertEquals(3, dynamicArrayList.size());
        assertEquals(A, dynamicArrayList.get(0));
        assertEquals(B, dynamicArrayList.get(1));
        assertEquals(C, dynamicArrayList.get(2));
    }

    @Test
    public void shouldConstructAndPopulateFromArray() {
        Item[] items = new Item[]{C, A};

        DynamicArrayList<Item> dynamicArrayList = new DynamicArrayList<Item>(items);

        assertEquals(2, dynamicArrayList.size());
        assertEquals(C, dynamicArrayList.get(0));
        assertEquals(A, dynamicArrayList.get(1));
    }

    @Test
    public void shouldConstructAndPopulateFromVarArgs() {
        DynamicArrayList<Item> dynamicArrayList = new DynamicArrayList<Item>(B, C, A, C);

        assertEquals(4, dynamicArrayList.size());
        assertEquals(B, dynamicArrayList.get(0));
        assertEquals(C, dynamicArrayList.get(1));
        assertEquals(A, dynamicArrayList.get(2));
        assertEquals(C, dynamicArrayList.get(3));
    }

    @Test
    public void shouldPolymorphToDynamicCollection() {
        DynamicCollection dynamicCollection = new DynamicArrayList();
    }

    @Test
    public void shouldPolymorphToDynamicList() {
        DynamicList dynamicList = new DynamicArrayList();
    }

    @Test
    public void shouldPolymorphToCollection() {
        Collection collection = new DynamicArrayList();
    }

    @Test
    public void shouldPolymorphToList() {
        List collection = new DynamicArrayList();
    }

    @Test
    public void shouldPolymorphToArrayList() {
        ArrayList arrayList = new DynamicArrayList();
    }

    @Test
    public void shouldTransform() {
        Function<Number, String> intoStrings = new Function<Number, String>() {
            @Override
            public String apply(Number number) {
                return number.toString();
            }
        };

        DynamicArrayList<Number> numbers = new DynamicArrayList<Number>(1, 2d, 3.5f);
        DynamicArrayList<Number> moreNumbers = new DynamicArrayList<Number>(1842.12d, 220, 1l);

        assertEquals(asList("1", "2.0", "3.5"), numbers.transform(intoStrings));
        assertEquals(asList("1842.12", "220", "1"), moreNumbers.transform(intoStrings));
    }

    @Test
    public void shouldCollect() {
        Function<Integer, Boolean> evenNumbers = new Function<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer integer) {
                return integer % 2 == 0;
            }
        };

        DynamicArrayList<Integer> integers = new DynamicArrayList<Integer>(1, 2, 3, 4, 5);
        DynamicArrayList<Integer> moreIntegers = new DynamicArrayList<Integer>(10, 11, 12, 13, 14, 15, 16);

        assertEquals(asList(2, 4), integers.collect(evenNumbers));
        assertEquals(asList(10, 12, 14, 16), moreIntegers.collect(evenNumbers));
    }
}
