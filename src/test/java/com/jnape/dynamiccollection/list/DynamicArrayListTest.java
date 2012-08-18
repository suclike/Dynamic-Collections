package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.DynamicCollection;
import com.jnape.dynamiccollection.datatype.Partition;
import com.jnape.dynamiccollection.lambda.Accumulator;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.lambda.IndexedProcedure;
import com.jnape.dynamiccollection.lambda.Procedure;
import com.jnape.dynamiccollection.list.exception.ListWasEmptyException;
import org.junit.Test;
import testsupport.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static com.jnape.dynamiccollection.factory.DynamicListFactory.list;
import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static testsupport.ItemFixture.*;
import static testsupport.assertion.InheritanceAssert.assertThat;

@SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "UnusedDeclaration", "unchecked"})
public class DynamicArrayListTest {

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
    public void shouldConstructAndPopulateFromIterator() {
        Iterator<Item> iterator = asList(A, B, C).iterator();
        DynamicArrayList<Item> dynamicArrayList = new DynamicArrayList<Item>(iterator);

        assertEquals(3, dynamicArrayList.size());
        assertEquals(A, dynamicArrayList.get(0));
        assertEquals(B, dynamicArrayList.get(1));
        assertEquals(C, dynamicArrayList.get(2));
    }

    @Test
    public void shouldHaveExpectedPolymorphisms() {
        assertThat(DynamicArrayList.class)
                .isA(DynamicCollection.class)
                .isA(DynamicList.class)
                .isA(Collection.class)
                .isA(List.class)
                .isA(ArrayList.class);
    }

    @Test
    public void shouldSubList() {
        DynamicArrayList<Integer> numbers = new DynamicArrayList<Integer>(1, 2, 3, 4, 5);

        assertEquals(list(), numbers.subList(0, 0));
        assertEquals(list(1, 2, 3), numbers.subList(0, 3));
        assertEquals(list(3, 4, 5), numbers.subList(2, 5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenSubListGivenNegativeToIndex() {
        new DynamicArrayList<Integer>(1, 2, 3).subList(0, -1);
    }

    @Test
    public void shouldConcatAnotherCollection() {
        DynamicArrayList<Integer> oneThroughThree = new DynamicArrayList<Integer>(1, 2, 3);
        Collection<Integer> fourAndFive = list(4, 5);

        assertEquals(list(1, 2, 3, 4, 5), oneThroughThree.concat(fourAndFive));
    }

    @Test
    public void shouldConcatElements() {
        DynamicArrayList<String> theRain = new DynamicArrayList<String>("The", "rain");
        String[] inSpain = {"in", "Spain"};

        assertEquals(list("The", "rain", "in", "Spain"), theRain.concat(inSpain));
    }

    @Test
    public void shouldExecuteProcedureForEachElementAndIndex() {
        DynamicArrayList<Integer> numbers = new DynamicArrayList<Integer>(1, 2, 3, 4, 5);

        final List<Integer> products = list();

        IndexedProcedure<Integer> elementTimesIndex = new IndexedProcedure<Integer>() {
            @Override
            public void execute(Integer element, Integer index) {
                products.add(element * index);
            }
        };

        assertSame(numbers, numbers.each(elementTimesIndex));
        assertEquals(list(0, 2, 6, 12, 20), products);
    }

    @Test
    public void shouldExecuteProcedureForEachElement() {
        DynamicArrayList<String> theRainInSpain = new DynamicArrayList<String>("The", "rain", "in", "Spain");

        final List<String> allCaps = list();

        Procedure<String> addUpperCasedToAllCaps = new Procedure<String>() {
            @Override
            public void execute(String word) {
                allCaps.add(word.toUpperCase());
            }
        };

        assertSame(theRainInSpain, theRainInSpain.forEach(addUpperCasedToAllCaps));
        assertEquals(list("THE", "RAIN", "IN", "SPAIN"), allCaps);
    }

    @Test
    public void shouldFilterElements() {
        DynamicArrayList<Integer> oneThroughFive = new DynamicArrayList<Integer>(1, 2, 3, 4, 5);
        Function<Integer, Boolean> greaterThanThree = new Function<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer integer) {
                return integer > 3;
            }
        };

        DynamicList<Integer> fourAndFive = list(4, 5);
        assertEquals(fourAndFive, oneThroughFive.filter(greaterThanThree));
    }

    @Test
    public void shouldRejectElements() {
        DynamicArrayList<Integer> oneThroughTen = new DynamicArrayList<Integer>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Function<Integer, Boolean> oddNumbers = new Function<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer integer) {
                return integer % 2 != 0;
            }
        };

