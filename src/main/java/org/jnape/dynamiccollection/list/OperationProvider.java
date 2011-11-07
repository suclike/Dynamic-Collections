package org.jnape.dynamiccollection.list;

import org.jnape.dynamiccollection.list.operator.CartesianMultiplier;
import org.jnape.dynamiccollection.list.operator.Concatenator;
import org.jnape.dynamiccollection.operator.Collector;
import org.jnape.dynamiccollection.operator.IterativeExecutor;
import org.jnape.dynamiccollection.operator.Transformer;

public class OperationProvider {

    public Concatenator concatenator() {
        return new Concatenator();
    }

    public CartesianMultiplier cartesianMultiplier() {
        return new CartesianMultiplier();
    }

    public IterativeExecutor iterativeExecutor() {
        return new IterativeExecutor();
    }

    public Collector collector() {
        return new Collector();
    }

    public Transformer transformer() {
        return new Transformer();
    }
}
