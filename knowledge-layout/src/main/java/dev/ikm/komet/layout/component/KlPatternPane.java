package dev.ikm.komet.layout.component;

import dev.ikm.komet.framework.observable.ObservablePattern;
import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.Pane;

/**
 * Represents a single Pattern presented in a Pane.
 *
 * KlPatternPane is a specialized interface that extends the KlComponentPane interface,
 * binding the observable component to an {@code ObservablePattern}. This allows the pane
 * to handle and present a specific pattern, with support for observing and modifying
 * the underlying pattern-related data.
 *
 * The `P` generic parameter defines the type of JavaFX {@code Pane} associated with this interface.
 *
 * @param <P> the type of JavaFX {@code Pane} for this component.
 * @see KlComponentPane
 * @see ObservablePattern
 */
public non-sealed interface KlPatternPane<P extends Pane> extends KlComponentPane<ObservablePattern, P> {

    /**
     * Retrieves the observable pattern associated with this pane.
     *
     * This method provides the current value of the observable pattern by accessing
     * the JavaFX {@code ObjectProperty} that encapsulates the observable entity.
     *
     * @return the {@code ObservablePattern} associated with this pane
     */
    default ObservablePattern observablePattern() {
        return componentProperty().get();
    }

    /**
     * Retrieves the JavaFX {@code ObjectProperty} that encapsulates the
     * {@code ObservablePattern} associated with this pane. This property allows
     * for observing changes to the pattern or modifying its value.
     *
     * @return the {@code ObjectProperty} holding the {@code ObservablePattern}.
     */
    default ObjectProperty<ObservablePattern> patternProperty() {
        return componentProperty();
    }

}
