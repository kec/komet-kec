package dev.ikm.komet.layout.feature;

import dev.ikm.komet.framework.observable.FeatureList;
import dev.ikm.komet.framework.observable.LocatableFeature;
import javafx.scene.layout.Region;

/**
 * Represents a sealed interface within the Knowledge Layout (KL) framework for managing
 * areas that handle observable lists of feature components. This interface provides a
 * contract for defining and interacting with structured regions tied to observable
 * features within a JavaFX context. It extends the functionality of {@link KlListArea}
 * to focus on feature-related behavior and management.
 *
 * The primary purpose of this interface is to enable modular and dynamic management
 * of feature data and their visual representation within a defined UI area. Classes
 * implementing this interface must specialize in managing feature components and their
 * integration with JavaFX regions to ensure seamless interaction and data updates.
 *
 * @param <LF> the type of elements that extend {@link LocatableFeature}, representing
 *             the observable feature components managed by this region
 * @param <FX> the type of JavaFX {@link Region} to which the managed features are associated
 */
public sealed interface KlListOfFeatureArea<LF extends LocatableFeature, FX extends Region>
        extends KlListArea<LF, FeatureList<LF>, FX>
        permits KlListOfFieldDefinitionArea, KlListOfFieldArea, KlListOfVersionArea {


    /**
     * Represents a factory interface for constructing and managing instances of areas that work with
     * observable features and their corresponding JavaFX regions. This interface extends the
     * functionality of the {@code KlListArea.Factory} by focusing specifically on areas dealing
     * with observable features.
     * <p>
     * The factory is specialized to produce areas that manage observable lists of elements extending
     * {@code ObservableFeature} and are associated with JavaFX regions of type {@code Region}.
     * Implementations of this interface will provide the necessary mechanisms for creating and
     * customizing these areas within the Knowledge Layout (KL) framework.
     *
     * @param <LF> the type of elements, extending {@code ObservableFeature}, representing the observable
     *             features to be managed within the list area.
     * @param <FX> the type of JavaFX region to which the managed features are associated, extending {@code Region}.
     * @param <KL> the type of list area, extending {@code KlListOfFeatureArea}, which represents the structure
     *             for managing observable features within the associated JavaFX region.
     */
    interface Factory<LF extends LocatableFeature, FX extends Region, KL extends KlListOfFeatureArea<LF, FX>>
            extends KlListArea.Factory<LF, FeatureList<LF>, FX, KL> {
    }
}
