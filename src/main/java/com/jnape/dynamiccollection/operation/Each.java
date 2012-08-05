package com.jnape.dynamiccollection.operation;

import com.jnape.dynamiccollection.lambda.Procedure;

import java.util.Collection;

public class Each {

    public static <Element> void each(Collection<Element> collection, Procedure<? super Element> procedure) {
        for (Element element : collection)
            procedure.execute(element);
    }
}
