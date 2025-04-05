package dev.ikm.komet.framework.observable;

import org.eclipse.collections.api.list.ImmutableList;

/**
 * The ObservableComponent interface is a sealed interface that defines the core contract
 * for components capable of providing observable attributes. It is restricted to specific
 * subtypes, which are ObservableEntity and ObservableVersion.
 * <p>
 * This interface is primarily concerned with exposing observable attributes and their
 * associated locators, enabling a detailed mapping of attributes within a structured context.
 * Implementations of this interface must ensure the immutability of the returned attribute
 * list and provide a means to pair attributes with the necessary locators for semantic clarity.
 */
public sealed interface ObservableComponent
        permits ObservableEntity, ObservableVersion {

    /**
     * Retrieves a list of observable attributes associated with their respective locators.
     * Each entry in the returned list represents a pairing of an attribute and the
     * locator providing its context.
     *
     * @return an immutable list of {@code AttributeWithLocator}, where each item
     * associates an observable attribute with its corresponding locator.
     */
    ImmutableList<ObservableAttributeWithLocator> getObservableAttributes();

    /**
     * Retrieves the native identifier (nid) of the observable component.
     * The nid is a unique, integer-based identifier used to represent
     * and distinguish components within the system.
     *
     * @return the native identifier (nid) as an integer.
     */
    int nid();

}
