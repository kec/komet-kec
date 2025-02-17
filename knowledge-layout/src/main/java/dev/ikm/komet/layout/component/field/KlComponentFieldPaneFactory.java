package dev.ikm.komet.layout.component.field;

import dev.ikm.tinkar.entity.Entity;
import javafx.scene.layout.Pane;

/**
 * Factory interface for creating and managing instances of {@link KlComponentFieldPane}.
 * This interface extends {@link KlFieldPaneFactory}, parameterized with a generic entity type {@link Entity},
 * a specific JavaFX {@link Pane}, and the specialized {@link KlComponentFieldPane}.
 * It serves as a Knowledge Layout framework-specific factory for component-related field panes.
 *
 * The default implementation of {@link #klInterfaceClass()} returns the {@link KlComponentFieldPane} class.
 *
 * @param <P> the type of the JavaFX {@link Pane} associated with this factory and its field panes
 */
public interface KlComponentFieldPaneFactory<P extends Pane> extends
        KlFieldPaneFactory<Entity, P, KlComponentFieldPane<Entity, P>> {

}
