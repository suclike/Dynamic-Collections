package com.jnape.dynamiccollection.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProcedureTest {

    @Test
    public void shouldConstruct() {
        new Procedure() {
            @Override
            public void execute(Object o) {
            }
        };
    }

    @Test
    public void shouldPolymorphToFunction() {
        Function<Object, Void> function = new Procedure<Object>() {
            @Override
            public void execute(Object o) {
            }
        };
    }

    @Test
    public void shouldExecuteAgainstArgument() {
        final List<String> list = new ArrayList<String>();

        Procedure<String> addStringToList = new Procedure<String>() {
            @Override
            public void execute(String string) {
                list.add(string);
            }
        };

        String argument = "one";
        addStringToList.execute(argument);

        assertEquals(1, list.size());
        assertEquals(argument, list.get(0));
    }
}
