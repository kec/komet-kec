package dev.ikm.komet.layout.component;

import dev.ikm.komet.framework.observable.ObservableEntity;
import javafx.scene.layout.Pane;

/**
 * A non-sealed factory interface for creating generic instances of {@code KlComponentPane}
 * associated with the {@code ObservableEntity} type. This interface is intended for creating
 * component panes that are not specialized for a specific domain entity (such as concepts, patterns, etc.).
 * <p>
 * The {@code KlGenericComponentPaneFactory} extends {@code KlComponentPaneFactory}, allowing for
 * the creation and initialization of generic panes using an {@code ObservableEntity} and user preferences.
 * <p>
 * This factory is considered generic as it operates on the broad type {@code ObservableEntity},
 * without targeting a specific subtype. Implementations of this factory produce panes that integrate
 * observable entities with a JavaFX {@code Pane} for visualization and interaction.
 */
public non-sealed interface KlGenericComponentPaneFactory
        extends KlComponentPaneFactory<KlComponentPane<ObservableEntity, Pane>, ObservableEntity> {
}
