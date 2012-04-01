package com.jnape.dynamiccollection.operator;

import com.jnape.dynamiccollection.lambda.Procedure;

import java.util.Collection;

public class IterativelyExecute {

    public static <Element> void iterativelyExecute(Collection<Element> collection, Procedure<Element> procedure) {
        for (Element element : collection)
            procedure.execute(element);
    }
}
