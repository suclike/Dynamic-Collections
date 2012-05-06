package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.list.DynamicArrayList;
import com.jnape.dynamiccollection.list.DynamicList;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.jnape.dynamiccollection.DynamicCollectionFactory.list;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SuppressWarnings("unchecked")
public class InGroupsOfTest {

    @Test
    public void shouldGetEvenlyDistributedListOfGroups() {
        DynamicList<Integer> oneAndTwo = list(1, 2);
        DynamicList<Integer> threeAndFour = list(3, 4);
        DynamicList<Integer> fiveAndSix = list(5, 6);

        List<Integer> oneThroughSix = oneAndTwo.concat(threeAndFour).concat(fiveAndSix);

        assertEquals(list(oneAndTwo, threeAndFour, fiveAndSix), InGroupsOf.inGroupsOf(oneThroughSix, 2));
    }

    @Test
    public void shouldGetNonFullGroupIfTotalElementCountNotEvenlyDivisibleByElementsPerGroup() {
        DynamicList<Integer> oneTwoThree = new DynamicArrayList<Integer>(1, 2, 3);
        DynamicList<Integer> fourFiveSix = new DynamicArrayList<Integer>(4, 5, 6);
        DynamicList<Integer> seven = new DynamicArrayList<Integer>(7);

        List<Integer> oneThroughSeven = oneTwoThree.concat(fourFiveSix).concat(seven);

        assertEquals(list(oneTwoThree, fourFiveSix, seven), InGroupsOf.inGroupsOf(oneThroughSeven, 3));
    }

    @Test
    public void shouldGetSingleListOfAllElementsIfElementsPerGroupGreaterThanTotalElements() {
        DynamicList<Integer> oneThroughFive = list(1, 2, 3, 4, 5);

        DynamicList<DynamicList<Integer>> expected = list();
        expected.add(oneThroughFive);

        assertEquals(expected, InGroupsOf.inGroupsOf(oneThroughFive, 6));
    }

    @Test
    public void shouldGetEmptyListIfZeroElements() {
        List<Object> empty = new ArrayList<Object>();

        assertEquals(empty, InGroupsOf.inGroupsOf(empty, 2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailIfElementsPerGroupLessThanOne() {
        InGroupsOf.inGroupsOf(new ArrayList<Object>(), -12);
    }

    @Test
    public void shouldOperateOnDefensiveCopyAndLeaveOriginalListAlone() {
        List<Object> spiedOnOriginalList = spy(new ArrayList<Object>());
        InGroupsOf.inGroupsOf(spiedOnOriginalList, 1);
        verify(spiedOnOriginalList).toArray();
        verifyNoMoreInteractions(spiedOnOriginalList);
    }
}
