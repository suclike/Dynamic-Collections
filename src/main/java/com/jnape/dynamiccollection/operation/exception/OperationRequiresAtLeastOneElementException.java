package com.jnape.dynamiccollection.operation.exception;

public class OperationRequiresAtLeastOneElementException extends RuntimeException {

    public OperationRequiresAtLeastOneElementException() {
        super("Operation requires at least one element");
    }
}
