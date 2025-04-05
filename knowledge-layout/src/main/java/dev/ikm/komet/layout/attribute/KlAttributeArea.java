package dev.ikm.komet.layout.attribute;

import dev.ikm.komet.framework.observable.ObservableAttribute;
import dev.ikm.komet.layout.KlFactory;
import dev.ikm.komet.layout.KlWidget;
import dev.ikm.komet.layout.area.KlArea;
import dev.ikm.tinkar.common.bind.ClassConceptBinding;
import dev.ikm.tinkar.common.bind.annotations.axioms.ParentProxy;
import dev.ikm.tinkar.common.bind.annotations.names.FullyQualifiedName;
import dev.ikm.tinkar.common.bind.annotations.names.RegularName;
import dev.ikm.tinkar.common.bind.annotations.publicid.PublicIdAnnotation;
import dev.ikm.tinkar.common.bind.annotations.publicid.UuidAnnotation;
import javafx.scene.layout.Region;

/**
 * Defines an interface within the Knowledge Layout framework for managing and interacting
 * with attributes of a specified data type and their associated JavaFX regions. This interface
 * provides methods for binding, observing, and manipulating attribute data in a type-safe manner.
 *
 * The interface extends multiple base types to integrate functionality for JavaFX region handling,
 * conceptual bindings, and knowledge layout area definitions.
 *
 * @param <DT> The data type managed by this attribute area.
 * @param <FX> The type of the JavaFX {@code Region} associated with this attribute area.
 */
@FullyQualifiedName("Knowledge layout regions")
@RegularName("Attribute regions")
@ParentProxy(parentName = "Komet panels (SOLOR)",
        parentPublicId = @PublicIdAnnotation(@UuidAnnotation("b3d1cdf6-27a5-502d-8f16-ed026a7b9d15")))
public sealed interface KlAttributeArea<DT, FX extends Region> extends KlWidget<FX>, ClassConceptBinding, KlArea<FX>
        permits KlFieldArea, KlListArea {

    /**
     * Sets the specified attribute to this attribute area. The attribute may represent
     * a piece of data or functionality that is managed and displayed within the associated
     * JavaFX {@code Region}. Implementations of this method define how the provided
     * attribute is internally applied and how it interacts with the UI region.
     *
     * @param attribute the attribute of type {@code DT} that needs to be set and managed
     *                  within this attribute area
     */
    void setAttribute(DT attribute);

    /**
     * Retrieves the attribute currently managed by this attribute area.
     * The returned attribute is of the type {@code DT}, representing the data
     * or functionality associated with the JavaFX {@code Region}.
     *
     * @return the attribute of type {@code DT} managed by this attribute area
     */
    DT getAttribute();

    /**
     * Represents a specialized factory interface for creating or restoring instances of a specified type.
     * This factory operates on data types (DT), a specific region implementation (FX),
     * and a custom Knowledge Layout area (KL) that extends the behavior of {@link KlFactory}.
     *
     * @param <DT> The data type managed by the factory.
     * @param <FX> The region type controlled by the factory. This extends the {@link Region} class.
     * @param <KL> The custom Knowledge Layout area type, which combines data type and region, extending {@link KlFactory}.
     */
    interface Factory<DT, FX extends Region, KL extends KlAttributeArea<DT, FX>>
            extends KlFactory<KL> {

    }
}
