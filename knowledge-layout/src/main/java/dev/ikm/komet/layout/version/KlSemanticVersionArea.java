package dev.ikm.komet.layout.version;


import dev.ikm.komet.framework.observable.ObservableSemanticVersion;
import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.Pane;

/**
 * The {@code KlSemanticVersionPane} interface represents a pane that displays a single version
 * of a semantic entity.
 *
 * This interface extends {@link KlVersionArea} and is specifically tailored for
 * handling {@link ObservableSemanticVersion} types.
 *
 * It provides methods to access and interact with versions of semantic entities
 * in the context of a JavaFX UI component.
 *
 * @param <FX> the type of the Pane that the interface works with
 *
 * @see KlVersionArea
 * @see ObservableSemanticVersion
 */
public non-sealed interface KlSemanticVersionArea<FX extends Pane> extends KlVersionArea<ObservableSemanticVersion, FX> {
    default ObservableSemanticVersion semanticVersion() {
        return KlVersionArea.super.version();
    }

    default ObjectProperty<ObservableSemanticVersion> semanticVersionProperty() {
        return versionProperty();
    }
}
