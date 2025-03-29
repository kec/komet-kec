package dev.ikm.komet.layout.attribute.factory;

import dev.ikm.komet.layout.attribute.KlAttributeArea;
import dev.ikm.komet.layout.attribute.KlComponentAttributeArea;
import dev.ikm.komet.layout.attribute.KlConceptAttributeArea;
import dev.ikm.tinkar.entity.ConceptEntity;
import javafx.scene.layout.Region;

/**
 * Represents a factory interface for creating and managing instances of
 * {@link KlConceptAttributeArea}, a specialized implementation of {@link KlAttributeArea}
 * designed for handling fields associated with {@link ConceptEntity}.
 *
 * This interface extends {@link KlFieldAreaFactory}, parameterized with
 * {@link ConceptEntity} for data binding, a specific JavaFX {@link Region} subclass for UI representation,
 * and {@link KlComponentAttributeArea} for component-specific functionality. It provides methods and
 * mechanisms to generate and manage field panes that integrate concept-related data
 * with JavaFX layouts.
 *
 * @param <FX> The JavaFX {@link Region} subclass associated with the field pane.
 */
public interface KlConceptFieldAreaFactory<FX extends Region> extends
        KlFieldAreaFactory<ConceptEntity, FX, KlComponentAttributeArea<ConceptEntity, FX>> {

}