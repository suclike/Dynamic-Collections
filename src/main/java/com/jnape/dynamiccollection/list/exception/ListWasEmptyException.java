package com.jnape.dynamiccollection.list.exception;

public class ListWasEmptyException extends IllegalStateException {

    public ListWasEmptyException() {
        super("The list was empty");
    }
}
