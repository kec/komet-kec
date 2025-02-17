package dev.ikm.komet.layout.component.field;

import javafx.scene.layout.Pane;

/**
 * A factory interface for creating instances of {@link KlBooleanFieldPane}. This interface
 * specializes {@link KlFieldPaneFactory} for working with boolean data types, enabling the
 * generation and management of {@code KlBooleanFieldPane} implementations to facilitate
 * layout and interaction with boolean fields in the Knowledge Layout framework.
 *
 * @param <FX> The JavaFX {@link Pane} subclass associated with the boolean field pane.
 */
public interface KlBooleanFieldPaneFactory<FX extends Pane> extends KlFieldPaneFactory<Boolean, FX, KlBooleanFieldPane<FX>> {

}
