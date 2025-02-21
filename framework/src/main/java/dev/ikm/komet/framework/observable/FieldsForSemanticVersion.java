package dev.ikm.komet.framework.observable;

import dev.ikm.tinkar.common.bind.ClassConceptBinding;

/**
 * Enumeration representing different types of semantic fields.
 * This enum is used to define various conceptual bindings for semantic fields.
 * Implements the ClassConceptBinding interface to allow mapping of semantic concepts.
 */
public enum FieldsForSemanticVersion implements ClassConceptBinding {
    SEMANTIC_PATTERN_FIELD,
    SEMANTIC_REFERENCED_COMPONENT_FIELD,
    SEMANTIC_FIELD_VALUE_LIST_FIELD,
    SEMANTIC_FIELD_LIST_FIELD,

}
