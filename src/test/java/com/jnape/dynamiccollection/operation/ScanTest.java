package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Accumulator;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.jnape.dynamiccollection.lambda.builtin.accumulator.Add.add;
import static com.jnape.dynamiccollection.list.factory.DynamicListFactory.fromTo;
import static com.jnape.dynamiccollection.list.factory.DynamicListFactory.numbers;
import static org.junit.Assert.assertEquals;

public class ScanTest {

    private static final List<Number>                ONE_THROUGH_FIVE = fromTo(1, 5);
    private static final Accumulator<Number, Number> SCANNER          = new Accumulator<Number, Number>() {
        @Override
        public Number apply(Number partialSum, Number number) {
            return add(partialSum, number);
        }
    };

    @Test
    public void shouldUseHeadIfNoStartingAccumulationProvided() {
        assertEquals(Arrays.<Number>asList(1, 3, 6, 10, 15), Scan.scanLeft(ONE_THROUGH_FIVE, SCANNER));
    }

    @Test
    public void shouldScanLeftUsingStartingAccumulation() {
        assertEquals(Arrays.<Number>asList(0, 1, 3, 6, 10, 15), Scan.scanLeft(ONE_THROUGH_FIVE, 0, SCANNER));
    }

    @Test
    public void shouldReturnEmptyListIfNoElementsAndNoStartingAccumulatorProvided() {
        assertEquals(Arrays.<Number>asList(), Scan.scanLeft(numbers(), SCANNER));
    }

    @Test
    public void shouldReturnListOfStartingAccumulationIfNoElementsAndStartingAccumulationProvided() {
        assertEquals(Arrays.<Number>asList(0), Scan.scanLeft(numbers(), 0, SCANNER));
    }
}
