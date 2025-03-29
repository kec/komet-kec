package dev.ikm.komet.layout.component.factory;

import dev.ikm.komet.framework.observable.ObservableEntity;
import dev.ikm.komet.layout.component.KlGenericComponentArea;

/**
 * A non-sealed interface representing a factory for creating instances of {@code KlGenericComponentArea}
 * associated with observable entities of type {@code ObservableEntity}.
 *
 * This factory provides a generic mechanism for creating component areas that manage observable entities
 * and their versions. It extends {@code KlComponentAreaFactory}, inheriting its functionality while
 * specializing it for {@code KlGenericComponentArea}.
 *
 * Implementations of this factory are responsible for the creation and initialization of
 * generic component areas, associating these areas with specific observable entities and
 * configuring their components based on provided preferences.
 *
 * @see KlComponentAreaFactory
 * @see KlGenericComponentArea
 */
public non-sealed interface KlGenericComponentAreaFactory
        extends KlComponentAreaFactory<KlGenericComponentArea, ObservableEntity> {
}
