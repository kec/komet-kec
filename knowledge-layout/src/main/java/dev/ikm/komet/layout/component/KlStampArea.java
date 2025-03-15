package dev.ikm.komet.layout.component;

import dev.ikm.komet.framework.observable.ObservableSemantic;
import dev.ikm.komet.framework.observable.ObservableSemanticVersion;
import dev.ikm.komet.framework.observable.ObservableStamp;
import dev.ikm.komet.framework.observable.ObservableStampVersion;
import dev.ikm.tinkar.entity.SemanticVersionRecord;
import dev.ikm.tinkar.entity.StampVersionRecord;
import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.Pane;

/**
 * The KlStampArea interface represents a specific type of component area within a JavaFX application
 * that is associated with observable stamps. This interface extends the KlComponentArea interface,
 * parameterized for observable stamps and their versions, providing specialized behavior and properties
 * related to stamp management in a user interface.
 *
 * It provides mechanisms to access and manage the observable stamp and its associated
 * JavaFX ObjectProperty, facilitating interaction and binding within application UIs.
 *
 * @param <FX> the type of the JavaFX {@code Pane} used by this component area
 * @see KlComponentArea
 * @see ObservableStamp
 */
public non-sealed interface KlStampArea<FX extends Pane>
        extends KlComponentArea<ObservableStamp, ObservableStampVersion, FX> {

    /**
     * Retrieves the observable stamp associated with this pane.
     *
     * This method provides access to the current value of the observable stamp by getting
     * the value from the JavaFX {@code ObjectProperty} that encapsulates the observable entity.
     *
     * @return the {@code ObservableStamp} associated with this pane.
     */
    default ObservableStamp observableStamp() {
        return componentProperty().get();
    }

    /**
     * Retrieves the JavaFX {@code ObjectProperty} that encapsulates the
     * {@code ObservableStamp} associated with this pane. This property allows
     * for observing changes to the stamp or modifying its value.
     *
     * @return the {@code ObjectProperty} holding the {@code ObservableStamp}.
     */
    default ObjectProperty<ObservableStamp> stampSemantic() {
        return componentProperty();
    }

}
