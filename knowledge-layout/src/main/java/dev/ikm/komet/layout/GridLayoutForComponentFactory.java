package dev.ikm.komet.layout;

import dev.ikm.komet.framework.observable.FieldLocator;
import dev.ikm.komet.layout.area.KlAreaSpecifier;
import org.eclipse.collections.api.list.ImmutableList;

public interface GridLayoutForComponentFactory {
    ImmutableList<KlAreaSpecifier> create(ImmutableList<FieldLocator> componentFieldSpecifications);
}
