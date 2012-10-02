package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.lambda.Function;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static com.jnape.dynamiccollection.list.DynamicArrayList.list;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
public class CachingStreamTest {

    @Mock private Function<DynamicList<Integer>, Integer> generator;

    @Before
    public void setUp() {
        givenGeneratorThatReturns(1, 2, 3, 4, 5);
    }

    @Test
    public void shouldTakeFiveItems() {
        CachingStream<Integer> cachingStream = new CachingStream<Integer>(generator);
        assertEquals(list(1, 2, 3, 4, 5), cachingStream.take(5));
    }

    @Test
    public void shouldCreateCacheFromListIfProvided() {
        CachingStream<Integer> cachingStream = new CachingStream<Integer>(list(1, 2, 3), generator);

        assertEquals(list(1, 2, 3), cachingStream.take(3));
        verifyZeroInteractions(generator);
    }

    @Test
    public void shouldUpdateCacheWithNewlyGeneratedValues() {
        CachingStream<Integer> cachingStream = new CachingStream<Integer>(generator);
        cachingStream.take(5);
        verify(generator, atLeast(5)).apply(any(DynamicList.class));

        cachingStream.take(5);
        verifyNoMoreInteractions(generator);
    }

    @Test
    public void shouldGrowCacheUsingLoadFactor() {
        CachingStream<Integer> cachingStream = new CachingStream<Integer>(list(1, 2, 3, 4, 5), generator);

        cachingStream.take(6);
        verify(generator, times(4)).apply(any(DynamicList.class));
    }

    private void givenGeneratorThatReturns(Integer... values) {
        when(generator.apply(any(DynamicList.class))).thenReturn(values[0], Arrays.copyOfRange(values, 1, values.length));
    }
}
