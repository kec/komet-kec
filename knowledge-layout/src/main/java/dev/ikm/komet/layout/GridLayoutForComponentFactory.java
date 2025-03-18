package dev.ikm.komet.layout;

import dev.ikm.komet.framework.observable.FieldLocator;
import dev.ikm.komet.layout.area.KlForFieldArea;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.map.ImmutableMap;

public interface GridLayoutForComponentFactory {
    ImmutableMap<FieldLocator, KlForFieldArea> create(ImmutableList<FieldLocator> componentFieldSpecifications);
}
