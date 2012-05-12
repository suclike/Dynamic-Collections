package testsupport.assertion;

import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.junit.Assert.assertTrue;

public class ReflectionAssert {

    public static <Type> void assertReflectionEquals(Type expected, Type actual) {
        assertTrue(
                "Expected <" + expected + "> to be reflectively equal to <" + actual + ">, but wasn't.",
                reflectionEquals(expected, actual));
    }
}
