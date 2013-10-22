package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.datatype.tuple.Tuple2;
import com.jnape.dynamiccollection.lambda.dyadic.DyadicFunction;
import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;

import java.util.ArrayList;
import java.util.List;

import static com.jnape.dynamiccollection.datatype.tuple.TupleFactory.tuple;
import static java.lang.Math.min;

public class Zip {

    public static <X, Y> List<Tuple2<X, Y>> zip(List<X> xs, List<Y> ys) {
        return zipWith(new DyadicFunction<X, Y, Tuple2<X, Y>>() {
            @Override
            public Tuple2<X, Y> apply(X x, Y y) {
                return tuple(x, y);
            }
        }, xs, ys);
    }

    public static <X, Y, Output> List<Output> zipWith(MonadicFunction<Tuple2<X, Y>, Output> zipper,
                                                      List<X> xs,
                                                      List<Y> ys) {
        List<Output> outputs = new ArrayList<Output>();

        for (int i = 0; i < min(xs.size(), ys.size()); i++)
            outputs.add(zipper.apply(tuple(xs.get(i), ys.get(i))));

        return outputs;
    }
}
