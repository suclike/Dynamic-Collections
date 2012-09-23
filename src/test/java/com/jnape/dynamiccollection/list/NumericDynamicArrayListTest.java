package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.lambda.Accumulator;
import com.jnape.dynamiccollection.lambda.Function;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;
import java.util.Iterator;

import static com.jnape.dynamiccollection.list.DynamicArrayList.list;
import static com.jnape.dynamiccollection.list.NumericDynamicArrayList.fromTo;
import static com.jnape.dynamiccollection.list.NumericDynamicArrayList.numbers;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static testsupport.assertion.InheritanceAssert.assertThat;

@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
@RunWith(MockitoJUnitRunner.class)
public class NumericDynamicArrayListTest {

    private static final NumericDynamicList HETEROGENEOUS_NUMBERS = numbers((byte) 1, (short) 2, 3, 4L, 5F, 6D);

    @Mock private Function<Number, Number>    numericMapper;
    @Mock private Function<Number, Boolean>   numericPredicate;
    @Mock private Accumulator<Number, Number> numericScanner;

    @Test
    public void shouldConstructAndPopulateFromVarArgs() {
        NumericDynamicArrayList numericDynamicArrayList = new NumericDynamicArrayList(1, 2, 3, 4);

        assertEquals(4, numericDynamicArrayList.size());
        assertEquals(1, numericDynamicArrayList.get(0));
        assertEquals(2, numericDynamicArrayList.get(1));
        assertEquals(3, numericDynamicArrayList.get(2));
        assertEquals(4, numericDynamicArrayList.get(3));
    }

    @Test
    public void shouldConstructAndPopulateFromIterator() {
        Iterator<? extends Number> iterator = asList(1f, 2l, 3).iterator();
        NumericDynamicArrayList dynamicArrayList = new NumericDynamicArrayList(iterator);

        assertEquals(3, dynamicArrayList.size());
        assertEquals(1f, dynamicArrayList.get(0));
        assertEquals(2l, dynamicArrayList.get(1));
        assertEquals(3, dynamicArrayList.get(2));
    }

    @Test
    public void shouldConstructAndPopulateFromCollection() {
        Collection<? extends Number> numbers = list(1f, (short) 2, (byte) 3);

        NumericDynamicArrayList numericDynamicArrayList = new NumericDynamicArrayList(numbers);

        assertEquals(3, numericDynamicArrayList.size());
        assertEquals(1f, numericDynamicArrayList.get(0));
        assertEquals((short) 2, numericDynamicArrayList.get(1));
        assertEquals((byte) 3, numericDynamicArrayList.get(2));
    }

    @Test
    public void shouldHaveFactoryMethodThatPopulatesFromVarArgs() {
        NumericDynamicArrayList numericDynamicArrayList = numbers(1, 2, 3, 4);

        assertEquals(4, numericDynamicArrayList.size());
        assertEquals(1, numericDynamicArrayList.get(0));
        assertEquals(2, numericDynamicArrayList.get(1));
        assertEquals(3, numericDynamicArrayList.get(2));
        assertEquals(4, numericDynamicArrayList.get(3));

    }

    @Test
    public void shouldHaveFactoryMethodThatPopulatesFromIterator() {
        Iterator<? extends Number> iterator = asList(1f, 2l, 3).iterator();

        NumericDynamicArrayList dynamicArrayList = numbers(iterator);

        assertEquals(3, dynamicArrayList.size());
        assertEquals(1f, dynamicArrayList.get(0));
        assertEquals(2l, dynamicArrayList.get(1));
        assertEquals(3, dynamicArrayList.get(2));

    }

    @Test
    public void shouldHaveFactoryMethodThatPopulatesFromCollection() {
        Collection<? extends Number> numbers = list(1f, (short) 2, (byte) 3);

        NumericDynamicArrayList numericDynamicArrayList = numbers(numbers);

        assertEquals(3, numericDynamicArrayList.size());
        assertEquals(1f, numericDynamicArrayList.get(0));
        assertEquals((short) 2, numericDynamicArrayList.get(1));
        assertEquals((byte) 3, numericDynamicArrayList.get(2));
    }

