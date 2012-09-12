package com.jnape.dynamiccollection.lambda.library.numeric.function.filter;

import com.jnape.dynamiccollection.lambda.Function;

public class LessThanOrEqualTo implements Function<Number, Boolean> {

    private final Number rightHand;

    public LessThanOrEqualTo(Number rightHand) {
        this.rightHand = rightHand;
    }

    @Override
    public Boolean apply(Number leftHand) {
        return leftHand.doubleValue() <= rightHand.doubleValue();
    }

    public static LessThanOrEqualTo lessThanOrEqualTo(Number rightHand) {
        return new LessThanOrEqualTo(rightHand);
    }
}
