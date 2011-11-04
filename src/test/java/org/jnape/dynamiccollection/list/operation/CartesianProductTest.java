package org.jnape.dynamiccollection.list.operation;

import org.jnape.dynamiccollection.list.DynamicArrayList;
import org.jnape.dynamiccollection.list.DynamicList;
import org.junit.Test;
import testsupport.Item;

import static org.junit.Assert.assertEquals;
import static testsupport.ItemFixture.*;

@SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "unchecked"})
public class CartesianProductTest {

    @Test
    public void shouldConstruct() {
        new CartesianProduct();
    }

    @Test
    public void shouldComputeCartesianProductOfTwoLists() {
        CartesianProduct cartesianProduct = new CartesianProduct();

        DynamicList<Integer> evens = new DynamicArrayList<Integer>(2, 4, 6, 8, 10);
        DynamicList<Integer> odds = new DynamicArrayList<Integer>(1, 3, 5, 7, 9);

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

        assertEquals(evensTimesOdds, cartesianProduct.execute(evens, odds));
    }

    @Test
    public void shouldComputeProductOfEmptyListAndPopulatedList() {
        CartesianProduct cartesianProduct = new CartesianProduct();

        DynamicList<Item> letters = new DynamicArrayList<Item>(A, B, C);
        DynamicList<Item> empty = new DynamicArrayList<Item>();

        assertEquals(new DynamicArrayList<DynamicList<Item>>(), cartesianProduct.execute(empty, letters));
        assertEquals(new DynamicArrayList<DynamicList<Item>>(), cartesianProduct.execute(letters, empty));
    }
}
