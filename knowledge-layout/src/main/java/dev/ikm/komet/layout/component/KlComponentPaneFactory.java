package dev.ikm.komet.layout.component;

import dev.ikm.komet.framework.observable.ObservableEntity;
import dev.ikm.komet.layout.KlEntityType;
import dev.ikm.komet.layout.KlFactory;
import dev.ikm.komet.layout.preferences.KlPreferencesFactory;
import javafx.scene.layout.Pane;

/**
 * A factory interface for creating instances of {@code KlComponentPane} associated
 * with a specific type of {@code ObservableEntity}. This interface extends
 * {@code KlEntityType} and {@code KlFactory}, providing methods for creating
 * component panes that are associated with a given observable entity and
 * initialized with user preferences. Implementations of this factory specialize
 * in creating specific types of component panes for various observable entities.
 *
 * @param <KL>  the specific type of {@code KlComponentPane} created by this factory
 * @param <OE> the type of {@code ObservableEntity} associated with the created component pane
 */
public sealed interface KlComponentPaneFactory<KL extends KlComponentPane<OE, Pane>, OE extends ObservableEntity>
        extends KlEntityType<OE>, KlFactory<KL>
        permits KlConceptPaneFactory, KlGenericComponentPaneFactory, KlPatternPaneFactory, KlSemanticPaneFactory, KlStampPaneFactory {
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
