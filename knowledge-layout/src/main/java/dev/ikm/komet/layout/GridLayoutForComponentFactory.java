package dev.ikm.komet.layout;

import dev.ikm.komet.framework.observable.ComponentFieldSpecification;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.map.ImmutableMap;

public interface GridLayoutForComponentFactory {
    ImmutableMap<ComponentFieldSpecification, GridLayoutForComponent> create(ImmutableList<ComponentFieldSpecification> componentFieldSpecifications);
}
