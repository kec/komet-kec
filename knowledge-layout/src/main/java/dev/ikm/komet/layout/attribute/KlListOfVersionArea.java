package dev.ikm.komet.layout.attribute;

import dev.ikm.komet.framework.observable.ObservableVersion;
import dev.ikm.tinkar.entity.EntityVersion;
import javafx.collections.ObservableList;
import javafx.scene.layout.Region;

/**
 * Represents a non-sealed interface within the Knowledge Layout framework
 * for managing and interacting specifically with areas containing lists of
 * observable version elements. This interface extends the {@link KlListArea}
 * interface to provide behavior and functionality specific to managing
 * observable versions associated with entities.
 *
 * This interface specializes in handling lists of elements extending
 * {@link ObservableVersion} and their associated parent JavaFX components
 * extending {@link Region}. The use of generics enforces type safety and
 * ensures consistency in the elements managed by implementations of this interface.
 *
 * This contract is primarily used for facilitating the observation,
 * manipulation, and interaction of observable versions within a defined
 * framework, thereby enabling developers to manage the relationship between
 * list data models and corresponding UI elements in a structured and reusable manner.
 *
 * @param <LE> the type of observable version elements contained within the list,
 *             extending {@link ObservableVersion} of an {@link EntityVersion}
 * @param <FX> the type of the JavaFX region associated with this list area, extending {@link Region}
 */
public non-sealed interface KlListOfVersionArea<
        LE extends ObservableVersion<? extends EntityVersion>,
        FX extends Region>
        extends KlListArea<ObservableList<LE>, LE, FX> {

    /**
     * Defines a factory interface for creating and managing instances of components
     * that handle observable lists of entity versions within a JavaFX {@link Region}.
     * <p>
     * This factory specializes in handling observable versions extending {@link EntityVersion}
     * and their interactions with JavaFX components that extend {@link Region}. It serves as
     * a contract for creating UI components or regions that can manage and bind to observable
     * entity version data models.
     * <p>
     * Implementations of this factory enable structured and reusable creation patterns
     * for managing relationships between data models of type {@link ObservableVersion}
     * and the corresponding UI regions.
     *
     * @param <LE> the type of observable version elements contained within the list,
     *             extending {@link ObservableVersion} of an {@link EntityVersion}
     * @param <FX> the type of the JavaFX region associated with this factory, extending {@link Region}
     */
    interface Factory<LE extends ObservableVersion<? extends EntityVersion>, FX extends Region, KL extends KlListOfVersionArea<LE, FX>>
        extends KlListArea.Factory<LE, FX, KL> {

    }
}
