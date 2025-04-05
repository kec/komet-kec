package dev.ikm.komet.layout.attribute;

import dev.ikm.komet.layout.KlFactory;
import dev.ikm.tinkar.common.bind.annotations.axioms.ParentConcept;
import dev.ikm.tinkar.common.bind.annotations.axioms.ParentProxy;
import dev.ikm.tinkar.common.bind.annotations.names.FullyQualifiedName;
import dev.ikm.tinkar.common.bind.annotations.names.RegularName;
import dev.ikm.tinkar.common.bind.annotations.publicid.PublicIdAnnotation;
import dev.ikm.tinkar.common.bind.annotations.publicid.UuidAnnotation;
import javafx.scene.layout.Region;

/**
 * Defines a sealed interface within the Knowledge Layout framework for managing and interacting
 * with fields of a specified data type and their associated JavaFX regions. This interface
 * extends {@link KlAttributeArea} to provide additional behavior specific to field-based
 * attributes, supporting manipulation and observation of these attributes in a type-safe manner.
 * <p>
 * This sealed interface serves as a base for more specialized interfaces or implementations,
 * which can define behavior specific to particular data types or UI component types. It ensures
 * compatibility with JavaFX regions and supports reactive and declarative UI programming.
 *
 * @param <DT> The data type managed by this field area.
 * @param <FX> The type of the JavaFX {@link Region} associated with this field area.
 */
@FullyQualifiedName("Knowledge layout field area")
@RegularName("Field area")
@ParentConcept(KlAttributeArea.class)
public sealed interface KlFieldArea<DT, FX extends Region> extends KlAttributeArea<DT, FX >
        permits KlFieldAreaForBoolean, KlFieldAreaForConcept, KlFieldAreaForEntity, KlFieldAreaForObject, KlFieldAreaForPattern, KlFieldAreaForPublicId, KlFieldAreaForSemantic, KlFieldAreaForStamp {
    /**
     * Represents a specialized factory interface for creating or restoring instances of a specified type.
     * This factory operates on data types (DT), a specific region implementation (FX),
     * and a custom Knowledge Layout area (KL) that extends the behavior of {@link KlFactory}.
     *
     * @param <DT> The data type managed by the factory.
     * @param <FX> The region type controlled by the factory. This extends the {@link Region} class.
     * @param <KL> The custom Knowledge Layout area type, which combines data type and region, extending {@link KlFactory}.
     */
    interface Factory<DT, FX extends Region, KL extends KlFieldArea<DT, FX>>
            extends KlAttributeArea.Factory<DT, FX, KL> {
    }
}
