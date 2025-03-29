package dev.ikm.komet.layout.attribute.factory;

import dev.ikm.komet.layout.attribute.KlComponentAttributeArea;
import dev.ikm.tinkar.entity.Entity;
import javafx.scene.layout.Region;

/**
 * Factory interface for creating and managing instances of {@link KlComponentAttributeArea}.
 * This interface extends {@link KlFieldAreaFactory}, parameterized with a generic entity type {@link Entity},
 * a specific JavaFX {@link Region}, and the specialized {@link KlComponentAttributeArea}.
 * It serves as a Knowledge Layout framework-specific factory for component-related field panes.
 *
 * The default implementation of {@link #klInterfaceClass()} returns the {@link KlComponentAttributeArea} class.
 *
 * @param <P> the type of the JavaFX {@link Region} associated with this factory and its field panes
 */
public interface KlComponentFieldAreaFactory<P extends Region> extends
        KlFieldAreaFactory<Entity, P, KlComponentAttributeArea<Entity, P>> {

}
