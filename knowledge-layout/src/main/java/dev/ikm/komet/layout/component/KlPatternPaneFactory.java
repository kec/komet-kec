package dev.ikm.komet.layout.component;

import dev.ikm.komet.framework.observable.ObservablePattern;

/**
 * A factory interface for creating instances of {@code KlPatternPane}, which are specialized
 * panes associated with {@code ObservablePattern} entities. The {@code KlPatternPaneFactory}
 * extends the {@code KlComponentPaneFactory} interface and provides methods for creating pattern-based
 * component panes that present and manage observable pattern data.
 *
 * This factory plays a crucial role in creating and initializing {@code KlPatternPane} instances
 * with specific {@code ObservablePattern} entities. It ensures the proper association and setup
 * of patterns, supporting dynamic updates and interactions within a JavaFX application.
 *
 * @see KlComponentPaneFactory
 * @see KlPatternPane
 * @see ObservablePattern
 */
public non-sealed interface KlPatternPaneFactory
        extends KlComponentPaneFactory<KlPatternPane, ObservablePattern> {
}