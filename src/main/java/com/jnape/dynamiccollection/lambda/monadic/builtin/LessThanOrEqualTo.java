package com.jnape.dynamiccollection.lambda.monadic.builtin;

import com.jnape.dynamiccollection.lambda.monadic.Predicate;

public final class LessThanOrEqualTo extends Predicate<Number> {

    private final Number rightHand;

    public LessThanOrEqualTo(Number rightHand) {
        this.rightHand = rightHand;
    }

    public static LessThanOrEqualTo lte(Number rightHand) {
        return new LessThanOrEqualTo(rightHand);
    }

    @Override
    public Boolean apply(Number leftHand) {
        return leftHand.doubleValue() <= rightHand.doubleValue();
    }
}
