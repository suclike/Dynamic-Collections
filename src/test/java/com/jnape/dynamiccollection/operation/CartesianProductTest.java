package com.jnape.dynamiccollection.operation;

import org.junit.Test;
import testsupport.Item;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static testsupport.fixture.ItemFixture.*;

@SuppressWarnings("unchecked")
public class CartesianProductTest {

    @Test
    public void shouldComputeCartesianProductOfTwoLists() {
        List<Integer> evens = asList(2, 4, 6);
        List<Integer> odds = asList(1, 3, 5);

        List<List<Integer>> evensTimesOdds = asList(
                asList(2, 1),
                asList(2, 3),
                asList(2, 5),
                asList(4, 1),
                asList(4, 3),
                asList(4, 5),
                asList(6, 1),
                asList(6, 3),
                asList(6, 5)
        );

        assertEquals(evensTimesOdds, CartesianProduct.cartesianProduct(evens, odds));
    }

    @Test
    public void shouldComputeProductOfEmptyListAndPopulatedList() {
        List<Item> letters = asList(A, B, C);
        List<Item> empty = new ArrayList<Item>();

        assertEquals(new ArrayList<List<Item>>(), CartesianProduct.cartesianProduct(empty, letters));
        assertEquals(new ArrayList<List<Item>>(), CartesianProduct.cartesianProduct(letters, empty));
    }
}
