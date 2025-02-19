package dev.ikm.komet.layout.component;

import dev.ikm.komet.framework.observable.ObservablePattern;
import javafx.scene.layout.Pane;

/**
 * An interface for creating instances of {@link KlPatternPane} associated with an {@link ObservablePattern}.
 * It extends the {@link KlComponentPaneFactory} interface, specializing in creating component panes
 * that interact with observable patterns in JavaFX applications.
 *
 * This factory allows for the dynamic binding of {@link ObservablePattern} entities to JavaFX panes,
 * facilitating the creation and initialization of UI components representing pattern-related data.
 * It ensures a consistent interface for managing and presenting pattern-related observable entities
 * within the Komet framework.
 *
 * @param <FX> the type of JavaFX {@code Pane} created by this factory.
 * @see KlPatternPane
 * @see ObservablePattern
 * @see KlComponentPaneFactory
 */
public non-sealed interface KlPatternPaneFactory<FX extends Pane>
        extends KlComponentPaneFactory<FX, KlPatternPane<FX>, ObservablePattern> {
}