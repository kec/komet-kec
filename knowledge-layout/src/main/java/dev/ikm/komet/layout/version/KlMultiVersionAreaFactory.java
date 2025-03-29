package dev.ikm.komet.layout.version;

import dev.ikm.komet.framework.observable.ObservableVersion;
import dev.ikm.komet.framework.view.ObservableView;
import dev.ikm.komet.layout.KlFactory;
import dev.ikm.komet.layout.KlVersionType;
import dev.ikm.komet.layout.KlWidget;
import dev.ikm.komet.preferences.KometPreferences;
import javafx.scene.layout.Pane;
import org.eclipse.collections.api.list.ImmutableList;

/**
 * A factory interface for creating instances of {@link KlMultiVersionArea}, a component designed to handle
 * and visually represent multiple observable versions of a specified type. This factory integrates the
 * requirements for observable version management, contextual view configuration, and user preference settings.
 *
 * @param <OV> the type of {@link ObservableVersion} that this factory operates on
 * @param <FX>  the type of {@link Pane} used as the visual container for the versions
 */
public interface KlMultiVersionAreaFactory<OV extends ObservableVersion, FX extends Pane>
        extends KlFactory<KlMultiVersionArea<OV, FX>>, KlVersionType<OV> {

}