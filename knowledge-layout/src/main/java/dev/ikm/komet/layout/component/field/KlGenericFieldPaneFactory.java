package dev.ikm.komet.layout.component.field;

/**
 * A factory interface for creating instances of {@code KlGenericFieldPane}.
 * This interface extends {@code KlFieldPaneFactory} and specializes it for
 * creating generic field panes that can handle fields of any type in the
 * Knowledge Layout framework.
 *
 * The implementation provides a default method to define the interface class
 * as {@code KlGenericFieldPane.class}.
 */
public interface KlGenericFieldPaneFactory extends KlFieldPaneFactory {
    @Override
    default Class<KlGenericFieldPane> klInterfaceClass() {
        return KlGenericFieldPane.class;
    }
}
