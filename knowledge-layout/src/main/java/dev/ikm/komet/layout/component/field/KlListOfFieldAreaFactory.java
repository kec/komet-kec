package dev.ikm.komet.layout.component.field;

import dev.ikm.tinkar.entity.Field;
import javafx.scene.layout.Region;

/**
 * Represents a factory interface within the Knowledge Layout framework for creating
 * and managing instances of {@link KlListOfFieldArea}. This interface extends the
 * {@link KlListFieldAreaFactory}, dedicated to field areas managing observable lists
 * containing field elements.
 *
 * This interface provides the ability to create specific list field areas associated
 * with parent UI components, facilitating interaction and customization of list
 * field areas within layouts.
 *
 * @param <LE> the list element type of the observable list managed by the field area, extending {@code Field}
 * @param <FX> the type of the parent UI element associated with the field area, extending {@code Region}
 */
public interface KlListOfFieldAreaFactory<LE extends Field, FX extends Region>
        extends KlListFieldAreaFactory<LE, FX> {
}
