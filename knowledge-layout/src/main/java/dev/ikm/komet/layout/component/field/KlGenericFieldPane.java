package dev.ikm.komet.layout.component.field;

import javafx.scene.Parent;

/**
 * Represents a generic field pane within the Knowledge Layout framework,
 * extending the functionality of {@code KlFieldPane} with a specific focus
 * on managing fields that are not bound to a specific data type. This interface
 * uses {@code Object} as the type parameter to support fields with any data type
 * and associates them with a JavaFX {@code Parent}.
 *
 * This interface specifically allows for the handling and observation of fields
 * with flexible data types, providing a generalized approach within the layout
 * framework. As a non-sealed interface, it can be further extended by other
 * implementations to suit additional requirements.
 */
public non-sealed interface KlGenericFieldPane extends KlFieldPane<Object, Parent> {

}
