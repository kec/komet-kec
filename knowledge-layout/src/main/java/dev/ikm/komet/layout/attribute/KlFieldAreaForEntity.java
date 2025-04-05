package dev.ikm.komet.layout.attribute;

import dev.ikm.tinkar.common.bind.annotations.axioms.ParentConcept;
import dev.ikm.tinkar.common.bind.annotations.names.FullyQualifiedName;
import dev.ikm.tinkar.common.bind.annotations.names.RegularName;
import dev.ikm.tinkar.terms.EntityFacade;
import javafx.scene.layout.Region;

/**
 * Defines a specialized, non-sealed interface within the Knowledge Layout framework for managing
 * fields representing entities. This interface extends the {@link KlFieldArea} interface, adding
 * behavior specific to managing fields associated with {@link EntityFacade} data types.
 *
 * The purpose of this interface is to provide a structured mechanism for integrating JavaFX
 * {@link Region} components with field areas representing entities in reactive and declarative
 * UI programming. It ensures type-safe interaction with fields related to entities while leveraging
 * the capabilities offered by the base {@link KlFieldArea} interface.
 *
 * @param <FX> The type of the JavaFX {@link Region} associated with this field area.
 */
@FullyQualifiedName("Knowledge layout entity field area")
@RegularName("Entity field area")
@ParentConcept(KlFieldArea.class)
public non-sealed interface KlFieldAreaForEntity<FX extends Region>
        extends KlFieldArea<EntityFacade, FX> {

    /**
     * Represents a factory interface in the context of the Knowledge Layout framework for creating or managing
     * specialized field areas associated with {@link EntityFacade} data types. This factory operates specifically
     * on JavaFX {@link Region} components, enabling the construction or restoration of field areas intended
     * for managing entities and their attributes in a type-safe manner.
     * <p>
     * This interface extends the generic factory pattern defined in {@link KlFieldArea.Factory}, providing
     * additional specialization for integrating JavaFX regions with {@link KlFieldAreaForEntity} implementations.
     *
     * @param <FX> The type of JavaFX {@link Region} associated with the field area, ensuring compatibility
     *             with the UI components managed by the factory.
     */
    interface Factory<FX extends Region> extends
            KlFieldArea.Factory<EntityFacade, FX, KlFieldAreaForEntity<FX>> {
    }

}
