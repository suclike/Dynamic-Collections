package com.jnape.dynamiccollection.stream;

import com.jnape.dynamiccollection.lambda.Function;

import java.util.List;

public class CapacityMonitor<Element, Elements extends List<Element>> {

    private final Elements elements;
    private final Function<Elements, Element> generator;

    public CapacityMonitor(Elements elements, Function<Elements, Element> generator) {
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
