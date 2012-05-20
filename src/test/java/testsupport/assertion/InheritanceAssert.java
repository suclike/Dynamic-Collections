package testsupport.assertion;

public class InheritanceAssert {

    private final Class type;

    private InheritanceAssert(Class type) {
        this.type = type;
    }

    public static InheritanceAssert assertThat(Class type) {
        return new InheritanceAssert(type);
    }

    public static <T> InheritanceAssert assertThat(T object) {
        return assertThat(object.getClass());
    }

    public InheritanceAssert isA(Class anotherType) {
        if (!anotherType.isAssignableFrom(type))
            throw new AssertionError("Expected type <" + type + "> to have isA relationship with <" + anotherType + ">, but it doesn't.");

        return this;
    }
}
