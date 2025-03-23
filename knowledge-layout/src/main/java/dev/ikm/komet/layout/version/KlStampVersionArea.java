package dev.ikm.komet.layout.version;

import dev.ikm.komet.framework.observable.ObservableStampVersion;
import javafx.beans.property.ObjectProperty;
import javafx.scene.layout.Pane;


/**
 * The {@code KlStampVersionPane} interface represents a specialized implementation of
 * {@link KlVersionArea} for managing and displaying observable stamp versions in UI components.
 * <p>
 * This interface is designated to handle {@link ObservableStampVersion} types and offers
 * default methods to access and manage the stamp version and its property associated with a pane.
 * <p>
 * Design:
 * - This interface extends the functionality of {@code KlVersionPane} focusing on the
 *   {@code ObservableStampVersion} as the version type.
 * - It inherits methods from {@code KlVersionPane} for version management and provides
 *   convenience wrappers specific to stamp versions.
 * <p>
 * Type Parameters:
 * @param <FX> the type of JavaFX {@link Pane} managed by this interface
 *
 * @see KlVersionArea
 * @see ObservableStampVersion
 */
public non-sealed interface KlStampVersionArea<FX extends Pane> extends KlVersionArea<ObservableStampVersion, FX> {

     default ObservableStampVersion stampVersion() {
        return KlVersionArea.super.version();
    }

    default ObjectProperty<ObservableStampVersion> stampVersionProperty() {
         return versionProperty();
    }
}
