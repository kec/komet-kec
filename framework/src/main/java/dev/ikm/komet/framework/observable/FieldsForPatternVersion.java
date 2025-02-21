package dev.ikm.komet.framework.observable;

import dev.ikm.tinkar.common.bind.ClassConceptBinding;

/**
 * Enumeration representing various pattern fields that can be used in
 * defining and working with patterns in a structured way.
 * <p>
 * Each constant in this enumeration represents a specific aspect or
 * feature of a pattern, allowing for consistent identification as
 * part of the ClassConceptBinding interface implementation.
 * <p>
 * Constants:
 * <p> PATTERN_MEANING_FIELD - Represents the field for the meaning of a pattern.
 * <p> PATTERN_PURPOSE_FIELD - Represents the field for the purpose of a pattern.
 * <p> PATTERN_FIELD_DEFINITION_LIST_FIELD - Represents the field for an array
 * of field definitions within a pattern.
 */
public enum FieldsForPatternVersion implements ClassConceptBinding {
    PATTERN_MEANING_FIELD,
    PATTERN_PURPOSE_FIELD,
    PATTERN_FIELD_DEFINITION_LIST_FIELD,
}
