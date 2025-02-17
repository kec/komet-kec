package dev.ikm.komet.layout.component.version;

import dev.ikm.komet.framework.observable.ObservableVersion;
import dev.ikm.komet.layout.component.KlPane;
import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.Pane;

/**
 * The {@code KlVersionPane} interface represents a pane that displays a single version of an entity.
 *
 * @param <OV> the type of the ObservableVersion that the pane works with
 *
 * @see ObservableVersion
 */
public sealed interface KlVersionPane <OV extends ObservableVersion, P extends Pane> extends KlPane<P>
        permits KlConceptVersionPane, KlGenericVersionPane, KlPatternVersionPane, KlSemanticVersionPane, KlStampVersionPane {
    /**
     * Returns the version of the observable entity associated with this pane.
     *
     * @return the version of the observable entity
     */
    default OV version() {
        return versionProperty().get();
    }

    /**
     * Returns the property representing the version of the observable entity associated with this pane.
     *
     * @return the property containing the version of the observable entity
     */
    ObjectProperty<OV> versionProperty();
}
