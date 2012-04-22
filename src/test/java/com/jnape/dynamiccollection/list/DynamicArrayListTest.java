package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.DynamicCollection;
import com.jnape.dynamiccollection.datatype.Partition;
import com.jnape.dynamiccollection.lambda.Accumulator;
import com.jnape.dynamiccollection.lambda.Function;
import com.jnape.dynamiccollection.lambda.Procedure;
import com.jnape.dynamiccollection.list.exception.ListNotSortableWithoutCustomComparatorException;
import com.jnape.dynamiccollection.list.exception.ListWasEmptyException;
import org.junit.Test;
import testsupport.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static com.jnape.dynamiccollection.DynamicCollectionFactory.list;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static testsupport.ItemFixture.*;

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
    public void shouldConcatAnotherCollection() {
        DynamicArrayList<Integer> oneThroughThree = new DynamicArrayList<Integer>(1, 2, 3);
        Collection<Integer> fourAndFive = list(4, 5);

        assertEquals(list(1, 2, 3, 4, 5), oneThroughThree.concat(fourAndFive));

        DynamicArrayList<String> aAndB = new DynamicArrayList<String>("a", "b");
        Collection<String> cThroughG = list("c", "d", "e", "f", "g");

        assertEquals(
                list("a", "b", "c", "d", "e", "f", "g"),
                aAndB.concat(cThroughG)
        );
    }

    @Test
    public void shouldExecuteProcedureOnEachElement() {
        DynamicArrayList<String> theRainInSpain = new DynamicArrayList<String>("The", "rain", "in", "Spain");

        final List<String> allCaps = list();

        Procedure<String> addUpperCasedToAllCaps = new Procedure<String>() {
            @Override
            public void execute(String word) {
                allCaps.add(word.toUpperCase());
            }
        };

        assertSame(theRainInSpain, theRainInSpain.each(addUpperCasedToAllCaps));
        assertEquals(list("THE", "RAIN", "IN", "SPAIN"), allCaps);
    }

    @Test
    public void shouldCollectElements() {
        DynamicArrayList<Integer> oneThroughFive = new DynamicArrayList<Integer>(1, 2, 3, 4, 5);
        Function<Integer, Boolean> greaterThanThree = new Function<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer integer) {
                return integer > 3;
            }
        };

        DynamicList<Integer> fourAndFive = list(4, 5);
        assertEquals(fourAndFive, oneThroughFive.collect(greaterThanThree));


        DynamicArrayList<Character> aThroughE = new DynamicArrayList<Character>('a', 'b', 'c', 'd', 'e');
        Function<Character, Boolean> vowels = new Function<Character, Boolean>() {
            @Override
            public Boolean apply(Character character) {
                return list('a', 'e', 'i', 'o', 'u').contains(character);
            }
        };

        DynamicList<Character> aAndE = list('a', 'e');
        assertEquals(aAndE, aThroughE.collect(vowels));
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


        DynamicArrayList<String> theRainInSpain = new DynamicArrayList<String>("The", "rain", "in", "Spain");
        Function<String, Boolean> wordsGreaterThanThreeCharacters = new Function<String, Boolean>() {
            @Override
            public Boolean apply(String word) {
                return word.length() > 3;
            }
        };

        DynamicList<String> theAndIn = list("The", "in");
        assertEquals(theAndIn, theRainInSpain.reject(wordsGreaterThanThreeCharacters));
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
        assertEquals(transformation, prepositions.transform(intoWordLength));


        DynamicArrayList<Double> perfectSquares = new DynamicArrayList<Double>(1d, 4d, 9d, 16d, 25d);
        Function<Double, Double> squareRoot = new Function<Double, Double>() {
            @Override
            public Double apply(Double perfectSquare) {
                return Math.sqrt(perfectSquare);
            }
        };

        DynamicList<Double> squareRoots = list(1d, 2d, 3d, 4d, 5d);
        assertEquals(squareRoots, perfectSquares.transform(squareRoot));
    }

    @Test
    public void shouldCreateDynamicCollectionWithoutElements() {
        DynamicArrayList<Character> aThroughE = new DynamicArrayList<Character>('a', 'b', 'c', 'd', 'e');
        DynamicList<Character> bAndC = list('b', 'c');
        assertEquals(bAndC, aThroughE.without('a', 'd', 'e'));

        DynamicArrayList<Integer> oneThroughTen = new DynamicArrayList<Integer>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        DynamicList<Integer> empty = list();
        assertEquals(empty, oneThroughTen.without(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
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
        DynamicArrayList<Integer> ages = new DynamicArrayList<Integer>(12, 42, 38, 38, 62, 25, 59, 59);

        assertEquals(new DynamicArrayList<String>("Alex", "Albert", "Bill", "Bob", "Chad", "Chris"), names.unique());
        assertEquals(new DynamicArrayList<Integer>(12, 42, 38, 62, 25, 59), ages.unique());
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

        DynamicArrayList<Integer> oddNumbers = new DynamicArrayList<Integer>(1, 3);
        List<Integer> evenNumbers = list(2, 4);

        DynamicList<DynamicList<Integer>> permutedNumbers = new DynamicArrayList<DynamicList<Integer>>(
                list(1, 2),
                list(1, 4),
                list(3, 2),
                list(3, 4)
        );

        assertEquals(permutedNumbers, oddNumbers.cartesianProduct(evenNumbers));
    }

    @Test
    public void shouldFoldLeft() {
        DynamicArrayList<String> theRainInSpain = new DynamicArrayList<String>("The", "rain", "in", "Spain");

        assertEquals((Integer) 120, theRainInSpain.foldLeft(1, new Accumulator<Integer, String>() {
            @Override
            public Integer apply(Integer accumulation, String word) {
                return word.length() * accumulation;
            }
        }));
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
    public void shouldReverse() {
        DynamicArrayList<Item> items = new DynamicArrayList<Item>(A, B, C);
        assertEquals(new DynamicArrayList<Item>(C, B, A), items.reverse());

        DynamicArrayList<Integer> numbers = new DynamicArrayList<Integer>(1, 2, 3);
        assertEquals(new DynamicArrayList<Integer>(3, 2, 1), numbers.reverse());
    }

    @Test
    public void shouldJoinElementsIntoStringWithCombiner() {
        assertEquals("a,b,c,d", new DynamicArrayList<Character>('a', 'b', 'c', 'd').join(","));
        assertEquals("1 and a 2 and a 3", new DynamicArrayList<Integer>(1, 2, 3).join(" and a "));
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
}
