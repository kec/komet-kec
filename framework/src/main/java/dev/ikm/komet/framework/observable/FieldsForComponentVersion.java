package dev.ikm.komet.framework.observable;

import dev.ikm.tinkar.common.bind.ClassConceptBinding;

/**
 * An enumeration representing various fields or properties associated with a component.
 * This can be used as part of data binding, field mapping, or classification within a system
 * that handles components and their metadata.
 * <p>
 * <p>Each constant in this enumeration represents a specific property or concept related to a component:
 * <p>- PUBLIC_ID_FIELD: Represents the public identifier field for the component.
 * <p>- COMPONENT_VERSIONS_FIELD: Represents the field containing version information for the component.
 *<p>
 * This enumeration also implements the ClassConceptBinding interface, signifying its role
 * in binding or associating class-level concepts.
 */
public enum FieldsForComponentVersion implements ClassConceptBinding {
    PUBLIC_ID_FIELD,
    COMPONENT_VERSIONS_FIELD,
}
