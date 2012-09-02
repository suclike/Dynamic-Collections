package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Accumulator;
import org.junit.Test;

import java.util.List;

import static com.jnape.dynamiccollection.list.DynamicArrayList.list;
import static org.junit.Assert.assertEquals;

public class ScanTest {

    @Test
    public void shouldScanLeft() {
        List<Integer> oneThroughFive = list(1, 2, 3, 4, 5);

        Accumulator<Integer, Integer> scanner = new Accumulator<Integer, Integer>() {
            @Override
            public Integer apply(Integer partialSum, Integer number) {
                return partialSum + number;
            }
        };

        assertEquals(list(0, 1, 3, 6, 10, 15), Scan.scanLeft(oneThroughFive, 0, scanner));
    }
}
