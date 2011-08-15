package org.jnape.dynamiccollection.list;

import org.jnape.dynamiccollection.DynamicCollection;
import org.jnape.dynamiccollection.datatype.ListPartition;
import org.jnape.dynamiccollection.lambda.Function;
import org.jnape.dynamiccollection.lambda.Procedure;
import org.junit.Test;
import testsupport.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static testsupport.ItemFixture.*;

@SuppressWarnings({"MismatchedQueryAndUpdateOfCollection", "UnusedDeclaration"})
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
    public void shouldConcatenateAnotherCollectionAfterLastElement() {
        DynamicArrayList<Item> a = new DynamicArrayList<Item>(A);
        DynamicArrayList<Item> bAndC = new DynamicArrayList<Item>(B, C);
        DynamicArrayList<Item> empty = new DynamicArrayList<Item>();

        assertEquals(new DynamicArrayList<Item>(A, B, C), a.concat(bAndC));
        assertEquals(new DynamicArrayList<Item>(B, C, A), bAndC.concat(a));
        assertEquals(new DynamicArrayList<Item>(B, C), empty.concat(bAndC));
        assertEquals(new DynamicArrayList<Item>(B, C), bAndC.concat(empty));
    }

    @Test
    @SuppressWarnings({"unchecked"})
    public void shouldGenerateListOfTuplesRepresentingCartesianProduct() {
        DynamicArrayList<Item> vowels = new DynamicArrayList<Item>(A);
        DynamicArrayList<Item> consonants = new DynamicArrayList<Item>(B, C);

        assertEquals(new DynamicArrayList<DynamicList<Item>>(
                new DynamicArrayList<Item>(A, B),
                new DynamicArrayList<Item>(A, C)
        ), vowels.cartesianProduct(consonants));

        DynamicArrayList<Integer> evens = new DynamicArrayList<Integer>(2, 4, 6, 8, 10);
        DynamicArrayList<Integer> odds = new DynamicArrayList<Integer>(1, 3, 5, 7, 9);
        DynamicArrayList<Integer> empty = new DynamicArrayList<Integer>();

        assertEquals(new DynamicArrayList<DynamicList<Integer>>(
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
        ), evens.cartesianProduct(odds));

        assertEquals(new DynamicArrayList<DynamicList<Integer>>(), empty.cartesianProduct(evens));
        assertEquals(new DynamicArrayList<DynamicList<Integer>>(), evens.cartesianProduct(empty));
    }

    @Test
    public void shouldApplyProcedureToEachElement() {
        final List<Character> letters = new ArrayList<Character>();

        Procedure<String> addLetterToList = new Procedure<String>() {
            @Override
            public void execute(String string) {
                for (int i = 0, stringLength = string.length(); i < stringLength; i++)
                    letters.add(string.charAt(i));
            }
        };

        DynamicArrayList<String> words = new DynamicArrayList<String>("The", "rain", "in", "Spain");

        assertSame(words, words.each(addLetterToList));
        assertEquals(
                new DynamicArrayList<Character>('T', 'h', 'e', 'r', 'a', 'i', 'n', 'i', 'n', 'S', 'p', 'a', 'i', 'n'),
                letters
        );
    }

    @Test
    public void shouldCollectSpecificElements() {
        Function<Integer, Boolean> evenNumbers = new Function<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer integer) {
                return integer % 2 == 0;
            }
        };

        DynamicArrayList<Integer> integers = new DynamicArrayList<Integer>(1, 2, 3, 4, 5);
        DynamicArrayList<Integer> moreIntegers = new DynamicArrayList<Integer>(10, 11, 12, 13, 14, 15, 16);

        assertEquals(new DynamicArrayList<Integer>(2, 4), integers.collect(evenNumbers));
        assertEquals(new DynamicArrayList<Integer>(10, 12, 14, 16), moreIntegers.collect(evenNumbers));
    }

    @Test
    public void shouldTransformElementsToDifferentType() {
        Function<Number, String> intoStrings = new Function<Number, String>() {
            @Override
            public String apply(Number number) {
                return number.toString();
            }
        };

        DynamicArrayList<Number> numbers = new DynamicArrayList<Number>(1, 2d, 3.5f);
        DynamicArrayList<Number> moreNumbers = new DynamicArrayList<Number>(1842.12d, 220, 1l);

        assertEquals(new DynamicArrayList<String>("1", "2.0", "3.5"), numbers.transform(intoStrings));
        assertEquals(new DynamicArrayList<String>("1842.12", "220", "1"), moreNumbers.transform(intoStrings));
    }

    @Test
    public void shouldListWithoutSpecifiedElements() {
        DynamicArrayList<Item> items = new DynamicArrayList<Item>(A, B, C, A, B, C);

        assertEquals(new DynamicArrayList<Item>(A, B, A, B), items.without(C));
        assertEquals(new DynamicArrayList<Item>(C, C), items.without(A, B));
    }

    @Test
    public void shouldPartitionElementsIntoTruesAndFalses() {
        Function<Integer, Boolean> intoEvensAndOdds = new Function<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer integer) {
                return integer % 2 == 0;
            }
        };

        DynamicArrayList<Integer> oneThroughTen = new DynamicArrayList<Integer>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        DynamicArrayList<Integer> elevenThroughFifteen = new DynamicArrayList<Integer>(11, 12, 13, 14, 15);

        ListPartition<Integer> oneThroughTenPartition = oneThroughTen.partition(intoEvensAndOdds);
        assertEquals(new DynamicArrayList<Integer>(2, 4, 6, 8, 10), oneThroughTenPartition.trues());
        assertEquals(new DynamicArrayList<Integer>(1, 3, 5, 7, 9), oneThroughTenPartition.falses());

        ListPartition<Integer> elevenThroughFifteenPartition = elevenThroughFifteen.partition(intoEvensAndOdds);
        assertEquals(new DynamicArrayList<Integer>(12, 14), elevenThroughFifteenPartition.trues());
        assertEquals(new DynamicArrayList<Integer>(11, 13, 15), elevenThroughFifteenPartition.falses());
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
}
