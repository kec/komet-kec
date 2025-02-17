package dev.ikm.komet.layout.component.field;

import dev.ikm.komet.layout.KlFactory;

import javafx.scene.Parent;


/**
 * Represents a factory interface for creating and managing instances of
 * {@link KlFieldPane}, which is a Knowledge Layout framework-specific component
 * that binds data of type {@code T} to an associated JavaFX {@link Parent}.
 *
 * @param <DT>  The data type handled by the field pane.
 * @param <FX> The JavaFX {@link Parent} subclass associated with the field pane.
 * @param <KL> The {@link KlFieldPane} implementation managed by this factory.
 */
public interface KlFieldPaneFactory<DT, FX extends Parent, KL extends KlFieldPane<DT, FX>>
        extends KlFactory<KL> {

}
