package dev.ikm.komet.layout.area;

import dev.ikm.komet.layout.KlArea;
import dev.ikm.komet.layout.KlWidget;
import dev.ikm.komet.layout.feature.KlFeatureArea;
import dev.ikm.komet.layout.feature.KlListArea;
import dev.ikm.tinkar.common.bind.ClassConceptBinding;
import dev.ikm.tinkar.common.bind.annotations.axioms.ParentProxy;
import dev.ikm.tinkar.common.bind.annotations.names.FullyQualifiedName;
import dev.ikm.tinkar.common.bind.annotations.names.RegularName;
import dev.ikm.tinkar.common.bind.annotations.publicid.PublicIdAnnotation;
import dev.ikm.tinkar.common.bind.annotations.publicid.UuidAnnotation;
import javafx.beans.property.Property;
import javafx.scene.layout.Region;
import org.eclipse.collections.api.factory.Lists;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * Defines an interface within the Knowledge Layout framework for managing and interacting
 * with attributes of a specified data type and their associated JavaFX regions. This interface
 * provides methods for binding, observing, and manipulating attribute data in a type-safe manner.
 *
 * The interface extends multiple base types to integrate functionality for JavaFX region handling,
 * conceptual bindings, and knowledge layout area definitions.
 *
 * @param <PT> The data type managed by this attribute area.
 * @param <FX> The type of the JavaFX {@code Region} associated with this attribute area.
 */
@FullyQualifiedName("Knowledge layout regions")
@RegularName("Attribute regions")
@ParentProxy(parentName = "Komet panels (SOLOR)",
        parentPublicId = @PublicIdAnnotation(@UuidAnnotation("b3d1cdf6-27a5-502d-8f16-ed026a7b9d15")))
public sealed interface KlPropertyArea<PT, FX extends Region> extends KlWidget<FX>, ClassConceptBinding, KlArea<FX>
        permits KlFeatureArea, KlListArea {

    /**
     * Sets the property for this property area using the provided value.
     *
     * @param property the property of type {@code PT} to set for this property area
     */
    void setProperty(Property<PT> property);

    /**
     * Retrieves the property associated with this property area.
     *
     * @return the property of type {@code PT} associated with this property area.
     */
    Property<PT> getProperty();

    /**
     * Represents a factory interface for creating and managing instances of components
     * that associate a specific data type with JavaFX {@code Region} elements and
     * their corresponding property handling areas.
     *
     * This factory interface extends {@code KlArea.Factory} to provide specialized
     * support for creating and managing property-based regions with specific layouts
     * and behaviors defined through a {@code Locator}.
     *
     * @param <DT> the data type managed by the property area.
     * @param <FX> the type of JavaFX region associated with the property area, extending {@code Region}.
     * @param <KL> the type of knowledge layout area, extending {@code KlPropertyArea}, representing
     *            the logical area and its behavior for managing properties of type {@code DT} with regions of type {@code FX}.
     */
    sealed interface Factory<DT, FX extends Region, KL extends KlPropertyArea<DT, FX>>
            extends KlArea.Factory<FX, KL> permits KlFeatureArea.Factory, KlListArea.Factory {

        default ImmutableList<Class<?>> areaFactoryServiceTypes() {
            return Lists.immutable.of(KlAssociationArea.Factory.class);
        }
    }
}
