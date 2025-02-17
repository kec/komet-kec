package dev.ikm.komet.layout.component;

import dev.ikm.komet.framework.observable.ObservableConcept;
import javafx.scene.layout.Pane;

/**
 * A specialized factory interface for creating instances of {@code KlComponentPane}
 * associated with {@code ObservableConcept}. This interface extends the
 * {@code KlComponentPaneFactory} to provide functionality specific to the creation
 * of panes for observable concepts. Implementations of this interface are responsible
 * for generating concept-specific component panes, integrating concepts into the Komet
 * framework's UI system.
 *
 * In this interface:
 * - {@code KL} represents the specific type of {@code KlComponentPane} created by this factory,
 *   tailored to handle {@code ObservableConcept}.
 * - {@code OE} is specifically constrained to {@code ObservableConcept}, ensuring that
 *   this factory supports only concept-related components.
 *
 * This interface is part of the `dev.ikm.komet.layout.component` package, which focuses
 * on enabling modular and flexible organization of UI components for concepts
 * and other observable entities.
 *
 * @param <KL> the specific type of {@code KlComponentPane} created by the factory
 * @param <OE> the {@code ObservableConcept} type associated with the created component pane
 * @see KlComponentPaneFactory
 * @see ObservableConcept
 */
public non-sealed interface KlConceptPaneFactory<KL extends KlComponentPane<OE, Pane>, OE extends ObservableConcept>
        extends KlComponentPaneFactory<KL, OE> {
}