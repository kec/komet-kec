package dev.ikm.komet.layout.attribute;

import dev.ikm.tinkar.common.bind.annotations.axioms.ParentConcept;
import dev.ikm.tinkar.common.bind.annotations.names.FullyQualifiedName;
import dev.ikm.tinkar.common.bind.annotations.names.RegularName;
import dev.ikm.tinkar.terms.SemanticFacade;
import dev.ikm.tinkar.terms.StampFacade;
import javafx.scene.layout.Region;


@FullyQualifiedName("Knowledge layout pattern field area")
@RegularName("Pattern field area")
@ParentConcept(KlFieldArea.class)
public non-sealed interface KlFieldAreaForStamp<FX extends Region>
        extends KlFieldArea<StampFacade, FX> {


    interface Factory<FX extends Region, KL extends KlFieldAreaForSemantic<FX>>
            extends KlFieldArea.Factory<SemanticFacade, FX, KL> {

    }
}
