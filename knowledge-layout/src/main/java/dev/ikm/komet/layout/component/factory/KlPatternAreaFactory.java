package dev.ikm.komet.layout.component.factory;

import dev.ikm.komet.framework.observable.ObservablePattern;
import dev.ikm.komet.layout.component.KlPatternArea;

/**
 * The {@code KlPatternAreaFactory} interface represents a specialized factory for creating
 * instances of {@code KlPatternArea}, which are associated with {@code ObservablePattern} entities.
 * It extends the {@code KlComponentAreaFactory} interface to provide additional focus
 * on component areas dealing specifically with observable patterns in a JavaFX application.
 *
 * This factory is used to construct {@code KlPatternArea} instances, initialize them using appropriate
 * preferences, and associate them with specific {@code ObservablePattern} entities. The {@code KlPatternArea}
 * produced by this factory is specifically designed to interact with the lifecycle of observable patterns
 * and their derivatives.
 *
 * @see KlComponentAreaFactory
 * @see KlPatternArea
 * @see ObservablePattern
 */
public non-sealed interface KlPatternAreaFactory
        extends KlComponentAreaFactory<KlPatternArea, ObservablePattern> {
}