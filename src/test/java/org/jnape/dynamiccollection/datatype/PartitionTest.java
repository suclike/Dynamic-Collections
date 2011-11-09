package org.jnape.dynamiccollection.datatype;

import org.jnape.dynamiccollection.DynamicCollection;
import org.jnape.dynamiccollection.list.DynamicArrayList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PartitionTest {

    @Test
    public void shouldConstruct() {
        new Partition<Object>(new ArrayList<Object>(), new ArrayList<Object>());
    }

    @Test
    public void shouldStoreTruesAndFalsesAsDynamicCollection() {
        Partition<Object> partition = new Partition<Object>(new ArrayList<Object>(), new ArrayList<Object>());

        assertTrue(DynamicCollection.class.isInstance(partition.trues()));
        assertTrue(DynamicCollection.class.isInstance(partition.falses()));
    }

    @Test
    public void shouldAccessTruesAndFalses() {
        Collection<Integer> evens = asList(2, 4, 6, 8, 10);
        Collection<Integer> odds = asList(1, 3, 5, 7, 9);

        Partition<Integer> evensAndOdds = new Partition<Integer>(evens, odds);
        assertEquals(new DynamicArrayList<Integer>(evens), evensAndOdds.trues());
        assertEquals(new DynamicArrayList<Integer>(odds), evensAndOdds.falses());

        Partition<Integer> oddsAndEvens = new Partition<Integer>(odds, evens);
        assertEquals(new DynamicArrayList<Integer>(odds), oddsAndEvens.trues());
        assertEquals(new DynamicArrayList<Integer>(evens), oddsAndEvens.falses());
    }
}
