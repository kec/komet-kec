package dev.ikm.komet.layout;

import dev.ikm.komet.framework.observable.AttributeLocator;
import dev.ikm.komet.layout.area.factory.KlDynamicAreaFactory;
import org.eclipse.collections.api.list.ImmutableList;

public interface LayoutComputer {
    ImmutableList<KlDynamicAreaFactory> create(ImmutableList<AttributeLocator> attributeLocators);
}
