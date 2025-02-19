package dev.ikm.komet.layout.component.field;

import javafx.scene.Parent;

/**
 * Represents a generic field pane in the Knowledge Layout framework that supports
 * managing and interacting with fields of generic data type. This interface extends
 * {@link KlFieldPane} and is parameterized with {@code Object} as the data type and
 * a JavaFX {@link Parent} as the parent UI component type.
 * <p>
 * This interface serves as a specialization of {@code KlFieldPane<Object, FX>}
 * and provides a base for field panes that do not have a predefined or specific
 * data type, allowing for flexible handling of various generic data fields.
 *
 * @param <FX> the type of the parent node associated with this pane
 */
public non-sealed interface KlGenericFieldPane<FX extends Parent> extends KlFieldPane<Object, FX> {

}
