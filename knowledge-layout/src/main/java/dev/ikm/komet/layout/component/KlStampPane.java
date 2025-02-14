package dev.ikm.komet.layout.component;

import dev.ikm.komet.framework.observable.ObservableStamp;
import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.Pane;

/**
 * Represents a single Stamp presented in a Pane.
 *
 * KlStampPane is a specialized interface that extends the KlComponentPane interface,
 * binding the observable component to an {@code ObservableStamp}. This allows the pane
 * to handle and present a specific stamp, with support for observing and modifying
 * the underlying stamp-related data.
 *
 * The `P` generic parameter defines the type of JavaFX {@code Pane} associated with this interface.
 *
 * @param <P> the type of JavaFX {@code Pane} for this component.
 * @see KlComponentPane
 */
public non-sealed interface KlStampPane<P extends Pane> extends KlComponentPane<ObservableStamp, P> {

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
