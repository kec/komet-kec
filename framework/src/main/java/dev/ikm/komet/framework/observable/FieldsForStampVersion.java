package dev.ikm.komet.framework.observable;

import dev.ikm.tinkar.common.bind.ClassConceptBinding;

/**
 * An enumeration that defines the fields of a stamp in a system.
 * The fields represent specific attributes or metadata associated with a stamp.
 * <p>
 * <p> Fields:
 * <p> - STATUS_FIELD: Represents the status field of the version/stamp.
 * <p> - TIME_FIELD: Represents the time field of the version/stamp.
 * <p> - AUTHOR_FIELD: Represents the author field of the version/stamp.
 * <p> - MODULE_FIELD: Represents the module field of the version/stamp.
 * <p> - PATH_FIELD: Represents the path field of the version/stamp.
 * <p>
 * This enumeration implements the ClassConceptBinding interface to associate
 * with a conceptual binding within a class or system.
 */
public enum FieldsForStampVersion implements ClassConceptBinding {
    STATUS_FIELD,
    TIME_FIELD,
    AUTHOR_FIELD,
    MODULE_FIELD,
    PATH_FIELD;
}
