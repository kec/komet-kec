package dev.ikm.komet.layout.component.field;

import dev.ikm.tinkar.entity.EntityVersion;
import javafx.collections.ObservableList;
import javafx.scene.layout.Region;

import java.util.List;

/**
 * Represents a non-sealed interface within the Knowledge Layout framework for managing
 * and interacting with fields containing observable lists of versioned entities. This
 * interface extends {@code KlListFieldArea} and is specifically tailored for handling
 * lists of versioned entities and their associated UI components.
 *
 * This interface provides type-safe operations to manage and observe both the list
 * of versioned entities and their selected items, enabling fine-grained interaction
 * with structured versioned data and its visualization in the user interface.
 *
 * @param <DT> the type of the observable list managed by the field area, extending {@code ObservableList}
 * @param <LE> the type of elements contained within the managed list, extending {@code EntityVersion}
 * @param <FX> the type of the parent UI element associated with this field area, extending {@code Region}
 */
public non-sealed interface KlListOfVersionsFieldArea<DT extends ObservableList<LE>,
        LE extends EntityVersion,
        FX extends Region>
        extends KlListFieldArea<DT, LE, FX> {
}
