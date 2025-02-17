package dev.ikm.komet.layout.component;

import dev.ikm.komet.framework.observable.ObservableStamp;
import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.Pane;

/**
 * Represents a pane bound to an observable stamp within a JavaFX application.
 *
 * KlStampPane is a specialized interface that extends the {@code KlComponentPane} interface,
 * specifically associating its observable component with an {@code ObservableStamp}.
 * This allows the pane to manage and present an observable stamp while supporting properties
 * and features that enable observation and modification of its state.
 *
 * By implementing this interface, one can work with the observable stamp and its
 * related JavaFX {@code Pane}, providing a seamless integration between UI components
 * and the underlying stamp-related observable data.
 *
 * @see KlComponentPane
 * @see ObservableStamp
 */
public non-sealed interface KlStampPane extends KlComponentPane<ObservableStamp, Pane> {

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
