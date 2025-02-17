package dev.ikm.komet.layout.component.field;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;

/**
 * Represents a specialized factory for creating and managing instances of
 * {@link KlFieldPane} that operate on generic data of type {@code Object}
 * and are associated with JavaFX {@link Pane}. This interface provides
 * methods and mechanisms specifically designed to support the creation
 * and management of generic field panes within the Knowledge Layout framework.
 *
 * Extends {@link KlFieldPaneFactory} parameterized with {@code Object} as
 * the data type, {@link Pane} as the JavaFX parent node type, and
 * {@link KlFieldPane} as the field pane implementation.
 */
public interface KlGenericFieldPaneFactory extends KlFieldPaneFactory<Object, Parent, KlGenericFieldPane> {

}
