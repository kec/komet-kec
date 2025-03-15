package dev.ikm.komet.layout.component.field;

import dev.ikm.tinkar.entity.Field;
import javafx.collections.ObservableList;
import javafx.scene.layout.Region;

/**
 * Represents a non-sealed interface in the Knowledge Layout framework for managing
 * and interacting with fields containing observable lists and their parent UI components.
 * This interface serves as an extension of {@code KlListFieldArea}, providing additional
 * functionality specific to lists of fields.
 *
 * This interface is parameterized to support type-safe operations on different types
 * of observable lists, their elements, and the associated FX components.
 *
 * @param <DT> the type of the observable list managed by the field area, extending {@code ObservableList}
 * @param <LE> the type of elements contained within the managed list, extending {@code Field<LE>}
 * @param <FX> the type of the parent UI element associated with this field area, extending {@code Region}
 */
public non-sealed interface KlListOfFieldArea<DT extends ObservableList<LE>, LE extends Field<LE>, FX extends Region>
        extends KlListFieldArea<DT, LE, FX> {
}
