package dev.ikm.komet.layout.feature;

import dev.ikm.komet.framework.observable.ObservableField;
import dev.ikm.komet.framework.observable.ObservableFieldDefinition;
import javafx.scene.layout.Region;

public non-sealed interface KlFieldDefinitionArea<FX extends Region>
        extends KlFieldArea<ObservableFieldDefinition, FX> {

    interface Factory<FX extends Region>
            extends KlFieldArea.Factory<ObservableFieldDefinition, FX, KlFieldDefinitionArea<FX>> {
    }

}
