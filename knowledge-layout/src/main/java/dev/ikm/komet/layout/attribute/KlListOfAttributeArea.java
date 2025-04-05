package dev.ikm.komet.layout.attribute;

import dev.ikm.komet.framework.observable.ObservableAttribute;
import dev.ikm.komet.framework.observable.ObservableField;
import dev.ikm.komet.layout.KlFactory;
import javafx.collections.ObservableList;
import javafx.scene.layout.Region;

/**
 * A sealed interface representing a specific type of list-based attribute area within the
 * Knowledge Layout (KL) framework. This interface is specialized in managing attribute areas
 * that handle observable lists of elements of type {@code LE}, where each element represents
 * an {@code ObservableAttribute}. Additionally, the interface integrates the functionality
 * with JavaFX components, particularly regions of type {@code FX}, enabling a seamless
 * interaction between observable attributes and their UI representation.
 * <p>
 * This interface builds upon the {@code KlListArea} interface, inheriting its list management
 * capabilities while focusing specifically on observable attributes. Concrete implementations
 * of this interface allow developers to manage and interact with attribute regions containing
 * lists of observable attributes in a structured and consistent manner.
 * <p>
 * It serves as a foundation for defining and extending functionality related to specific
 * attribute areas, supporting elements such as attribute definitions or observable fields.
 * Classes extending this interface must also be present in the sealed hierarchy defined by
 * this interface.
 *
 * @param <LE> the type of elements contained within the observable list, extending
 *             {@code ObservableAttribute<?>}
 * @param <FX> the type of the JavaFX parent region associated with the attribute area, extending
 *             {@code Region}
 */
public sealed interface KlListOfAttributeArea<LE extends ObservableAttribute<?>, FX extends Region>
        extends KlListArea<ObservableList<LE>, LE, FX>
        permits KlListOfFieldDefinitionArea, KlListOfFieldArea {

    /**
     * Represents a factory interface for creating instances of {@code KlListOfAttributeArea}
     * and its associated types. This interface serves as a blueprint for defining factories
     * that generate attribute areas containing observable attributes, enabling integration
     * with JavaFX regions.
     * <p>
     * This interface extends the functionality of {@code KlFactory}, focusing on the
     * creation of attribute-specific components that manage observable attributes within
     * a specified JavaFX region. It is designed to facilitate the creation and management
     * of complex UI components where observability and data binding are required.
     *
     * @param <LE> The type of elements extending {@code ObservableAttribute}, representing
     *             the observable attributes that are part of the attribute list.
     * @param <FX> The type of JavaFX region extending {@code Region}, representing the parent
     *             region that serves as the container for the attribute list area.
     * @param <KL> The type of {@code KlListOfAttributeArea} created by this factory, representing
     *             the specific implementation of the attribute list area.
     */
    interface Factory<LE extends ObservableAttribute<?>, FX extends Region, KL extends KlListOfAttributeArea<LE, FX>>
            extends KlFactory<KL> {
    }

}
