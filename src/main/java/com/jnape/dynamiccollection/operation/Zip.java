package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.datatype.tuple.Tuple2;
import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;

import java.util.ArrayList;
import java.util.List;

import static com.jnape.dynamiccollection.datatype.tuple.TupleFactory.tuple;
import static java.lang.Math.min;

public class Zip {

    public static <Element> List<List<Element>> zip(List<? extends Element> heads, List<? extends Element>... tails) {
        List<List<Element>> zipped = new ArrayList<List<Element>>();

        for (int i = 0; i < smallestListSize(heads, tails); i++) {
            List<Element> entry = new ArrayList<Element>();
            entry.add(heads.get(i));
            for (List<? extends Element> tail : tails)
                entry.add(tail.get(i));
            zipped.add(entry);
        }

        return zipped;
    }

    public static <Element1, Element2, Output> List<Output> zipWith(
            MonadicFunction<Tuple2<Element1, Element2>, Output> zipper, List<? extends Element1> xs,
            List<? extends Element2> ys) {
        List<Output> outputs = new ArrayList<Output>();

        for (int i = 0; i < smallestListSize(xs, ys); i++)
            outputs.add(zipper.apply(tuple(xs.get(i), ys.get(i))));

        return outputs;
    }

    private static int smallestListSize(List heads, List... tails) {
        int smallestListSize = heads.size();

        for (List tail : tails)
            smallestListSize = min(smallestListSize, tail.size());

        return smallestListSize;
    }
}
