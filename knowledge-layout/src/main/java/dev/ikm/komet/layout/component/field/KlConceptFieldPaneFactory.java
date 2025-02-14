package dev.ikm.komet.layout.component.field;

import dev.ikm.tinkar.entity.ConceptEntity;

/**
 * A factory interface for creating instances of {@code KlConceptFieldPane}.
 * This interface extends {@code KlFieldPaneFactory} and specializes it for
 * creating field panes that are designed to manage and interact with fields
 * associated with concept entities in the Knowledge Layout framework.
 *
 * The implementation provides a default method to define the interface class
 * as {@code KlComponentFieldPane.class}.
 */
public interface KlConceptFieldPaneFactory extends
        KlFieldPaneFactory<ConceptEntity, KlComponentFieldPane<ConceptEntity>> {

    @Override
    default Class<KlComponentFieldPane<ConceptEntity>> klInterfaceClass() {
        return (Class<KlComponentFieldPane<ConceptEntity>>) (Class<?>) KlComponentFieldPane.class;
    }
}