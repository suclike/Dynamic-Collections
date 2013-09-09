package com.jnape.dynamiccollection.datatype.option;

public class OptionFactory {
    public static <Value> Option<Value> option(Value value) {
        return value != null ? new Some<Value>(value) : new None<Value>();
    }

    public static <Value> Option<Value> some(Value value) {
        return new Some<Value>(value);
    }
}
