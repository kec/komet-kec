package dev.ikm.komet.layout.field;

import javafx.collections.ObservableList;
import javafx.scene.layout.Region;

/**
 * Represents a sealed interface within the Knowledge Layout framework for managing
 * and interacting with fields containing observable lists. This interface provides
 * type-safe operations and data-binding capabilities for observable lists and their
 * parent UI components.
 *
 * This interface is extended by specific implementations, such as {@code KlListOfFieldDefinitionArea},
 * {@code KlListOfFieldArea}, and {@code KlListOfVersionsFieldArea}, each dealing with specific types
 * of list structures. It supports manipulation and observation of both lists and selected items.
 *
 * @param <DT> the type of the observable list managed by the field area, extending {@code ObservableList}
 * @param <LE> the type of elements contained within the managed list
 * @param <FX> the type of the parent UI element associated with this field area, extending {@code Region}
 */
public sealed interface KlListFieldArea<DT extends ObservableList<LE>, LE, FX extends Region>
        extends KlFieldArea<DT, FX>
        permits KlListOfFieldDefinitionArea, KlListOfFieldArea, KlListOfVersionsFieldArea {

    /**
     * Retrieves the observable list of elements managed by the field area.
     * The list represents the data elements of type {@code LE} contained within
     * the associated field.
     *
     * @return an {@code ObservableList} of elements of type {@code LE} representing the managed list
     */
    default ObservableList<LE> list() {
        return getField().value();
    }

    /**
     * Retrieves the observable list of selected elements managed by the field area.
     * The returned list represents the currently selected elements of type {@code LE}.
     *
     * @return an {@code ObservableList} of elements of type {@code LE}, representing the selected items
     */
    ObservableList<LE> selectedItems();
}
