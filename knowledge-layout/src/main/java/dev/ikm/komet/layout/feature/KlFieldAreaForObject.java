package dev.ikm.komet.layout.feature;

import dev.ikm.komet.framework.observable.ObservableField;
import dev.ikm.komet.layout.area.KlPropertyArea;
import dev.ikm.tinkar.common.bind.annotations.axioms.ParentConcept;
import dev.ikm.tinkar.common.bind.annotations.names.FullyQualifiedName;
import dev.ikm.tinkar.common.bind.annotations.names.RegularName;
import javafx.scene.layout.Region;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * Represents an interface in the Knowledge Layout framework to manage field areas
 * specifically associated with objects and their corresponding JavaFX regional components.
 * This non-sealed interface builds upon {@link KlFieldArea} with a generalized focus
 * on object-related attributes and their visualization.
 *
 * @param <FX> The type of JavaFX {@link Region} associated with this field area for displaying or managing the object-related fields.
 */
@FullyQualifiedName("Knowledge layout object field area")
@RegularName("Object field area")
@ParentConcept(KlFieldArea.class)
public non-sealed interface KlFieldAreaForObject<FX extends Region>
        extends KlFieldArea<Object, FX> {

    /**
     * Represents a factory interface for creating and managing instances of knowledge layout field areas specifically
     * associated with objects and their corresponding JavaFX regional components. This factory provides the contract
     * for building field areas associated with object data types and managing their properties within JavaFX regions.
     *
     * Extending from {@code KlFieldArea.Factory}, this interface focuses on field areas that integrate object-type observable
     * fields with JavaFX {@code Region} elements. It enables the creation, restoration, and configuration of these field
     * areas, supporting modular and reusable components for object-related layouts.
     *
     * @param <FX> the type of JavaFX region associated with the field area, extending {@code Region}.
     */
    interface Factory<FX extends Region>
            extends KlFieldArea.Factory<Object, FX, KlFieldAreaForObject<FX>> {
        @Override
        default ImmutableList<Class<?>> areaFactoryServiceTypes() {
            return Lists.immutable.of(KlFieldArea.Factory.class);
        }
    }

}
