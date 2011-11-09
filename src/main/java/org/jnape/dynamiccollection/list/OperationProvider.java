package org.jnape.dynamiccollection.list;

import org.jnape.dynamiccollection.operator.*;

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

    public Reducer reducer() {
        return new Reducer();
    }

    public Transformer transformer() {
        return new Transformer();
    }

    public ElementExcluder elementExcluder() {
        return new ElementExcluder();
    }

    public Partitioner partitioner() {
        return new Partitioner();
    }
}
