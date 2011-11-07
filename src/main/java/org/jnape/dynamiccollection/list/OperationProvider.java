package org.jnape.dynamiccollection.list;

import org.jnape.dynamiccollection.operator.CartesianMultiplier;
import org.jnape.dynamiccollection.operator.*;
import org.jnape.dynamiccollection.operator.Concatenator;

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
