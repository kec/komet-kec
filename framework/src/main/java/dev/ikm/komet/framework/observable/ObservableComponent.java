package dev.ikm.komet.framework.observable;

import dev.ikm.tinkar.coordinate.stamp.calculator.StampCalculator;
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
     * Retrieves an immutable list of features associated with this observable component
     * based on the provided stamp calculator. The list represents the attributes or
     * characteristics of the component, each defined by a {@code Feature}.
     *
     * @param stampCalculator the {@code StampCalculator} used to determine the visibility
     *                        and context of the features to be retrieved.
     * @return an {@code ImmutableList} of {@code Feature} objects associated with this component.
     */
    ImmutableList<Feature> getFeatures(StampCalculator stampCalculator);

    /**
     * Retrieves an immutable list of {@code Feature} objects representing only those features
     * that are associated with a version.
     *
     * @param stampCalculator the {@code StampCalculator} used to determine visibility and context
     *                        of the features to be filtered and retrieved.
     * @return an {@code ImmutableList} of {@code Feature} objects matching any version criteria.
     */
    default ImmutableList<Feature> getVersionsAsFeatures(StampCalculator stampCalculator) {
        return getFeatures(stampCalculator).collectIf(feature ->
                FeatureLocator.anyVersion().match(feature.locator()),
                feature -> feature);
    }
    /**
     * Retrieves the native identifier (nid) of the observable component.
     * The nid is a unique, integer-based identifier used to represent
     * and distinguish components within the system.
     *
     * @return the native identifier (nid) as an integer.
     */
    int nid();

}
