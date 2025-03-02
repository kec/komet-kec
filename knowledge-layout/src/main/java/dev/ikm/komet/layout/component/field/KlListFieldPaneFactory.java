package dev.ikm.komet.layout.component.field;

import javafx.scene.layout.Pane;

import java.util.List;

/**
 * Represents a factory interface for creating and managing instances of
 * {@link KlListFieldPane}, which is a specialized field pane in the Knowledge Layout
 * framework that supports handling of list-based data structures.
 *<p>
 * This interface extends {@link KlFieldPaneFactory} to provide additional
 * type parameters and capabilities for managing {@code List}-based field panes,
 * enabling support for lists of specific data types.
 *
 * @param <DT> the data type of the elements contained in the list managed by the field pane
 * @param <L>  the list type managed within the field pane, containing elements of type {@code DT}
 * @param <FX> the type of the parent UI element associated with this pane, extending {@link Pane}
 */
public interface KlListFieldPaneFactory<DT, L extends List<DT>, FX extends Pane> extends
        KlFieldPaneFactory<L, FX, KlFieldPane<L, FX>> {
}
