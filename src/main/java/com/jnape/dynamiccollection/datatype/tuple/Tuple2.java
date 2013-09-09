package com.jnape.dynamiccollection.datatype.tuple;

import org.apache.commons.lang.builder.StandardToStringStyle;

import java.util.Map;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang.builder.HashCodeBuilder.reflectionHashCode;
import static org.apache.commons.lang.builder.ToStringBuilder.reflectionToString;

public class Tuple2<_1, _2> implements Map.Entry<_1, _2> {
    public final _1 _1;
    public final _2 _2;

    public Tuple2(_1 _1, _2 _2) {
        this._1 = _1;
        this._2 = _2;
    }

    @Override
    public _1 getKey() {
        return _1;
    }

    @Override
    public _2 getValue() {
        return _2;
    }

    @Override
    public _2 setValue(_2 value) {
        throw new IllegalStateException("Tuples are final");
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object that) {
        return reflectionEquals(this, that);
    }

    @Override
    public String toString() {
        return reflectionToString(this, new StandardToStringStyle() {{
            setUseClassName(false);
            setUseIdentityHashCode(false);
            setFieldSeparator(", ");
            setContentStart("<");
            setContentEnd(">");
            setUseFieldNames(false);
        }});
    }
}
