package com.jnape.dynamiccollection.lambda;

import com.jnape.dynamiccollection.datatype.tuple.Tuple2;

public abstract class DyadicFunction<Input1, Input2, Output> extends MonadicFunction<Tuple2<Input1, Input2>, Output> {

    @Override
    public final Output apply(Tuple2<Input1, Input2> args) {
        return apply(args._1, args._2);
    }

    public abstract Output apply(Input1 input1, Input2 input2);
}
