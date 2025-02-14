package dev.ikm.komet.layout.component.field;

import dev.ikm.tinkar.entity.ConceptEntity;

/**
 * Represents a pane in the Knowledge Layout framework that is associated with fields
 * tied to concept entities. This interface extends {@code KlFieldPane}, providing
 * specialized support for managing and interacting with fields whose values are
 * of type {@code ConceptEntity}.
 *
 * @param <E> the type of {@code ConceptEntity} associated with this field pane
 */
public non-sealed interface KlConceptFieldPane<E extends ConceptEntity>
        extends KlFieldPane<E> {
}