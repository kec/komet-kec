package dev.ikm.komet.framework.observable;


import java.util.Collection;

import dev.ikm.tinkar.collection.ImmutableEnumSet;

import static dev.ikm.komet.framework.observable.AttributeCategory.*;

/**
 * A concrete implementation of {@link ImmutableEnumSet} that uses {@link AttributeCategory}
 * as the enumeration type. This class provides a type-safe, immutable set of {@link AttributeCategory}
 * elements, enabling efficient storage and quick operations for sets involving {@link AttributeCategory}.
 */
public class AttributeCategorySet extends ImmutableEnumSet<AttributeCategory> {
    /**
     * Constructs a new {@code ComponentFieldSet} instance, representing an immutable set of
     * {@link AttributeCategory} elements. This constructor allows for the direct initialization of
     * the set using a variable number of {@link AttributeCategory} elements.
     *
     * @param elements the enumeration elements to include in the set. These must be of type {@link AttributeCategory}.
     */
    public AttributeCategorySet(AttributeCategory... elements) {
        super(elements);
    }

    /**
     * Constructs a new {@code ComponentFieldSet} instance, representing an immutable set of
     * {@link AttributeCategory} elements using the elements provided within the given collection.
     * This constructor allows for initializing the set with a collection of {@link AttributeCategory}
     * elements, ensuring type safety and immutability.
     *
     * @param elements the collection of {@link AttributeCategory} elements to include in the set.
     *                 These elements must be of type {@link AttributeCategory}.
     */
    public AttributeCategorySet(Collection<? extends AttributeCategory> elements) {
        super(elements);
    }

    /**
     * Retrieves the {@link Class} object associated with the enumeration type
     * {@link AttributeCategory}, used by this class to define immutable enum sets.
     *
     * @return the {@link Class} object representing the {@link AttributeCategory} enumeration type.
     */
    @Override
    protected Class<AttributeCategory> enumClass() {
        return AttributeCategory.class;
    }


    private static final AttributeCategorySet COMPONENT_ATTRIBUTES = new AttributeCategorySet(PUBLIC_ID_FIELD, COMPONENT_VERSIONS_LIST);
    private static final AttributeCategorySet PATTERN_VERSION_FIELDS = new AttributeCategorySet(PUBLIC_ID_FIELD, PATTERN_MEANING_FIELD, PATTERN_PURPOSE_FIELD, PATTERN_FIELD_DEFINITION_LIST,
            STATUS_FIELD, TIME_FIELD, AUTHOR_FIELD, MODULE_FIELD, PATH_FIELD);

    private static final AttributeCategorySet SEMANTIC_FIELDS = new AttributeCategorySet(PUBLIC_ID_FIELD, SEMANTIC_PATTERN_FIELD, SEMANTIC_REFERENCED_COMPONENT_FIELD, COMPONENT_VERSIONS_LIST);
    private static final AttributeCategorySet SEMANTIC_VERSION_FIELDS = new AttributeCategorySet(PUBLIC_ID_FIELD, SEMANTIC_PATTERN_FIELD, SEMANTIC_REFERENCED_COMPONENT_FIELD, SEMANTIC_FIELD_LIST,
            STATUS_FIELD, TIME_FIELD, AUTHOR_FIELD, MODULE_FIELD, PATH_FIELD);

    private static final AttributeCategorySet STAMP_VERSION_FIELDS = new AttributeCategorySet(PUBLIC_ID_FIELD,
            STATUS_FIELD, TIME_FIELD, AUTHOR_FIELD, MODULE_FIELD, PATH_FIELD);
    public static AttributeCategorySet componentFields() {
        return COMPONENT_ATTRIBUTES;
    }

    public static AttributeCategorySet conceptFields() {
        return COMPONENT_ATTRIBUTES;
    }

    public static AttributeCategorySet patternFields() {
        return COMPONENT_ATTRIBUTES;
    }

    public static AttributeCategorySet semanticFields() {
        return SEMANTIC_FIELDS;
    }

    public static AttributeCategorySet stampFields() {
        return COMPONENT_ATTRIBUTES;
    }

    public static AttributeCategorySet conceptVersionFields() {
        return STAMP_VERSION_FIELDS;
    }

    public static AttributeCategorySet semanticVersionFields() {
        return SEMANTIC_VERSION_FIELDS;
    }

    public static AttributeCategorySet patternVersionFields() {
        return PATTERN_VERSION_FIELDS;
    }

    public static AttributeCategorySet stampVersionFields() {
        return STAMP_VERSION_FIELDS;
    }

    public static AttributeCategorySet fieldsForObservable(ObservableEntity observableEntity) {
        return switch (observableEntity) {
            case ObservableConcept _ -> conceptFields();
            case ObservablePattern _ -> patternFields();
            case ObservableSemantic _ -> semanticFields();
            case ObservableStamp _ -> stampFields();
        };
    }

    public static AttributeCategorySet fieldsForObservable(ObservableEntitySnapshot observableSnapshot) {
        return switch (observableSnapshot) {
            case ObservableConceptSnapshot _ -> conceptFields();
            case ObservablePatternSnapshot _ -> patternFields();
            case ObservableSemanticSnapshot _ -> semanticFields();
        };
    }

    public static AttributeCategorySet fieldsForObservable(ObservableVersion observableVersion) {
        return switch (observableVersion) {
            case ObservableConceptVersion _ -> conceptVersionFields();
            case ObservableSemanticVersion _ -> semanticVersionFields();
            case ObservablePatternVersion _ -> patternVersionFields();
            case ObservableStampVersion _ -> stampVersionFields();
        };
    }
}
