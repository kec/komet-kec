package dev.ikm.komet.layout.component.field;

import dev.ikm.komet.layout.KlWidget;
import dev.ikm.tinkar.entity.ConceptEntity;
import javafx.scene.layout.Pane;

/**
 * Represents a factory interface for creating and managing instances of
 * {@link KlConceptFieldPane}, a specialized implementation of {@link KlFieldPane}
 * designed for handling fields associated with {@link ConceptEntity}.
 *
 * This interface extends {@link KlFieldPaneFactory}, parameterized with
 * {@link ConceptEntity} for data binding, a specific JavaFX {@link Pane} subclass for UI representation,
 * and {@link KlComponentFieldPane} for component-specific functionality. It provides methods and
 * mechanisms to generate and manage field panes that integrate concept-related data
 * with JavaFX layouts.
 *
 * @param <FX> The JavaFX {@link Pane} subclass associated with the field pane.
 */
public interface KlConceptFieldPaneFactory<FX extends Pane> extends
        KlFieldPaneFactory<ConceptEntity, FX, KlComponentFieldPane<ConceptEntity, FX>> {

}