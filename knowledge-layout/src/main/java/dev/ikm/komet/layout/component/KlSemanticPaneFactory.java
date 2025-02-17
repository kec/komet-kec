package dev.ikm.komet.layout.component;

import dev.ikm.komet.framework.observable.ObservableSemantic;

/**
 * A factory interface for creating instances of {@code KlSemanticPane} associated
 * with {@code ObservableSemantic}. This interface specializes the generic
 * {@code KlComponentPaneFactory} to ensure a direct relationship between the semantic pane
 * and its observable semantic entity. It provides methods for creating and initializing
 * semantic panes with specific observable entities and user preferences.
 *
 * Implementations of this interface are responsible for defining the behavior
 * for constructing semantic panes that represent or manipulate semantic-related
 * components within the UI context.
 *
 * @see KlComponentPaneFactory
 * @see KlSemanticPane
 * @see ObservableSemantic
 */
public non-sealed interface KlSemanticPaneFactory
        extends KlComponentPaneFactory<KlSemanticPane, ObservableSemantic> {
}
