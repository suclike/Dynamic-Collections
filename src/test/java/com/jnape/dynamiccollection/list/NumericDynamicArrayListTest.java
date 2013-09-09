package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.lambda.dyadic.Accumulator;
import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;
import com.jnape.dynamiccollection.lambda.monadic.Predicate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static testsupport.assertion.InheritanceAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
public class NumericDynamicArrayListTest {

    private static final NumericDynamicList HETEROGENEOUS_NUMBERS = new NumericDynamicArrayList((byte) 1, (short) 2, 3, 4L, 5F, 6D);
    @Mock private MonadicFunction<Number, Number> numericMapper;
    @Mock private Predicate<Number>               numericPredicate;
    @Mock private Accumulator<Number, Number>     numericScanner;

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
    public void shouldConstructAndPopulateFromArray() {
        NumericDynamicArrayList numericDynamicArrayList = new NumericDynamicArrayList(new Integer[]{1, 2, 3});

        assertEquals(3, numericDynamicArrayList.size());
        assertEquals(1, numericDynamicArrayList.get(0));
        assertEquals(2, numericDynamicArrayList.get(1));
        assertEquals(3, numericDynamicArrayList.get(2));
    }

    @Test
    public void shouldConstructAndPopulateFromIterator() {
        Iterator<Number> iterator = Arrays.<Number>asList(1f, 2l, 3).iterator();
        NumericDynamicArrayList dynamicArrayList = new NumericDynamicArrayList(iterator);

        assertEquals(3, dynamicArrayList.size());
        assertEquals(1f, dynamicArrayList.get(0));
        assertEquals(2l, dynamicArrayList.get(1));
        assertEquals(3, dynamicArrayList.get(2));
    }

    @Test
    public void shouldConstructAndPopulateFromCollection() {
        Collection<? extends Number> numbers = Arrays.<Number>asList(1f, (short) 2, (byte) 3);

        NumericDynamicArrayList numericDynamicArrayList = new NumericDynamicArrayList(numbers);

        assertEquals(3, numericDynamicArrayList.size());
        assertEquals(1f, numericDynamicArrayList.get(0));
        assertEquals((short) 2, numericDynamicArrayList.get(1));
        assertEquals((byte) 3, numericDynamicArrayList.get(2));
    }

    @Test
    public void shouldHaveExpectedPolymorphisms() {
        assertThat(NumericDynamicArrayList.class)
                .isA(DynamicArrayList.class);
    }

    @Test
    public void shouldHaveCustomScanLeftThatReturnsNumericDynamicArrayListIfAccumulatorExtendsNumber() {
        assertThat(
                new NumericDynamicArrayList().scanLeft(0, numericScanner)
        ).isA(NumericDynamicArrayList.class);
    }

    @Test
    public void shouldComputeSum() {
        assertEquals(10, new NumericDynamicArrayList(1, 2, 3, 4).sum());
    }

    @Test
    public void shouldComputeProduct() {
        assertEquals(24, new NumericDynamicArrayList(1, 2, 3, 4).product());
    }

    @Test
    public void shouldComputeArithmeticMean() {
        assertEquals(2, new NumericDynamicArrayList(1, 2, 3).arithmeticMean());
    }

    @Test
    public void shouldComputeGeometricMean() {
        assertEquals(4, new NumericDynamicArrayList(2, 8).geometricMean());
    }

    @Test
    public void shouldComputeHarmonicMean() {
        assertEquals(12f / 7, new NumericDynamicArrayList(1f, 2f, 4f).harmonicMean());
    }

    @Test
    public void shouldMapToBytes() {
        assertEquals(
                new NumericDynamicArrayList((byte) 1, (byte) 2, (byte) 3, (byte) 4, (byte) 5, (byte) 6),
                HETEROGENEOUS_NUMBERS.toBytes()
        );
    }

    @Test
    public void shouldMapToShorts() {
        assertEquals(
                new NumericDynamicArrayList((short) 1, (short) 2, (short) 3, (short) 4, (short) 5, (short) 6),
                HETEROGENEOUS_NUMBERS.toShorts()
        );
    }

    @Test
    public void shouldMapToIntegers() {
        assertEquals(
                new NumericDynamicArrayList(1, 2, 3, 4, 5, 6),
                HETEROGENEOUS_NUMBERS.toIntegers()
        );
    }

    @Test
    public void shouldMapToLongs() {
        assertEquals(
                new NumericDynamicArrayList(1L, 2L, 3L, 4L, 5L, 6L),
                HETEROGENEOUS_NUMBERS.toLongs()
        );
    }

    @Test
    public void shouldMapToFloats() {
        assertEquals(
                new NumericDynamicArrayList(1F, 2F, 3F, 4F, 5F, 6F),
                HETEROGENEOUS_NUMBERS.toFloats()
        );
    }

    @Test
    public void shouldMapToDoubles() {
        assertEquals(
                new NumericDynamicArrayList(1D, 2D, 3D, 4D, 5D, 6D),
                HETEROGENEOUS_NUMBERS.toDoubles()
        );
    }
}
