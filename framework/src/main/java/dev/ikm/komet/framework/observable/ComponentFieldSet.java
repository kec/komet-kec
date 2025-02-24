package dev.ikm.komet.framework.observable;


import java.util.Collection;

/**
 * A concrete implementation of {@link ImmutableEnumSet} that uses {@link ComponentField}
 * as the enumeration type. This class provides a type-safe, immutable set of {@link ComponentField}
 * elements, enabling efficient storage and quick operations for sets involving {@link ComponentField}.
 */
public class ComponentFieldSet extends ImmutableEnumSet<ComponentField> {
    /**
     * Constructs a new {@code ComponentFieldSet} instance, representing an immutable set of
     * {@link ComponentField} elements. This constructor allows for the direct initialization of
     * the set using a variable number of {@link ComponentField} elements.
     *
     * @param elements the enumeration elements to include in the set. These must be of type {@link ComponentField}.
     */
    public ComponentFieldSet(ComponentField... elements) {
        super(elements);
    }

    /**
     * Constructs a new {@code ComponentFieldSet} instance, representing an immutable set of
     * {@link ComponentField} elements using the elements provided within the given collection.
     * This constructor allows for initializing the set with a collection of {@link ComponentField}
     * elements, ensuring type safety and immutability.
     *
     * @param elements the collection of {@link ComponentField} elements to include in the set.
     *                 These elements must be of type {@link ComponentField}.
     */
    public ComponentFieldSet(Collection<? extends ComponentField> elements) {
        super(elements);
    }

    /**
     * Retrieves the {@link Class} object associated with the enumeration type
     * {@link ComponentField}, used by this class to define immutable enum sets.
     *
     * @return the {@link Class} object representing the {@link ComponentField} enumeration type.
     */
    @Override
    protected Class<ComponentField> enumClass() {
        return ComponentField.class;
    }
}
