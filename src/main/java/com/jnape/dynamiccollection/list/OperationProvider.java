package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.operator.ElementExcluder;
import com.jnape.dynamiccollection.operator.Folder;
import com.jnape.dynamiccollection.operator.Partitioner;
import com.jnape.dynamiccollection.operator.Rejector;

public class OperationProvider {

    public Rejector rejector() {
        return new Rejector();
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
