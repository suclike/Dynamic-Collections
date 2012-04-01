package com.jnape.dynamiccollection.list;

import com.jnape.dynamiccollection.operator.Folder;
import com.jnape.dynamiccollection.operator.Partitioner;

public class OperationProvider {

    public Partitioner partitioner() {
        return new Partitioner();
    }

    public Folder folder() {
        return new Folder();
    }
}
