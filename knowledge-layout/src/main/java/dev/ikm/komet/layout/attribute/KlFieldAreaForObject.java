package dev.ikm.komet.layout.attribute;

import dev.ikm.tinkar.common.bind.annotations.axioms.ParentConcept;
import dev.ikm.tinkar.common.bind.annotations.names.FullyQualifiedName;
import dev.ikm.tinkar.common.bind.annotations.names.RegularName;
import javafx.scene.layout.Region;

/**
 * Represents a generic attribute pane in the Knowledge Layout framework that supports
 * managing and interacting with fields of generic data type. This interface extends
 * {@link KlAttributeArea} and is parameterized with {@code Object} as the data type and
 * a JavaFX {@link Region} as the parent UI component type.
 * <p>
 * This interface serves as a specialization of {@code KlFieldPane<Object, FX>}
 * and provides a base for attribute panes that do not have a predefined or specific
 * data type, allowing for flexible handling of various generic data fields.
 *
 * @param <FX> the type of the parent node associated with this pane
 */
@FullyQualifiedName("Knowledge layout object field area")
@RegularName("Object field area")
@ParentConcept(KlFieldArea.class)
public non-sealed interface KlFieldAreaForObject<FX extends Region> extends KlFieldArea<Object, FX> {

    /**
     * Represents a factory interface specialized for creating or restoring instances of
     * {@link KlFieldAreaForObject}, which is a type of field area managing generic data types
     * associated with a JavaFX {@link Region}.
     * <p>
     * This interface extends the base factory capabilities provided by {@link KlFieldArea.Factory},
     * inheriting its contract for creating, managing, and interacting with JavaFX regions
     * and their associated data models in the Knowledge Layout framework.
     *
     * @param <FX> The JavaFX {@link Region} type associated with this factory's operations.
     * @param <KL> The specific implementation of {@link KlFieldAreaForObject} that this factory creates or manages.
     */
    interface Factory<FX extends Region, KL extends KlFieldAreaForObject<FX>> extends KlFieldArea.Factory<Object, FX, KL> {
    }

}
