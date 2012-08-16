package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Accumulator;

import java.util.ArrayList;
import java.util.List;

public class Scan {
    public static <Element, Output> List<Output> scanLeft(List<Element> elements, Output startingAccumulation, Accumulator<Output, Element> scanner) {
        List<Output> accumulations = new ArrayList<Output>();
        accumulations.add(startingAccumulation);

        for (Element element : elements)
            accumulations.add(scanner.apply(accumulations.get(accumulations.size() - 1), element));

        return accumulations;
    }
}
