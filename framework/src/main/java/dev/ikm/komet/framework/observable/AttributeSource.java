package dev.ikm.komet.framework.observable;

/**
 * The FieldSource enumeration defines the various sources from which fields are derived
 * within the system. These sources represent the different types of components or data
 * structures to which fields can belong or be related.
 *
 * This enumeration is utilized in various contexts such as field definitions, component
 * data processing, and framework-related field operations. The constants allow for the
 * categorization and organization of field definitions based on their origin.
 * <p>
 * The constants in this enumeration include:
 * <p>
 * <p> 1. COMPONENT: Represents fields that belong to a component.
 * <p> 2. COMPONENT_VERSION_LIST: Represents fields related to a list of component versions.
 * <p> 3. COMPONENT_VERSION: Represents fields specific to a particular version of a component.
 * <p> 4. PATTERN: Represents fields associated with a pattern. No additional fields are defined
 *    on a pattern beyond what is on a component.
 * <p> 5. PATTERN_VERSION: Represents fields specific to a version of a pattern.
 * <p> 6. PATTERN_FIELD_DEFINITION_LIST: Represents fields associated with a pattern's field
 *    definition list.
 * <p> 7. SEMANTIC: Represents fields that belong to a semantic. Additional fields may exist that
 *    are not present on the component.
 * <p> 8. SEMANTIC_VERSION: Represents fields specific to a version of a semantic.
 * <p> 9. SEMANTIC_FIELD_LIST: Represents a list of semantic fields.
 * <p> 10. STAMP: Represents fields belonging to a stamp. Symmetrically, no additional fields are
 *     defined on a stamp beyond what is on a component.
 * <p> 11. STAMP_VERSION: Represents fields specific to a version of a stamp.
 * <p>
 * This enumeration enables a structured approach to categorize fields in a way that reflects
 * their location and purpose within the system.
 */
public enum AttributeSource {
    COMPONENT,
    COMPONENT_VERSION_LIST,
    COMPONENT_VERSION,
    PATTERN, // for symmetry, there are no fields on a pattern that aren't on the component
    PATTERN_VERSION,
    PATTERN_FIELD_DEFINITION_LIST,
    SEMANTIC, // Semantic has fields that aren't on the component
    SEMANTIC_VERSION,
    SEMANTIC_FIELD_LIST,
    STAMP, // for symmetry, there are no fields on a pattern that aren't on the component
    STAMP_VERSION
}
