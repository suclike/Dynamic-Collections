package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.MonadicProcedure;

import java.util.Collection;

public class ForEach {

    public static <Element> void forEach(Collection<Element> collection, MonadicProcedure<? super Element> procedure) {
        for (Element element : collection)
            procedure.execute(element);
    }
}
