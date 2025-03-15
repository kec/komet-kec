package dev.ikm.komet.layout.component;

import dev.ikm.komet.framework.observable.ObservableEntity;
import dev.ikm.komet.framework.observable.ObservableVersion;
import dev.ikm.tinkar.entity.EntityVersion;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;


/**
 * Represents a generic component area within a JavaFX application that manages observable entities.
 *
 * The KlComponentArea interface is a sealed interface designed to represent areas of a UI that
 * are associated with observable components. It acts as a foundational interface for handling
 * observable entities, observable versions, and their JavaFX panes, enabling a structured approach
 * to managing components in a user interface.
 *
 * The type parameters used by this interface allow for flexibility and specificity:
 * - {@code OE}: Represents the type of the observable entity associated with the component area.
 * - {@code OV}: Represents the type of the observable version associated with the entity.
 * - {@code V}: Represents the version type of the entity.
 * - {@code FX}: Represents the type of the JavaFX {@code Pane} used by this component area.
 *
 * Concrete implementations of this interface are defined by specific subtypes, which handle
 * specialized behaviors for various entity types, such as concepts, patterns, semantics, stamps, etc.
 *
 * The interface provides methods to access the observable component, interact with its property, and manage
 * a list of selected versions, contributing to efficient UI updates and interactions in JavaFX applications.
 *
 * @param <OE> the type of the observable entity associated with this component area
 * @param <OV> the type of the observable version associated with this entity
 * @param <V> the version type of the entity
 * @param <FX> the type of the JavaFX {@code Pane} representing this component area
 * @see KlConceptArea
 * @see KlGenericComponentArea
 * @see KlPatternArea
 * @see KlSemanticArea
 * @see KlStampArea
 */
public sealed interface KlComponentArea<OE extends ObservableEntity<OV>, OV extends ObservableVersion<? extends EntityVersion>,
        FX extends Pane>
        extends KlArea<FX>
        permits KlConceptArea, KlGenericComponentArea, KlPatternArea, KlSemanticArea, KlStampArea {
    /**
     * Retrieves the observable component associated with this pane.
     *
     * @return the observable component of type {@code OE}.
     */
    default OE observableComponent() {
        return componentProperty().get();
    }

    /**
     * Provides access to the JavaFX {@code ObjectProperty} holding the observable entity
     * associated with this component pane. This property allows for retrieving and observing
     * changes to the underlying component.
     *
     * @return the {@code ObjectProperty} encapsulating the observable component of type {@code OE}
     */
    ObjectProperty<OE> componentProperty();

    /**
     * Retrieves the list of selected observable versions associated with the component area.
     *
     * This method provides access to an observable list, enabling tracking and reacting to changes
     * in the currently selected versions of observable entities. Changes to the list can be observed,
     * making it useful for binding or responding to user interactions within the user interface.
     *
     * @return an {@code ObservableList} containing the selected versions of type {@code OV}.
     */
    ObservableList<OV> selectedVersions();
}
