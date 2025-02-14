package dev.ikm.komet.layout.component.field;

import dev.ikm.komet.layout.KlFactory;

public interface KlFieldPaneFactory<T, P extends KlFieldPane<T>> extends KlFactory<P> {

    @Override
    Class<P> klInterfaceClass();
}
