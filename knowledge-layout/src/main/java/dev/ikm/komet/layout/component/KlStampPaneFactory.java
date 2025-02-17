package dev.ikm.komet.layout.component;

import dev.ikm.komet.framework.observable.ObservableStamp;

/**
 * A factory interface for creating instances of {@code KlStampPane},
 * which are specialized component panes associated with an {@code ObservableStamp}.
 * This interface extends the {@code KlComponentPaneFactory} interface, inheriting
 * the ability to create component panes initialized with observable entity data
 * and user preferences.
 *
 * The {@code KlStampPaneFactory} serves as a bridge between {@code ObservableStamp}
 * entities and their corresponding UI components in a JavaFX application, ensuring
 * seamless interaction and presentation of stamp-related data.
 *
 * @see KlComponentPaneFactory
 * @see KlStampPane
 * @see ObservableStamp
 */
public non-sealed interface KlStampPaneFactory
        extends KlComponentPaneFactory<KlStampPane, ObservableStamp> {
}
