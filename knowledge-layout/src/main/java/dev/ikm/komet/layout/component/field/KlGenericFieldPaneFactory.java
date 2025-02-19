package dev.ikm.komet.layout.component.field;

import javafx.scene.Parent;

/**
 * A factory interface for creating and managing instances of {@link KlGenericFieldPane},
 * which represents a generic field pane in the Knowledge Layout framework.
 * This factory specializes in handling field panes parameterized with {@code Object} as the
 * data type and a JavaFX {@link Parent} as the parent UI component type.
 *
 * This interface extends {@link KlFieldPaneFactory}, specifying the data type as {@code Object},
 * offering support for creating and managing generic field panes without restricting the type
 * of data associated with those panes.
 *
 * @param <FX> the type of the JavaFX {@link Parent} subclass associated with the field pane
 */
public interface KlGenericFieldPaneFactory<FX extends Parent> extends KlFieldPaneFactory<Object, FX, KlGenericFieldPane<FX>> {

}
