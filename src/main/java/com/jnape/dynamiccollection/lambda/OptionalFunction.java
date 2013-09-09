package com.jnape.dynamiccollection.lambda;

import com.jnape.dynamiccollection.datatype.option.Option;

public abstract class OptionalFunction<Input, Output> extends MonadicFunction<Option<Input>, Output> {

    public abstract Output applySome(Input input);

    public abstract Output applyNone();

    @Override
    public final Output apply(Option<Input> someOrNone) {
        return someOrNone
                .map(new MonadicFunction<Input, Output>() {
                    @Override
                    public Output apply(Input input) {
                        return applySome(input);
                    }
                })
                .getOrElse(applyNone());
    }
}
