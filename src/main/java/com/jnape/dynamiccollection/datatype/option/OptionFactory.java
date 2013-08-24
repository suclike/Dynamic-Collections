package com.jnape.dynamiccollection.datatype.option;

public class OptionFactory {
    public static <Value> Option<Value> option(Value value) {
        return value != null ? new Some<Value>(value) : new None<Value>();
    }
}
