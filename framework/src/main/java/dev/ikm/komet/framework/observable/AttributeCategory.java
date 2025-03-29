package dev.ikm.komet.framework.observable;

/**
 * Attributes are a more general category than fields (which always have datatype,
 * meaning, and purpose concepts) and Fx properties. Attributes can be of any type
 * but are somehow associated with a {@code ObservableComponent}. For example,
 * a component version is an attribute but is polymorphic and does not have a
 * concrete meaning and purpose in the same way a public id does, but the version
 * is definitely associated with an {@code ObservableComponent}
 * <p>
 * The AttributeCategory enumeration defines various categories of attributes
 * based on their associated source within the system. Each constant within this
 * enumeration is explicitly tied to a specific {@link AttributeSource}, which represents
 * the origins or grouping of the fields these attributes belong to.
 * <p>
 * This enumeration serves the purpose of organizing and categorizing attributes
 * according to their structural or component-related associations. It is particularly
 * useful in contexts such as data modeling, component management, and the processing
 * of framework-related fields.
 * <p>
 * The constants in this enumeration include:
 * <p>
 * <p> - PUBLIC_ID_FIELD: Represents the public ID field linked to the COMPONENT source.
 * <p> - COMPONENT_VERSIONS_LIST: Represents a list of component versions from the COMPONENT source.
 * <p> - COMPONENT_VERSION: Represents a specific component version from the COMPONENT_VERSION_LIST source.
 * <p> - VERSION_STAMP_FIELD: Represents a version stamp specific to a COMPONENT_VERSION.
 * <p> - PATTERN_VERSION: Represents a pattern version defined under the COMPONENT_VERSION_LIST source.
 * <p> - PATTERN_MEANING_FIELD: Represents the pattern meaning attribute from the PATTERN_VERSION source.
 * <p> - PATTERN_PURPOSE_FIELD: Represents the purpose of a pattern, originating from the PATTERN_VERSION.
 * <p> - PATTERN_FIELD_DEFINITION_LIST: Represents a list of field definitions associated with a pattern version.
 * <p> - PATTERN_FIELD_DEFINITION: Represents an individual field definition from the PATTERN_FIELD_DEFINITION_LIST source.
 * <p> - SEMANTIC_PATTERN_FIELD: Represents a field categorized under SEMANTIC.
 * <p> - SEMANTIC_REFERENCED_COMPONENT_FIELD: Represents a referenced component in the SEMANTIC source.
 * <p> - SEMANTIC_VERSION: Represents a semantic version under the COMPONENT_VERSION_LIST source.
 * <p> - SEMANTIC_FIELD_LIST: Represents a list of fields specific to a SEMANTIC_VERSION.
 * <p> - SEMANTIC_FIELD: Represents an individual field in the SEMANTIC_FIELD_LIST source.
 * <p> - STAMP_VERSION: Represents a stamp version derived from the COMPONENT_VERSION_LIST source.
 * <p> - STATUS_FIELD: Represents the status field in a STAMP_VERSION.
 * <p> - TIME_FIELD: Represents the time field in a STAMP_VERSION.
 * <p> - AUTHOR_FIELD: Represents the author field in a STAMP_VERSION.
 * <p> - MODULE_FIELD: Represents the module field in a STAMP_VERSION.
 * <p> - PATH_FIELD: Represents the path field in a STAMP_VERSION.
 * <p>
 * Each constant includes a reference to its corresponding {@link AttributeSource}
 * via the {@code source} member, allowing for dynamic determination of
 * the attribute's origin or classification.
 */
public enum AttributeCategory {
    PUBLIC_ID_FIELD(AttributeSource.COMPONENT),
    COMPONENT_VERSIONS_LIST(AttributeSource.COMPONENT),
    COMPONENT_VERSION(AttributeSource.COMPONENT_VERSION_LIST),
    VERSION_STAMP_FIELD(AttributeSource.COMPONENT_VERSION),
    PATTERN_VERSION(AttributeSource.COMPONENT_VERSION_LIST),
    PATTERN_MEANING_FIELD(AttributeSource.PATTERN_VERSION),
    PATTERN_PURPOSE_FIELD(AttributeSource.PATTERN_VERSION),
    PATTERN_FIELD_DEFINITION_LIST(AttributeSource.PATTERN_VERSION),
    PATTERN_FIELD_DEFINITION(AttributeSource.PATTERN_FIELD_DEFINITION_LIST),
    SEMANTIC_PATTERN_FIELD(AttributeSource.SEMANTIC),
    SEMANTIC_REFERENCED_COMPONENT_FIELD(AttributeSource.SEMANTIC),
    SEMANTIC_VERSION(AttributeSource.COMPONENT_VERSION_LIST),
    SEMANTIC_FIELD_LIST(AttributeSource.SEMANTIC_VERSION),
    SEMANTIC_FIELD(AttributeSource.SEMANTIC_FIELD_LIST),
    STAMP_VERSION(AttributeSource.COMPONENT_VERSION_LIST),
    STATUS_FIELD(AttributeSource.STAMP_VERSION),
    TIME_FIELD(AttributeSource.STAMP_VERSION),
    AUTHOR_FIELD(AttributeSource.STAMP_VERSION),
    MODULE_FIELD(AttributeSource.STAMP_VERSION),
    PATH_FIELD(AttributeSource.STAMP_VERSION);

    public final AttributeSource source;

    AttributeCategory(AttributeSource attributeSource) {
        this.source = attributeSource;
    }
}
