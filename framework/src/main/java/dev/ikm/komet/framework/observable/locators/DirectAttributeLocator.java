package dev.ikm.komet.framework.observable.locators;

import dev.ikm.komet.framework.observable.AttributeLocator;
import dev.ikm.komet.framework.observable.ObservableAttributeWithLocator;

public sealed interface DirectAttributeLocator extends AttributeLocator
        permits DirectListElementLocator, DirectListElementLocatorWithObservable,
                DirectSingularAttributeLocator, DirectSingularAttributeLocatorWithObservable,
        ObservableAttributeWithLocator {
}
