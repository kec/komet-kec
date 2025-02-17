package dev.ikm.komet.layout.component.field;

import dev.ikm.tinkar.entity.Entity;
import javafx.scene.Parent;

/**
 * Represents a pane in the Knowledge Layout framework that is associated with component-related fields.
 * This interface extends {@code KlFieldPane}, parameterized with an entity type {@code E} and a parent type {@code P}.
 * It provides specialized support for handling and interacting with fields tied to component entities.
 *
 * This interface serves as a specialization of {@code KlFieldPane<E, P>} to support integration with
 * field panes specifically designed for managing component-related data.
 *
 * @param <E> the type of the entity associated with this component field pane
 * @param <P> the type of the parent node associated with this pane
 */
public non-sealed interface KlComponentFieldPane<E extends Entity, P extends Parent>
        extends KlFieldPane<E, P> {
}
