package dev.ikm.komet.layout.component;

import dev.ikm.komet.framework.observable.ObservableSemantic;
import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.Pane;

/**
 * Represents a single Semantic presented in a Pane.
 *
 * KlSemanticPane is a specialized interface that extends the KlComponentPane interface
 * with an {@code ObservableSemantic}, enabling this pane to handle and present
 * specific semantic-related components and data. It provides methods for accessing
 * and managing the encapsulated semantic-related observable entity, ensuring the
 * semantic data is both retrievable and modifiable.
 *
 * The `P` generic parameter defines the type of JavaFX {@code Pane} associated with this interface.
 *
 * @param <P> the type of JavaFX {@code Pane} for this component.
 * @see KlComponentPane
 */
public non-sealed interface KlSemanticPane<P extends Pane> extends KlComponentPane<ObservableSemantic, P> {

    /**
     * Retrieves the observable semantic pattern associated with this pane.
     *
     * This method provides access to the current value of the observable semantic
     * pattern by getting the value from the JavaFX {@code ObjectProperty} that
     * encapsulates the observable entity.
     *
     * @return the {@code ObservableSemantic} associated with this pane
     */
    default ObservableSemantic observablePattern() {
        return componentProperty().get();
    }

    /**
     * Retrieves the JavaFX {@code ObjectProperty} encapsulating the
     * {@code ObservableSemantic} associated with this pane. This property
     * allows for observing changes to the semantic pattern or modifying its value.
     *
     * @return the {@code ObjectProperty} holding the {@code ObservableSemantic}.
     */
    default ObjectProperty<ObservableSemantic> patternSemantic() {
        return componentProperty();
    }

}
