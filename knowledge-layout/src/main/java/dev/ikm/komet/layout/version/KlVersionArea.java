package dev.ikm.komet.layout.version;

import dev.ikm.komet.framework.observable.ObservableVersion;
import dev.ikm.komet.layout.KlArea;
import dev.ikm.komet.layout.KlParent;
import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.Pane;

/**
 * The {@code KlVersionPane} interface represents a pane that displays a single version of an entity.
 *
 * @param <OV> the type of the ObservableVersion that the pane works with
 *
 * @see ObservableVersion
 */
public sealed interface KlVersionArea<OV extends ObservableVersion, FX extends Pane> extends KlArea<FX>, KlParent
        permits KlConceptVersionArea, KlGenericVersionArea, KlPatternVersionArea, KlSemanticVersionArea, KlStampVersionArea {
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

    sealed interface Factory<FX extends Pane, OV extends ObservableVersion, KL extends KlVersionArea<OV, FX>>
            extends KlArea.Factory<FX, KL>
            permits KlConceptVersionArea.Factory, KlGenericVersionArea.Factory, KlPatternVersionArea.Factory,
                    KlSemanticVersionArea.Factory, KlStampVersionArea.Factory {


    }
}
