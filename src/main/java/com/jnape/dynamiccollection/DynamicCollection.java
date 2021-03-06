package com.jnape.dynamiccollection;

import com.jnape.dynamiccollection.datatype.Partition;
import com.jnape.dynamiccollection.datatype.option.Option;
import com.jnape.dynamiccollection.lambda.dyadic.DyadicFunction;
import com.jnape.dynamiccollection.lambda.dyadic.IndexedProcedure;
import com.jnape.dynamiccollection.lambda.monadic.MonadicFunction;
import com.jnape.dynamiccollection.lambda.monadic.MonadicProcedure;

import java.util.Collection;

public interface DynamicCollection<Element> extends Collection<Element> {

    DynamicCollection<Element> concat(Collection<Element> collection);

    DynamicCollection<Element> concat(Element... elements);

    Option<Element> find(MonadicFunction<? super Element, Boolean> predicate);

    DynamicCollection<Element> each(IndexedProcedure<? super Element> indexedProcedure);

    DynamicCollection<Element> forEach(MonadicProcedure<? super Element> procedure);

    DynamicCollection<Element> filter(MonadicFunction<? super Element, Boolean> filterer);

    DynamicCollection<Element> reject(MonadicFunction<? super Element, Boolean> rejector);

    <Output> DynamicCollection<Output> map(MonadicFunction<? super Element, Output> mapper);

    <Output> DynamicCollection<Output> map(DyadicFunction<Number, ? super Element, Output> indexedMapper);

    <Output> DynamicCollection<Output> mapWhile(MonadicFunction<? super Element, Output> mapper,
                                                MonadicFunction<? super Output, Boolean> predicate);

    DynamicCollection<Element> without(Collection<? super Element> exclusions);

    Partition<Element> partition(MonadicFunction<? super Element, Boolean> partitioner);

    DynamicCollection<Element> unique();

    DynamicCollection<Element> unique(MonadicFunction<? super Element, ?> mapper);

    DynamicCollection<Element> duplicates();

    DynamicCollection<? extends DynamicCollection<Element>> group();

    DynamicCollection<? extends DynamicCollection<Element>> groupBy(MonadicFunction<? super Element, ?> mapper);

    boolean any(MonadicFunction<? super Element, Boolean> matcher);

    boolean anyWhile(MonadicFunction<? super Element, Boolean> matcher,
                     MonadicFunction<? super Element, Boolean> predicate);

    boolean all(MonadicFunction<? super Element, Boolean> matcher);

    boolean none(MonadicFunction<? super Element, Boolean> matcher);
}
