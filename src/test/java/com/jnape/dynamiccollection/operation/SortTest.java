package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.MonadicFunction;
import org.junit.Test;
import testsupport.Item;

import java.util.List;

import static com.jnape.dynamiccollection.list.factory.DynamicListFactory.list;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static testsupport.ItemFixture.*;

public class SortTest {

    @Test
    public void shouldSortListUsingComparisonMapper() {
        List<Item> items = asList(B, C, A);

        MonadicFunction<Item, String> byLabel = new MonadicFunction<Item, String>() {
            @Override
            public String apply(Item item) {
                return item.getLabel();
            }
        };

        assertEquals(list(A, B, C), Sort.sort(items, byLabel));
    }

}
