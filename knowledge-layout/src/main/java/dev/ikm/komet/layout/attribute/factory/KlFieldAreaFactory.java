package dev.ikm.komet.layout.attribute.factory;

import dev.ikm.komet.layout.KlFactory;
import dev.ikm.komet.layout.attribute.KlAttributeArea;
import javafx.scene.layout.Region;



/**
 * Represents a factory interface for creating and managing instances of
 * {@link KlAttributeArea}, which is a Knowledge Layout framework-specific component
 * that binds data of type {@code T} to an associated JavaFX {@link Region}.
 *
 * @param <DT>  The data type handled by the field pane.
 * @param <FX> The JavaFX {@link Region} subclass associated with the field pane.
 * @param <KL> The {@link KlAttributeArea} implementation managed by this factory.
 */
public interface KlFieldAreaFactory<DT, FX extends Region, KL extends KlAttributeArea<DT, FX>>
        extends KlFactory<KL> {

}
