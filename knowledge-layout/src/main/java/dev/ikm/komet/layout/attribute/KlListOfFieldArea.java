package dev.ikm.komet.layout.attribute;

import dev.ikm.komet.framework.observable.ObservableAttribute;
import dev.ikm.komet.framework.observable.ObservableField;
import dev.ikm.tinkar.common.bind.ClassConceptBinding;
import dev.ikm.tinkar.common.bind.annotations.axioms.ParentProxy;
import dev.ikm.tinkar.common.bind.annotations.names.FullyQualifiedName;
import dev.ikm.tinkar.common.bind.annotations.names.RegularName;
import dev.ikm.tinkar.common.bind.annotations.publicid.PublicIdAnnotation;
import dev.ikm.tinkar.common.bind.annotations.publicid.UuidAnnotation;
import javafx.collections.ObservableList;
import javafx.scene.layout.Region;

/**
 * Represents a field component list area within the Knowledge Layout system.
 * This interface defines the structure and behavior of a component area that manages
 * an observable list of field components. Each instance of this component is uniquely
 * associated with a particular region and binds to a defined class concept.
 *
 * @param <LE> the type of elements that extend {@link ObservableAttribute}, representing the field components
 * @param <FX> the type of JavaFX {@link Region} associated with the graphical area
 */
@FullyQualifiedName("Knowledge Layout field list area")
@RegularName("Field list area")
@ParentProxy(parentName = "Komet panels (SOLOR)",
        parentPublicId = @PublicIdAnnotation(@UuidAnnotation("b3d1cdf6-27a5-502d-8f16-ed026a7b9d15")))
public non-sealed interface KlListOfFieldArea<FX extends Region>
        extends KlListOfAttributeArea<ObservableField<?>, FX>, ClassConceptBinding {


    /**
     * Represents a factory interface specialized in creating instances of {@code KlListOfFieldArea}.
     * This factory provides methods to create and restore implementations of {@code KlListOfFieldArea},
     * which are used to manage observable field components within a specific JavaFX region.
     *
     * @param <FX> the type of JavaFX {@code Region} associated with the field area
     * @param <KL> the specific type of {@code KlListOfFieldArea} to be created by this factory
     */
    interface Factory<FX extends Region, KL extends KlListOfFieldArea<FX>>
            extends KlListOfAttributeArea.Factory<ObservableField<?>, FX, KL> {
    }
}
