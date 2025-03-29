package dev.ikm.komet.layout.component.factory;

import dev.ikm.komet.framework.observable.ObservableStamp;
import dev.ikm.komet.layout.component.KlStampArea;

/**
 * The {@code KlStampAreaFactory} interface defines a non-sealed factory for creating
 * instances of {@link KlStampArea}, which are specialized UI components for managing
 * and displaying {@link ObservableStamp} entities. It extends the {@link KlComponentAreaFactory}
 * to provide stamp-specific functionality while maintaining compatibility with the broader
 * component-area framework.
 *
 * This factory supports the creation, initialization, and configuration of {@code KlStampArea}
 * instances using observable stamp entities and preferences. By leveraging the
 * {@code KlComponentAreaFactory}, it ensures consistent management of the UI components
 * across the application.
 *
 * @see KlComponentAreaFactory
 * @see KlStampArea
 * @see ObservableStamp
 */
public non-sealed interface KlStampAreaFactory
        extends KlComponentAreaFactory<KlStampArea, ObservableStamp> {
}
