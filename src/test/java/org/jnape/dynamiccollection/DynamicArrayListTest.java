package org.jnape.dynamiccollection;

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

public class DynamicArrayListTest {

    private static final Item A = new Item();
    private static final Item B = new Item();
    private static final Item C = new Item();

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
    public void shouldConcatenateAnotherCollectionAfterLastElement() {
        DynamicArrayList<Item> a = new DynamicArrayList<Item>(A);
        DynamicArrayList<Item> bAndC = new DynamicArrayList<Item>(B, C);

        assertEquals(new DynamicArrayList<Item>(A, B, C), a.concat(bAndC));
        assertEquals(new DynamicArrayList<Item>(B, C, A), bAndC.concat(a));
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
