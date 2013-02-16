package com.jnape.dynamiccollection.lambda.library.numeric.function.filter;

import com.jnape.dynamiccollection.lambda.Predicate;

public class LessThanOrEqualTo implements Predicate<Number> {

    private final Number rightHand;

    public LessThanOrEqualTo(Number rightHand) {
        this.rightHand = rightHand;
    }

    public static LessThanOrEqualTo lessThanOrEqualTo(Number rightHand) {
        return new LessThanOrEqualTo(rightHand);
    }

    @Override
    public Boolean apply(Number leftHand) {
        return leftHand.doubleValue() <= rightHand.doubleValue();
    }
}
