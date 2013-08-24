package com.jnape.dynamiccollection.datatype.tuple;

public final class TupleFactory {

    public static <_1, _2> Tuple2<_1, _2> tuple(_1 _1, _2 _2) {
        return new Tuple2<_1, _2>(_1, _2);
    }

    public static <_1, _2, _3> Tuple3<_1, _2, _3> tuple(_1 _1, _2 _2, _3 _3) {
        return new Tuple3<_1, _2, _3>(_1, _2, _3);
    }

    public static <_1, _2, _3, _4> Tuple4<_1, _2, _3, _4> tuple(_1 _1, _2 _2, _3 _3, _4 _4) {
        return new Tuple4<_1, _2, _3, _4>(_1, _2, _3, _4);
    }
}
