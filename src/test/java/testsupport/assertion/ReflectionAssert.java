package testsupport.assertion;

import java.util.List;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReflectionAssert {

    public static <Type> void assertReflectionEquals(Type expected, Type actual) {
        assertTrue(
                "Expected <" + expected + "> to be reflectively equal to <" + actual + ">, but wasn't.",
                reflectionEquals(expected, actual));
    }

    public static <Type> void assertReflectionEquals(List<Type> expected, List<Type> actual) {
        int expectedSize = expected.size();
        int actualSize = actual.size();

        assertEquals(
                "Expected list to have same size, but didn't.",
                expectedSize,
                actualSize);
        
        for (int i = 0; i < expectedSize; i++) {
            Type expectedType = expected.get(i);
            Type actualType = actual.get(i);

            assertTrue(
                    "Expected <" + expected + "> to be reflectively equal to <" + actual + ">, but differed at element:\n" +
                            "Expected: <" + expectedType + ">\n" +
                            "Actual: <" + actualType + ">",
                    expectedType.equals(actualType));
        }
    }
}
