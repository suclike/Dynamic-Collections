package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Function;
import org.junit.Test;

import java.util.Collection;

import static com.jnape.dynamiccollection.list.NumericDynamicArrayList.fromTo;
import static com.jnape.dynamiccollection.operation.None.none;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NoneTest {

    private static final Collection<Number> ODDS  = fromTo(1, 9, 2);
    private static final Collection<Number> EVENS = fromTo(2, 10, 2);

    private static final Function<Number, Boolean> ARE_EVEN = new Function<Number, Boolean>() {
        @Override
        public Boolean apply(Number number) {
            return number.intValue() % 2 == 0;
        }
    };

    @Test
    public void shouldBeTrueIfMatcherDoesNotMatchAnyElements() {
        assertTrue(none(ODDS, ARE_EVEN));
    }

    @Test
    public void shouldBeFalseIfMatcherMatchesAnyElements() {
        assertFalse(none(EVENS, ARE_EVEN));
    }
}
