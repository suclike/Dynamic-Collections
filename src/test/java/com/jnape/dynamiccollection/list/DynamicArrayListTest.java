package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.DynamicCollection;
import com.jnape.dynamiccollection.datatype.Partition;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.lambda.Procedure;
import com.jnape.dynamiccollection.list.exception.ListNotSortableWithoutCustomComparatorException;
import com.jnape.dynamiccollection.list.exception.ListWasEmptyException;
import com.jnape.dynamiccollection.operator.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import testsupport.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.jnape.dynamiccollection.DynamicCollectionFactory.list;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static testsupport.ItemFixture.*;

@SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "UnusedDeclaration", "unchecked"})
public class DynamicArrayListTest {

    @Mock
    private OperationProvider operationProvider;

    @Mock
    private Concatenator concatenator;

    @Mock
    private CartesianMultiplier cartesianMultiplier;

    @Mock
    private IterativeExecutor iterativeExecutor;

    @Mock
    private Collector collector;

    @Mock
    private Transformer transformer;

    @Mock
    private Rejector rejector;

    @Mock
    private ElementExcluder elementExcluder;

    @Mock
    private Partitioner partitioner;

    @Before
    public void setUp() {
        initMocks(this);

        when(operationProvider.concatenator()).thenReturn(concatenator);
        when(operationProvider.cartesianMultiplier()).thenReturn(cartesianMultiplier);
        when(operationProvider.iterativeExecutor()).thenReturn(iterativeExecutor);
        when(operationProvider.collector()).thenReturn(collector);
        when(operationProvider.transformer()).thenReturn(transformer);
        when(operationProvider.rejector()).thenReturn(rejector);
        when(operationProvider.elementExcluder()).thenReturn(elementExcluder);
        when(operationProvider.partitioner()).thenReturn(partitioner);
    }

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
        Item[] items = {C, A};

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
    public void shouldReturnDynamicListForSubList() {
        DynamicList<Item> items = new DynamicArrayList<Item>(A, B, C);
        DynamicList<Item> subList = items.subList(0, items.size());
    }

