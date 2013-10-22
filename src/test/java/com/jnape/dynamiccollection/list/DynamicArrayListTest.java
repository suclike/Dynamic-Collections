package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.datatype.Partition;
import com.jnape.dynamiccollection.datatype.tuple.Tuple2;
import com.jnape.dynamiccollection.lambda.dyadic.Accumulator;
import com.jnape.dynamiccollection.lambda.dyadic.DyadicFunction;
import com.jnape.dynamiccollection.lambda.dyadic.IndexedFunction;
import com.jnape.dynamiccollection.lambda.dyadic.IndexedProcedure;
import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;
import com.jnape.dynamiccollection.lambda.monadic.MonadicProcedure;
import com.jnape.dynamiccollection.lambda.monadic.Predicate;
import com.jnape.dynamiccollection.list.exception.ListWasEmptyException;
import org.junit.Assert;
import org.junit.Test;
import testsupport.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static com.jnape.dynamiccollection.datatype.option.Some.some;
import static com.jnape.dynamiccollection.datatype.tuple.TupleFactory.tuple;
import static com.jnape.dynamiccollection.lambda.monadic.builtin.LessThanOrEqualTo.lte;
import static java.lang.String.format;
import static java.util.Arrays.asList;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.hasItems;
import static testsupport.assertion.InheritanceAssert.assertThat;
import static testsupport.fixture.ItemFixture.*;

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
        DynamicArrayList<Item> dynamicArrayList = new DynamicArrayList<Item>(new Item[]{A, B, C});

        assertEquals(3, dynamicArrayList.size());
        assertEquals(A, dynamicArrayList.get(0));
        assertEquals(B, dynamicArrayList.get(1));
        assertEquals(C, dynamicArrayList.get(2));
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
                .isA(ArrayList.class)
                .isA(DynamicList.class);
    }

    @Test
    public void shouldGetElementsUsingIndex() {
        DynamicArrayList<String> abc = new DynamicArrayList<String>("a", "b", "c");

        assertEquals("a", abc.get(0));
    }

    @Test
    public void shouldGetElementsFromEndUsingNegativeNumbersAsIndexes() {
        DynamicArrayList<String> abc = new DynamicArrayList<String>("a", "b", "c");

        assertEquals("c", abc.get(-1));
    }

    @Test
    public void shouldSubList() {
        DynamicArrayList<Integer> numbers = new DynamicArrayList<Integer>(1, 2, 3, 4, 5);

        assertEquals(new DynamicArrayList<Integer>(), numbers.subList(0, 0));
        assertEquals(new DynamicArrayList<Integer>(1, 2, 3), numbers.subList(0, 3));
        assertEquals(new DynamicArrayList<Integer>(3, 4, 5), numbers.subList(2, 5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenSubListGivenNegativeToIndex() {
        new DynamicArrayList<Integer>(1, 2, 3).subList(0, -1);
    }

    @Test
    public void shouldConcatAnotherCollection() {
        DynamicArrayList<Integer> oneThroughThree = new DynamicArrayList<Integer>(1, 2, 3);
        Collection<Integer> fourAndFive = new DynamicArrayList<Integer>(4, 5);

        assertEquals(new DynamicArrayList<Integer>(1, 2, 3, 4, 5), oneThroughThree.concat(fourAndFive));
    }

    @Test
    public void shouldConcatElements() {
        DynamicArrayList<String> theRain = new DynamicArrayList<String>("The", "rain");
        String[] inSpain = {"in", "Spain"};

        assertEquals(new DynamicArrayList<String>("The", "rain", "in", "Spain"), theRain.concat(inSpain));
    }

    @Test
    public void shouldFindElement() {
        DynamicArrayList<Integer> oneThroughFive = new DynamicArrayList<Integer>(5, 4, 3, 2, 1);
        assertEquals(some(3), oneThroughFive.find(lte(3)));
    }

    @Test
    public void shouldExecuteProcedureForEachElementAndIndex() {
        DynamicArrayList<Integer> numbers = new DynamicArrayList<Integer>(1, 2, 3, 4, 5);

        final List<Integer> products = new ArrayList<Integer>();

        IndexedProcedure<Integer> elementTimesIndex = new IndexedProcedure<Integer>() {
            @Override
            public void execute(Integer element, Integer index) {
                products.add(element * index);
            }
        };

        assertSame(numbers, numbers.each(elementTimesIndex));
        assertEquals(new DynamicArrayList<Integer>(0, 2, 6, 12, 20), products);
    }

    @Test
    public void shouldExecuteProcedureForEachElement() {
        DynamicArrayList<String> theRainInSpain = new DynamicArrayList<String>("The", "rain", "in", "Spain");

        final List<String> allCaps = new ArrayList<String>();

        MonadicProcedure<String> addUpperCasedToAllCaps = new MonadicProcedure<String>() {
            @Override
            public void execute(String word) {
                allCaps.add(word.toUpperCase());
            }
        };

        assertSame(theRainInSpain, theRainInSpain.forEach(addUpperCasedToAllCaps));
        assertEquals(new DynamicArrayList<String>("THE", "RAIN", "IN", "SPAIN"), allCaps);
    }

    @Test
    public void shouldFilterElements() {
        DynamicArrayList<Integer> oneThroughFive = new DynamicArrayList<Integer>(1, 2, 3, 4, 5);
        Predicate<Integer> greaterThanThree = new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer integer) {
                return integer > 3;
            }
        };

        DynamicArrayList<Integer> fourAndFive = new DynamicArrayList<Integer>(4, 5);
        assertEquals(fourAndFive, oneThroughFive.filter(greaterThanThree));
    }

    @Test
    public void shouldRejectElements() {
        DynamicArrayList<Integer> oneThroughTen = new DynamicArrayList<Integer>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Predicate<Integer> oddNumbers = new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer integer) {
                return integer % 2 != 0;
            }
        };

        DynamicArrayList<Integer> twoFourSixEightTen = new DynamicArrayList<Integer>(2, 4, 6, 8, 10);
        assertEquals(twoFourSixEightTen, oneThroughTen.reject(oddNumbers));
    }

    @Test
    public void shouldMapOverList() {
        DynamicArrayList<String> prepositions = new DynamicArrayList<String>("Aboard", "About", "Above", "Across");
        MonadicFunction<String, Integer> intoWordLength = new MonadicFunction<String, Integer>() {
            @Override
            public Integer apply(String word) {
                return word.length();
            }
        };

        DynamicArrayList<Integer> mapped = new DynamicArrayList<Integer>(6, 5, 5, 6);
        assertEquals(mapped, prepositions.map(intoWordLength));
    }

    @Test
    public void shouldMapOverListWithIndex() {
        DynamicArrayList<String> verbs = new DynamicArrayList<String>("grow", "go", "see", "be");
        DyadicFunction<Number, String, Tuple2<Number, Integer>> intoIndexAndWordLength = new IndexedFunction<String, Tuple2<Number, Integer>>() {
            @Override
            public Tuple2<Number, Integer> apply(Number index, String string) {
                return tuple(index, string.length());
            }
        };

        DynamicArrayList<Tuple2<Number, Integer>> mapped = new DynamicArrayList<Tuple2<Number, Integer>>(
                new Tuple2<Number, Integer>(0, 4),
                new Tuple2<Number, Integer>(1, 2),
                new Tuple2<Number, Integer>(2, 3),
                new Tuple2<Number, Integer>(3, 2)
        );

        assertEquals(mapped, verbs.map(intoIndexAndWordLength));
    }

    @Test
    public void shouldMapOverListWhilePredicate() {
        DynamicArrayList<Integer> numbers = new DynamicArrayList<Integer>(1, 2, 3, 4, 5);

        MonadicFunction<Integer, Integer> timesTwo = new MonadicFunction<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer * 2;
            }
        };
        Predicate<Integer> lessThanEight = new

                Predicate<Integer>() {
                    @Override
                    public Boolean apply(Integer integer) {
                        return integer < 8;
                    }
                };

        assertEquals(new DynamicArrayList<Integer>(2, 4, 6), numbers.mapWhile(timesTwo, lessThanEight));
    }

    @Test
    public void shouldCreateDynamicCollectionWithoutElements() {
        DynamicArrayList<Character> aThroughE = new DynamicArrayList<Character>('a', 'b', 'c', 'd', 'e');
        DynamicArrayList<Character> bAndC = new DynamicArrayList<Character>('b', 'c');
        assertEquals(bAndC, aThroughE.without(new DynamicArrayList<Character>('a', 'd', 'e')));
    }

    @Test
    public void shouldPartitionElements() {
        DynamicArrayList<Integer> oneThroughTen = new DynamicArrayList<Integer>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Predicate<Integer> intoEvensAndOdds = new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer integer) {
                return integer % 2 == 0;
            }
        };

        DynamicList<Integer> trues = new DynamicArrayList<Integer>(2, 4, 6, 8, 10);
        DynamicList<Integer> falses = new DynamicArrayList<Integer>(1, 3, 5, 7, 9);

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
    public void shouldGetUniqueElementsUsingCustomMapper() {
        DynamicArrayList<String> mixedCaseWords = new DynamicArrayList<String>(
                "One", "ONE", "Two", "two", "three"
        );
        assertEquals(new DynamicArrayList<String>("One", "Two", "three"), mixedCaseWords.unique(new MonadicFunction<String, String>() {
            @Override
            public String apply(String mixedCaseWord) {
                return mixedCaseWord.toLowerCase();
            }
        }));
    }

    @Test
    public void shouldGetDuplicateElements() {
        DynamicArrayList<Integer> numbers = new DynamicArrayList<Integer>(1, 1, 2, 3, 4, 4, 5, 6, 6);
        assertEquals(new DynamicArrayList<Integer>(1, 4, 6), numbers.duplicates());
    }

    @Test
    public void shouldGroupElements() {
        DynamicArrayList<Integer> numbers = new DynamicArrayList<Integer>(1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5);

        assertEquals(new DynamicArrayList<DynamicList<Integer>>(
                new DynamicArrayList<Integer>(1),
                new DynamicArrayList<Integer>(2, 2),
                new DynamicArrayList<Integer>(3, 3, 3),
                new DynamicArrayList<Integer>(4, 4, 4, 4),
                new DynamicArrayList<Integer>(5, 5, 5, 5, 5)
        ), numbers.group());
    }

    @Test
    public void shouldGroupElementsByMappedOutput() {
        DynamicArrayList<String> words = new DynamicArrayList<String>("one", "two", "three");
        assertEquals(new DynamicArrayList<DynamicList<String>>(
                new DynamicArrayList<String>("one", "two"),
                new DynamicArrayList<String>("three")
        ), words.groupBy(new MonadicFunction<String, Integer>() {
            @Override
            public Integer apply(String word) {
                return word.length();
            }
        }));
    }

    @Test
    public void shouldDivideIntoGroups() {
        DynamicArrayList<Number> oneAndTwo = new DynamicArrayList<Number>(1, 2.0);
        DynamicArrayList<Number> threeAndFour = new DynamicArrayList<Number>(3d, 4);
        DynamicArrayList<Number> fiveAndSix = new DynamicArrayList<Number>(5.0, 6d);

        DynamicList<Number> oneThroughSix = oneAndTwo.concat(threeAndFour).concat(fiveAndSix);

        assertEquals(new DynamicArrayList<DynamicList<Number>>(
                oneAndTwo,
                threeAndFour,
                fiveAndSix
        ), oneThroughSix.inGroupsOf(2));
    }

    @Test
    public void shouldGetSequencesOfElements() {
        DynamicArrayList<Integer> oneThroughTen = new DynamicArrayList<Integer>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        assertEquals(new DynamicArrayList<DynamicList<Integer>>(
                new DynamicArrayList<Integer>(1, 2, 3),
                new DynamicArrayList<Integer>(2, 3, 4),
                new DynamicArrayList<Integer>(3, 4, 5),
                new DynamicArrayList<Integer>(4, 5, 6),
                new DynamicArrayList<Integer>(5, 6, 7),
                new DynamicArrayList<Integer>(6, 7, 8),
                new DynamicArrayList<Integer>(7, 8, 9),
                new DynamicArrayList<Integer>(8, 9, 10)
        ), oneThroughTen.allSequencesOf(3));
    }

    @Test
    public void shouldZipLists() {
        DynamicArrayList<Integer> oneTwo = new DynamicArrayList<Integer>(1, 2, 3);
        List<Integer> fourFiveSix = asList(4, 5, 6);

        assertEquals(new DynamicArrayList<Tuple2<Integer, Integer>>(
                tuple(1, 4),
                tuple(2, 5),
                tuple(3, 6)
        ), oneTwo.zip(fourFiveSix));
    }

    @Test
    public void shouldZipWithAnotherListUsingZipperFunction() {
        DynamicArrayList<Integer> indices = new DynamicArrayList<Integer>(0, 1);
        DynamicArrayList<String> names = new DynamicArrayList<String>("foo", "bar");

        MonadicFunction<Tuple2<Integer, String>, String> concatWithSpace = new MonadicFunction<Tuple2<Integer, String>, String>() {
            @Override
            public String apply(Tuple2<Integer, String> tuple) {
                return format("%s %s", tuple._1, tuple._2);
            }
        };

        assertEquals(new DynamicArrayList<String>("0 foo", "1 bar"), indices.zipWith(concatWithSpace, names));
    }

    @Test
    public void shouldMatchIfAny() {
        DynamicArrayList<Boolean> allTrue = new DynamicArrayList<Boolean>(
                true, true, true, true, true
        );

        Predicate<Boolean> trues = new Predicate<Boolean>() {
            @Override
            public Boolean apply(Boolean bool) {
                return bool;
            }
        };
        Predicate<Boolean> falses = new

                Predicate<Boolean>() {
                    @Override
                    public Boolean apply(Boolean bool) {
                        return !bool;
                    }
                };

        assertTrue(allTrue.any(trues));
        assertFalse(allTrue.any(falses));
    }

    @Test
    public void shouldMatchIfAnyWhile() {
        DynamicArrayList<Integer> numbers = new DynamicArrayList<Integer>(1, 3, 5, 6, 7, 8, 9, 10);

        Predicate<Integer> evens = new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer integer) {
                return integer % 2 == 0;
            }
        };
        Predicate<Integer> odds = new

                Predicate<Integer>() {
                    @Override
                    public Boolean apply(Integer integer) {
                        return integer % 2 == 1;
                    }
                };
        Predicate<Integer> lessThanFive = new

                Predicate<Integer>() {
                    @Override
                    public Boolean apply(Integer integer) {
                        return integer < 5;
                    }
                };

        assertFalse(numbers.anyWhile(evens, lessThanFive));
        assertTrue(numbers.anyWhile(odds, lessThanFive));
    }

    @Test
    public void shouldMatchIfAll() {
        DynamicArrayList<Boolean> allFalse = new DynamicArrayList<Boolean>(
                false, false, false, false, false
        );

        Predicate<Boolean> areFalse = new Predicate<Boolean>() {
            @Override
            public Boolean apply(Boolean bool) {
                return !bool;
            }
        };
        Predicate<Boolean> areTrue = new

                Predicate<Boolean>() {
                    @Override
                    public Boolean apply(Boolean bool) {
                        return bool;
                    }
                };

        assertTrue(allFalse.all(areFalse));
        assertFalse(allFalse.all(areTrue));
    }

    @Test
    public void shouldMatchIfNone() {
        DynamicArrayList<Integer> odds = new DynamicArrayList<Integer>(1, 3, 5, 7, 9);

        final Predicate<? super Integer> even = new Predicate<Integer>() {
            @Override
            public Boolean apply(Integer integer) {
                return integer % 2 == 0;
            }
        };
        Predicate<? super Integer> odd = new

                Predicate<Integer>() {
                    @Override
                    public Boolean apply(Integer integer) {
                        return !even.apply(integer);
                    }
                };

        assertTrue(odds.none(even));
        assertFalse(odds.none(odd));
    }

    @Test
    public void shouldComputeCartesianProduct() {
        DynamicArrayList<String> firstNames = new DynamicArrayList<String>("Adam", "Bob", "Charlie");
        List<String> lastNames = new DynamicArrayList<String>("West", "Barker", "Kaufman");

        DynamicList<DynamicList<String>> permutedNames = new DynamicArrayList<DynamicList<String>>(
                new DynamicArrayList<String>("Adam", "West"),
                new DynamicArrayList<String>("Adam", "Barker"),
                new DynamicArrayList<String>("Adam", "Kaufman"),
                new DynamicArrayList<String>("Bob", "West"),
                new DynamicArrayList<String>("Bob", "Barker"),
                new DynamicArrayList<String>("Bob", "Kaufman"),
                new DynamicArrayList<String>("Charlie", "West"),
                new DynamicArrayList<String>("Charlie", "Barker"),
                new DynamicArrayList<String>("Charlie", "Kaufman")
        );

        assertEquals(permutedNames, firstNames.cartesianProduct(lastNames));
    }

    @Test
    public void shouldComputeCartesianProductOfSelf() {
        DynamicArrayList<Integer> oneAndTwo = new DynamicArrayList<Integer>(1, 2);

        DynamicList<DynamicList<Integer>> permutations = new DynamicArrayList<DynamicList<Integer>>(
                new DynamicArrayList<Integer>(1, 1),
                new DynamicArrayList<Integer>(1, 2),
                new DynamicArrayList<Integer>(2, 1),
                new DynamicArrayList<Integer>(2, 2)
        );

        assertEquals(permutations, oneAndTwo.cartesianProduct());
    }

    @Test
    public void shouldShuffleElements() {
        DynamicArrayList<Integer> integers = new DynamicArrayList<Integer>(1, 2, 3, 4, 5);
        DynamicList<Integer> shuffled = integers.shuffle();

        assertFalse(shuffled.equals(integers));
        Assert.assertThat(shuffled, hasItems(1, 2, 3, 4, 5));
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
                return 0;
            }
        };

        new DynamicArrayList<Integer>().reduce(accumulator);
    }

    @Test
    public void shouldScanLeft() {
        DynamicArrayList<Integer> oneThroughFive = new DynamicArrayList<Integer>(1, 2, 3, 4, 5);

        Accumulator<Integer, Integer> partialProducts = new Accumulator<Integer, Integer>() {
            @Override
            public Integer apply(Integer product, Integer element) {
                return product * element;
            }
        };

        assertEquals(new DynamicArrayList<Integer>(1, 2, 6, 24, 120), oneThroughFive.scanLeft(partialProducts));
    }

    @Test
    public void shouldScanLeftUsingStartingAccumulator() {
        DynamicArrayList<Integer> oneThroughFive = new DynamicArrayList<Integer>(1, 2, 3, 4, 5);

        Accumulator<Integer, Integer> partialSums = new Accumulator<Integer, Integer>() {
            @Override
            public Integer apply(Integer accumulation, Integer integer) {
                return accumulation + integer;
            }
        };

        assertEquals(new DynamicArrayList<Integer>(0, 1, 3, 6, 10, 15), oneThroughFive.scanLeft(0, partialSums));
    }

    @Test
    public void shouldSortWithCustomComparator() {
        MonadicFunction<Item, String> byLabel = new MonadicFunction<Item, String>() {
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
        MonadicFunction<String, Integer> wordLength = new MonadicFunction<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };

        assertEquals("Spain", new DynamicArrayList<String>("the", "rain", "in", "Spain").max(wordLength));
    }

    @Test
    public void shouldGetMin() {
        MonadicFunction<Integer, Integer> integerValue = new MonadicFunction<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer;
            }
        };

        assertEquals((Integer) 1, new DynamicArrayList<Integer>(1, 2, 3, 4, 5).min(integerValue));
    }
}
