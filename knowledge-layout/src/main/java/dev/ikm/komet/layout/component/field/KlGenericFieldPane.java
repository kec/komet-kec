package dev.ikm.komet.layout.component.field;

/**
 * Represents a pane in the Knowledge Layout framework that can handle fields of any type.
 * This interface extends the {@code KlFieldPane} interface parameterized with {@code Object},
 * allowing it to manage interactions and observations for fields without restrictions on the field type.
 *
 * This interface does not add new methods or properties and serves as a generic specialization
 * of {@code KlFieldPane<Object>} in the framework.
 */
public non-sealed interface KlGenericFieldPane extends KlFieldPane<Object> {

}
