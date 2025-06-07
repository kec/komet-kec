package dev.ikm.komet.layout;

import dev.ikm.komet.layout.window.KlRenderView;

/**
 *
 * @param <FX>
 * @deprecated use KlView or descendent. Class slated for removal.
 */
@Deprecated
public sealed interface KlGadget<FX> extends KlView<FX>
        permits KlArea {

    /**
     * Provides an instance of the generic type T JavaFx gadget associated with the knowledge layout component.
     *
     * @return an instance of type T, representing a specific knowledge layout gadget.
     * @deprecated use {@code fxObject} to prevent overloaded use of gadget...
     */
    @Deprecated
    default FX fxGadget() {
        return fxObject();
    }
}
