package dev.ikm.komet.layout;

import javafx.scene.Parent;
import javafx.scene.layout.Region;

import java.util.UUID;

/**
 * The {@code KlWidget} interface defines a contract for a customizable widget
 * that integrates with a scene graph and provides various layout and styling
 * functionalities. It extends {@code KlGadget} and genericizes the
 * {@code Node} class, representing the underlying JavaFX node.
 *
 * @param <FX> the type of {@code Node} that this widget extends or encapsulates.
 * @deprecated Use KlArea instead. Class slated for removal.
 */

@Deprecated
public non-sealed interface KlWidget<FX extends Region> extends KlArea<FX> {

    /**
     * Retrieves the unique identifier for this KlWidget. Note that the UUID for the
     * KlWidget is independent of whatever entity it may contain at a particular instant. And the
     * UUID will not change across the life of this Knowledge Layout Component.
     *
     * @return the UUID representing the unique identifier of the KlWidget.
     * @deprecated use klObjectId() instead.
     */
    @Deprecated
    default UUID klWidgetId() {
        return klObjectId();
    }

    @Override
    default FX fxObject() {
        return klWidget();
    }

    /**
     * Retrieves the widget representation for this KlWidget.
     *
     * @return The widget instance, represented by the specific implementation of the KlWidget.
     * @deprecated use {@code fxObject} to prevent overloaded use of gadget...
     */
    @Deprecated
    default FX fxGadget() {
        return klWidget();
    }

    /**
     * Retrieves the scene graph node that presents this KlWidget.
     *
     * @param <SGN> The type of the scene graph node extending {@code Node}.
     * @return The scene graph node instance.
     * @deprecated use {@code fxObject} to prevent overloaded use of gadget...
     */
    @Deprecated
    default <SGN extends Parent> SGN klWidget() {
        return (SGN) this;
    }

}
