package com.jnape.dynamiccollection.operation;

import java.util.List;

public class Join {

    public static String join(List<?> list, String combiner) {
        StringBuilder joined = new StringBuilder();

        for (int i = 0, size = list.size(); i < size; i++) {
            joined.append(list.get(i));
            if (i < size - 1)
                joined.append(combiner);
        }

        return joined.toString();
    }
}
