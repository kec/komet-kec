package dev.ikm.komet.layout.component.version;


import dev.ikm.komet.framework.observable.ObservableSemanticVersion;
import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.Pane;

/**
 * The {@code KlSemanticVersionPane} interface represents a pane that displays a single version
 * of a semantic entity.
 *
 * This interface extends {@link KlVersionPane} and is specifically tailored for
 * handling {@link ObservableSemanticVersion} types.
 *
 * It provides methods to access and interact with versions of semantic entities
 * in the context of a JavaFX UI component.
 *
 * @param <P> the type of the Pane that the interface works with
 *
 * @see KlVersionPane
 * @see ObservableSemanticVersion
 */
public non-sealed interface KlSemanticVersionPane<P extends Pane> extends KlVersionPane<ObservableSemanticVersion, P> {
    default ObservableSemanticVersion semanticVersion() {
        return KlVersionPane.super.version();
    }

    default ObjectProperty<ObservableSemanticVersion> semanticVersionProperty() {
        return versionProperty();
    }
}
