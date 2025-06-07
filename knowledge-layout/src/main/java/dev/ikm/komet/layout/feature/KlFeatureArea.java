package dev.ikm.komet.layout.feature;

import dev.ikm.komet.framework.observable.LocatableFeature;
import dev.ikm.komet.layout.area.KlPropertyArea;
import javafx.scene.layout.Region;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * Represents a non-sealed interface within the Knowledge Layout framework for managing and
 * interacting with specific features of a given data type and their associated JavaFX regions.
 * This interface defines methods for observing, updating, and managing feature properties in
 * a type-safe and context-aware manner.
 *
 * @param <DT> the data type of the observable features managed by this feature area
 * @param <FX> the type of the JavaFX {@code Region} associated with this feature area
 */
public non-sealed interface KlFeatureArea<LF extends LocatableFeature, FX extends Region>
        extends KlPropertyArea<LF, FX> {

    /**
     * Sets the specified feature in this feature area. This method assigns an observable
     * feature of type {@code DT} to the property of the area, allowing it to be observed
     * and managed within the current context.
     *
     * @param feature the observable feature of type {@code DT} to be set in the feature area
     */
    default void setFeature(LF feature) {
        getProperty().setValue(feature);
    }

    /**
     * Retrieves the observable feature managed by this feature area. The returned observable feature
     * represents the current value of type {@code DT} associated with the property of the feature area.
     *
     * @return an {@code ObservableFeature} of type {@code DT}, representing the current value managed
     *         within this feature area.
     */
    default LF getFeature() {
        return getProperty().getValue();
    }

    non-sealed interface Factory<LF extends LocatableFeature, FX extends Region, KL extends KlFeatureArea<LF, FX>>
            extends KlPropertyArea.Factory<LF, FX, KL> {

        default ImmutableList<Class<?>> areaFactoryServiceTypes() {
            return Lists.immutable.of(KlFeatureArea.Factory.class);
        }
    }

}
