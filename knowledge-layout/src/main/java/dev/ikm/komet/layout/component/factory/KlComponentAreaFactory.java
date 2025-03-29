package dev.ikm.komet.layout.component.factory;

import dev.ikm.komet.framework.observable.ObservableEntity;
import dev.ikm.komet.layout.KlEntityType;
import dev.ikm.komet.layout.KlFactory;
import dev.ikm.komet.layout.component.*;
import dev.ikm.komet.layout.preferences.KlPreferencesFactory;

/// A sealed factory interface for creating instances of `KlComponentArea` associated with
/// observable entities of type `ObservableEntity`. This interface is extended by several
/// specialized factories that cater to specific types of component areas and observable entities.
/// The purpose of this factory is to provide a consistent mechanism for creating instances
/// of `KlComponentArea`, initializing them using a `KlPreferencesFactory`,
/// and associating them with specific observable entities through their component properties.
/// The predefined subtypes of this sealed interface include:
/// - `KlConceptAreaFactory` for concept-related components
/// - `KlGenericComponentAreaFactory` for generic components
/// - `KlPatternAreaFactory` for pattern-related components
/// - `KlSemanticAreaFactory` for semantic-related components
/// - `KlStampAreaFactory` for stamp-related components
/// This factory leverages the `KlEntityType` and `KlFactory` interfaces to
/// ensure proper type management of entities and components.
///
/// @param <KL> the type of `KlComponentArea` created by this factory
/// @param <OE> the type of `ObservableEntity` associated with the created `KlComponentArea`
/// @see KlEntityType
/// @see KlFactory
/// @see KlConceptAreaFactory
/// @see KlGenericComponentAreaFactory
/// @see KlPatternAreaFactory
/// @see KlSemanticAreaFactory
/// @see KlStampAreaFactory
public sealed interface KlComponentAreaFactory<KL extends KlComponentArea, OE extends ObservableEntity>
        extends KlEntityType<OE>, KlFactory<KL>
        permits KlConceptAreaFactory, KlGenericComponentAreaFactory, KlPatternAreaFactory, KlSemanticAreaFactory, KlStampAreaFactory {
    /**
     * Creates an instance of {@code KL} associated with a specified {@code OE observableEntity},
     * and initializes it using the provided {@code preferencesFactory}. The {@code observableEntity}
     * is set on the created component pane's {@code componentProperty}.
     *
     * @param observableEntity the observable entity of type {@code OE} to associate with the created component pane
     * @param preferencesFactory an instance of {@code KlPreferencesFactory} used to initialize the created component pane
     * @return an instance of {@code KL} initialized with the provided {@code preferencesFactory} and set with the given {@code observableEntity}
     */
    default KL create(OE observableEntity,
                      KlPreferencesFactory preferencesFactory) {
        KL componentPane = create(preferencesFactory);
        componentPane.componentProperty().setValue(observableEntity);
        return componentPane;
    }
}