    @Test
    public void shouldHaveFactoryMethodThatCreatesRangeOfNumbersInIncrementsOfNWithTypeOfHighestPrecedenceOfFromAndTo() {
        assertEquals(numbers(1l, 2l, 3l, 4l, 5l), fromTo(1l, 5l, 1));
        assertEquals(numbers(1d, 3d, 5d), fromTo(1, 5d, 2));
        assertEquals(numbers((short) 1), fromTo((short) 1, (byte) 2, 2f));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfFromToWithIncrementOfZeroOrLess() {
        fromTo(1, 10, 0);
    }

    @Test
    public void shouldHaveFactoryMethodThatCreatesRangeOfNumbersInIncrementsOfOneIfNoIncrementProvided() {
        assertEquals(numbers(1, 2, 3, 4, 5), fromTo(1, 5));
    }

    @Test
    public void shouldHaveExpectedPolymorphisms() {
        assertThat(NumericDynamicArrayList.class)
                .isA(DynamicArrayList.class);
    }

    @Test
    public void shouldHaveCustomMapThatReturnsNumericDynamicArrayListIfOutputExtendsNumber() {
        assertThat(
                numbers().map(numericMapper)
        ).isA(NumericDynamicArrayList.class);
    }

    @Test
    public void shouldHaveCustomMapWhileThatReturnsNumericDynamicArrayListIfOutputExtendsNumber() {
        assertThat(
                numbers().mapWhile(numericMapper, numericPredicate)
        ).isA(NumericDynamicArrayList.class);
    }

    @Test
    public void shouldHaveCustomScanLeftThatReturnsNumericDynamicArrayListIfAccumulatorExtendsNumber() {
        assertThat(
                numbers().scanLeft(0, numericScanner)
        ).isA(NumericDynamicArrayList.class);
    }

    @Test
    public void shouldComputeSum() {
        assertEquals(10, numbers(1, 2, 3, 4).sum());
    }

    @Test
    public void shouldComputeProduct() {
        assertEquals(24, numbers(1, 2, 3, 4).product());
    }

    @Test
    public void shouldComputeArithmeticMean() {
        assertEquals(2, numbers(1, 2, 3).arithmeticMean());
    }

    @Test
    public void shouldComputeGeometricMean() {
        assertEquals(4, numbers(2, 8).geometricMean());
    }

    @Test
    public void shouldComputeHarmonicMean() {
        assertEquals(12f / 7, numbers(1f, 2f, 4f).harmonicMean());
    }

    @Test
    public void shouldMapToBytes() {
        assertEquals(
                fromTo((byte) 1, (byte) 6),
                HETEROGENEOUS_NUMBERS.toBytes()
        );
    }

    @Test
    public void shouldMapToShorts() {
        assertEquals(
                fromTo((short) 1, (short) 6),
                HETEROGENEOUS_NUMBERS.toShorts()
        );
    }

    @Test
    public void shouldMapToIntegers() {
        assertEquals(
                fromTo(1, 6),
                HETEROGENEOUS_NUMBERS.toIntegers()
        );
    }

    @Test
    public void shouldMapToLongs() {
        assertEquals(
                fromTo(1L, 6L),
                HETEROGENEOUS_NUMBERS.toLongs()
        );
    }

    @Test
    public void shouldMapToFloats() {
        assertEquals(
                fromTo(1F, 6F),
                HETEROGENEOUS_NUMBERS.toFloats()
        );
    }

    @Test
    public void shouldMapToDoubles() {
        assertEquals(
                fromTo(1D, 6D),
                HETEROGENEOUS_NUMBERS.toDoubles()
        );
    }
}
