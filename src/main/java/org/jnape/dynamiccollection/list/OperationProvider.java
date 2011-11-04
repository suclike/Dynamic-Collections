package org.jnape.dynamiccollection.list;

import org.jnape.dynamiccollection.list.operation.CartesianProduct;
import org.jnape.dynamiccollection.list.operation.Concatenation;
import org.jnape.dynamiccollection.list.operation.Each;

public class OperationProvider {

    public Concatenation concatenation() {
        return new Concatenation();
    }

    public CartesianProduct cartesianProduct() {
        return new CartesianProduct();
    }

    public Each each() {
        return new Each();
    }
}
