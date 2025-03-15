package dev.ikm.komet.layout.component;

import dev.ikm.komet.framework.observable.ObservableConcept;
import dev.ikm.komet.framework.observable.ObservableConceptVersion;
import dev.ikm.komet.framework.observable.ObservableEntity;
import dev.ikm.komet.framework.observable.ObservableVersion;
import dev.ikm.tinkar.entity.ConceptVersionRecord;
import dev.ikm.tinkar.entity.EntityVersion;
import javafx.scene.layout.Pane;

/**
 * Represents a generic, non-sealed component area in a JavaFX application that manages observable entities
 * and their corresponding versions within a specific JavaFX {@code Pane}.
 *
 * This interface extends the functionality of the {@code KlComponentArea} interface by introducing a more
 * generic approach to handling component areas and observable entities. It allows implementations to define
 * custom logic for interacting with a generic pane type and its associated entities.
 *
 * The type parameter {@code FX} ensures that implementations can work with a specified type of JavaFX {@code Pane}
 * while maintaining compatibility with observable entities and versions.
 *
 * @param <FX> the type of JavaFX {@code Pane} associated with this component area
 * @see KlComponentArea
 */
public non-sealed interface KlGenericComponentArea<FX extends Pane>
        extends KlComponentArea<ObservableEntity<ObservableVersion<EntityVersion>>, ObservableVersion<EntityVersion>, FX> {
}
