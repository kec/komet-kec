package dev.ikm.komet.layout.attribute;

import dev.ikm.tinkar.common.bind.annotations.axioms.ParentConcept;
import dev.ikm.tinkar.common.bind.annotations.names.FullyQualifiedName;
import dev.ikm.tinkar.common.bind.annotations.names.RegularName;
import dev.ikm.tinkar.common.id.PublicId;
import javafx.scene.layout.Region;

/**
 * Represents a non-sealed interface within the Knowledge Layout framework for
 * managing and interacting with fields associated with {@link PublicId}.
 * This interface provides functionality for working with attributes of type
 * {@code PublicId} and their associated JavaFX parent regions.
 * <p>
 * This interface extends the {@link KlAttributeArea} and specifies the
 * {@link PublicId} as the data type and a JavaFX {@link Region} as the
 * parent UI component type. It is designed to facilitate the manipulation
 * and observation of attributes related to public identifiers while
 * maintaining type safety and supporting reactive data-binding behavior.
 *
 * @param <FX> the type of the parent JavaFX {@link Region} associated with this attribute area
 */
@FullyQualifiedName("Knowledge layout public identifier field area")
@RegularName("Public identifier field area")
@ParentConcept(KlFieldArea.class)
public non-sealed interface KlFieldAreaForPublicId<FX extends Region> extends KlFieldArea<PublicId, FX> {

    /**
     * Represents a factory interface for creating or restoring instances of objects associated
     * with {@link PublicId} within the Knowledge Layout framework. This factory operates
     * specifically on regions extending the JavaFX {@link Region} class and knowledge layout areas
     * that extend {@link KlFieldAreaForPublicId}.
     *
     * This interface is a specialized variant of the {@link KlFieldArea.Factory} that defines
     * type-safe operations for factories dealing with {@link PublicId} attributes, allowing for the
     * creation and restoration of objects adhering to these constraints. It enables seamless interaction
     * with JavaFX UI components and provides integration with the reactive behavior of the framework.
     *
     * @param <FX> The type of the JavaFX {@link Region} controlled by this factory.
     * @param <KL> The type of the knowledge layout area created or restored by this factory,
     *             extending {@link KlFieldAreaForPublicId}.
     */
    interface Factory<FX extends Region, KL extends KlFieldAreaForPublicId<FX>> extends KlFieldArea.Factory<PublicId, FX, KL> {
    }
}