    @Test
    public void shouldSubList() {
        List<Integer> numbers = new DynamicArrayList<Integer>(1, 2, 3, 4, 5);

        assertEquals(new DynamicArrayList<Integer>(), numbers.subList(0, 0));
        assertEquals(new DynamicArrayList<Integer>(1, 2, 3), numbers.subList(0, 3));
        assertEquals(new DynamicArrayList<Integer>(3, 4, 5), numbers.subList(2, 5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenSubListGivenNegativeToIndex() {
        new DynamicArrayList<Item>(A, B, C).subList(0, -1);
    }

    @Test
    public void shouldDelegateConcat() {
        DynamicArrayList<Integer> dynamicArrayList = new DynamicArrayList<Integer>(operationProvider);
        Collection<Integer> collection = new ArrayList<Integer>();

        DynamicList<Integer> expected = new DynamicArrayList<Integer>();
        when(concatenator.concatenate(dynamicArrayList, collection)).thenReturn(expected);

        assertSame(expected, dynamicArrayList.concat(collection));
    }

    @Test
    public void shouldDelegateCartesianProduct() {
        DynamicArrayList<Integer> dynamicArrayList = new DynamicArrayList<Integer>(operationProvider);
        Collection<Integer> collection = new ArrayList<Integer>();

        DynamicList<DynamicCollection<Integer>> expected = new DynamicArrayList<DynamicCollection<Integer>>();
        when(cartesianMultiplier.multiply(dynamicArrayList, collection)).thenReturn(expected);

        assertSame(expected, dynamicArrayList.cartesianProduct(collection));
    }

    @Test
    public void shouldDelegateEach() {
        Procedure procedure = mock(Procedure.class);
        DynamicArrayList<Object> dynamicArrayList = new DynamicArrayList<Object>(operationProvider);

        assertSame(dynamicArrayList, dynamicArrayList.each(procedure));
        verify(iterativeExecutor).iterativelyExecute(dynamicArrayList, procedure);
    }

    @Test
    public void shouldDelegateCollect() {
        Function function = mock(Function.class);
        DynamicArrayList<?> dynamicArrayList = new DynamicArrayList<Object>(operationProvider);

        DynamicList<Object> expected = new DynamicArrayList<Object>();
        when(collector.collect(dynamicArrayList, function)).thenReturn(expected);

        assertSame(expected, dynamicArrayList.collect(function));
    }

    @Test
    public void shouldDelegateTransform() {
        Function function = mock(Function.class);
        DynamicArrayList<?> dynamicArrayList = new DynamicArrayList<Object>(operationProvider);

        DynamicList<Object> expected = new DynamicArrayList<Object>();
        when(transformer.transform(dynamicArrayList, function)).thenReturn(expected);

        assertSame(expected, dynamicArrayList.transform(function));
    }

    @Test
    public void shouldDelegateReject() {
        Function function = mock(Function.class);
        DynamicArrayList<?> dynamicArrayList = new DynamicArrayList<Object>(operationProvider);

        DynamicList<Object> expected = new DynamicArrayList<Object>();
        when(rejector.reject(dynamicArrayList, function)).thenReturn(expected);

        assertSame(expected, dynamicArrayList.reject(function));
    }

    @Test
    public void shouldDelegateWithout() {
        DynamicArrayList<Object> dynamicArrayList = new DynamicArrayList<Object>(operationProvider);
        Object[] exclusions = {};

        DynamicList<Object> expected = new DynamicArrayList<Object>();
        when(elementExcluder.exclude(dynamicArrayList, exclusions)).thenReturn(expected);

        assertSame(expected, dynamicArrayList.without(exclusions));
    }

    @Test
    public void shouldDelegatePartition() {
        Function function = mock(Function.class);
        DynamicArrayList<Object> dynamicArrayList = new DynamicArrayList<Object>(operationProvider);

        Partition<Object> expected = new Partition<Object>(emptyList(), emptyList());
        when(partitioner.partition(dynamicArrayList, function)).thenReturn(expected);

        assertSame(expected, dynamicArrayList.partition(function));
    }

    @Test
    public void shouldGetUniqueElements() {
        DynamicArrayList<String> names = new DynamicArrayList<String>(
                "Alex", "Albert", "Bill", "Bill", "Bob", "Chad", "Chris", "Chris"
        );
        DynamicArrayList<Integer> ages = new DynamicArrayList<Integer>(12, 42, 38, 38, 62, 25, 59, 59);

        assertEquals(new DynamicArrayList<String>("Alex", "Albert", "Bill", "Bob", "Chad", "Chris"), names.unique());
        assertEquals(new DynamicArrayList<Integer>(12, 42, 38, 62, 25, 59), ages.unique());
    }

    @Test
    public void shouldReverse() {
        DynamicArrayList<Item> items = new DynamicArrayList<Item>(A, B, C);
        assertEquals(new DynamicArrayList<Item>(C, B, A), items.reverse());

        DynamicArrayList<Integer> numbers = new DynamicArrayList<Integer>(1, 2, 3);
        assertEquals(new DynamicArrayList<Integer>(3, 2, 1), numbers.reverse());
    }

    @Test
    public void shouldSort() {
        DynamicArrayList<Character> letters = new DynamicArrayList<Character>('c', 'a', 'd', 'b');
        assertEquals(new DynamicArrayList<Character>('a', 'b', 'c', 'd'), letters.sort());

        DynamicArrayList<Integer> numbers = new DynamicArrayList<Integer>(5, 3, 2, 4, 1);
        assertEquals(new DynamicArrayList<Integer>(1, 2, 3, 4, 5), numbers.sort());
    }

    @Test(expected = ListNotSortableWithoutCustomComparatorException.class)
    public void shouldThrowExceptionIfSortingNonComparablesWithoutCustomComparator() {
        list(A).sort();
    }

    @Test
    public void shouldSortWithCustomComparator() {
        Function<Item, String> byLabel = new Function<Item, String>() {
            @Override
            public String apply(Item item) {
                return item.getLabel();
            }
        };

        DynamicArrayList<Item> ABC = new DynamicArrayList<Item>(A, B, C);

        assertEquals(ABC, new DynamicArrayList<Item>(B, C, A).sort(byLabel));
        assertEquals(ABC, new DynamicArrayList<Item>(C, B, A).sort(byLabel));
        assertEquals(ABC, new DynamicArrayList<Item>(A, C, B).sort(byLabel));
    }

    @Test
    public void shouldGetFirst() {
        DynamicArrayList<Item> items = new DynamicArrayList<Item>(A, B, C);
        assertSame(A, items.first());

        DynamicArrayList<Integer> numbers = new DynamicArrayList<Integer>(3, 2, 1);
        assertSame(3, numbers.first());
    }

    @Test(expected = ListWasEmptyException.class)
    public void shouldThrowExceptionIfGetFirstOnEmptyList() {
        new DynamicArrayList<Object>().first();
    }

    @Test
    public void shouldGetLast() {
        DynamicArrayList<Item> items = new DynamicArrayList<Item>(A, B, C);
        assertSame(C, items.last());

        DynamicArrayList<Integer> numbers = new DynamicArrayList<Integer>(3, 2, 1);
        assertSame(1, numbers.last());
    }

    @Test(expected = ListWasEmptyException.class)
    public void shouldThrowExceptionIfGetLastOnEmptyList() {
        new DynamicArrayList<Object>().last();
    }

    @Test
    public void shouldGetMax() {
        Function<String, Integer> wordLength = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };

        assertEquals("Spain", new DynamicArrayList<String>("the", "rain", "in", "Spain").max(wordLength));

        Function<Integer, Integer> integerValue = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer;
            }
        };

        assertEquals((Integer) 5, new DynamicArrayList<Integer>(1, 2, 3, 4, 5).max(integerValue));
    }

    @Test
    public void shouldGetMin() {
        Function<String, Integer> wordLength = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };

        assertEquals("in", new DynamicArrayList<String>("the", "rain", "in", "Spain").min(wordLength));

        Function<Integer, Integer> integerValue = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer;
            }
        };

        assertEquals((Integer) 1, new DynamicArrayList<Integer>(1, 2, 3, 4, 5).min(integerValue));
    }

    @Test
    public void shouldJoinElementsIntoStringWithCombiner() {
        assertEquals("a,b,c,d", new DynamicArrayList<Character>('a', 'b', 'c', 'd').join(","));
        assertEquals("1 and a 2 and a 3", new DynamicArrayList<Integer>(1, 2, 3).join(" and a "));
    }
}
