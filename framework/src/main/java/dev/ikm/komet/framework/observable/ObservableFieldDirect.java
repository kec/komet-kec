package dev.ikm.komet.framework.observable;

import dev.ikm.tinkar.entity.Field;

// A field directly on an entity or version of an entity.
public final class ObservableFieldDirect extends ObservableFieldAbstract {
    final FeatureLocator locator;
    public ObservableFieldDirect(FeatureLocator locator, Field<?> attribute, ObservableComponent containingComponent) {
        this(locator, attribute, containingComponent, false);
    }

    public ObservableFieldDirect(FeatureLocator locator, Field<?> attribute, ObservableComponent containingComponent, boolean writeOnEveryChange) {
        super(attribute, containingComponent, writeOnEveryChange);
        this.locator = locator;
    }

    @Override
    public FeatureLocator locator() {
        return locator;
    }
}
