package dev.ikm.komet.layout.area;

import dev.ikm.komet.layout.KlWidget;
import dev.ikm.komet.layout.component.KlComponentArea;
import dev.ikm.komet.layout.attribute.KlAttributeArea;
import dev.ikm.komet.layout.version.KlVersionArea;
import javafx.scene.layout.Region;

/**
 * The {@code KlArea} interface represents a fundamental building block within
 * the Knowledge Layout framework, intended for managing and organizing user interface
 * regions. This sealed interface defines the contract for various specialized
 * pane or area types, enabling structured, type-safe layouts and interactions
 * within the framework.
 * <p>
 * Implementations of this interface can serve distinct purposes, such as managing
 * components, versions, fields, or supplemental layouts. By leveraging a type-safe
 * hierarchy, the {@code KlArea} interface ensures consistent and extensible design
 * across different areas of the framework.
 * <p>
 * @param <FX> the type of JavaFX {@code Region} that serves as the base node for this area
 * <p>
 * @see KlComponentArea
 * @see KlVersionArea
 * @see KlAttributeArea
 * @see KlSupplementalArea
 */
public sealed interface KlArea<FX extends Region> extends KlWidget<FX>
        permits KlComponentArea, KlVersionArea, KlAttributeArea, KlSupplementalArea {
}
