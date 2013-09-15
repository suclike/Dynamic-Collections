package com.jnape.dynamiccollection.stream;

import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;

import java.util.List;

public class CapacityMonitor<Element, Elements extends List<Element>> {

    private final Elements                                   elements;
    private final MonadicFunction<? super Elements, Element> generator;

    public CapacityMonitor(Elements elements, MonadicFunction<? super Elements, Element> generator) {
        this.elements = elements;
        this.generator = generator;
    }

    public void ensureCapacity(int k) {
        while (capacityIsTooLowToAccommodate(k))
            increaseCapacity();
    }

    private boolean capacityIsTooLowToAccommodate(int k) {
        return k > elements.size();
    }

    private void increaseCapacity() {
        elements.add(generator.apply(elements));
    }
}
