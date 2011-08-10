package org.jnape.dynamiccollection.datatype;

import org.jnape.dynamiccollection.DynamicArrayList;
import org.jnape.dynamiccollection.DynamicList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class ListPartitionTest {

    @Test
    public void shouldConstruct() {
        new ListPartition<Object>(new ArrayList<Object>(), new ArrayList<Object>());
    }

    @Test
    public void shouldStoreTruesAndFalsesAsDynamicList() {
        ListPartition<Object> partition = new ListPartition<Object>(new ArrayList<Object>(), new ArrayList<Object>());

        DynamicList<Object> trues = partition.trues();
        DynamicList<Object> falses = partition.falses();
    }

    @Test
    public void shouldAccessTruesAndFalses() {
        List<Integer> evens = asList(2, 4, 6, 8, 10);
        List<Integer> odds = asList(1, 3, 5, 7, 9);

        ListPartition<Integer> evensAndOdds = new ListPartition<Integer>(evens, odds);
        assertEquals(new DynamicArrayList<Integer>(evens), evensAndOdds.trues());
        assertEquals(new DynamicArrayList<Integer>(odds), evensAndOdds.falses());

        ListPartition<Integer> oddsAndEvens = new ListPartition<Integer>(odds, evens);
        assertEquals(new DynamicArrayList<Integer>(odds), oddsAndEvens.trues());
        assertEquals(new DynamicArrayList<Integer>(evens), oddsAndEvens.falses());
    }
}
