package com.jnape.dynamiccollection.lambda.monadic.builtin;

import com.jnape.dynamiccollection.lambda.monadic.Predicate;

public class EqualTo<Value> extends Predicate<Value> {

    private final Value leftHand;

    public EqualTo(Value leftHand) {
        this.leftHand = leftHand;
    }

    @Override
    public Boolean apply(Value rightHand) {
        return leftHand.equals(rightHand);
    }

    public static <Value> EqualTo<Value> eq(Value leftHand) {
        return new EqualTo<Value>(leftHand);
    }
}
