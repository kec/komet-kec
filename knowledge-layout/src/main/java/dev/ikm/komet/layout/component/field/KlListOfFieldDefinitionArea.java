package dev.ikm.komet.layout.component.field;

import dev.ikm.tinkar.component.FieldDefinition;
import javafx.collections.ObservableList;
import javafx.scene.layout.Region;

/**
 * Represents a specific implementation of the {@code KlListFieldArea} interface
 * within the Knowledge Layout framework. This interface is designed to manage
 * and interact with fields containing a list of {@code FieldDefinition} objects
 * and their associated parent UI components.
 *
 * The {@code KlListOfFieldDefinitionArea} provides type-safe operations for handling
 * lists of {@code FieldDefinition} items and integrating them with the parent user
 * interface, extending the functionality defined in {@code KlListFieldArea}.
 *
 * @param <DT> the type of the observable list managed by this field area, extending {@code ObservableList<LE>}
 * @param <LE> the type of elements contained within the managed list, extending {@code FieldDefinition}
 * @param <FX> the type of the parent UI element associated with this field area, extending {@code Region}
 */
public non-sealed interface KlListOfFieldDefinitionArea<DT extends ObservableList<LE>, LE extends FieldDefinition, FX extends Region>
        extends KlListFieldArea<DT, LE, FX> {
}
