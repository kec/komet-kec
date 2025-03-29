package dev.ikm.komet.layout.attribute.factory;

import dev.ikm.komet.layout.attribute.KlListAttributeArea;
import dev.ikm.tinkar.entity.EntityVersion;
import javafx.scene.layout.Region;

/**
 * Represents a factory interface within the Knowledge Layout framework for creating
 * and managing instances of {@link KlListAttributeArea} specifically designed for
 * handling fields containing observable lists of entity versions. This interface extends
 * {@link KlListFieldAreaFactory}, providing additional constraints and functionality
 * specific to managing lists composed of entity version elements.
 *
 * The type parameter {@code LE} represents the type of the entity versions managed
 * within the list, while {@code FX} represents the type of the JavaFX {@link Region}
 * parent element associated with the field area.
 *
 * @param <LE> the type of entity versions managed within the observable list
 * @param <FX> the type of the JavaFX {@link Region} parent element associated with this field area
 */
public interface KlListOfVersionsFieldAreaFactory<LE extends EntityVersion, FX extends Region>
        extends KlListFieldAreaFactory<LE, FX> {
}
