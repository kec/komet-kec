package dev.ikm.komet.layout.component;

import dev.ikm.komet.framework.observable.ObservableEntity;
import dev.ikm.komet.layout.KlWidget;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

/**
 * The {@code KlMultiComponentPane} interface provides a contract for presenting
 * multiple observable entities within a pane.
 *
 * @param <OE> the type parameter that extends {@link ObservableEntity}, representing
 * the specific observable entity type managed by this pane.
 *
 * @see KlWidget
 * @see ObservableEntity
 * @see KlComponentArea
 */
public interface KlMultiComponentArea<OE extends ObservableEntity, FX extends Pane> extends KlWidget<FX> {
    /**
     * Retrieves the list of observable entities associated with this pane.
     *
     * @return an ObservableList of ObservableEntity objects, representing the entities and their versions contained within this pane.
     */
    ObservableList<OE> observableEntities();
    /**
     * Retrieves the list of single pane components associated with this multi-component pane.
     *
     * @return an ObservableList of KlComponentPane objects, representing the individual component panes contained within this multi-component pane.
     */
    ObservableList<KlComponentArea> klComponentAreas();
}
