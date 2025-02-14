package dev.ikm.komet.layout.component.field;

import dev.ikm.tinkar.entity.Entity;

/**
 * A factory interface for creating instances of {@code KlComponentFieldPane}.
 * This interface extends {@code KlFieldPaneFactory} and specializes it for
 * creating field panes specifically designed to interact with {@code Entity}-based
 * components in the Knowledge Layout framework.
 *
 * The implementation provides a default method to define the interface class
 * as {@code KlComponentFieldPane.class}.
 */
public interface KlComponentFieldPaneFactory extends
        KlFieldPaneFactory<Entity, KlComponentFieldPane<Entity>> {

    @Override
    default Class<KlComponentFieldPane<Entity>> klInterfaceClass() {
        return (Class<KlComponentFieldPane<Entity>>) (Class<?>) KlComponentFieldPane.class;
    }
}
