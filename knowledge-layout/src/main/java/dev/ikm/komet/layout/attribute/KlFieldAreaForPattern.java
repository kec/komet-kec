package dev.ikm.komet.layout.attribute;

import dev.ikm.tinkar.common.bind.annotations.axioms.ParentConcept;
import dev.ikm.tinkar.common.bind.annotations.names.FullyQualifiedName;
import dev.ikm.tinkar.common.bind.annotations.names.RegularName;
import dev.ikm.tinkar.terms.PatternFacade;
import javafx.scene.layout.Region;


@FullyQualifiedName("Knowledge layout pattern field area")
@RegularName("Pattern field area")
@ParentConcept(KlFieldArea.class)
public non-sealed interface KlFieldAreaForPattern<FX extends Region>
        extends KlFieldArea<PatternFacade, FX> {

    interface Factory<FX extends Region, KL extends KlFieldAreaForPattern<FX>>
            extends KlFieldArea.Factory<PatternFacade, FX, KL> {

    }
}
