package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.operator.*;

public class OperationProvider {

    public CartesianMultiplier cartesianMultiplier() {
        return new CartesianMultiplier();
    }

    public Collector collector() {
        return new Collector();
    }

    public Rejector rejector() {
        return new Rejector();
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

    public Folder folder() {
        return new Folder();
    }
}
