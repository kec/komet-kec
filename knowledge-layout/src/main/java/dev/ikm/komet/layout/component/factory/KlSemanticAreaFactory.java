package dev.ikm.komet.layout.component.factory;

import dev.ikm.komet.framework.observable.ObservableSemantic;
import dev.ikm.komet.layout.component.KlSemanticArea;

/**
 * A factory interface for creating instances of {@code KlSemanticArea} associated with
 * observable semantic entities of type {@code ObservableSemantic}.
 *
 * This non-sealed interface extends the {@code KlComponentAreaFactory} interface, specifically
 * for semantic-related components. It provides a mechanism for initializing and associating
 * semantic-based JavaFX panes with observable semantics through their component properties.
 *
 * The purpose of this factory is to enable the creation of semantic-specific component areas
 * that are tailored for integrating semantic entities and patterns into user interface components.
 *
 * @see KlSemanticArea
 * @see KlComponentAreaFactory
 * @see ObservableSemantic
 */
public non-sealed interface KlSemanticAreaFactory
        extends KlComponentAreaFactory<KlSemanticArea, ObservableSemantic> {
}
