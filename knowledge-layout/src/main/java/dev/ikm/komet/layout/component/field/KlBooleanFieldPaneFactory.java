package dev.ikm.komet.layout.component.field;

/**
 * A factory interface for creating instances of {@code KlBooleanFieldPane}.
 * This interface extends {@code KlFieldPaneFactory} and specializes it for
 * working with Boolean-based field panes in the Knowledge Layout framework.
 *
 * The implementation provides default methods to define the interface class
 * as {@code KlBooleanFieldPane.class}.
 */
public interface KlBooleanFieldPaneFactory extends KlFieldPaneFactory<Boolean, KlBooleanFieldPane> {
    @Override
    default Class<KlBooleanFieldPane> klInterfaceClass() {
        return KlBooleanFieldPane.class;
    }
}
