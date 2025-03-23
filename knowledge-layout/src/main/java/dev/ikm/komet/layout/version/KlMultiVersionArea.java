package dev.ikm.komet.layout.version;

import dev.ikm.komet.framework.observable.ObservableVersion;
import dev.ikm.komet.layout.KlWidget;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

/**
 * The {@code KlMultiVersionPane} interface provides a contract for components handling
 * multiple versions of the same entity.
 *
 * @param <OV> the type of the ObservableVersion that the pane works with
 *
 * @see KlWidget
 * @see ObservableVersion
 */
public interface KlMultiVersionArea<OV extends ObservableVersion, FX extends Pane> extends KlWidget<FX> {
    /**
     * Retrieves the list of observable versions associated with an entity in this multi-version pane.
     *
     * @return an ObservableList of ObservableVersion<V> objects, representing the multiple versions of the entity managed by this pane.
     */
    ObservableList<OV> observableVersions();
    /**
     * Retrieves the list of single version panes associated with this multi-version pane.
     *
     * @return an ObservableList of KlVersionPane<V> objects, representing the individual version panes
     *         that handle and display single versions of the entity managed by this multi-version pane.
     */
    ObservableList<KlVersionArea<OV, FX>> klVersionAreas();
}
