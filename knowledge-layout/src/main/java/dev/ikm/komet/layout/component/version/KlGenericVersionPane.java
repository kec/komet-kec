package dev.ikm.komet.layout.component.version;

import dev.ikm.komet.framework.observable.ObservableVersion;
import javafx.scene.layout.Pane;

/**
 * The {@code KlGenericVersionPane} interface represents a generic implementation of {@link KlVersionPane}
 * tailored to work with observable versions of entities.
 * <p>
 * This interface extends the {@code KlVersionPane} with the following specifications:
 * - It works with {@link ObservableVersion} as the version type.
 * - It uses a JavaFX Pane or a subclass thereof specified by the generic parameter {@code P}.
 * <p>
 * Purpose:
 * - The {@code KlGenericVersionPane} serves as a flexible and reusable interface for managing
 *   and displaying versioned entities in UI components.
 * <p>
 * Design:
 * - This non-sealed interface can be further extended or implemented for specific use cases
 *   involving generic pane types and entity versions.
 * <p>
 * Type Parameters:
 * @param <P> the type of the JavaFX Pane this version pane manages
 *
 * @see KlVersionPane
 * @see ObservableVersion
 */
public non-sealed interface KlGenericVersionPane<P extends Pane> extends KlVersionPane<ObservableVersion, P> {
}
