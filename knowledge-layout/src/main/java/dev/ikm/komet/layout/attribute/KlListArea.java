package dev.ikm.komet.layout.attribute;

import javafx.collections.ObservableList;
import javafx.scene.layout.Region;

/**
 * A sealed interface representing a specialized area within the Knowledge Layout framework
 * for managing observable lists of elements within a UI region. The interface provides
 * methods to handle the observation of list items and their selected subsets, enabling
 * dynamic interaction with JavaFX-based components.
 *
 * This interface extends {@link KlAttributeArea}, focusing on attribute areas
 * that specifically deal with list-based data models. Through the use of generics,
 * this interface ensures type safety for the observable list, the elements contained
 * in the list, and the associated JavaFX region.
 *
 * @param <DT> the type of the observable list of elements managed by this area,
 *             extending {@code ObservableList<LE>}
 * @param <LE> the type of elements contained within the observable list
 * @param <FX> the type of the JavaFX region associated with the attribute area,
 *             extending {@code Region}
 */
public sealed interface KlListArea<DT extends ObservableList<LE>, LE, FX extends Region>
        extends KlAttributeArea<DT, FX>
        permits KlListOfAttributeArea, KlListOfVersionArea {

    /**
     * Sets the attribute for the current list area. The given attribute is set internally
     * as the managed list within the attribute area.
     *
     * @param list the data of type {@code DT} to be set as the current attribute
     */
    default void setAttribute(DT list) {
        setList(list);
    }

    /**
     * Retrieves the attribute currently managed by this list area.
     * This method delegates internally to the {@code getList} method
     * to fetch and return the associated attribute.
     *
     * @return the attribute of type {@code DT} managed by this list area
     */
    default DT getAttribute() {
        return getList();
    }

    /**
     * Retrieves the internal list managed by this list area.
     *
     * @return the list of type {@code DT} managed within this list area
     */
    DT getList();

    /**
     * Updates the internal list managed by this list area. The provided list
     * is set as the current list to be handled within the attribute area.
     *
     * @param list the list of type {@code DT} that is to be set and managed
     *             within this list area
     */
    void setList(DT list);


    /**
     * Retrieves the observable list of selected elements managed by the attribute area.
     * The returned list represents the currently selected elements of type {@code LE}.
     *
     * @return an {@code ObservableList} of elements of type {@code LE}, representing the selected items
     */
    ObservableList<LE> selectedItems();

    /**
     * Represents a factory interface for creating and managing instances of components
     * dealing with observable lists of entities and their associated JavaFX regions.
     *
     * This interface extends the functionality of the {@code KlAttributeArea.Factory}
     * to specialize in the creation of attribute areas specifically designed for list-based
     * data models. The factory encapsulates the behavior required to construct and manage
     * components that bind observable lists with their corresponding JavaFX {@code Region}
     * representations.
     *
     * @param <LE> the type of elements contained within the observable list, representing list entries
     * @param <FX> the type of JavaFX region associated with the factory, extending {@code Region}
     * @param <KL> the type of the custom Knowledge Layout area, extending {@code KlListArea} for
     *            handling and displaying list-based attributes
     */
    interface Factory<LE,
                      FX extends Region,
                      KL extends KlListArea<ObservableList<LE>, LE, FX>> extends
            KlAttributeArea.Factory<ObservableList<LE>, FX, KL> {
    }
}
