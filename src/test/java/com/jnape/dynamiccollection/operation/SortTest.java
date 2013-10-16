package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;
import org.junit.Test;
import testsupport.Item;
import testsupport.OrderedItem;

import java.util.List;

import static com.jnape.dynamiccollection.list.factory.DynamicListFactory.list;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static testsupport.fixture.ItemFixture.*;

@SuppressWarnings("unchecked")
public class SortTest {

    private static final MonadicFunction<Item, String>         BY_LABEL = new MonadicFunction<Item, String>() {
        @Override
        public String apply(Item item) {
            return item.getLabel();
        }
    };
    private static final MonadicFunction<OrderedItem, Integer> BY_ORDER = new MonadicFunction<OrderedItem, Integer>() {
        @Override
        public Integer apply(OrderedItem orderedItem) {
            return orderedItem.getOrder();
        }
    };

    @Test
    public void shouldSortListUsingComparisonMapper() {
        List<Item> items = asList(B, C, A);

        assertEquals(list(A, B, C), Sort.sort(items, BY_LABEL));
    }

    @Test
    public void shouldSortListUsingMultipleComparisonMappers() {
        List<OrderedItem> orderedItems = asList(A2, B1, A1);

        assertEquals(list(A1, A2, B1), Sort.sort(orderedItems, BY_LABEL, BY_ORDER));
    }
}
