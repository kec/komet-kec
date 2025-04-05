package dev.ikm.komet.layout.attribute;

import dev.ikm.komet.framework.observable.ObservableFieldDefinition;
import javafx.scene.layout.Region;

/**
 * A non-sealed specialized interface that extends {@code KlListOfAttributeArea}, specifically
 * designed for handling lists of {@code ObservableFieldDefinition} in association with JavaFX
 * {@code Region} components. This interface represents a focused implementation of the
 * {@code KlListArea} framework for managing attribute areas related to field definitions.
 * <p>
 * The primary purpose of this interface is to provide structured interactions and data binding
 * mechanisms for managing collections of observable field definitions tied to specific JavaFX
 * regions. It acts as a foundation for creating dynamic, responsive, and context-aware
 * attribute regions in the user interface.
 * <p>
 * Implementing classes must provide concrete behavior for managing these regions, including
 * functionalities such as subscribing to shared context spaces and supporting consistent
 * save/revert logic for corresponding UI components. Additionally, this interface allows
 * expanding the Knowledge Layout (KL) framework with field-specific attribute area extensions.
 *
 * @param <FX> The type of JavaFX {@code Region} associated with this attribute area. This
 *             represents the parent region responsible for containing and rendering the
 *             attribute area.
 */
public non-sealed interface KlListOfFieldDefinitionArea<FX extends Region>
        extends KlListOfAttributeArea<ObservableFieldDefinition, FX> {


    /**
     * Represents a specialized factory interface for creating instances of
     * {@code KlListOfAttributeDefinitionArea} and its related classes. This interface
     * builds upon the generic {@code KlListOfAttributeArea.Factory} interface by
     * defining methods tailored for producing attribute areas that handle lists of
     * {@code ObservableFieldDefinition} and their integration with JavaFX regions.
     * <p>
     * It simplifies the creation and management of attribute definition areas associated
     * with specific JavaFX {@code Region} components. The produced attribute areas are
     * designed to facilitate data binding and interaction within the framework.
     *
     * @param <FX> The type of JavaFX {@code Region} that serves as the parent region
     *             for the {@code KlListOfAttributeDefinitionArea}.
     * @param <KL> The type of {@code KlListOfAttributeDefinitionArea} instance created
     *             by this factory.
     */
    interface Factory<FX extends Region, KL extends KlListOfFieldDefinitionArea<FX>>
            extends KlListOfAttributeArea.Factory<ObservableFieldDefinition, FX, KL> {
    }
}
