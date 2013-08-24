package com.jnape.dynamiccollection.lambda;

import com.jnape.dynamiccollection.datatype.tuple.Tuple2;

public abstract class DyadicProcedure<Input1, Input2> extends MonadicProcedure<Tuple2<Input1, Input2>> {

    @Override
    public final void execute(Tuple2<Input1, Input2> args) {
        execute(args._1, args._2);
    }

    public abstract void execute(Input1 input1, Input2 input2);
}