        DynamicList<Integer> twoFourSixEightTen = list(2, 4, 6, 8, 10);
        assertEquals(twoFourSixEightTen, oneThroughTen.reject(oddNumbers));
    }

    @Test
    public void shouldTransformElements() {
        DynamicArrayList<String> prepositions = new DynamicArrayList<String>("Aboard", "About", "Above", "Across");
        Function<String, Object> intoWordLength = new Function<String, Object>() {
            @Override
            public Object apply(String word) {
                return word.length();
            }
        };

        DynamicList<Integer> transformation = list(6, 5, 5, 6);
        assertEquals(transformation, prepositions.map(intoWordLength));
    }

    @Test
    public void shouldCreateDynamicCollectionWithoutElements() {
        DynamicArrayList<Character> aThroughE = new DynamicArrayList<Character>('a', 'b', 'c', 'd', 'e');
        DynamicList<Character> bAndC = list('b', 'c');
        assertEquals(bAndC, aThroughE.without('a', 'd', 'e'));
    }

    @Test
    public void shouldPartitionElements() {
        DynamicArrayList<Integer> oneThroughTen = new DynamicArrayList<Integer>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Function<Integer, Boolean> intoEvensAndOdds = new Function<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer integer) {
                return integer % 2 == 0;
            }
        };

        DynamicList<Integer> trues = list(2, 4, 6, 8, 10);
        DynamicList<Integer> falses = list(1, 3, 5, 7, 9);

        Partition<Integer> partition = oneThroughTen.partition(intoEvensAndOdds);
        assertEquals(trues, partition.trues());
        assertEquals(falses, partition.falses());
    }

    @Test
    public void shouldGetUniqueElements() {
        DynamicArrayList<String> names = new DynamicArrayList<String>(
                "Alex", "Albert", "Bill", "Bill", "Bob", "Chad", "Chris", "Chris"
        );
        assertEquals(new DynamicArrayList<String>("Alex", "Albert", "Bill", "Bob", "Chad", "Chris"), names.unique());
    }

    @Test
    public void shouldDivideIntoGroups() {
        DynamicList<Number> oneAndTwo = new DynamicArrayList<Number>(1, 2.0);
        DynamicList<Number> threeAndFour = new DynamicArrayList<Number>(3d, 4);
        DynamicList<Number> fiveAndSix = new DynamicArrayList<Number>(5.0, 6d);

        DynamicList<Number> oneThroughSix = oneAndTwo.concat(threeAndFour).concat(fiveAndSix);

        assertEquals(list(oneAndTwo, threeAndFour, fiveAndSix), oneThroughSix.inGroupsOf(2));
    }

    @Test
    public void shouldMatchIfAny() {
        DynamicArrayList<Boolean> allTrue = new DynamicArrayList<Boolean>(
                true, true, true, true, true
        );

        Function<Boolean, Boolean> trues = new Function<Boolean, Boolean>() {
            @Override
            public Boolean apply(Boolean bool) {
                return bool;
            }
        };
        Function<Boolean, Boolean> falses = new Function<Boolean, Boolean>() {
            @Override
            public Boolean apply(Boolean bool) {
                return !bool;
            }
        };

        assertTrue(allTrue.any(trues));
        assertFalse(allTrue.any(falses));
    }

    @Test
    public void shouldMatchIfAll() {
        DynamicArrayList<Boolean> allFalse = new DynamicArrayList<Boolean>(
                false, false, false, false, false
        );

        Function<Boolean, Boolean> areFalse = new Function<Boolean, Boolean>() {
            @Override
            public Boolean apply(Boolean bool) {
                return !bool;
            }
        };
        Function<Boolean, Boolean> areTrue = new Function<Boolean, Boolean>() {
            @Override
            public Boolean apply(Boolean bool) {
                return bool;
            }
        };

        assertTrue(allFalse.all(areFalse));
        assertFalse(allFalse.all(areTrue));
    }

    @Test
    public void shouldComputeCartesianProduct() {
        DynamicArrayList<String> firstNames = new DynamicArrayList<String>("Adam", "Bob", "Charlie");
        List<String> lastNames = list("West", "Barker", "Kaufman");

        DynamicList<DynamicList<String>> permutedNames = new DynamicArrayList<DynamicList<String>>(
                list("Adam", "West"),
                list("Adam", "Barker"),
                list("Adam", "Kaufman"),
                list("Bob", "West"),
                list("Bob", "Barker"),
                list("Bob", "Kaufman"),
                list("Charlie", "West"),
                list("Charlie", "Barker"),
                list("Charlie", "Kaufman")
        );

        assertEquals(permutedNames, firstNames.cartesianProduct(lastNames));
    }

    @Test
    public void shouldComputeCartesianProductOfSelf() {
        DynamicArrayList<Integer> oneAndTwo = new DynamicArrayList<Integer>(1, 2);

        DynamicList<DynamicList<Integer>> permutations = new DynamicArrayList<DynamicList<Integer>>(
                list(1, 1),
                list(1, 2),
                list(2, 1),
                list(2, 2)
        );

        assertEquals(permutations, oneAndTwo.cartesianProduct());
    }

    @Test
    public void shouldFoldLeft() {
        DynamicArrayList<String> theRainInSpain = new DynamicArrayList<String>("The", "rain", "in", "Spain");

        Accumulator<Integer, String> product = new Accumulator<Integer, String>() {
            @Override
            public Integer apply(Integer accumulation, String word) {
                return word.length() * accumulation;
            }
        };

        assertEquals((Integer) 120, theRainInSpain.foldLeft(1, product));
    }

    @Test
    public void shouldFoldRight() {
        DynamicArrayList<Integer> oneThroughFive = new DynamicArrayList<Integer>(1, 2, 3, 4, 5);

        Accumulator<Integer, Integer> sum = new Accumulator<Integer, Integer>() {
            @Override
            public Integer apply(Integer accumulation, Integer integer) {
                return integer + accumulation;
            }
        };

        assertEquals((Integer) 15, oneThroughFive.foldRight(0, sum));
    }

    @Test
    public void shouldReduce() {
        DynamicArrayList<Integer> oneThroughFive = new DynamicArrayList<Integer>(1, 2, 3, 4, 5);
        Accumulator<Integer, Integer> product = new Accumulator<Integer, Integer>() {
            @Override
            public Integer apply(Integer accumulation, Integer integer) {
                return accumulation * integer;
            }
        };

        assertEquals((Integer) 120, oneThroughFive.reduce(product));
    }

    @Test(expected = ListWasEmptyException.class)
    public void shouldThrowExceptionIfReduceOnEmptyList() {
        Accumulator<Integer, Integer> accumulator = new Accumulator<Integer, Integer>() {
            @Override
            public Integer apply(Integer accumulation, Integer integer) {
                return null;
            }
        };

        new DynamicArrayList<Integer>().reduce(accumulator);
    }

    @Test
    public void shouldScanLeft() {
        DynamicArrayList<Integer> oneThroughFive = new DynamicArrayList<Integer>(1, 2, 3, 4, 5);


        Accumulator<Integer, Integer> partialSums = new Accumulator<Integer, Integer>() {
            @Override
            public Integer apply(Integer accumulation, Integer integer) {
                return accumulation + integer;
            }
        };

        assertEquals(list(0, 1, 3, 6, 10, 15), oneThroughFive.scanLeft(0, partialSums));
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
    public void shouldReverse() {
        DynamicArrayList<Item> items = new DynamicArrayList<Item>(A, B, C);
        assertEquals(new DynamicArrayList<Item>(C, B, A), items.reverse());
    }

    @Test
    public void shouldJoinElementsIntoStringWithCombiner() {
        assertEquals("1 and a 2 and a 3", new DynamicArrayList<Integer>(1, 2, 3).join(" and a "));
    }

    @Test
    public void shouldGetFirst() {
        DynamicArrayList<Item> items = new DynamicArrayList<Item>(A, B, C);
        assertSame(A, items.first());
    }

    @Test(expected = ListWasEmptyException.class)
    public void shouldThrowExceptionIfGetFirstOnEmptyList() {
        new DynamicArrayList<Object>().first();
    }

    @Test
    public void shouldGetLast() {
        DynamicArrayList<Integer> numbers = new DynamicArrayList<Integer>(1, 2, 3);
        assertSame(3, numbers.last());
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
    }

    @Test
    public void shouldGetMin() {
        Function<Integer, Integer> integerValue = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer;
            }
        };

        assertEquals((Integer) 1, new DynamicArrayList<Integer>(1, 2, 3, 4, 5).min(integerValue));
    }
}
