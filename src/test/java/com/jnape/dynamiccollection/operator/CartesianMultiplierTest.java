package com.jnape.dynamiccollection.operator;

import com.jnape.dynamiccollection.DynamicCollection;
import com.jnape.dynamiccollection.list.DynamicArrayList;
import com.jnape.dynamiccollection.list.DynamicList;
import org.junit.Test;
import testsupport.Item;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static testsupport.ItemFixture.*;

@SuppressWarnings("unchecked")
public class CartesianMultiplierTest {

    @Test
    public void shouldComputeCartesianProductOfTwoLists() {
        List<Integer> evens = asList(2, 4, 6, 8, 10);
        List<Integer> odds = asList(1, 3, 5, 7, 9);

        DynamicList<DynamicList<Integer>> evensTimesOdds = new DynamicArrayList<DynamicList<Integer>>(
                new DynamicArrayList<Integer>(2, 1),
                new DynamicArrayList<Integer>(2, 3),
                new DynamicArrayList<Integer>(2, 5),
                new DynamicArrayList<Integer>(2, 7),
                new DynamicArrayList<Integer>(2, 9),
                new DynamicArrayList<Integer>(4, 1),
                new DynamicArrayList<Integer>(4, 3),
                new DynamicArrayList<Integer>(4, 5),
                new DynamicArrayList<Integer>(4, 7),
                new DynamicArrayList<Integer>(4, 9),
                new DynamicArrayList<Integer>(6, 1),
                new DynamicArrayList<Integer>(6, 3),
                new DynamicArrayList<Integer>(6, 5),
                new DynamicArrayList<Integer>(6, 7),
                new DynamicArrayList<Integer>(6, 9),
                new DynamicArrayList<Integer>(8, 1),
                new DynamicArrayList<Integer>(8, 3),
                new DynamicArrayList<Integer>(8, 5),
                new DynamicArrayList<Integer>(8, 7),
                new DynamicArrayList<Integer>(8, 9),
                new DynamicArrayList<Integer>(10, 1),
                new DynamicArrayList<Integer>(10, 3),
                new DynamicArrayList<Integer>(10, 5),
                new DynamicArrayList<Integer>(10, 7),
                new DynamicArrayList<Integer>(10, 9)
        );

        assertEquals(evensTimesOdds, CartesianMultiplier.multiply(evens, odds));
    }

    @Test
    public void shouldComputeProductOfEmptyListAndPopulatedList() {
        DynamicList<Item> letters = new DynamicArrayList<Item>(A, B, C);
        DynamicList<Item> empty = new DynamicArrayList<Item>();

        assertEquals(new DynamicArrayList<DynamicCollection<Item>>(), CartesianMultiplier.multiply(empty, letters));
        assertEquals(new DynamicArrayList<DynamicCollection<Item>>(), CartesianMultiplier.multiply(letters, empty));
    }
}
