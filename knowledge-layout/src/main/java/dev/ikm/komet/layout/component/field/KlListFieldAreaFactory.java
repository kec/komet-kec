package dev.ikm.komet.layout.component.field;

import javafx.collections.ObservableList;
import javafx.scene.layout.Region;

import java.util.List;

/**
 * Represents a factory interface for creating and managing instances of {@link KlFieldArea}
 * specifically designed for handling fields containing an {@link ObservableList} of elements.
 * This interface builds upon {@link KlFieldAreaFactory}, providing additional type constraints
 * and functionality tailored for list-based field areas within the Knowledge Layout framework.
 *
 * @param <LE> the type of elements managed within the {@link ObservableList}
 * @param <FX> the type of the JavaFX {@link Region} parent element associated with this field area
 */
public interface KlListFieldAreaFactory<LE, FX extends Region> extends
        KlFieldAreaFactory<ObservableList<LE>, FX, KlFieldArea<ObservableList<LE>, FX>> {
}
